package com.example.stockmarket;

public interface Observer {
    public void notifyPriceChange(String stockSymbol, double price);
}
