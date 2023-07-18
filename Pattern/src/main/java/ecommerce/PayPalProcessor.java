package ecommerce;

public class PayPalProcessor implements PaymentProcessor{
    private PaymentGateway paymentGateway;

    public PayPalProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        paymentGateway.processPayment(amount);
    }
}
