package com.joinzad.wallet.transaction.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joinzad.wallet.transaction.modals.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final CustomTransactionManager transactionManager;

    @KafkaListener(topics = "user-requests", groupId = "transaction-group")
    public void consume(String message) {
        if (message.isEmpty()) {
            return;
        }
        processMessage(message);
    }

    private void processMessage(String message) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserRequestDTO userRequest = objectMapper.readValue(message, UserRequestDTO.class);

            switch (userRequest.getOperationType()) {
                case DEPOSIT:
                    transactionManager.processDeposit(userRequest.getUserId(), userRequest.getAmount(), userRequest.getFromCurrency());
                    break;
                case WITHDRAWAL:
                    transactionManager.processWithdrawal(userRequest.getUserId(), userRequest.getAmount(), userRequest.getFromCurrency());
                    break;
                case EXCHANGE:
                    transactionManager.processExchange(userRequest.getUserId(), userRequest.getAmount(), userRequest.getFromCurrency(), userRequest.getToCurrency());
                    break;
                default:
                    log.info("Unkown operation type: " + userRequest.getOperationType());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
