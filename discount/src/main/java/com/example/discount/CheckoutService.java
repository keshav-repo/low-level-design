package com.example.discount;

import org.springframework.stereotype.Service;

public class CheckoutService {
   private DiscountContext discountContext;

    public CheckoutService(DiscountContext discountContext) {
        this.discountContext = discountContext;
    }

    public void setDiscountContext(DiscountContext discountContext) {
        this.discountContext = discountContext;
    }

    public double calculateTotalPrice(double totalPrice) {
        return discountContext.applyDiscount(totalPrice);
    }
}
