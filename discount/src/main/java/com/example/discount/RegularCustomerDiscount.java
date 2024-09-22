package com.example.discount;

public class RegularCustomerDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice*0.1;
    }
}
