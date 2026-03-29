package com.fraud.fraud_analyser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String transactionId;
    private String userId;
    private BigDecimal amount;
    private String merchantId;
    private Long timestamp;
}
