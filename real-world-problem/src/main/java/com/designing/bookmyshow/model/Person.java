package com.designing.bookmyshow.model;

public class Person {

    private static int incrementer = 1;
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Auth auth;

    public Person(Auth auth) {
        this.auth = auth;
    }

    public boolean validatePerson(AuthInput authInput){
        return this.auth.validateAccount(authInput);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getUserName(){
        return this.auth.getUserName();
    }
}
