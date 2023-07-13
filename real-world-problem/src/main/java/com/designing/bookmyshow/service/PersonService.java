package com.designing.bookmyshow.service;

import com.designing.bookmyshow.model.*;

import java.util.HashMap;
import java.util.Map;

public class PersonService {

    private Map<String, Person> customerMap = new HashMap<>();

    public Customer createCustomer(Auth auth){
        Customer customer = new Customer(auth);
        customerMap.put(customer.getUserName(), customer);
        return customer;
    }

    public Customer getCustomer(String userName){
        return (Customer) customerMap.get(userName);
    }

    public boolean validateCustomer(AuthInput authInput){
        Customer customer = (Customer) customerMap.get(authInput.getUserName());
        return customer.validatePerson(authInput);
    }
}
