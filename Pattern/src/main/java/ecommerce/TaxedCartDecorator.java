package ecommerce;

public class TaxedCartDecorator extends CartDecorator{
    private double taxRate;

    public TaxedCartDecorator(Cart cart, double taxRate) {
        super(cart);
        this.taxRate = taxRate;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        double taxedPrice = totalPrice + (totalPrice * taxRate);
        return taxedPrice;
    }
}
