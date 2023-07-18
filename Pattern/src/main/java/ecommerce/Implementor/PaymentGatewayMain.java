package ecommerce.Implementor;

import ecommerce.*;

public class PaymentGatewayMain {
    public static void main(String[] args) {
        PaymentGateway stripeGateway = new StripeGateway();
        PaymentGateway payPalGateway = new PayPalGateway();

        PaymentProcessor creditCardProcessor = new CreditCardProcessor(stripeGateway);
        PaymentProcessor payPalProcessor = new PayPalProcessor(payPalGateway);

        double amount = 100.0;

        creditCardProcessor.processPayment(amount);
        payPalProcessor.processPayment(amount);
    }
}
