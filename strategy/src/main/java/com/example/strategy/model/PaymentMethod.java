package com.example.strategy.model;

public interface PaymentMethod {
    double calculateProcessingFee(double amount);
}
