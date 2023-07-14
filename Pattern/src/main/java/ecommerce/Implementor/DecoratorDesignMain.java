package ecommerce.Implementor;

import ecommerce.ConcreteProduct;
import ecommerce.GiftWrapProductDecorator;
import ecommerce.Product;
import ecommerce.SaleProductDecorator;

public class DecoratorDesignMain {
    public static void main(String[] args) {
        // Create a base product
        Product baseProduct = new ConcreteProduct("product name","Electronics", 100.0 );

        // Decorate the base product with a sale discount
        Product decoratedProduct = new SaleProductDecorator(baseProduct, 0.2);
        System.out.println("Name: " + decoratedProduct.getName());
        System.out.println("Price: " + decoratedProduct.getPrice());

        // Decorate the already decorated product with gift wrapping
        Product doubleDecoratedProduct = new GiftWrapProductDecorator(decoratedProduct, 5.0);
        System.out.println("Name: " + doubleDecoratedProduct.getName());
        System.out.println("Price: " + doubleDecoratedProduct.getPrice());
    }
}

