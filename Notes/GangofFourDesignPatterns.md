# Gang of Four Design Patterns

1. Creational Design Patterns
2. Structural Design Patterns
3. Behavior Design Patterns

### 1. Factory Design Pattern 
The Factory Design Pattern is a **creational design pattern** that provides an interface for creating objects but allows subclasses or concrete implementations to decide which class to instantiate. **It is used when there is a need to create multiple objects of similar types, but the specific type of object to be created is determined at runtime**.

The main goal of the Factory Design Pattern is to separate the object creation logic from the client code, promoting loose coupling and improved code maintainability.

The pattern involves the following key components:

1. Product: This is an interface or abstract class that defines the common interface for the objects to be created by the factory.

2. Concrete Products: These are the specific implementations of the product interface.

3. Factory: This is an interface or abstract class that declares a method for creating objects. It encapsulates the object creation logic and returns instances of the product interface.

4. Concrete Factory: These are the implementations of the factory interface. Each concrete factory is responsible for creating specific types of products.

Here's a simplified example to illustrate the Factory Design Pattern in Java:

```java
// Product interface
interface Shape {
    void draw();
}

// Concrete Products
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

// Factory interface
interface ShapeFactory {
    Shape createShape();
}

// Concrete Factories
class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new CircleFactory();
        Shape shape = factory.createShape();
        shape.draw();
    }
}
```

In this example, we have a `Shape` interface that defines the common `draw()` method. We have two concrete implementations, `Circle` and `Rectangle`, that implement the `Shape` interface.

We also have a `ShapeFactory` interface with a `createShape()` method that returns an instance of `Shape`. We have concrete factories, `CircleFactory` and `RectangleFactory`, that implement the `ShapeFactory` interface and create instances of `Circle` and `Rectangle` respectively.

The client code can use the factory to create shapes without being aware of the specific implementations. It simply interacts with the `ShapeFactory` interface and calls the `createShape()` method to obtain an instance of `Shape`.

The Factory Design Pattern allows us to abstract the object creation process and provide a consistent way of creating objects, making it easier to manage and extend the codebase.

2. **Decorator Design Pattern**:
The Decorator Design Pattern in Java is a **structural design pattern that allows behavior to be added to an object dynamically.** It provides a flexible alternative to subclassing for extending functionality.

In the Decorator pattern, there is a base component interface or class that defines the common functionality. Decorator classes wrap the base component and enhance its behavior by adding additional features dynamically. Both the base component and decorators implement the same interface or extend the same base class, allowing decorators to be used interchangeably with the base component.

Here's an example to illustrate the Decorator Design Pattern:

```java
// Base component interface
public interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete implementation of the base component
public class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}

// Decorator class
public abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

// Concrete decorator class
public class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return super.getCost() + 2.0;
    }
}
```

In this example, the `Pizza` interface defines the common functionality for pizzas. The `PlainPizza` class is a concrete implementation of the `Pizza` interface.

The `PizzaDecorator` class is the base decorator class that implements the `Pizza` interface. It has a reference to a `Pizza` object and delegates calls to it. This allows decorators to wrap around other decorators or the base component.

The `CheeseDecorator` class is a concrete decorator that adds additional behavior to the `Pizza` component. It extends the `PizzaDecorator` and overrides the `getDescription()` and `getCost()` methods to add cheese to the description and increase the cost.

You can create and chain multiple decorators to add various toppings or features to the base component dynamically.

The Decorator Design Pattern allows for the dynamic addition of behavior to objects at runtime without affecting their existing structure. It promotes flexible and reusable code by separating concerns and providing a modular way to enhance functionality.

## 3. Chain of responsibility 

The Chain of Responsibility Design Pattern is a **behavioral design pattern that allows an object to pass a request along a chain of potential handlers until the request is handled or reaches the end of the chain.** Each handler in the chain has the capability to handle the request or pass it to the next handler in the chain.

Key components of the Chain of Responsibility Design Pattern:

1. Handler Interface/Abstract Class:
   - Defines a common interface for all the handlers in the chain.
   - Typically declares a method to handle the request.

2. Concrete Handlers:
   - Implement the handler interface/extend the abstract class.
   - Handle the request if they have the capability, otherwise pass it to the next handler in the chain.

3. Client:
   - Initiates the request and starts the chain.
   - Has no knowledge of the specific handler in the chain, only knows the abstract handler.

The main idea behind the Chain of Responsibility Design Pattern is to decouple the sender of a request from its receivers. It allows multiple objects to have a chance to handle the request without coupling the sender to specific receiver objects.

Example Scenario: Logging System
Let's consider an example scenario where we have a logging system that needs to handle log messages based on their severity level. The system consists of multiple loggers arranged in a chain, each responsible for handling log messages of a specific severity level.

1. Handler Interface/Abstract Class:
```java
public abstract class Logger {
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public abstract void logMessage(String message, int severity);
}
```

2. Concrete Handlers:
```java
public class InfoLogger extends Logger {
    public void logMessage(String message, int severity) {
        if (severity <= 1) {
            System.out.println("Info Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(message, severity);
        }
    }
}

public class WarningLogger extends Logger {
    public void logMessage(String message, int severity) {
        if (severity <= 2) {
            System.out.println("Warning Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(message, severity);
        }
    }
}

public class ErrorLogger extends Logger {
    public void logMessage(String message, int severity) {
        if (severity <= 3) {
            System.out.println("Error Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(message, severity);
        }
    }
}
```

3. Client:
```java
public class Client {
    public static void main(String[] args) {
        Logger infoLogger = new InfoLogger();
        Logger warningLogger = new WarningLogger();
        Logger errorLogger = new ErrorLogger();

        // Build the chain
        infoLogger.setNextLogger(warningLogger);
        warningLogger.setNextLogger(errorLogger);

        // Start the chain by passing a log message
        infoLogger.logMessage("This is an informational message.", 1);
        infoLogger.logMessage("This is a warning message.", 2);
        infoLogger.logMessage("This is an error message.", 3);
    }
}
```

In this example, the `Logger` abstract class defines the common interface for all loggers in the chain. Each concrete logger (`InfoLogger`, `WarningLogger`, `ErrorLogger`) implements the `logMessage` method to handle the log messages of specific severity levels. If a logger cannot handle a log message, it passes the message to the next logger in the chain.

The `Client` class sets up the chain by linking the loggers together and starts the chain by passing log messages to the first logger (`infoLogger`).

When you run the `Client` class, it will output the log messages handled by the appropriate loggers based on their severity levels.

The Chain of Responsibility Design Pattern provides flexibility in adding or modifying handlers in the chain without impacting the client code. It promotes loose coupling and helps achieve separation of concerns in handling requests.

## Strategy Pattern 

The Strategy pattern is a **behavioral design pattern** that allows you to define a family of algorithms or strategies, encapsulate each one as a separate class, and make them interchangeable at runtime. **It enables the client to choose the desired algorithm or strategy dynamically without tightly coupling it to the specific implementation.**

In the Strategy pattern, you have a context or client class that requires a certain behavior or algorithm to be performed. Instead of implementing the behavior directly in the context class, it delegates the behavior to a separate strategy class. The context class holds a reference to an interface or abstract class, which serves as the common interface for all the strategies. Each strategy class implements this interface and provides its own implementation of the behavior.

This pattern promotes flexibility and extensibility by allowing new strategies to be added or existing ones to be modified without affecting the client code. It enables the client to switch between different strategies at runtime by simply changing the strategy object that the context holds.

Key components of the Strategy pattern:
- Context: The class that interacts with the strategy objects. It maintains a reference to the strategy interface and delegates the behavior to the currently set strategy object.
- Strategy: The interface or abstract class that defines the common interface for all the concrete strategies.
- Concrete Strategies: The classes that implement the strategy interface or extend the abstract class, providing their own implementation of the behavior.

Benefits of the Strategy pattern:
- Encourages code reuse and maintainability by separating behavior into reusable strategy classes.
- Enables dynamic selection of strategies at runtime, allowing for flexibility and customization.
- Provides a clear and defined interface for each strategy, promoting loose coupling and modularity.

Overall, the Strategy pattern helps in achieving cleaner, more flexible, and maintainable code by decoupling the behavior from the client and providing the ability to switch between different strategies as needed.

## Composite Design Pattern 
**The Composite design pattern is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies**. It lets clients treat individual objects and compositions of objects uniformly.

In the Composite pattern, there are two main components:

1. Component: This represents the common interface for all objects in the composition, whether they are leaf nodes or composite nodes. The Component interface defines the common operations that can be performed on the objects.

2. Composite: This represents a composite object that can have child objects. It implements the Component interface and provides methods for adding, removing, and accessing child components. The Composite object delegates the operations to its child components recursively.

The Composite pattern allows you to create a tree-like structure of objects, where individual objects and groups of objects are treated the same way. This enables you to work with complex hierarchies in a consistent manner.

Some key benefits of using the Composite pattern include:

- **Simplified client code**: Clients can treat individual objects and compositions uniformly, simplifying the client code and making it more intuitive.
- **Recursive structure**: The composite structure allows for recursive traversal and manipulation of the object hierarchy.
- **Flexibility and scalability**: New components can be added to the composition without affecting the existing structure, providing flexibility and scalability in the design.

Common use cases for the Composite pattern include representing hierarchical structures like file systems, organization charts, GUI components, and menu systems.

By using the Composite pattern, you can create a unified and flexible design that allows you to work with complex object structures in a consistent manner.

## Flyweight design pattern 

The Flyweight Design Pattern is a structural design pattern that aims to minimize memory usage by sharing as much data as possible across multiple objects. It focuses on efficiently managing and reusing fine-grained objects at a granular level.

The key idea behind the Flyweight pattern is to separate intrinsic and extrinsic state in objects. Intrinsic state refers to the common, immutable, and shared data that can be shared among multiple objects, while extrinsic state refers to the contextual, mutable data specific to each object.

By separating the intrinsic and extrinsic state, the Flyweight pattern allows multiple objects to share the same intrinsic state, reducing memory consumption and improving performance.

Here are the key components of the Flyweight pattern:

1. Flyweight: Represents the interface or abstract class that defines the operations to be performed on flyweight objects. It usually contains methods that take the extrinsic state as an argument.

2. Concrete Flyweight: Implements the Flyweight interface and represents the concrete flyweight objects. These objects are typically shared and reused across multiple contexts.

3. Flyweight Factory: Manages and creates flyweight objects. It ensures that flyweight objects are shared and reused when requested, rather than creating new objects unnecessarily.

4. Client: Requests and uses flyweight objects from the flyweight factory. The client may also provide the extrinsic state required for the flyweight objects.

Here's a simplified example of the Flyweight pattern in Java:

```java
// Flyweight interface
public interface Shape {
    void draw();
}

// Concrete flyweight class
public class Circle implements Shape {
    private final String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with color: " + color);
    }
}

// Flyweight factory
public class ShapeFactory {
    private final Map<String, Shape> circleMap;

    public ShapeFactory() {
        circleMap = new HashMap<>();
    }

    public Shape getCircle(String color) {
        Shape circle = circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
        }

        return circle;
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape circle1 = shapeFactory.getCircle("red");
        circle1.draw();

        Shape circle2 = shapeFactory.getCircle("blue");
        circle2.draw();

        Shape circle3 = shapeFactory.getCircle("red");
        circle3.draw();

        // circle3 is the same instance as circle1, as it is retrieved from the flyweight factory
    }
}
```

In the above example, the `Shape` interface represents the flyweight objects, and the `Circle` class is a concrete implementation of the `Shape` interface. The `ShapeFactory` acts as the flyweight factory, managing and creating flyweight objects based on the requested color. The client code requests circle objects from the flyweight factory and draws them.

By utilizing the Flyweight pattern, the same color circles are shared and reused, resulting in memory savings and improved performance.

The Flyweight pattern is useful when you have a large number of fine-grained objects that share common state and can be effectively shared among multiple contexts. It is especially beneficial in scenarios where memory usage is a concern or when you need to optimize performance by reducing object creation.

## Builder Design Pattern 

The Builder Design Pattern is a **creational design pattern that provides a flexible way to construct complex objects step by step**. 

**The main idea behind the Builder pattern is to abstract the construction process of an object so that the client code can specify the desired configuration without needing to know the internal details of the object's construction.**

### Can we say that @Builder is following builder annotation ? 
Yes, the @Builder annotation in Java, specifically from the Lombok library, follows the Builder design pattern. The Lombok @Builder annotation generates boilerplate code for creating a builder class and simplifies the process of building complex objects.

## When do we need to apply builder pattern ? 
 Here are some scenarios where applying the Builder pattern can be beneficial:

1. **Objects with many optional parameters**:
   When dealing with classes that have a large number of optional parameters, it can be cumbersome to create constructors or setters for each possible combination. The Builder pattern allows you to specify only the desired parameters, making the object creation code more readable and avoiding constructor overload.

2. Immutable objects with multiple fields:
   If you want to create immutable objects, where the state of the object cannot be changed after instantiation, the Builder pattern can be helpful. By using the Builder pattern, you can construct the object step-by-step, and once the object is built, it becomes immutable, ensuring thread-safety and preventing accidental modification of its state.

3. **Objects requiring complex instantiation logic**:
   In cases where the object instantiation involves complex logic, validation, or dependencies, the Builder pattern allows you to encapsulate that logic within the builder class. **The builder class can perform the necessary validations or handle dependencies before constructing the object, simplifying the object creation process**.

4. Improved readability and maintainability:
   The Builder pattern enhances the readability of code by providing a fluent and self-explanatory API for object construction. It makes the code more expressive and self-documenting, especially when dealing with objects that have many properties. **Additionally, it promotes maintainability by allowing you to add new optional or mandatory fields to the object without modifying the existing codebase extensively.**

5. Flexible object creation:
   The Builder pattern enables you to **create objects with default values, override specific properties, or set values dynamically based on certain conditions.** It provides flexibility in constructing objects, allowing you to adapt to different scenarios without cluttering the codebase with various constructors or methods.

Overall, the Builder pattern is a good choice when you need to create complex objects with optional or mutable fields, maintain immutability, improve code readability, and provide a flexible and intuitive API for object construction. It helps streamline the object creation process and enhances the maintainability and extensibility of your code.

```java
package designPattern.builder;

public class Car {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    // ...

    private Car(String brand, String model, int year, boolean isElectric) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isElectric = isElectric;
        // ...
    }

    // Getters...

    public static class Builder {
        private String brand;
        private String model;
        private int year;
        private boolean isElectric;
        // ...

        public Builder(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder isElectric(boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        // Other builder methods for additional properties...

        public Car build() {
            // Complex validation or instantiation logic can be performed here
            if (year < 1886) {
                throw new IllegalArgumentException("Invalid year");
            }

            return new Car(brand, model, year, isElectric);
        }
    }

    public static void main(String[] args) {

        Car car = new Car.Builder("Tesla", "Model S")
                .year(2022)
                .isElectric(true)
                .build();

        Car car2 = new Car.Builder("Tata", "Model 9") // Exception in thread "main" java.lang.IllegalArgumentException: Invalid year
                .year(1880)
                .isElectric(true)
                .build();

    }
}

```

## Can we write our own logic in the class for year while using @Builder? 
Yes, you can write your own logic for the `year` field while using `@Builder` annotation in Lombok. The `@Builder` annotation generates a builder class with setter methods for each field, allowing you to customize the behavior of those methods.

Here's an example of how you can write your own logic for the `year` field using Lombok's `@Builder` annotation:

```java
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Car {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    // ...

    public static class CarBuilder {
        private int year;

        public CarBuilder year(int year) {
            // Custom logic for year field
            if (year < 1886) {
                throw new IllegalArgumentException("Invalid year");
            }
            this.year = year;
            return this;
        }
    }
}
```

```java
Car car = Car.builder()
        .brand("Tesla")
        .model("Model S")
        .year(2022) // This value satisfies the custom logic
        .isElectric(true)
        .build();
```

### How to make a field mandatory using builder pattern with example not using lombok
```java
public Car build() {
        // Complex validation or instantiation logic can be performed here
        if (year < 1886) {
            throw new IllegalArgumentException("Invalid year");
        }

        if (brand == null || model == null) {
            throw new IllegalStateException("Brand and model are mandatory");
        }

        return new Car(brand, model, year, isElectric);
    }
```
we can achieve in lombok using @NonNull annotation 

### Also how we can create objects with default values, override specific properties, or set values dynamically based on certain conditions following builder pattern without using lombok 

```java
public class Person {
    private String name;
    private int age;
    private String address;

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private int age;
        private String address;

        public Builder() {
            // Set default values if needed
            this.name = "Default";
            this.age = 0;
            this.address = "";
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            // Set values dynamically based on certain conditions
            if (this.age < 18) {
                this.address = "Child Address";
            } else {
                this.address = "Adult Address";
            }

            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return String.format("name %s, age %d, address %s", name, age, address);
    }
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .name("John Doe") 
                .age(17) // Override specific property
                .build();

        System.out.println(person);

    }
}
```
Using lombok 
```java
package designPattern.builder;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Bike {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    public static class BikeBuilder {
        private int year;
        private boolean isElectric = false;// giving default value
        public BikeBuilder year(int year) {
            if (year < 1886) {
                throw new IllegalArgumentException("Invalid year");
            }
            this.year = year;
            return this;
        }
    }

    public static void main(String[] args) {
        Bike bike = Bike.builder()
                .brand("Tesla")
                .model("Model S")
                .year(2022) // This value satisfies the custom logic
                .build();
        System.out.println(bike);

    }
}
```
## Observer design pattern 

**The Observer design pattern is a behavioral pattern that establishes a one-to-many dependency between objects. In this pattern, when the state of one object (called the subject or observable) changes, all its dependents (called observers) are notified and updated automatically. This allows for loose coupling between objects and promotes the principle of "separating concerns".**

Here's an example of the Observer design pattern in Java:

```java
import java.util.ArrayList;
import java.util.List;

// Subject (Observable)
interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Observer
interface Observer {
    void update(int temperature);
}

// Concrete Observer
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(int temperature) {
        System.out.println(name + ": The temperature is " + temperature + " degrees Celsius.");
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        // Create the subject
        WeatherStation weatherStation = new WeatherStation();

        // Create observers
        Observer user1 = new User("User 1");
        Observer user2 = new User("User 2");

        // Register observers with the subject
        weatherStation.registerObserver(user1);
        weatherStation.registerObserver(user2);

        // Simulate temperature change
        weatherStation.setTemperature(25);
        weatherStation.setTemperature(30);

        // Unregister an observer
        weatherStation.unregisterObserver(user1);

        // Simulate temperature change
        weatherStation.setTemperature(27);
    }
}
```

In this example, we have a `WeatherStation` class that acts as the subject or observable. It maintains a list of observers and notifies them when the temperature changes. The `User` class represents the observers, and each user is notified and updated with the new temperature.

When we run the `ObserverPatternExample`, it creates a `WeatherStation` object, registers `User` objects as observers, and simulates temperature changes. Each user observer receives the temperature updates and displays them.

The Observer pattern allows for easy extensibility and decoupling between subjects and observers. It enables objects to maintain consistency and respond to changes in a flexible and efficient manner.

## Filter Design Pattern 
The Filter design pattern, also known as the Criteria pattern, **is a structural pattern that allows you to apply multiple criteria or filters to a collection of objects and retrieve the filtered results.** It provides a way to easily combine and chain filters to perform complex queries or searches.

Here's an example of the Filter design pattern in Java:

Suppose we have a collection of `Product` objects, and we want to filter them based on different criteria such as category, price range, and availability.

1. Create the Product class:
```java
public class Product {
    private String name;
    private String category;
    private double price;
    private boolean available;

    // constructor, getters, and setters
}
```

2. Create the Filter interface:
```java
public interface Filter {
    List<Product> filter(List<Product> products);
}
```

3. Implement concrete filter classes:
```java
public class CategoryFilter implements Filter {
    private String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}

public class PriceRangeFilter implements Filter {
    private double minPrice;
    private double maxPrice;

    public PriceRangeFilter(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}

public class AvailabilityFilter implements Filter {
    private boolean available;

    public AvailabilityFilter(boolean available) {
        this.available = available;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isAvailable() == available) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
```

4. Use the filters to filter the products:
```java
public class Main {
    public static void main(String[] args) {
        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 1000, true));
        products.add(new Product("T-shirt", "Apparel", 25, true));
        products.add(new Product("TV", "Electronics", 1500, false));
        products.add(new Product("Shoes", "Footwear", 50, true));

        // Apply the filters
        Filter categoryFilter = new CategoryFilter("Electronics");
        List<Product> electronicsProducts = categoryFilter.filter(products);
        System.out.println("Electronics Products: " + electronicsProducts);

        Filter priceRangeFilter = new PriceRangeFilter(0, 100);
        List<Product> cheapProducts = priceRangeFilter.filter(products);
        System.out.println("Cheap Products: " + cheapProducts);

        Filter availabilityFilter = new AvailabilityFilter(true);
        List<Product> availableProducts = availabilityFilter.filter(products);
        System.out.println("Available Products: " + availableProducts);
    }
}
```

In this example, we have the `Product` class representing individual products. The `Filter` interface defines the contract for applying filters. We implement concrete filter classes such as `CategoryFilter`, `PriceRangeFilter`, and `AvailabilityFilter`, which apply specific filtering criteria to the list of products.

We can create instances of these filter classes and apply them to the list of products to retrieve the desired filtered results. The filters can be easily combined and extended to create more complex filtering scenarios.

This demonstrates how the Filter design pattern allows you to separate the filtering logic from the objects being filtered, providing a flexible and modular way to filter collections based on specific criteria.

## Adapter design pattern with example in java 
**The Adapter design pattern is a structural pattern that allows incompatible interfaces to work together by creating an adapter that acts as a bridge between them.** It converts the interface of one class into another interface that the client expects.

Here's an example of the Adapter design pattern in Java:

Suppose we have an existing legacy class `LegacyLibrary` that provides a method `legacyRequest()` with a different interface from what our client code expects. We want to use this legacy class in our new system by adapting its interface to match our expected interface.

1. Create the Target interface:
```java
public interface Target {
    void request();
}
```

2. Implement the Adaptee (LegacyLibrary) with the incompatible interface:
```java
public class LegacyLibrary {
    public void legacyRequest() {
        System.out.println("Legacy Library: Legacy Request");
    }
}
```

3. Create the Adapter class that implements the Target interface and wraps the Adaptee:
```java
public class Adapter implements Target {
    private LegacyLibrary legacyLibrary;

    public Adapter(LegacyLibrary legacyLibrary) {
        this.legacyLibrary = legacyLibrary;
    }

    @Override
    public void request() {
        // Call the legacy method using the adapted interface
        legacyLibrary.legacyRequest();
    }
}
```

4. Use the Adapter in client code:
```java
public class Main {
    public static void main(String[] args) {
        // Create the LegacyLibrary instance
        LegacyLibrary legacyLibrary = new LegacyLibrary();

        // Create the Adapter and pass the LegacyLibrary instance to it
        Target adapter = new Adapter(legacyLibrary);

        // Call the method through the adapter
        adapter.request();
    }
}
```

In this example, we have a `LegacyLibrary` class that has a `legacyRequest()` method with an incompatible interface. We create the `Target` interface that defines the expected interface in our new system. The `Adapter` class implements the `Target` interface and internally wraps the `LegacyLibrary` object.

By using the Adapter class, we can bridge the gap between the incompatible interfaces. The `Adapter` class delegates the `request()` method call to the `legacyRequest()` method of the `LegacyLibrary` object, allowing the client code to work seamlessly with the legacy library.

The Adapter pattern helps in integrating or reusing existing code that might have different interfaces, allowing them to work together without modifying the existing code. It promotes code reusability, maintainability, and flexibility in adapting interfaces between different components or systems.

## Abstract design factory 
The Abstract Factory design pattern is a creational pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. It encapsulates the creation logic, allowing the client code to work with a factory interface rather than directly creating objects.

## Prototype design pattern
Cloneable interface in Java is related to the Prototype design pattern. It is a marker interface that indicates that a class can be cloned, allowing objects of that class to be created by making copies of existing objects.

## Facade design pattern:
The Facade design pattern is a **structural design pattern that provides a simplified interface or facade to a complex subsystem of classes or components.** It aims to simplify the usage of the subsystem by providing a unified and higher-level interface that hides the complexities and inner workings of the subsystem.

**The key idea behind the Facade pattern is to encapsulate the subsystem's functionality and expose a simplified interface that abstracts the underlying complexities.** This simplifies the client's interaction with the subsystem, reduces coupling, and promotes ease of use.

The Facade design pattern offers the following benefits:

1. **Simplified interface**: The facade provides a simple and unified interface that abstracts the complexities of the subsystem. It presents a higher-level view of the subsystem's functionality, making it easier for clients to understand and use.

2. **Decoupling**: The facade acts as an intermediary between the client and the subsystem, reducing the direct coupling between them. Clients interact with the facade without needing to know the details of the underlying subsystem components.

3. **Subsystem encapsulation**: The facade encapsulates the complex subsystem and shields clients from its internal workings. This helps to isolate clients from changes in the subsystem's implementation, making it easier to maintain and modify the subsystem without affecting the clients.

4. **Code organization**: The Facade pattern promotes a modular and organized structure by providing a central point of access to the subsystem. It improves code readability and maintainability by separating the complex subsystem code from the client code.

The Facade pattern is commonly used in various scenarios, such as simplifying complex APIs, providing high-level interfaces to external libraries or frameworks, managing complex system interactions, and hiding implementation details. It promotes abstraction, simplicity, and reusability in software design.

Overall, the Facade design pattern is a powerful tool for simplifying the usage of complex subsystems, improving the overall structure of the codebase, and enhancing the maintainability and flexibility of the system.

```java 
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

// Client code
class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder("ABC123", 2, 100.0, "ORDER123");
    }
}
```
- Facade design pattern is more like a helper for client applications, it doesn’t hide subsystem interfaces from the client. Whether to use Facade or not is completely dependent on client code.
- Facade design pattern can be applied at any point of development, usually when the number of interfaces grow and system gets complex.
- Subsystem interfaces are not aware of Facade and they shouldn’t have any reference of the Facade interface.
- Facade design pattern should be applied for similar kind of interfaces, its purpose is to provide a single interface rather than multiple interfaces that does the similar kind of jobs.
- We can use Factory pattern with Facade to provide better interface to client systems.

## Bridge design pattern with a example 
The Bridge design pattern is a structural design pattern that decouples an abstraction from its implementation, allowing them to vary independently. It is used when there is a need to separate an abstraction's interface from its implementation so that they can be modified and extended independently.

Here's an example of the Bridge design pattern:

```java
// Abstraction interface
interface Vehicle {
    void manufacture();
}

// Refined Abstraction classes
class Car implements Vehicle {
    private Workshop workshop1;
    private Workshop workshop2;

    public Car(Workshop workshop1, Workshop workshop2) {
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    public void manufacture() {
        System.out.println("Car is being manufactured.");
        workshop1.doWork();
        workshop2.doWork();
    }
}

class Bike implements Vehicle {
    private Workshop workshop;

    public Bike(Workshop workshop) {
        this.workshop = workshop;
    }

    public void manufacture() {
        System.out.println("Bike is being manufactured.");
        workshop.doWork();
    }
}

// Implementation interface
interface Workshop {
    void doWork();
}

// Concrete Implementation classes
class AssembleWorkshop implements Workshop {
    public void doWork() {
        System.out.println("Assembling the vehicle.");
    }
}

class PaintWorkshop implements Workshop {
    public void doWork() {
        System.out.println("Painting the vehicle.");
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        Workshop assembleWorkshop = new AssembleWorkshop();
        Workshop paintWorkshop = new PaintWorkshop();

        Vehicle car = new Car(assembleWorkshop, paintWorkshop);
        car.manufacture();

        Vehicle bike = new Bike(assembleWorkshop);
        bike.manufacture();
    }
}
```

In this example, the Bridge pattern is used to separate the abstraction (`Vehicle`) from its implementation (`Workshop`). The `Vehicle` interface represents different types of vehicles that can be manufactured. The `Car` and `Bike` classes are refined abstractions that implement the `Vehicle` interface.

The `Workshop` interface represents different types of workshops that can perform specific tasks during the vehicle manufacturing process. The `AssembleWorkshop` and `PaintWorkshop` classes are concrete implementations of the `Workshop` interface.

The `Car` class takes two instances of the `Workshop` interface in its constructor, representing different workshops involved in car manufacturing. The `Bike` class takes a single instance of the `Workshop` interface for bike manufacturing.

The `manufacture()` method in each vehicle class represents the manufacturing process. It delegates the specific work to the corresponding workshop objects. The `Car` class invokes the `doWork()` method of both workshops, while the `Bike` class invokes the `doWork()` method of the single workshop.

The client code creates instances of the workshops and passes them to the vehicle objects. It then calls the `manufacture()` method on each vehicle, which in turn invokes the respective workshop's `doWork()` method.

By using the Bridge design pattern in this scenario, the abstraction (`Vehicle`) and the implementation (`Workshop`) are decoupled, allowing them to vary independently. New types of vehicles and workshops can be added without modifying the existing code. It promotes code flexibility, maintainability, and extensibility.

Another Example 
```java
// Abstraction interface
interface Shape {
    void draw();
}

// Refined Abstraction classes
class Circle implements Shape {
    private Color color;

    public Circle(Color color) {
        this.color = color;
    }

    public void draw() {
        System.out.print("Drawing a circle in ");
        color.applyColor();
    }
}

class Square implements Shape {
    private Color color;

    public Square(Color color) {
        this.color = color;
    }

    public void draw() {
        System.out.print("Drawing a square in ");
        color.applyColor();
    }
}

// Implementation interface
interface Color {
    void applyColor();
}

// Concrete Implementation classes
class Red implements Color {
    public void applyColor() {
        System.out.println("Red color");
    }
}

class Blue implements Color {
    public void applyColor() {
        System.out.println("Blue color");
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        Color redColor = new Red();
        Color blueColor = new Blue();

        Shape circle = new Circle(redColor);
        Shape square = new Square(blueColor);

        circle.draw();
        square.draw();
    }
}
```

## When we apply bridge pattern ?
The Bridge design pattern is typically applied in the following scenarios:

1. **When you want to decouple an abstraction from its implementation**: The Bridge pattern allows you to separate the abstraction and its implementation, enabling them to vary independently. This is useful when you want to change or extend the implementation without affecting the abstraction or vice versa.

2. **When you have a hierarchy of classes with multiple dimensions of variation:** If you have a class hierarchy where you need to support multiple variations along different dimensions, such as different platforms, devices, or versions, the Bridge pattern can help you manage these variations more effectively.

3. **When you want to avoid a permanent binding between an abstraction and its implementation:** The Bridge pattern allows you to select an implementation at runtime, rather than at compile time. This provides more flexibility and adaptability to your code.

4. **When you want to promote code flexibility and maintainability**: By decoupling the abstraction and implementation, the Bridge pattern promotes code flexibility and maintainability. It allows you to add new abstractions or implementations without modifying the existing code, making your codebase more extensible and easier to maintain.

Overall, the Bridge pattern is useful when you want to separate the abstraction from its implementation and enable them to vary independently, providing flexibility, maintainability, and extensibility to your code. It is particularly beneficial in scenarios where you expect changes or variations in both the abstraction and its implementation over time.

## Difference between bridge pattern and adapter pattern 
The Bridge pattern and the Adapter pattern are both structural design patterns, but they have different purposes and intentions. Here's a comparison between the two:

Bridge Pattern:
1. Purpose: The Bridge pattern is used to decouple an abstraction from its implementation, allowing them to vary independently.
2. Relationship: The abstraction and implementation are separate entities connected through a bridge interface.
3. Intent: The Bridge pattern focuses on separating the abstraction and implementation to handle different variations and dimensions independently. It promotes code flexibility and extensibility.
4. Usage: It is commonly used when you have a hierarchy of classes with multiple dimensions of variation, and you want to avoid a permanent binding between the abstraction and its implementation.

Adapter Pattern:
1. Purpose: The Adapter pattern is used to convert the interface of one class into another interface that clients expect.
2. Relationship: The adapter acts as a wrapper or intermediary between two incompatible interfaces.
3. Intent: The Adapter pattern is used to make two incompatible interfaces work together by converting the interface of one class to match the other. It provides a way for different classes to work together without changing their existing interfaces.
4. Usage: It is commonly used when you have existing classes with incompatible interfaces, and you want to make them work together without modifying their original source code.

In summary, the Bridge pattern focuses on decoupling an abstraction from its implementation, while the Adapter pattern focuses on making two incompatible interfaces work together. The Bridge pattern is about designing for variations and dimensions, while the Adapter pattern is about adapting existing classes to work together.

## Interpretor design pattern

The Interpreter design pattern is a behavioral pattern that is used to define a grammar for interpreting expressions and provide a way to evaluate those expressions. **It is commonly used for tasks such as language parsing, mathematical expression evaluation, and query processing**.

Here's an example of the Interpreter pattern for evaluating simple arithmetic expressions using the four basic operations: addition, subtraction, multiplication, and division.

```java
// Abstract Expression interface
interface Expression {
    int interpret();
}

// Terminal Expression classes
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

// Non-Terminal Expression classes
class AdditionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AdditionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

class SubtractionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }
}

class MultiplicationExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public MultiplicationExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        return leftExpression.interpret() * rightExpression.interpret();
    }
}

class DivisionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public DivisionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public int interpret() {
        int divisor = rightExpression.interpret();
        if (divisor != 0) {
            return leftExpression.interpret() / divisor;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        // Create the expression: (5 + 3) * (10 - 2) / 4
        Expression expression = new DivisionExpression(
            new MultiplicationExpression(
                new AdditionExpression(
                    new NumberExpression(5),
                    new NumberExpression(3)
                ),
                new SubtractionExpression(
                    new NumberExpression(10),
                    new NumberExpression(2)
                )
            ),
            new NumberExpression(4)
        );

        // Evaluate the expression
        int result = expression.interpret();
        System.out.println("Result: " + result);
    }
}
```

In this example, we have defined a set of expression classes that implement the `Expression` interface. The `NumberExpression` represents a single number, while the other expression classes represent the various arithmetic operations.

The client code constructs an expression by combining different expressions using the appropriate operators. Finally, the expression is evaluated by calling the `interpret()` method, which recursively evaluates the sub-expressions and performs the corresponding arithmetic operation.

The Interpreter pattern allows us to define complex grammars and evaluate expressions in a structured and extensible way. It separates the expression evaluation logic from the grammar, making it easier to add new operations or modify the evaluation process without changing the existing classes.

## Visitor pattern 

The Visitor design pattern is a behavioral pattern that allows adding new operations or behaviors to an existing set of classes without modifying their structure. It achieves this by separating the operations from the classes on which they operate.

Here's an example of the Visitor pattern using an e-commerce application scenario:

```java
// Visitor interface
interface Visitor {
    void visit(Product product);
    void visit(Order order);
    // Add more visit methods for other elements
}

// Element interface
interface Element {
    void accept(Visitor visitor);
}

// Concrete Element classes
class Product implements Element {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Order implements Element {
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor classes
class PriceCalculatorVisitor implements Visitor {
    private double totalPrice;

    public void visit(Product product) {
        totalPrice += product.getPrice();
    }

    public void visit(Order order) {
        for (Product product : order.getProducts()) {
            product.accept(this);
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 15.0));
        products.add(new Product("Product 3", 20.0));

        Order order = new Order(products);

        PriceCalculatorVisitor priceCalculator = new PriceCalculatorVisitor();
        order.accept(priceCalculator);

        double totalPrice = priceCalculator.getTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
    }
}
```

In this example, we have an e-commerce application that consists of two types of elements: `Product` and `Order`. The `Visitor` interface defines the visit methods for each element type.

The concrete elements (`Product` and `Order`) implement the `Element` interface and provide the `accept` method, which allows a visitor to visit them.

The concrete visitor class `PriceCalculatorVisitor` implements the `Visitor` interface and defines the specific behavior for calculating the total price. It keeps track of the accumulated price while visiting each product and order.

In the client code, we create a list of products and an order containing those products. We then create an instance of the `PriceCalculatorVisitor` and call the `accept` method on the order, passing in the visitor. The visitor traverses the products in the order and calculates the total price.

The Visitor pattern allows us to add new behaviors or operations to the elements (products and orders) without modifying their structure. We can easily create new visitor classes to perform different operations on the same set of elements. This promotes extensibility and separation of concerns in the application.

## When we need to use visitor pattern ? 
The Visitor pattern is useful in the following scenarios:

1. When you have a complex hierarchy of objects with different types and you want to perform operations or apply behaviors to them without modifying their structure.

2. When you have a set of unrelated or independent operations that need to be applied to multiple classes, and you want to avoid modifying those classes to accommodate the new operations.

3. When you want to add new behaviors or operations to existing classes without affecting their existing code or introducing tight coupling.

4. When you have a collection of objects with varying types and you want to perform a specific operation on each object, but the objects themselves need to decide how the operation is carried out.

5. When you want to separate the concerns of an algorithm from the objects it operates on, allowing different implementations of the algorithm to be applied to different objects without modifying their structure.

The Visitor pattern provides a way to add new operations to a set of objects without modifying those objects. It achieves this by introducing a separate Visitor interface and concrete Visitor classes that encapsulate the operations to be performed on the objects. The objects accept the visitors and call the appropriate method based on their type, enabling the visitor to perform the desired operation.

By using the Visitor pattern, you can achieve a higher level of extensibility, maintainability, and flexibility in your codebase. It allows for the separation of concerns and enables the addition of new behaviors or operations without modifying existing classes.

## state design pattern example in java 
The State design pattern is a behavioral pattern that allows an object to alter its behavior when its internal state changes. It encapsulates state-specific logic into separate state classes, reducing conditional statements and making the code more modular and maintainable.

Here's an example of the State pattern in Java using a simplified vending machine scenario:

```java
// VendingMachine interface
interface VendingMachineState {
    void insertCoin();
    void selectProduct();
    void dispenseProduct();
}

// Concrete state classes
class NoCoinState implements VendingMachineState {
    public void insertCoin() {
        System.out.println("Coin inserted");
    }

    public void selectProduct() {
        System.out.println("Please insert a coin first");
    }

    public void dispenseProduct() {
        System.out.println("Please insert a coin first");
    }
}

class HasCoinState implements VendingMachineState {
    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    public void selectProduct() {
        System.out.println("Product selected");
    }

    public void dispenseProduct() {
        System.out.println("Product dispensed");
    }
}

// Context class (VendingMachine)
class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        currentState = new NoCoinState();
    }

    public void insertCoin() {
        currentState.insertCoin();
        if (currentState instanceof NoCoinState) {
            currentState = new HasCoinState();
        }
    }

    public void selectProduct() {
        currentState.selectProduct();
        if (currentState instanceof HasCoinState) {
            currentState = new NoCoinState();
        }
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.selectProduct(); // Output: Please insert a coin first

        vendingMachine.insertCoin(); // Output: Coin inserted
        vendingMachine.selectProduct(); // Output: Product selected
        vendingMachine.dispenseProduct(); // Output: Product dispensed

        vendingMachine.selectProduct(); // Output: Please insert a coin first
    }
}
```

In this example, we have a `VendingMachine` class that encapsulates the current state of the vending machine. The `VendingMachineState` interface defines the common methods that represent the possible actions in the vending machine.

The concrete state classes (`NoCoinState` and `HasCoinState`) implement the `VendingMachineState` interface and provide specific implementations for the actions based on the current state.

The `VendingMachine` class maintains the current state and delegates the actions to the appropriate state object. When a coin is inserted, the state transitions from `NoCoinState` to `HasCoinState`, and when a product is selected, the state transitions back to `NoCoinState`.

The client code demonstrates the usage of the `VendingMachine` class by inserting a coin, selecting a product, and dispensing the product. It also shows that certain actions are not allowed in certain states.

The State pattern allows you to manage the behavior of an object based on its internal state, resulting in cleaner and more maintainable code. It promotes encapsulation and modularization by separating state-specific behavior into individual state classes.

## Where do we use state design pattern ? 
The State design pattern is used in the following scenarios:

1. When an object's behavior changes based on its internal state, and the number of states and corresponding behaviors can vary.

2. When there are multiple conditional statements based on an object's state, and the code becomes complex and hard to maintain.

3. When you want to avoid code duplication and keep related state-specific logic together in separate state classes.

4. When you want to add new states and behaviors to an object without modifying its existing code.

5. When you want to ensure that the transitions between states are managed consistently and avoid incorrect state changes.

Some common use cases of the State pattern include:

- Vending machines: The behavior of a vending machine changes based on its current state, such as when a coin is inserted, a product is selected, or the machine is out of stock.

- Document editing software: The behavior of the document editor changes depending on the current editing mode, such as insert mode, overwrite mode, or formatting mode.

- Game character behavior: The behavior of a game character may change based on its current state, such as idle, walking, running, or attacking.

- Traffic signal system: The behavior of a traffic signal changes based on the current state, such as red, green, or yellow.

The State pattern allows for cleaner and more modular code by encapsulating state-specific behavior into separate classes. It promotes extensibility and maintainability by allowing new states and corresponding behaviors to be added without modifying the existing code. It also improves code readability by reducing the number of conditional statements and focusing on specific state-related logic.

## Command Design Pattern in java 
The Command design pattern is a behavioral pattern that encapsulates a request as an object, thereby allowing you to parameterize clients with different requests, queue or log requests, and support undoable operations.

Here's an example of the Command pattern in Java:

```java
// Command interface
interface Command {
    void execute();
}

// Concrete command classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

// Receiver class
class Light {
    public void turnOn() {
        System.out.println("Light is turned on");
    }

    public void turnOff() {
        System.out.println("Light is turned off");
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        remoteControl.setCommand(lightOnCommand);
        remoteControl.pressButton(); // Output: Light is turned on

        remoteControl.setCommand(lightOffCommand);
        remoteControl.pressButton(); // Output: Light is turned off
    }
}
```

In this example, we have a `Command` interface that defines the `execute()` method. The concrete command classes (`LightOnCommand` and `LightOffCommand`) implement this interface and encapsulate the requests to turn on and turn off a `Light` object.

The `Light` class acts as the receiver, which performs the actual operations when the commands are executed.

The `RemoteControl` class acts as the invoker, which sets and executes the commands.

The client code demonstrates the usage of the Command pattern by creating a `RemoteControl` object, a `Light` object, and the corresponding command objects. The `RemoteControl` sets the command to either turn on or turn off the light, and then presses the button to execute the command.

By using the Command pattern, **we can decouple the sender of the request from the object that performs the action, allowing for more flexibility and extensibility.** It also provides a way to encapsulate requests as objects, which can be stored, manipulated, or queued as needed. Additionally, it allows for the implementation of undoable or redoable operations by maintaining a history of commands.

## Where Command Design Pattern should be used ?
The Command design pattern is typically used in the following scenarios:

1. Decoupling sender and receiver: The pattern allows you to decouple the object that invokes an operation (the sender) from the object that performs the actual operation (the receiver). This promotes loose coupling between objects and enhances code flexibility and maintainability.

2. Parameterizing requests: The pattern enables you to encapsulate a request as an object, allowing you to parameterize clients with different requests. This allows for the dynamic composition of commands and enables the execution of various operations without the sender needing to know the specifics of the receiver.

3. Queuing and logging requests: The pattern allows you to queue and log requests, providing the ability to implement features such as undo/redo functionality, transactional systems, and audit trails.

4. Support for undoable operations: The pattern can be used to implement undoable or redoable operations by maintaining a history of executed commands. This enables the ability to reverse or replay commands, providing a powerful mechanism for handling user interactions.

5. Multi-level menu systems and GUI actions: The pattern can be employed to handle complex menu systems and GUI actions by encapsulating each menu item or action as a command. This simplifies the implementation of user interfaces and supports extensibility.

6. Event-driven systems: The pattern is suitable for event-driven systems where events trigger specific actions or operations. Each event can be encapsulated as a command, allowing for the flexibility to handle different events and their corresponding behaviors.

Overall, the Command design pattern is valuable in situations where you want to decouple the sender and receiver of requests, parameterize and compose requests dynamically, support undo/redo operations, and enable queuing and logging of requests.

## Template Method Design Pattern with example 
**The Template Method design pattern is a behavioral pattern that defines the skeleton of an algorithm in a base class and allows subclasses to provide specific implementations for certain steps of the algorithm. It promotes code reuse and provides a way to define a common algorithm structure while allowing subclasses to customize certain parts of the algorithm.**

Here's an example of the Template Method pattern in Java:

```java
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
class Main {
    public static void main(String[] args) {
        AbstractPaymentProcessor paymentProcessor = new CreditCardPaymentProcessor();
        paymentProcessor.processPayment();

        System.out.println();

        paymentProcessor = new PayPalPaymentProcessor();
        paymentProcessor.processPayment();
    }
}
```

In this example, we have an abstract class `AbstractPaymentProcessor` that defines the template method `processPayment()`. The template method encapsulates the common payment processing algorithm, which consists of three steps: validating the payment, performing the payment, and notifying the payment.

The abstract class also declares abstract methods `validatePayment()`, `performPayment()`, and `notifyPayment()`. These methods are meant to be implemented by the concrete subclasses, allowing them to customize the specific behavior for each step of the algorithm.

The concrete subclasses `CreditCardPaymentProcessor` and `PayPalPaymentProcessor` extend the abstract class and provide their own implementations for the abstract methods. This way, each subclass can define the specific logic for validating, performing, and notifying the payment according to the payment method.

The client code demonstrates the usage of the Template Method pattern by creating instances of the concrete subclasses and calling the `processPayment()` method. The template method is executed, invoking the implemented methods in the correct sequence to perform the payment processing.

By using the Template Method pattern, we can define a common structure for payment processing while allowing subclasses to provide custom implementations for specific steps. This promotes code reuse, simplifies the implementation of related algorithms, and provides a consistent way to perform similar operations with varying behaviors.

## Mediator Design Pattern in Java

The Mediator design pattern is a behavioral pattern that promotes loose coupling between objects by encapsulating their communication through a central mediator object. It allows objects to communicate with each other without having direct references to one another, reducing dependencies and making the system more maintainable and flexible.

Here's an example of the Mediator pattern in Java:

```java
// Mediator interface
interface ChatMediator {
    void sendMessage(String message, User user);
}

// Concrete mediator class
class ChatRoom implements ChatMediator {
    public void sendMessage(String message, User user) {
        System.out.println(user.getName() + " sends message: " + message);
    }
}

// Colleague interface
interface User {
    void sendMessage(String message);
    void receiveMessage(String message);
    String getName();
}

// Concrete colleague class
class ChatUser implements User {
    private String name;
    private ChatMediator mediator;

    public ChatUser(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(name + " receives message: " + message);
    }
}

// Client code
class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoom();

        User user1 = new ChatUser("User1", mediator);
        User user2 = new ChatUser("User2", mediator);
        User user3 = new ChatUser("User3", mediator);

        user1.sendMessage("Hello everyone!");
        user2.sendMessage("Hi User1!");
        user3.sendMessage("Hey there!");

        /*
        Output:
        User1 sends message: Hello everyone!
        User2 receives message: Hello everyone!
        User3 receives message: Hello everyone!
        User2 sends message: Hi User1!
        User1 receives message: Hi User1!
        User3 receives message: Hi User1!
        User3 sends message: Hey there!
        User1 receives message: Hey there!
        User2 receives message: Hey there!
        */
    }
}
```

In this example, we have a `ChatMediator` interface that defines the `sendMessage()` method, which mediates communication between `User` objects. The concrete mediator class `ChatRoom` implements this interface and handles the actual message delivery to the appropriate users.

The `User` interface represents the colleagues in the system, defining methods for sending and receiving messages. The concrete colleague class `ChatUser` implements this interface and has a reference to the mediator. When a user sends a message, it calls the `sendMessage()` method of the mediator, which then relays the message to the other users.

The client code demonstrates the usage of the Mediator pattern by creating a mediator object (`ChatRoom`) and several user objects (`ChatUser`). The users communicate with each other by sending messages through the mediator, and the mediator handles the message distribution.

By using the Mediator pattern, we achieve loose coupling between users, as they only communicate through the mediator. This makes it easier to add new users or modify the messaging behavior without affecting the individual users. The mediator encapsulates the communication logic, reducing dependencies and promoting a more maintainable and extensible system.

## Give me another real world  example for Mediator Design Pattern

Another real-world example of the Mediator design pattern is an Air Traffic Control (ATC) system. In an ATC system, there are multiple aircraft (colleagues) communicating with each other and the air traffic controller (mediator) to ensure safe and efficient flight operations.

The ATC system works as follows:

1. Mediator (Air Traffic Controller): The mediator, the air traffic controller, is responsible for coordinating the communication between aircraft and making decisions based on the information received. The air traffic controller acts as a central point of control and manages the airspace.

2. Colleagues (Aircraft): The aircraft are the individual participants in the system. Each aircraft communicates with the air traffic controller and other aircraft through the mediator. They provide information about their current position, speed, altitude, and other relevant data.

3. Communication and Coordination: The mediator (air traffic controller) receives messages from aircraft regarding their positions, requests for takeoff or landing, changes in flight plans, etc. The mediator analyzes the information and coordinates with other aircraft to avoid collisions, ensure safe distances, and maintain the overall efficiency of air traffic.

By using the Mediator pattern in an ATC system, the communication and coordination between aircraft are centralized, and the air traffic controller can make informed decisions based on the collective information received from aircraft. This promotes safer and more efficient air traffic management.

The Mediator pattern allows for the decoupling of individual aircraft, as they only need to communicate with the mediator (air traffic controller) instead of directly interacting with each other. This reduces the complexity and dependencies between aircraft, simplifying the system's design and making it easier to add new aircraft or modify the behavior of existing ones.

Overall, the Mediator pattern is a suitable choice for systems where multiple participants need to communicate and coordinate with each other through a central entity. The ATC system is a prime example of such a scenario, where the mediator plays a crucial role in ensuring the safety and efficiency of air traffic.

## Memento Design pattern 

## Iterator design pattern 




### Resource
- https://www.digitalocean.com/community/tutorials/gangs-of-four-gof-design-patterns
- 


