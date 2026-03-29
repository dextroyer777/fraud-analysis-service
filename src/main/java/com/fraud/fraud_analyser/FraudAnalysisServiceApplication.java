package com.fraud.fraud_analyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fraud")
public class FraudAnalysisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudAnalysisServiceApplication.class, args);
	}

}
