package com.joinzad.wallet.transaction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joinzad.wallet.account.models.Currency;
import com.joinzad.wallet.transaction.modals.OperationType;
import com.joinzad.wallet.transaction.modals.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServices {


    private final Producer producer;

    public void triggerKafka() throws JsonProcessingException {

        var userForDeposit = UserRequestDTO.builder()
                .userId("12345")
                .operationType(OperationType.DEPOSIT)
                .amount(99.0)
                .toCurrency(Currency.TRY)
                .build();
        producer.sendMessage(userForDeposit);
    }
}
