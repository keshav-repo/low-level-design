package designPattern.visitor;


import java.util.ArrayList;
import java.util.List;

// Visitor interface
interface Visitor {
    void visit(Product product);
    void visit(Order order);
    // Add more visit methods for other elements
}

// Element interface
interface Element {
    void accept(Visitor visitor);
}

// Concrete Element classes
class Product implements Element {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Order implements Element {
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor classes
class PriceCalculatorVisitor implements Visitor {
    private double totalPrice;

    public void visit(Product product) {
        totalPrice += product.getPrice();
    }

    public void visit(Order order) {
        for (Product product : order.getProducts()) {
            product.accept(this);
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

public class OrderVisitor {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 15.0));
        products.add(new Product("Product 3", 20.0));

        Order order = new Order(products);

        PriceCalculatorVisitor priceCalculator = new PriceCalculatorVisitor();
        order.accept(priceCalculator);

        double totalPrice = priceCalculator.getTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
    }
}
