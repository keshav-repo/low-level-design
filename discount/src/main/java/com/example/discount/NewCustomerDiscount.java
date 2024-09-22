package com.example.discount;

public class NewCustomerDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice*0.2;
    }
}
