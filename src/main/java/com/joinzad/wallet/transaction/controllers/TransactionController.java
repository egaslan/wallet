package com.joinzad.wallet.transaction.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joinzad.wallet.transaction.services.TransactionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServices transactionServices;

    @GetMapping("/{id}")
    public String getTransaction(@PathVariable String id) {
        return "hello";
    }

    @GetMapping("/start-transaction")
    public String startTransaction() throws JsonProcessingException {
        transactionServices.triggerKafka();
        return "hello";
    }

}
