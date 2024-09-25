package com.example.atmdispense;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmDispenseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtmDispenseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DispenseChain dispense50Dollar = new Dollar50Dispenser();
		DispenseChain dispense20Dollar = new Dollar20Dispenser();
		DispenseChain dispense10Dollar = new Dollar10Dispenser();

		dispense50Dollar.setNextchain(dispense20Dollar);
		dispense20Dollar.setNextchain(dispense10Dollar);
		dispense10Dollar.setNextchain(null);

		dispense50Dollar.dispense(new Currency(580));
	}
}
