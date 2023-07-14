package ecommerce;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcreteProduct implements Product{
    private String name;
    private String category;
    private double price;
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return category;
    }
}
