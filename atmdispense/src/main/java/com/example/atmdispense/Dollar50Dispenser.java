package com.example.atmdispense;

public class Dollar50Dispenser implements DispenseChain {
    private DispenseChain dispenseChain;

    @Override
    public void setNextchain(DispenseChain nextchain) {
        this.dispenseChain = nextchain;
    }

    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 50) {
            int notesCount = currency.getAmount() / 50;
            int leftAmount = currency.getAmount() % 50;
            System.out.println("Dispense "+notesCount+" of $50");
            if(leftAmount!=0) dispenseChain.dispense(new Currency(leftAmount));
        }else{
            dispenseChain.dispense(currency);
        }
    }
}
