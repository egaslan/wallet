package com.joinzad.wallet.transaction.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joinzad.wallet.transaction.modal.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {


    private final CustomTransactionManager transactionManager;

    @KafkaListener(topics = "user-requests", groupId = "transaction-group")
    public void consume(String message) {
        System.out.println("Mesaj alındı: " + message);
        if (message.isEmpty()) {
            return;

        }
        processMessage(message);
    }

    private void processMessage(String message) {

        // JSON veya başka bir formatta gelen mesajı parse et ve işleme mantığını çağır
        // Örneğin, JSON kullanarak:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserRequestDTO userRequest = objectMapper.readValue(message, UserRequestDTO.class);

            switch (userRequest.getType()) {
                case "deposit":
                    transactionManager.processDeposit(userRequest.getUserId(), userRequest.getAmount());
                    break;
                case "withdrawal":
                    transactionManager.processWithdrawal(userRequest.getUserId(), userRequest.getAmount());
                    break;
                case "exchange":
                    var data = transactionManager.processExchange(userRequest.getUserId(), userRequest.getAmount(), userRequest.getFromCurrency(), userRequest.getToCurrency());
                    System.out.println("data: " + data);
                    break;
                default:
                    System.out.println("Bilinmeyen işlem türü: " + userRequest.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
