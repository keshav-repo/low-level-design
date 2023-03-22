package com.designing.bookmyshow.model;

public abstract class Auth {

    private AuthInput authInput;

    public abstract boolean validateAccount(AuthInput input);

    public abstract String getUserName();
}
