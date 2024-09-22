package com.example.strategy.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentMethod{
    @Override
    public double calculateProcessingFee(double amount) {
        return amount*0.02;
    }
}
