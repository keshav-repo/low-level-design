package com.designing.bookmyshow.model;

public class UserNamePasswordAuth extends Auth {

    private UserNamePasswordInput userNamePasswordInput;

    public UserNamePasswordAuth(AuthInput userNamePasswordInput) {
        this.userNamePasswordInput = (UserNamePasswordInput) userNamePasswordInput;
    }

    @Override
    public boolean validateAccount(AuthInput authInput) {
        UserNamePasswordInput userNamePasswordInput = (UserNamePasswordInput) authInput;
        boolean isUserNameSame =  this.userNamePasswordInput.getUserName().equals(userNamePasswordInput.getUserName());

        boolean isPasswordMatching = true;
        char[] input = userNamePasswordInput.getPassword();
        char[] password = this.userNamePasswordInput.getPassword();
        int i =0; int j=0;
        while(i<password.length && j<input.length){
            if(password[i++] != input[j++]){
                isPasswordMatching = false;
            }
        }
        return isUserNameSame && isPasswordMatching;
    }

    @Override
    public String getUserName() {
        return userNamePasswordInput.getUserName();
    }
}
