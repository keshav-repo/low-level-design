package ecommerce.Implementor;


import ecommerce.*;

public class DecoratorCartMain {
    public static void main(String[] args) {
        // Create a base shopping cart
        Cart shoppingCart = new ShoppingCart();

        // Decorate the shopping cart with a discount
        Cart discountedCart = new DiscountedCartDecorator(shoppingCart, 0.1);

        // Decorate the already decorated cart with tax calculation
        Cart finalCart = new TaxedCartDecorator(discountedCart, 0.08);

        // Add products to the final cart
        Product product1 = new ConcreteProduct("Example Product 1","Clothes", 100.0 );
        Product product2 = new ConcreteProduct("Example Product 2", "Clothes",50.0);
        finalCart.addProduct(product1);
        finalCart.addProduct(product2);

        // Calculate the total price of the final cart
        double totalPrice = finalCart.getTotalPrice();
        System.out.println("Total Price: " + totalPrice);

    }
}
