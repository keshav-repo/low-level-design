package com.example.wather;

public class User implements Observer{
    private String name;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(int temparature) {
        System.out.println("change in temparature "+ temparature);
    }
}
