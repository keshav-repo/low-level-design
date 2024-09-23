package com.example.stockmarket;

import java.util.ArrayList;
import java.util.List;

public class StockMarketImpl implements StockMarket{
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        this.observerList.add(observer);
    }
    @Override
    public void unregisterObserver(Observer observer) {
        this.observerList.remove(observer);
    }
    @Override
    public void notifyPriceChange(String stockSymbol, double price) {
        this.observerList.forEach(observer -> observer.notifyPriceChange(stockSymbol, price));
    }
    public void setStockPrice(String stockSymbol, double price){
        notifyPriceChange(stockSymbol, price);
    }
}
