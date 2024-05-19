package com.joinzad.wallet.transaction.services;

import com.joinzad.wallet.currency.conversion.modals.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomTransactionManager {
    private final StringRedisTemplate redisTemplate;

    public void processDeposit(String userId, double amount) {
        // Para yatırma işlemi
        System.out.println("Para yatırma işlemi: " + userId + " için " + amount);
    }

    public void processWithdrawal(String userId, double amount) {
        // Para çekme işlemi
        System.out.println("Para çekme işlemi: " + userId + " için " + amount);
    }

    public Double processExchange(String userId, double amount, String fromCurrency, String toCurrency) {
        if(Objects.equals(toCurrency, Currency.USD.name())){
            return amount;
        }
        if (Objects.equals(fromCurrency, Currency.USD.name()) && Objects.equals(toCurrency, Currency.TRY.name())){
            var rate = redisTemplate.opsForValue().get(Currency.TRY.name());
            assert rate != null;
            return amount*Double.parseDouble(rate);
        }
        return amount;
    }
}
