package ecommerce;

public class GiftWrapProductDecorator extends ProductDecorator {
    private double giftWrapPrice;

    public GiftWrapProductDecorator(Product product, double giftWrapPrice) {
        super(product);
        this.giftWrapPrice = giftWrapPrice;
    }

    @Override
    public double getPrice() {
        double totalPrice = product.getPrice() + giftWrapPrice;
        return totalPrice;
    }
}
