package com.fraud.fraud_analyser.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.fraud_analyser.dto.TransactionDTO;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FraudAnalysisService {

    private final StringRedisTemplate redisTemplate;
    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;
    private final MeterRegistry registry;

    // Limite de 3 transações conforme validado nos teus logs de sucesso
    private static final int TRANSACTIONS_LIMIT = 3;
    private static final String REDIS_PREFIX = "fraud:user:";

    @KafkaListener(topics = "transactions", groupId = "fraud-group")
    public void analyze(TransactionDTO transaction) {
        String key = REDIS_PREFIX + transaction.getUserId();
        
        // Incrementa o contador no Redis de forma atômica para garantir precisão no Ryzen 5
        Long currentCount = redisTemplate.opsForValue().increment(key);

        // Se for a primeira transação, define a janela de tempo de 60s
        if (currentCount != null && currentCount == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(60));
        }

        log.info("Analisando transação {} para o usuário {}. Contagem atual: {}", 
                 transaction.getTransactionId(), transaction.getUserId(), currentCount);

        // Lógica de Detecção de Fraude unificada
        if (currentCount != null && currentCount > TRANSACTIONS_LIMIT) {
            handleFraud(transaction, currentCount);
        } else {
            log.info("Transação aprovada para o usuário {}", transaction.getUserId());
        }
    }

    private void handleFraud(TransactionDTO transaction, Long count) {
        log.warn("⚠️ ALERTA DE FRAUDE: Usuário {} excedeu o limite com {} transações em 60s!", 
                 transaction.getUserId(), count);
        
        // 1. Incrementa a métrica de negócio no Prometheus
        // Isso permitirá visualizar o pico de fraudes no Actuator
        registry.counter("fraud.alerts.total", "user", transaction.getUserId()).increment();
        
        // 2. Envia para o tópico de alertas no Kafka para processamento posterior (bloqueio/notificação)
        // O Kafka-UI mostrará esta mensagem no tópico 'fraud-alerts'
        kafkaTemplate.send("fraud-alerts", transaction);
    }
}
