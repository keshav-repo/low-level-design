package com.designing.bookmyshow.model;

public abstract class AuthInput {
    private final String userName;

    public AuthInput(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }
}
