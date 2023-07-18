package ecommerce;

public class PayPalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment via PayPal: $" + amount);
    }
}