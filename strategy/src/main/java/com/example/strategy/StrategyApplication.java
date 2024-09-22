package com.example.strategy;

import com.example.strategy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StrategyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StrategyApplication.class, args);
	}

	@Autowired
	private PaymentService paymentService;

	@Override
	public void run(String... args) throws Exception {
		paymentService.processPayment(1000, "CreditCard");

		paymentService.processPayment(1000, "NetBanking");
	}
}
