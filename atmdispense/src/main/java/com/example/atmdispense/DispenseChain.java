package com.example.atmdispense;

public interface DispenseChain {
    void setNextchain(DispenseChain nextchain);
    void dispense(Currency currency);
}
