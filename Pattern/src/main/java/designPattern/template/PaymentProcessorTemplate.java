package designPattern.template;

// Abstract class defining the template method
abstract class AbstractPaymentProcessor {

    // Template method that defines the payment processing algorithm
    public void processPayment() {
        validatePayment();
        performPayment();
        notifyPayment();
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void validatePayment();
    protected abstract void performPayment();
    protected abstract void notifyPayment();
}

// Concrete class implementing the template method
class CreditCardPaymentProcessor extends AbstractPaymentProcessor {

    protected void validatePayment() {
        System.out.println("Validating credit card payment...");
        // Validation logic specific to credit card payment
    }

    protected void performPayment() {
        System.out.println("Performing credit card payment...");
        // Payment processing logic specific to credit card payment
    }

    protected void notifyPayment() {
        System.out.println("Notifying credit card payment...");
        // Notification logic specific to credit card payment
    }
}

// Concrete class implementing the template method
class PayPalPaymentProcessor extends AbstractPaymentProcessor {

    protected void validatePayment() {
        System.out.println("Validating PayPal payment...");
        // Validation logic specific to PayPal payment
    }

    protected void performPayment() {
        System.out.println("Performing PayPal payment...");
        // Payment processing logic specific to PayPal payment
    }

    protected void notifyPayment() {
        System.out.println("Notifying PayPal payment...");
        // Notification logic specific to PayPal payment
    }
}

// Client code
public class PaymentProcessorTemplate {
    public static void main(String[] args) {
        AbstractPaymentProcessor paymentProcessor = new CreditCardPaymentProcessor();
        paymentProcessor.processPayment();

        System.out.println();

        paymentProcessor = new PayPalPaymentProcessor();
        paymentProcessor.processPayment();
    }
}
