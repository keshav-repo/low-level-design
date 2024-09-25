package com.example.atmdispense;

public class Dollar10Dispenser implements DispenseChain{
    private DispenseChain dispenseChain;
    @Override
    public void setNextchain(DispenseChain nextchain) {
        this.dispenseChain = nextchain;
    }
    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount()>=10){
            int num = currency.getAmount()/10;
            int remender = currency.getAmount()%10;
            System.out.println("Dispensing "+num+" $10 notes");
            if(remender!=0) dispenseChain.dispense(new Currency(remender));
        }else{
            dispenseChain.dispense(currency);
        }
    }
}
