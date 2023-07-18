package designPattern.filter2;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityFilter implements Filter{
    private boolean available;

    public AvailabilityFilter(boolean available) {
        this.available = available;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isAvailable() == available) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
