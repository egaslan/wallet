package com.joinzad.wallet.account.services;

import com.joinzad.wallet.account.models.Account;
import com.joinzad.wallet.account.models.Currency;
import com.joinzad.wallet.account.repositories.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceRedisService {

    private final StringRedisTemplate redisTemplate;
    private final AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        updateRedisForBalance();
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void updateRedisForBalance() {

        List<Account> accountList = accountRepository.findAll();
        Map<String, List<Account>> groupedAccounts = accountList.stream().collect(Collectors.groupingBy(Account::getUserId));

        groupedAccounts.forEach((userId, accounts) -> {
            List<Double> totalBalanceList = new ArrayList<>();
            Currency currency = null;

            for (Account account : accounts) {
                if(account.getCurrency() == Currency.TRY){
                    totalBalanceList.add(account.getBalance() * Double.parseDouble(Objects.requireNonNull(redisTemplate.opsForValue().get(userId))));
                }else {
                    totalBalanceList.add(account.getBalance());
                }
            }
            Optional<Double> totalBalance = totalBalanceList.stream().reduce(Double::sum);
            redisTemplate.opsForValue().set(accounts.get(0).getUserName(), String.valueOf(totalBalance), 24, TimeUnit.HOURS);
        });
    }
}
