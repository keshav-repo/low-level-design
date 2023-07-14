package ecommerce.Implementor;

import ecommerce.ConcreteProduct;
import ecommerce.DiscountCalculator;
import ecommerce.Product;

public class DiscountCalculationClient {
    public static void main(String[] args) {
        // Create products
        Product clothingProduct = new ConcreteProduct("Shirt","Clothing", 100.0);
        Product electronicsProduct = new ConcreteProduct("iphone 8","Electronics", 200.0);
        Product booksProduct = new ConcreteProduct("The Five Love Languages","Books", 50.0);

        // Create DiscountCalculator instance
        DiscountCalculator discountCalculator = new DiscountCalculator();

        // Calculate discounts for products
        double clothingDiscount = discountCalculator.calculateDiscount(clothingProduct);
        double electronicsDiscount = discountCalculator.calculateDiscount(electronicsProduct);
        double booksDiscount = discountCalculator.calculateDiscount(booksProduct);

        System.out.println("Clothing Discount: " + clothingDiscount);
        System.out.println("Electronics Discount: " + electronicsDiscount);
        System.out.println("Books Discount: " + booksDiscount);
    }
}
