package com.example.strategy.service.impl;

import com.example.strategy.model.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateway {

    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double processPayment(double amount) {
        return amount + paymentMethod.calculateProcessingFee(amount);
    }

}
