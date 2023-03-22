package com.designing.bookmyshow.service;

import com.designing.bookmyshow.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    public void setup(){
        personService = new PersonService();
    }

    @Test
    public void testCreateCustomer(){
        AuthInput authInput = new UserNamePasswordInput("userI", new char[]{'a','b','c','d'});
        Auth auth = new UserNamePasswordAuth(authInput);
        Customer customer = personService.createCustomer(auth);
        personService.validateCustomer(authInput);
    }




}
