package com.example.stockmarket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockmarketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		StockMarket stockMarket = new StockMarketImpl();

		Observer investor1 = new Invester("u1");
		Observer investor2 = new Invester("u2");
		stockMarket.registerObserver(investor1);
		stockMarket.registerObserver(investor2);

		stockMarket.notifyPriceChange("INFY", 1888.15);
		stockMarket.notifyPriceChange("Reliance", 2990);
	}
}
