package com.designing.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private String paymentId;
    private String paymentMode;
    private LocalDateTime paymentDate;
    private String bookingId;
    private double amount;

    public Payment( String bookingId, String paymentMode,double amount) {
        this.bookingId = bookingId;
        this.paymentMode = paymentMode;
        this.paymentId = UUID.randomUUID().toString();
        this.paymentDate = LocalDateTime.now();
        this.amount = amount;
    }
    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }
}
