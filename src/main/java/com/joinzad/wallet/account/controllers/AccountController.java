package com.joinzad.wallet.account.controllers;


import com.joinzad.wallet.account.models.Account;
import com.joinzad.wallet.account.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public void createAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        return accountService.getAccount(id);
    }

    @PutMapping("/{id}/balance")
    public void updateBalance(@PathVariable String id, @RequestParam double newBalance) {
        accountService.updateBalance(id, newBalance);
    }

    @GetMapping("/{id}/balance")
    public double getBalance(@PathVariable String id) {
        return accountService.getBalance(id);
    }


}
