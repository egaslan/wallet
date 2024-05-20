package com.joinzad.wallet.transaction.services;

import com.joinzad.wallet.account.models.Currency;
import com.joinzad.wallet.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomTransactionManager {
    private final StringRedisTemplate redisTemplate;
    private final AccountRepository accountRepository;

    public void processDeposit(String userId, Double amount, Currency fromCurrency) {
        updateAccountFromDB(userId, fromCurrency, amount);
        List<Double> amountList = new ArrayList<>();
        if (fromCurrency == Currency.TRY) {
            amountList.add(exchangeCurrency(amount));
        } else {
            amountList.add(amount);
        }

        var oldAmount = getAccountBalanceFromRedis(userId);
        if (oldAmount != null) {
            amountList.add(oldAmount);
        }
        Optional<Double> totalAmount = amountList.stream().reduce(Double::sum);
        totalAmount.ifPresent(balance -> updateAccountBalanceOnRedis(userId, balance));
    }

    public void processWithdrawal(String userId, double amount, Currency fromCurrency) {
        updateAccountFromDB(userId, fromCurrency, -amount);

        double newAmount;
        if (fromCurrency == Currency.TRY) {
            newAmount = exchangeCurrency(amount);
        } else {
            newAmount = amount;
        }
        var oldAmount = getAccountBalanceFromRedis(userId);
        if (oldAmount == null || newAmount > oldAmount) {
            throw new IllegalStateException("Insufficient funds.");
        }
        updateAccountBalanceOnRedis(userId, oldAmount - newAmount);

        System.out.println("Para çekme işlemi: " + userId + " için " + amount);
    }

    public void processExchange(String userId, double amount, Currency fromCurrency, Currency toCurrency) {
        updateAccountFromDB(userId, fromCurrency, -amount);
        updateAccountFromDB(userId, toCurrency, amount);
    }

    private void updateAccountFromDB(String userId, Currency fromCurrency, Double amount) {
        var account = accountRepository.findByUserIdAndCurrency(userId, fromCurrency);
        var balance = account.getBalance();
        if (amount < 0.0 || balance < Math.abs(amount)) {
            throw new IllegalStateException("Insufficient funds.");
        }
        account.setBalance(balance + amount);
        accountRepository.save(account);
    }

    private Double exchangeCurrency(Double amount) {
        String exchangeRateStr = redisTemplate.opsForValue().get(Currency.TRY.toString());
        if (exchangeRateStr == null) {
            throw new IllegalStateException("TRY exchange rate not found.");
        }
        double exchangeRate = Double.parseDouble(exchangeRateStr);
        return amount * exchangeRate;
    }

    private void updateAccountBalanceOnRedis(String userId, Double amount) {
        LocalTime endOfDay = LocalTime.MAX;
        long secondsUntilEndOfDay = LocalTime.now().until(endOfDay, ChronoUnit.SECONDS);
        redisTemplate.opsForValue().set(userId, String.valueOf(amount), secondsUntilEndOfDay, TimeUnit.SECONDS);
    }

    private Double getAccountBalanceFromRedis(String userId) {
        String balanceStr = redisTemplate.opsForValue().get(userId);
        return balanceStr != null ? Double.parseDouble(balanceStr) : null;
    }
}
