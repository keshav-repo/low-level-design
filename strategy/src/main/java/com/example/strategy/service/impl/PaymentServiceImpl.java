package com.example.strategy.service.impl;

import com.example.strategy.model.CreditCardPayment;
import com.example.strategy.model.DebitCardPayment;
import com.example.strategy.model.NetBankingPayment;
import com.example.strategy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentGateway paymentGateway;
    @Autowired
    private CreditCardPayment creditCardPayment;
    @Autowired
    private DebitCardPayment debitCardPayment;
    @Autowired
    private NetBankingPayment netBankingPayment;

    @Override
    public void processPayment(double amount, String paymentMethod) {
        switch (paymentMethod) {
            case "CreditCard":
                paymentGateway.setPaymentMethod(creditCardPayment);
                break;
            case "DebitCard":
                paymentGateway.setPaymentMethod(debitCardPayment);
                break;
            case "NetBanking":
                paymentGateway.setPaymentMethod(netBankingPayment);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
        double processedAmount = paymentGateway.processPayment(amount);
        System.out.println("Processed amount: " + processedAmount);
    }
}
