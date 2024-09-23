package com.example.wather;

public interface Subject {
    public void registerObserver(Observer observer);
    public void unregister(Observer observer);
    public void notifyObserver();
}
