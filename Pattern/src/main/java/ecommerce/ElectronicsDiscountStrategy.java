package ecommerce;

public class ElectronicsDiscountStrategy implements DiscountStrategy{
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice*0.12;
    }
}
