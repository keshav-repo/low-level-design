package ecommerce;

public class ClothingDiscountStrategy implements DiscountStrategy{
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice*0.1;
    }
}
