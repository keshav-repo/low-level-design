package com.example.model;

public class Fan {
    private String name;

    public Fan(String name) {
        this.name = name;
    }
    public void onFan(){
        System.out.println(name + " fan is on");
    }
    public void offFan(){
        System.out.println(name+" fan is off");
    }
}
