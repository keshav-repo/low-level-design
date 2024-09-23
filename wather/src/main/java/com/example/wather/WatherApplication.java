package com.example.wather;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WatherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WeatherStation weatherStation = new WeatherStation();

		Observer u1 = new User("u1");
		Observer u2 = new User("u2");

		weatherStation.registerObserver(u1);
		weatherStation.registerObserver(u2);

		weatherStation.setTemparature(30);

		weatherStation.unregister(u2);
		weatherStation.setTemparature(25);
	}
}
