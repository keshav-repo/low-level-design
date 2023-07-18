package ecommerce;

public class StripeGateway  implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment via Stripe: $" + amount);
    }
}