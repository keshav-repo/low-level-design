package ecommerce;

public abstract class CartDecorator implements Cart{
    protected Cart cart;

    public CartDecorator(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addProduct(Product product) {
        cart.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    @Override
    public double getTotalPrice() {
        return cart.getTotalPrice();
    }
}
