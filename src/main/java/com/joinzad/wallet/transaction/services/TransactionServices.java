package com.joinzad.wallet.transaction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joinzad.wallet.transaction.modal.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServices {


    private final Producer producer;

    public void triggerKafka() throws JsonProcessingException {

        var user = UserRequestDTO.builder()
                .userId("123456")
                .type("exchange")
                .amount(99.0)
                .fromCurrency("USD")
                .toCurrency("TRY")
                .build();
        producer.sendMessage(user);
    }
}
