package com.example.stockmarket;

public class Invester implements Observer{
    private String user;

    public Invester(String user) {
        this.user = user;
    }
    @Override
    public void notifyPriceChange(String stockSymbol, double price) {
        System.out.println("Current price of stock " + stockSymbol +" is: "+price);
    }
}
