package ecommerce;

public class CreditCardProcessor implements PaymentProcessor{
    private PaymentGateway paymentGateway;

    public CreditCardProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        paymentGateway.processPayment(amount);
    }
}
