package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Cart{
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
