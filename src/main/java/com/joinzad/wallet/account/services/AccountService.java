package com.joinzad.wallet.account.services;

import com.joinzad.wallet.account.models.Account;
import com.joinzad.wallet.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccount(String id) {
        return accountRepository.getReferenceById(id);
    }

    public void updateBalance(String id, double newBalance) {
        Account account = getAccount(id);
        if (account != null) {
            account.setBalance(newBalance);
            saveAccount(account);
        }
    }

    public double getBalance(String id) {
        Account account = getAccount(id);
        return (account != null) ? account.getBalance() : 0.0;
    }
}
