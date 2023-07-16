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

The Builder Design Pattern is a creational design pattern that provides a flexible way to construct complex objects step by step. 

The main idea behind the Builder pattern is to abstract the construction process of an object so that the client code can specify the desired configuration without needing to know the internal details of the object's construction







