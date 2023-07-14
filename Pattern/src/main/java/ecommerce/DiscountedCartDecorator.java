package ecommerce;

public class DiscountedCartDecorator extends CartDecorator{
    private double discount;

    public DiscountedCartDecorator(Cart cart, double discount) {
        super(cart);
        this.discount = discount;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        double discountedPrice = totalPrice - (totalPrice * discount);
        return discountedPrice;
    }
}
