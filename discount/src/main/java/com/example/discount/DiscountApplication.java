package com.example.discount;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscountApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DiscountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DiscountContext discountContext = new DiscountContext(new RegularCustomerDiscount());

		CheckoutService checkoutService = new CheckoutService(discountContext);

		double totalPrice = 1000;
		double finalPrice = checkoutService.calculateTotalPrice(totalPrice);
		System.out.println("Total Price after discount for regular customer: " + finalPrice);

		discountContext.setDiscountStrategy(new NewCustomerDiscount());
		finalPrice = checkoutService.calculateTotalPrice(totalPrice);
		System.out.println("Total Price after discount for new customer: " + finalPrice);

	}
}
