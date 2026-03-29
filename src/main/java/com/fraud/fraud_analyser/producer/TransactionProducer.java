package com.fraud.fraud_analyser.producer;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fraud.fraud_analyser.dto.TransactionDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionProducer implements CommandLineRunner {

    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando simulador de transações...");

        // Simulação: Enviando 5 transações rápidas para o mesmo usuário "filipe-gomes"
        // Isso deve disparar o alerta de fraude no Redis (limite > 3)
        for (int i = 1; i <= 5; i++) {
            TransactionDTO t = new TransactionDTO(
                UUID.randomUUID().toString(),
                "filipe-gomes",
                new BigDecimal("100.00"),
                "loja-teste-1",
                System.currentTimeMillis()
            );

            log.info("Enviando transação {}/5...", i);
            kafkaTemplate.send("transactions", t.getUserId(), t);
            
            // Pequeno intervalo para não ser instantâneo, mas dentro da janela de 60s
            Thread.sleep(500); 
        }
    }
}
