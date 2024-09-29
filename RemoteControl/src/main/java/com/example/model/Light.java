package com.example.model;

public class Light {
    private String name;
    public Light(String name) {
        this.name = name;
    }
    public void onLight(){
        System.out.println(name+" light is on");
    }
    public void offLight(){
        System.out.println(name+" light is off");
    }
}
