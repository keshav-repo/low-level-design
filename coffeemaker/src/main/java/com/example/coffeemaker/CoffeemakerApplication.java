package com.example.coffeemaker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeemakerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoffeemakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Coffee coffee = new SimpleCoffee();

		System.out.println(coffee.getDescription()+" cost is: "+coffee.cost());

		coffee = new WithMilk(coffee);
		System.out.println(coffee.getDescription()+" cost is: "+coffee.cost());

		coffee = new WithSugar(coffee);
		System.out.println(coffee.getDescription()+" cost is: "+coffee.cost());
	}
}
