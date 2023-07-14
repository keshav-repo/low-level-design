package ecommerce;

public class SaleProductDecorator extends ProductDecorator {
    private double discount;

    public SaleProductDecorator(Product product, double discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        double discountedPrice = product.getPrice() - (product.getPrice() * discount);
        return discountedPrice;
    }

}
