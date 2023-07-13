package com.designing.bookmyshow.exception;

public class PaymentAlreadyCompleted extends RuntimeException{
    public PaymentAlreadyCompleted(String message) {
        super(message);
    }
}
