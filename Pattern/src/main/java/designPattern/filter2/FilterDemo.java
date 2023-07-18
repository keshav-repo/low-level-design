package designPattern.filter2;

import java.util.ArrayList;
import java.util.List;

public class FilterDemo {
    public static void main(String[] args) {
        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 1000, true));
        products.add(new Product("T-shirt", "Apparel", 25, true));
        products.add(new Product("TV", "Electronics", 1500, false));
        products.add(new Product("Shoes", "Footwear", 50, true));

        // Apply the filters
        Filter categoryFilter = new CategoryFilter("Electronics");
        List<Product> electronicsProducts = categoryFilter.filter(products);
        System.out.println("Electronics Products: " + electronicsProducts);

        Filter priceRangeFilter = new PriceRangeFilter(0, 100);
        List<Product> cheapProducts = priceRangeFilter.filter(products);
        System.out.println("Cheap Products: " + cheapProducts);

        Filter availabilityFilter = new AvailabilityFilter(true);
        List<Product> availableProducts = availabilityFilter.filter(products);
        System.out.println("Available Products: " + availableProducts);

    }
}
