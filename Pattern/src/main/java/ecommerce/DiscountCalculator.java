package ecommerce;

import java.util.HashMap;
import java.util.Map;

public class DiscountCalculator {
    private final Map<String, DiscountStrategy> discountStrategies;

    public DiscountCalculator() {
        discountStrategies = new HashMap<>();
        // Initialize the discount strategies for each category
        discountStrategies.put("Clothing", new ClothingDiscountStrategy());
        discountStrategies.put("Electronics", new ElectronicsDiscountStrategy());
        discountStrategies.put("Books", new BooksDiscountStrategy());
        // Add more categories and corresponding strategies as needed
    }

    public double calculateDiscount(Product product) {
        DiscountStrategy strategy = discountStrategies.get(product.getCategory());
        if (strategy != null) {
            return strategy.applyDiscount(product.getPrice());
        }
        return 0.0; // No applicable discount strategy found
    }
}
