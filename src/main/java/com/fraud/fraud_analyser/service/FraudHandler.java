package com.fraud.fraud_analyser.service;

import java.util.List;
import org.apache.kafka.clients.consumer.Consumer; // IMPORTANTE: Deve ser este
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class FraudHandler implements CommonErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(FraudHandler.class);

    @Override
    public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, 
                                Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.error("❌ Erro crítico no lote. Causa: {}", thrownException.getMessage());
    }

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, 
                             Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.error("❌ Erro no tópico: {} | Offset: {}", record.topic(), record.offset());
        return true; 
    }
}