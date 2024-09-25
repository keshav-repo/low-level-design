package com.example.atmdispense;

public class AtmDispenseApplication{
	public static void main(String[] args) {
		DispenseChain dispense50Dollar = new Dollar50Dispenser();
		DispenseChain dispense20Dollar = new Dollar20Dispenser();
		DispenseChain dispense10Dollar = new Dollar10Dispenser();

		dispense50Dollar.setNextchain(dispense20Dollar);
		dispense20Dollar.setNextchain(dispense10Dollar);
		dispense10Dollar.setNextchain(null);

		dispense50Dollar.dispense(new Currency(580));
	}
}
