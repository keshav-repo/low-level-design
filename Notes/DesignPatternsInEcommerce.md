# Design Pattern case in Ecommerce application 

### Cases of Factory Design Pattern example in ecommerce application like amazon
In an ecommerce application like Amazon, the Factory Design Pattern can be applied in various scenarios to handle object creation and improve code maintainability. Here are a few examples:

1. Product Creation: When adding new products to the inventory, the application can use a factory to create instances of different product types based on user input. For example, the application can have a `ProductFactory` that takes the product type (e.g., "electronics", "books", "clothing") and returns an appropriate product object.

2. Payment Method Selection: During the checkout process, when a user selects a payment method, a factory can be used to create the corresponding payment processor object. For instance, a `PaymentProcessorFactory` can create instances of `CreditCardProcessor`, `PayPalProcessor`, or `BankTransferProcessor` based on the selected payment method.

3. Shipping Method Selection: Similar to the payment method, when a user selects a shipping method, a factory can be used to create the corresponding shipping service object. For example, a `ShippingServiceFactory` can create instances of `StandardShippingService`, `ExpressShippingService`, or `PrimeShippingService` based on the selected shipping method.

4. Discount Calculation: When applying discounts to products, a factory can be used to determine the appropriate discount strategy based on various factors such as user type, product category, or promotional offers. The factory can return instances of different discount strategies, such as `PercentageDiscount`, `FixedAmountDiscount`, or `BuyOneGetOneFreeDiscount`.

5. Report Generation: When generating reports for different metrics (e.g., sales, inventory, customer analytics), a factory can be used to create the appropriate report generator based on the selected report type. For example, a `ReportGeneratorFactory` can create instances of `SalesReportGenerator`, `InventoryReportGenerator`, or `CustomerAnalyticsReportGenerator`.

These are just a few examples of how the Factory Design Pattern can be utilized in an ecommerce application like Amazon. The pattern helps encapsulate object creation logic, promotes loose coupling, and allows for flexibility and extensibility in handling different object types.


## Please give me all the class related to Product class. product class have productId, name, price, category using factory pattern
Here are the classes for the given scenario of different product types (Clothing, Electronics, Books) using the Factory Design Pattern:

1. Product (Abstract Class or Interface):
```java
public abstract class Product {
    private String productId;
    private String name;
    private double price;
    private String category;

    // Constructor
    public Product(String productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Abstract method
    public abstract void displayInfo();
}
```

2. ClothingProduct (Concrete Product):
```java
public class ClothingProduct extends Product {
    private String size;

    // Constructor
    public ClothingProduct(String productId, String name, double price, String category, String size) {
        super(productId, name, price, category);
        this.size = size;
    }

    // Getter and Setter for size

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Override displayInfo() method
    @Override
    public void displayInfo() {
        System.out.println("Clothing Product - " +
                "Product ID: " + getProductId() +
                ", Name: " + getName() +
                ", Price: " + getPrice() +
                ", Category: " + getCategory() +
                ", Size: " + getSize());
    }
}
```

3. ElectronicsProduct (Concrete Product):
```java
public class ElectronicsProduct extends Product {
    private String brand;

    // Constructor
    public ElectronicsProduct(String productId, String name, double price, String category, String brand) {
        super(productId, name, price, category);
        this.brand = brand;
    }

    // Getter and Setter for brand

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Override displayInfo() method
    @Override
    public void displayInfo() {
        System.out.println("Electronics Product - " +
                "Product ID: " + getProductId() +
                ", Name: " + getName() +
                ", Price: " + getPrice() +
                ", Category: " + getCategory() +
                ", Brand: " + getBrand());
    }
}
```

4. BooksProduct (Concrete Product):
```java
public class BooksProduct extends Product {
    private String author;

    // Constructor
    public BooksProduct(String productId, String name, double price, String category, String author) {
        super(productId, name, price, category);
        this.author = author;
    }

    // Getter and Setter for author

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Override displayInfo() method
    @Override
    public void displayInfo() {
        System.out.println("Books Product - " +
                "Product ID: " + getProductId() +
                ", Name: " + getName() +
                ", Price: " + getPrice() +
                ", Category: " + getCategory() +
                ", Author: " + getAuthor());
    }
}
```

5. ProductFactory (Abstract Factory):
```java
public abstract class ProductFactory {
    public abstract Product createProduct(String productId, String name, double price, String category);
}
```

6. ClothingProductFactory (Concrete Factory):
```java
public class ClothingProductFactory extends ProductFactory {
    @Override
    public Product createProduct(String productId, String name, double price, String category) {
        // Additional logic specific to ClothingProduct, such as getting size
        String size = // get size based on productId or other logic

        return new ClothingProduct(productId, name, price, category, size);
    }
}
```

7. ElectronicsProductFactory (Concrete Factory):
```java
public class ElectronicsProductFactory extends ProductFactory {
    @Override
    public Product createProduct(String productId, String name, double price, String category) {
        // Additional logic specific to ElectronicsProduct, such as getting brand
        String brand = // get brand based on productId or other logic

        return new ElectronicsProduct(productId, name, price, category, brand);
    }
}
```

8. BooksProductFactory (Concrete Factory):
```java
public class BooksProductFactory extends ProductFactory {
    @Override
    public Product createProduct(String productId, String name, double price, String category) {
        // Additional logic specific to BooksProduct, such as getting author
        String author = // get author based on productId or other logic

        return new BooksProduct(productId, name, price, category, author);
    }
}
```

With these classes, you can create different types of products (Clothing, Electronics, Books) using the appropriate factory. For example, you would use the `ClothingProductFactory` to create a clothing product by calling its `createProduct()` method, which will return an instance of `ClothingProduct`.

### Decorator Design Pattern example in ecommerce platform ?

In an ecommerce platform, the Decorator Design Pattern can be applied to enhance the functionality of product listings or shopping cart features. Here's an example:

1. Base Component:
   ```java
   public interface Product {
       String getName();
       double getPrice();
   }
   ```

2. Concrete Component:
   ```java
   public class ConcreteProduct implements Product {
       private String name;
       private double price;

       public ConcreteProduct(String name, double price) {
           this.name = name;
           this.price = price;
       }

       @Override
       public String getName() {
           return name;
       }

       @Override
       public double getPrice() {
           return price;
       }
   }
   ```

3. Decorator:
   ```java
   public abstract class ProductDecorator implements Product {
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
   }
   ```

4. Concrete Decorators:
   ```java
   public class SaleProductDecorator extends ProductDecorator {
       private double discount;

       public SaleProductDecorator(Product product, double discount) {
           super(product);
           this.discount = discount;
       }

       @Override
       public double getPrice() {
           double discountedPrice = product.getPrice() - (product.getPrice() * discount);
           return discountedPrice;
       }
   }

   public class GiftWrapProductDecorator extends ProductDecorator {
       private double giftWrapPrice;

       public GiftWrapProductDecorator(Product product, double giftWrapPrice) {
           super(product);
           this.giftWrapPrice = giftWrapPrice;
       }

       @Override
       public double getPrice() {
           double totalPrice = product.getPrice() + giftWrapPrice;
           return totalPrice;
       }
   }
   ```

In this example, the `Product` interface defines the common functionality for products in the ecommerce platform. The `ConcreteProduct` class is a concrete implementation of the `Product` interface.

The `ProductDecorator` class is the base decorator class that implements the `Product` interface. It has a reference to a `Product` object and delegates calls to it.

The `SaleProductDecorator` and `GiftWrapProductDecorator` are concrete decorators that add additional behavior to the product. The `SaleProductDecorator` applies a discount to the price of the product, while the `GiftWrapProductDecorator` adds an additional cost for gift wrapping.

You can create and chain multiple decorators to add various features or modifications to the base product dynamically. For example, you can decorate a product with both sale discount and gift wrapping.

The Decorator Design Pattern allows you to enhance the behavior of product listings or shopping cart features without modifying the existing classes. It provides flexibility and modularity in extending the functionality of the ecommerce platform.

### Give me example of shopping cart features considering decorator pattern 
Certainly! Here's an example of how the Decorator Design Pattern can be used to enhance shopping cart features in an ecommerce application:

1. Base Component:
```java
public interface Cart {
    void addProduct(Product product);
    void removeProduct(Product product);
    double getTotalPrice();
}
```

2. Concrete Component:
```java
public class ShoppingCart implements Cart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
```

3. Decorator:
```java
public abstract class CartDecorator implements Cart {
    protected Cart cart;

    public CartDecorator(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addProduct(Product product) {
        cart.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    @Override
    public double getTotalPrice() {
        return cart.getTotalPrice();
    }
}
```

4. Concrete Decorators:
```java
public class DiscountedCartDecorator extends CartDecorator {
    private double discount;

    public DiscountedCartDecorator(Cart cart, double discount) {
        super(cart);
        this.discount = discount;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        double discountedPrice = totalPrice - (totalPrice * discount);
        return discountedPrice;
    }
}

public class TaxedCartDecorator extends CartDecorator {
    private double taxRate;

    public TaxedCartDecorator(Cart cart, double taxRate) {
        super(cart);
        this.taxRate = taxRate;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        double taxedPrice = totalPrice + (totalPrice * taxRate);
        return taxedPrice;
    }
}
```

In this example, the `Cart` interface defines the common functionality for the shopping cart. The `ShoppingCart` class is a concrete implementation of the `Cart` interface.

The `CartDecorator` class is the base decorator class that implements the `Cart` interface. It has a reference to a `Cart` object and delegates calls to it.

The `DiscountedCartDecorator` and `TaxedCartDecorator` are concrete decorators that add additional behavior to the shopping cart. The `DiscountedCartDecorator` applies a discount to the total price of the cart, while the `TaxedCartDecorator` adds tax to the total price.

You can create and chain multiple decorators to add various features or modifications to the shopping cart dynamically. For example, you can decorate the cart with both a discount and tax calculation.

The Decorator Design Pattern allows you to enhance the functionality of the shopping cart without modifying the existing classes. It provides flexibility and extensibility in adding features to the ecommerce application.



## 4. ChainOfResponsibility design pattern example in ecommerce application 

Certainly! Let's consider an example of how the Chain of Responsibility Design Pattern can be applied in an ecommerce application:

Scenario: Order Processing in an Ecommerce Application

In an ecommerce application, when a customer places an order, the order goes through various stages of processing, such as payment validation, inventory check, fraud detection, and shipping confirmation. Each stage requires specific handling based on the nature of the order. We can use the Chain of Responsibility Design Pattern to handle these order processing stages.

1. Handler Interface/Abstract Class:
```java
public abstract class OrderProcessor {
    protected OrderProcessor nextProcessor;

    public void setNextProcessor(OrderProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void processOrder(Order order);
}
```

2. Concrete Handlers:
```java
public class PaymentProcessor extends OrderProcessor {
    public void processOrder(Order order) {
        // Perform payment validation logic here
        // ...

        if (nextProcessor != null) {
            nextProcessor.processOrder(order);
        }
    }
}

public class InventoryProcessor extends OrderProcessor {
    public void processOrder(Order order) {
        // Perform inventory check logic here
        // ...

        if (nextProcessor != null) {
            nextProcessor.processOrder(order);
        }
    }
}

public class FraudDetectionProcessor extends OrderProcessor {
    public void processOrder(Order order) {
        // Perform fraud detection logic here
        // ...

        if (nextProcessor != null) {
            nextProcessor.processOrder(order);
        }
    }
}

public class ShippingProcessor extends OrderProcessor {
    public void processOrder(Order order) {
        // Perform shipping confirmation logic here
        // ...

        if (nextProcessor != null) {
            nextProcessor.processOrder(order);
        }
    }
}
```

3. Client:
```java
public class OrderProcessingClient {
    public static void main(String[] args) {
        // Create the order processors
        OrderProcessor paymentProcessor = new PaymentProcessor();
        OrderProcessor inventoryProcessor = new InventoryProcessor();
        OrderProcessor fraudDetectionProcessor = new FraudDetectionProcessor();
        OrderProcessor shippingProcessor = new ShippingProcessor();

        // Build the chain of order processors
        paymentProcessor.setNextProcessor(inventoryProcessor);
        inventoryProcessor.setNextProcessor(fraudDetectionProcessor);
        fraudDetectionProcessor.setNextProcessor(shippingProcessor);

        // Create an order
        Order order = new Order(/* order details */);

        // Start the order processing by passing the order to the first processor in the chain
        paymentProcessor.processOrder(order);
    }
}
```

In this example, we have the following components:

- `OrderProcessor` is the abstract class defining the common interface for all order processors.
- `PaymentProcessor`, `InventoryProcessor`, `FraudDetectionProcessor`, and `ShippingProcessor` are concrete order processors implementing the `processOrder` method to handle specific processing tasks related to payment validation, inventory check, fraud detection, and shipping confirmation, respectively.
- The `OrderProcessingClient` sets up the chain of order processors by linking them together and starts the order processing by passing an order to the first processor in the chain (`paymentProcessor`).

When an order is processed, it goes through the chain of order processors. Each processor performs its specific processing task and, if necessary, passes the order to the next processor in the chain. This way, each processor focuses on its specific responsibility, and the order moves through the stages seamlessly.

The Chain of Responsibility Design Pattern allows for flexible and dynamic handling of order processing stages. It enables easy addition or removal of processors in the chain without affecting the client code. Additionally, it promotes loose coupling and enhances the maintainability and scalability of the order processing system in the ecommerce application.

### Another example of chain of responsibility 

Certainly! Here's another example of how the Chain of Responsibility Design Pattern can be applied in an ecommerce platform:

Scenario: Product Discount Calculation

In an ecommerce platform, different types of products may have different discount calculation strategies based on their category, price range, or any other criteria. We can use the Chain of Responsibility Design Pattern to calculate the discount for a product based on its specific category.

1. Handler Interface/Abstract Class:
```java
public abstract class DiscountCalculator {
    protected DiscountCalculator nextCalculator;

    public void setNextCalculator(DiscountCalculator nextCalculator) {
        this.nextCalculator = nextCalculator;
    }

    public abstract double calculateDiscount(Product product);
}
```

2. Concrete Handlers:
```java
public class ClothingDiscountCalculator extends DiscountCalculator {
    public double calculateDiscount(Product product) {
        if (product.getCategory().equals("Clothing")) {
            // Perform clothing-specific discount calculation logic here
            // ...

            return /* calculated discount */;
        } else if (nextCalculator != null) {
            return nextCalculator.calculateDiscount(product);
        }

        return 0.0; // No applicable discount
    }
}

public class ElectronicsDiscountCalculator extends DiscountCalculator {
    public double calculateDiscount(Product product) {
        if (product.getCategory().equals("Electronics")) {
            // Perform electronics-specific discount calculation logic here
            // ...

            return /* calculated discount */;
        } else if (nextCalculator != null) {
            return nextCalculator.calculateDiscount(product);
        }

        return 0.0; // No applicable discount
    }
}

public class BooksDiscountCalculator extends DiscountCalculator {
    public double calculateDiscount(Product product) {
        if (product.getCategory().equals("Books")) {
            // Perform books-specific discount calculation logic here
            // ...

            return /* calculated discount */;
        } else if (nextCalculator != null) {
            return nextCalculator.calculateDiscount(product);
        }

        return 0.0; // No applicable discount
    }
}
```

3. Client:
```java
public class DiscountCalculationClient {
    public static void main(String[] args) {
        // Create the discount calculators
        DiscountCalculator clothingDiscountCalculator = new ClothingDiscountCalculator();
        DiscountCalculator electronicsDiscountCalculator = new ElectronicsDiscountCalculator();
        DiscountCalculator booksDiscountCalculator = new BooksDiscountCalculator();

        // Build the chain of discount calculators
        clothingDiscountCalculator.setNextCalculator(electronicsDiscountCalculator);
        electronicsDiscountCalculator.setNextCalculator(booksDiscountCalculator);

        // Create a product
        Product product = new Product("Example Product", "Electronics", 100.0);

        // Calculate the discount by passing the product to the first calculator in the chain
        double discount = clothingDiscountCalculator.calculateDiscount(product);
        System.out.println("Discount: " + discount);
    }
}
```

In this example, we have the following components:

- `DiscountCalculator` is the abstract class defining the common interface for all discount calculators.
- `ClothingDiscountCalculator`, `ElectronicsDiscountCalculator`, and `BooksDiscountCalculator` are concrete discount calculators implementing the `calculateDiscount` method to calculate the discount for products of specific categories.
- The `DiscountCalculationClient` sets up the chain of discount calculators by linking them together and starts the discount calculation by passing a product to the first calculator in the chain (`clothingDiscountCalculator`).

When a discount calculation is performed, the product is passed through the chain of discount calculators. Each calculator checks if it can handle the product's category and performs the specific discount calculation. If a calculator cannot handle the product's category, it passes the product to the next calculator in the chain. This continues until a calculator provides a valid discount or the end of the chain is reached.

The Chain of Responsibility Design Pattern allows for flexible and extensible discount calculation strategies based on product categories. It enables easy addition or removal of calculators in the chain without affecting the client code. Additionally, it promotes loose coupling and enhances the maintainability and scalability of the discount calculation system in the ecommerce platform.

### Is there any other design pattern to calculate the discount for different category



If there are different types of discounts for different categories of products, a suitable design pattern to handle this scenario is the **Strategy Design Pattern**. The Strategy pattern allows you to define a family of algorithms (discount strategies) and encapsulate each algorithm into a separate class. This way, you can easily switch between different discount strategies at runtime without modifying the client code.

Here's an example of how you can use the Strategy Design Pattern for handling discounts in different categories of products:

1. DiscountStrategy Interface:
```java
public interface DiscountStrategy {
    double applyDiscount(double originalPrice);
}
```

1. Concrete Discount Strategy Classes:
```java
public class ClothingDiscountStrategy implements DiscountStrategy {
    public double applyDiscount(double originalPrice) {
        // Apply clothing-specific discount calculation logic
        // ...

        return /* calculated discount */;
    }
}

public class ElectronicsDiscountStrategy implements DiscountStrategy {
    public double applyDiscount(double originalPrice) {
        // Apply electronics-specific discount calculation logic
        // ...

        return /* calculated discount */;
    }
}

public class BooksDiscountStrategy implements DiscountStrategy {
    public double applyDiscount(double originalPrice) {
        // Apply books-specific discount calculation logic
        // ...

        return /* calculated discount */;
    }
}
```

3. Product Class:
```java
public class Product {
    private String category;
    private double price;
    // Other product properties and methods

}
```

4.  DiscountCalculator Class:
```java
public class DiscountCalculator {
    public double calculateDiscount(Product product, DiscountStrategy discountStrategy) {
        return discountStrategy.applyDiscount(product.getPrice());
    }
}
```


5. Client Code:
```java
public class DiscountCalculationClient {
    public static void main(String[] args) {
        // Create products
        Product clothingProduct = new Product("Clothing", 100.0);
        Product electronicsProduct = new Product("Electronics", 200.0);
        Product booksProduct = new Product("Books", 50.0);

        // Create discount strategies
        DiscountStrategy clothingDiscountStrategy = new ClothingDiscountStrategy();
        DiscountStrategy electronicsDiscountStrategy = new ElectronicsDiscountStrategy();
        DiscountStrategy booksDiscountStrategy = new BooksDiscountStrategy();

        // Create DiscountCalculator instance
        DiscountCalculator discountCalculator = new DiscountCalculator();

        // Calculate discounts for products using the appropriate strategy
        double clothingDiscount = discountCalculator.calculateDiscount(clothingProduct, clothingDiscountStrategy);
        double electronicsDiscount = discountCalculator.calculateDiscount(electronicsProduct, electronicsDiscountStrategy);
        double booksDiscount = discountCalculator.calculateDiscount(booksProduct, booksDiscountStrategy);

        System.out.println("Clothing Discount: " + clothingDiscount);
        System.out.println("Electronics Discount: " + electronicsDiscount);
        System.out.println("Books Discount: " + booksDiscount);
    }
}

```

In this example, the Strategy Design Pattern is used to encapsulate different discount calculation algorithms (strategies) into separate classes implementing the `DiscountStrategy` interface. Each concrete strategy class defines the specific discount calculation logic for its corresponding category.

The `Product` class has a `calculateDiscount` method that accepts a `DiscountStrategy` object as a parameter. This method delegates the discount calculation to the provided strategy object. By passing the appropriate discount strategy to the `calculateDiscount` method of a `Product` instance, you can apply the specific discount calculation logic for that category of product.

Using the Strategy Design Pattern, you can easily add new discount strategies for different categories without modifying the existing code. It promotes code reusability, flexibility, and separation of concerns by encapsulating the varying discount algorithms into separate classes.

### Can we modify above program applying solid principle and keeping same design pattern 

Certainly! To apply SOLID principles and enhance the design of the program while keeping the Strategy Design Pattern intact, we can make some improvements. Let's consider the Single Responsibility Principle (SRP) and the Open-Closed Principle (OCP) in our modifications:

1. Create a separate class for the discount calculation logic:
```java
public class DiscountCalculator {
    private final Map<String, DiscountStrategy> discountStrategies;

    public DiscountCalculator() {
        discountStrategies = new HashMap<>();
        // Initialize the discount strategies for each category
        discountStrategies.put("Clothing", new ClothingDiscountStrategy());
        discountStrategies.put("Electronics", new ElectronicsDiscountStrategy());
        discountStrategies.put("Books", new BooksDiscountStrategy());
        // Add more categories and corresponding strategies as needed
    }

    public double calculateDiscount(Product product) {
        DiscountStrategy strategy = discountStrategies.get(product.getCategory());
        if (strategy != null) {
            return strategy.applyDiscount(product.getPrice());
        }
        return 0.0; // No applicable discount strategy found
    }
}
```

2. Modify the Product class to remove the dependency on the DiscountStrategy:
```java
public class Product {
    private String category;
    private double price;
    // Other product properties and methods

    // Getters and setters

    // No calculateDiscount method in Product class
}
```

3. Client code:
```java
public class DiscountCalculationClient {
    public static void main(String[] args) {
        // Create products
        Product clothingProduct = new Product("Clothing", 100.0);
        Product electronicsProduct = new Product("Electronics", 200.0);
        Product booksProduct = new Product("Books", 50.0);

        // Create DiscountCalculator instance
        DiscountCalculator discountCalculator = new DiscountCalculator();

        // Calculate discounts for products
        double clothingDiscount = discountCalculator.calculateDiscount(clothingProduct);
        double electronicsDiscount = discountCalculator.calculateDiscount(electronicsProduct);
        double booksDiscount = discountCalculator.calculateDiscount(booksProduct);

        System.out.println("Clothing Discount: " + clothingDiscount);
        System.out.println("Electronics Discount: " + electronicsDiscount);
        System.out.println("Books Discount: " + booksDiscount);
    }
}
```

With these modifications, we have separated the responsibility of discount calculation from the `Product` class and moved it to the `DiscountCalculator` class. The `DiscountCalculator` class encapsulates the discount strategies for different categories and selects the appropriate strategy based on the product's category. This adheres to the SRP by ensuring that each class has a single responsibility.

Additionally, the program is now open for extension as new discount strategies can be added by creating a new class implementing the `DiscountStrategy` interface and registering it in the `DiscountCalculator` class without modifying the existing code, thus adhering to the OCP.

The overall design still follows the Strategy Design Pattern, allowing for flexible discount calculation based on the product category while promoting modularity and maintainability.



















