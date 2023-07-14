package ecommerce;

public class BooksDiscountStrategy implements DiscountStrategy{
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice*0.12;
    }
}
