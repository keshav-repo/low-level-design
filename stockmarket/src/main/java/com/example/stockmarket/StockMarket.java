package com.example.stockmarket;

public interface StockMarket {
    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    public void notifyPriceChange(String stockSymbol, double price);
}
