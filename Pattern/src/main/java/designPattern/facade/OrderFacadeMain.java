package designPattern.facade;

// Subsystem classes
class InventoryManager {
    public void updateInventory(String productId, int quantity) {
        // Update the inventory for the given product with the specified quantity
        System.out.println("Inventory updated for product: " + productId);
    }
}

class PaymentProcessor {
    public void processPayment(double amount) {
        // Process the payment for the given amount
        System.out.println("Payment processed: $" + amount);
    }
}

class ShippingService {
    public void shipOrder(String orderId) {
        // Ship the order with the specified ID
        System.out.println("Order shipped: " + orderId);
    }
}

// Facade class
class OrderFacade {
    private InventoryManager inventoryManager;
    private PaymentProcessor paymentProcessor;
    private ShippingService shippingService;

    public OrderFacade() {
        inventoryManager = new InventoryManager();
        paymentProcessor = new PaymentProcessor();
        shippingService = new ShippingService();
    }

    public void placeOrder(String productId, int quantity, double amount, String orderId) {
        inventoryManager.updateInventory(productId, quantity);
        paymentProcessor.processPayment(amount);
        shippingService.shipOrder(orderId);
    }
}

public class OrderFacadeMain {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder("ABC123", 2, 100.0, "ORDER123");
    }
}
