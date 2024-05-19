package com.joinzad.wallet.transaction.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joinzad.wallet.transaction.modal.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UserRequestDTO userRequest ) throws JsonProcessingException {
        String userAsMessage = objectMapper.writeValueAsString(userRequest);
        kafkaTemplate.send(orderTopic, userAsMessage);

        log.info("food order produced {}", userAsMessage);

    }
}