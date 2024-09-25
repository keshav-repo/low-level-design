package com.example.atmdispense;

public class Dollar20Dispenser implements DispenseChain{
    private DispenseChain dispenseChain;
    @Override
    public void setNextchain(DispenseChain nextchain) {
        this.dispenseChain = nextchain;
    }
    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount()>=20){
            int notesCount = currency.getAmount()/20;
            int leftAmount = currency.getAmount() % 20;
            System.out.println("Dispense "+notesCount+" of $20");
            if(leftAmount!=0) dispenseChain.dispense(new Currency(leftAmount));
        }else{
            dispenseChain.dispense(currency);
        }
    }
}
