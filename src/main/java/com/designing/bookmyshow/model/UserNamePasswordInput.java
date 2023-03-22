package com.designing.bookmyshow.model;

public class UserNamePasswordInput extends AuthInput{
    private char[] password;

    public UserNamePasswordInput(String userName, char[] password) {
        super(userName);
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
