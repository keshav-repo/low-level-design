package ecommerce;

public abstract class ProductDecorator implements Product{
    protected Product product;

    public ProductDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public String getCategory(){
        return product.getCategory();
    }
}
