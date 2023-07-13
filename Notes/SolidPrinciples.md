# Solid Principles

- S - Single-responsiblity Principle
- O - Open-closed Principle
- L - Liskov Substitution Principle
- I - Interface Segregation Principle
- D - Dependency Inversion Principle

### Single-responsiblity Principle (S)
**A class should have one and only one reason to change, meaning that a class should have only one job.**

According to the SRP, a class should have a single responsibility or purpose and should encapsulate a single functionality or concern. In other words, a class should have only one primary responsibility, and any changes to that responsibility should not affect other unrelated parts of the system.

The key benefits of adhering to the Single Responsibility Principle include:

1. Separation of Concerns: By assigning a single responsibility to a class, it becomes easier to understand, maintain, and modify. Each class focuses on one specific functionality, making the code more modular and easier to reason about.

2. Increased Reusability: When classes have clear and well-defined responsibilities, they become more reusable in different contexts. Reusing a class that has a single responsibility is often simpler and less error-prone compared to reusing a class that has multiple responsibilities.

3. Improved Testability: Classes with single responsibilities are generally easier to test. The smaller the scope of responsibility, the more focused and targeted the tests can be, leading to better test coverage and more reliable tests.

4. Reduced Code Fragility: When a class has multiple responsibilities, changes to one responsibility can inadvertently affect other parts of the codebase. By adhering to SRP, changes to a specific responsibility are isolated, reducing the risk of introducing unintended side effects and making the codebase more maintainable.

To adhere to SRP, it's important to identify the distinct responsibilities within a system, and when a class starts to have multiple responsibilities, consider refactoring it into smaller, more focused classes. Applying SRP helps in achieving better code organization, maintainability, and flexibility in the long run.

### Example of single responsibility principle:
The Single Responsibility Principle (SRP) states that a class should have only one reason to change. In other words, a class should have a single responsibility or purpose. This principle encourages designing classes that are focused and have a clear and well-defined responsibility.

Let's consider an example of a `FileLogger` class in Java that adheres to the Single Responsibility Principle. The `FileLogger` class is responsible for logging messages to a file.

```java
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger {
    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    public void log(String message) {
        String formattedMessage = formatMessage(message);
        writeToFile(formattedMessage);
    }

    private String formatMessage(String message) {
        LocalDateTime timestamp = LocalDateTime.now();
        return "[" + timestamp.toString() + "] " + message;
    }

    private void writeToFile(String message) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
```

In this example, the `FileLogger` class has a single responsibility, which is to log messages to a file. It provides a `log()` method that takes a message as input, formats it with a timestamp, and writes it to the specified file.

By keeping the logging functionality in a separate class, we adhere to the Single Responsibility Principle. If we need to change the logging mechanism or add additional logging capabilities, we only need to modify the `FileLogger` class, keeping the changes localized to the relevant functionality.

On the other hand, if the `FileLogger` class had additional responsibilities, such as handling the formatting of messages, managing log levels, or sending log entries to a remote server, it would violate the Single Responsibility Principle. In such cases, it would be beneficial to separate these responsibilities into separate classes to achieve better separation of concerns and maintainability.

Following the Single Responsibility Principle helps to create more modular and maintainable code. It improves code readability, reusability, and testability by ensuring that each class has a clear and focused purpose, reducing the likelihood of unintended side effects and making the code easier to understand and maintain.

### If we have to handle the formatting of messages, how to design classes adhering to single responsibility principle ?
If we need to handle the formatting of messages in addition to logging them, we can adhere to the Single Responsibility Principle by separating these responsibilities into two separate classes: one for message formatting and another for logging. This separation ensures that each class has a single responsibility and is focused on its specific task.

Let's refactor the previous example to demonstrate this:

```java
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MessageFormatter {
    public String format(String message) {
        LocalDateTime timestamp = LocalDateTime.now();
        return "[" + timestamp.toString() + "] " + message;
    }
}

public class FileLogger {
    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    public void log(String message) {
        writeToFile(message);
    }

    private void writeToFile(String message) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
```

In this refactored example, we have introduced a new class called `MessageFormatter` responsible for formatting messages. It contains a `format()` method that takes a message as input and returns the formatted message with a timestamp.

The `FileLogger` class now focuses solely on logging the messages. It receives a message, passes it to the `MessageFormatter` for formatting, and then writes the formatted message to the specified file.

By separating the responsibilities of formatting and logging into distinct classes (`MessageFormatter` and `FileLogger`, respectively), we adhere to the Single Responsibility Principle. Each class now has a single responsibility and is focused on performing its specific task.

This separation allows for better maintainability, as changes related to formatting can be made in the `MessageFormatter` class, while changes related to logging can be made in the `FileLogger` class. The classes are decoupled and can evolve independently, promoting code reusability and making the system more flexible.

Applying the Single Responsibility Principle in this manner leads to more modular and maintainable code, as each class has a clear purpose and is responsible for a specific part of the overall functionality.

### Open-closed Principle
Objects or entities should be open for extension but closed for modification.

The Open-Closed Principle (OCP) is a fundamental principle in object-oriented programming (OOP) that states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This principle encourages designing software in a way that allows adding new functionality without changing the existing code.

To illustrate the Open-Closed Principle in Java, let's consider an example of a basic shape hierarchy. We have a `Shape` superclass and two subclasses, `Circle` and `Rectangle`, each implementing their own `area()` method. We want to extend this hierarchy to include a new shape, `Triangle`, without modifying the existing code for `Shape`, `Circle`, or `Rectangle`.

```java
// Shape.java
public abstract class Shape {
    public abstract double area();
}

// Circle.java
public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

// Rectangle.java
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}

// Triangle.java (new shape)
public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double area() {
        return 0.5 * base * height;
    }
}

// Usage example
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        System.out.println("Circle area: " + circle.area());

        Shape rectangle = new Rectangle(4, 6);
        System.out.println("Rectangle area: " + rectangle.area());

        Shape triangle = new Triangle(3, 8);
        System.out.println("Triangle area: " + triangle.area());
    }
}
```

In this example, the `Shape` class is open for extension, as we can add new shapes like `Triangle` without modifying its code. Each shape subclass provides its own implementation of the `area()` method, encapsulating the calculation logic specific to that shape. This way, we can add new shapes to the system without modifying the existing code, achieving the goal of the Open-Closed Principle.

The advantage of adhering to the Open-Closed Principle is that it promotes code reusability, maintainability, and extensibility. It allows for easy addition of new functionality while minimizing the risk of introducing bugs or unintended side effects in existing code.

#### adding will violate Open-closed Principle ?
```java
public abstract double perimeter()
```
Yes, adding a `perimeter()` method to the `Shape` class would violate the Open-Closed Principle. According to the principle, software entities should be closed for modification, which means that once a class is implemented and tested, it should not be modified to add new behavior.

Instead of modifying the existing `Shape` class, a better approach would be to create a new interface or abstract class that represents shapes with a perimeter. This way, the existing code remains unchanged, and the new functionality can be added through extension.

Here's an example of how you can refactor the code to adhere to the Open-Closed Principle:

```java
// Shape.java
public abstract class Shape {
    public abstract double area();
}

// ShapeWithPerimeter.java
public abstract class ShapeWithPerimeter extends Shape {
    public abstract double perimeter();
}

// Circle.java
public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

// Rectangle.java
public class Rectangle extends ShapeWithPerimeter {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }
}

// Triangle.java
public class Triangle extends ShapeWithPerimeter {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double area() {
        // Implementation of area calculation for triangles
        // ...
    }

    public double perimeter() {
        return side1 + side2 + side3;
    }
}

// Usage example
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        System.out.println("Circle area: " + circle.area());

        ShapeWithPerimeter rectangle = new Rectangle(4, 6);
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Rectangle perimeter: " + rectangle.perimeter());

        ShapeWithPerimeter triangle = new Triangle(3, 4, 5);
        System.out.println("Triangle area: " + triangle.area());
        System.out.println("Triangle perimeter: " + triangle.perimeter());
    }
}
```

In this refactored example, a new abstract class `ShapeWithPerimeter` is introduced to represent shapes with a perimeter. The `Rectangle` and `Triangle` classes extend `ShapeWithPerimeter` instead of directly extending `Shape`, allowing them to provide an implementation for the `perimeter()` method specific to their shape.

By using inheritance and creating new specialized classes for shapes with a perimeter, we can add the desired functionality without modifying the existing `Shape` class, thereby adhering to the Open-Closed Principle.

### Liskov Substitution Principle
The Liskov Substitution Principle (LSP) is a principle in object-oriented programming that defines a contract between a base class and its derived classes, ensuring that **objects of derived classes can be substituted for objects of the base class without affecting the correctness of the program**. In other words, the principle states that objects of a superclass should be replaceable with objects of its subclasses without breaking the behavior of the system.

To illustrate the Liskov Substitution Principle in Java, let's consider an example involving a `Shape` hierarchy. We have a base `Shape` class with a method `getArea()` that calculates the area of the shape. We also have two subclasses, `Rectangle` and `Square`, which inherit from `Shape`.

```java
// Shape.java
public abstract class Shape {
    public abstract double getArea();
}

// Rectangle.java
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}

// Square.java
public class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getArea() {
        return side * side;
    }
}
```

According to the Liskov Substitution Principle, we should be able to use objects of the `Rectangle` and `Square` classes interchangeably with objects of the `Shape` class without affecting the correctness of the program. For example:

```java
public class Main {
    public static void printArea(Shape shape) {
        double area = shape.getArea();
        System.out.println("Area: " + area);
    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4, 6);
        printArea(rectangle);  // Output: Area: 24

        Shape square = new Square(5);
        printArea(square);  // Output: Area: 25
    }
}
```

In this example, we have a `printArea()` method that takes an object of the `Shape` class as a parameter and calculates and prints the area of the shape. We can pass objects of both `Rectangle` and `Square` classes to this method, as they inherit from `Shape` and provide their own implementation of the `getArea()` method.

The Liskov Substitution Principle is upheld in this example because we can substitute objects of `Rectangle` and `Square` for objects of `Shape` without changing the behavior of the `printArea()` method. This principle allows for polymorphism and code reuse, as we can treat objects of derived classes as objects of the base class, enabling flexibility and extensibility in our code.

By adhering to the Liskov Substitution Principle, we ensure that our object-oriented designs are more robust and maintainable, as objects can be replaced with their subtypes seamlessly, enabling polymorphism and reducing dependencies on specific implementations.

### Interface Segregation Principle
The Interface Segregation Principle (ISP) states that clients should not be forced to depend on interfaces they do not use. **It emphasizes creating specific interfaces tailored to the needs of clients, rather than having a single, monolithic interface that encompasses all possible behaviors.** By following this principle, we can avoid unnecessary dependencies and minimize the impact of changes in the system.

Let's consider an example where we have a `Printer` interface that defines various print-related methods. However, different clients have different requirements and might not need all the methods provided by the `Printer` interface. To adhere to the Interface Segregation Principle, we can break down the interface into smaller, more focused interfaces based on client needs.

```java
// Printer.java
public interface Printer {
    void print();
    void scan();
    void fax();
}

// PrintClient.java
public class PrintClient {
    private Printer printer;

    public PrintClient(Printer printer) {
        this.printer = printer;
    }

    public void printDocument() {
        printer.print();
    }
}

// ScanClient.java
public class ScanClient {
    private Printer printer;

    public ScanClient(Printer printer) {
        this.printer = printer;
    }

    public void scanDocument() {
        printer.scan();
    }
}

// FaxClient.java
public class FaxClient {
    private Printer printer;

    public FaxClient(Printer printer) {
        this.printer = printer;
    }

    public void sendFax() {
        printer.fax();
    }
}
```

In this example, we initially have a single `Printer` interface that includes `print()`, `scan()`, and `fax()` methods. However, different clients have different needs and may not require all these methods. To adhere to the Interface Segregation Principle, we can refactor the interface into smaller interfaces based on client requirements:

```java
// Printable.java
public interface Printable {
    void print();
}

// Scanable.java
public interface Scanable {
    void scan();
}

// Faxable.java
public interface Faxable {
    void fax();
}

// Printer.java implements Printable, Scanable, Faxable
public class Printer implements Printable, Scanable, Faxable {
    public void print() {
        // Implementation for printing
    }

    public void scan() {
        // Implementation for scanning
    }

    public void fax() {
        // Implementation for faxing
    }
}
```

Now, we have separate interfaces `Printable`, `Scanable`, and `Faxable`, each with a single method corresponding to the specific functionality. The `Printer` class implements all three interfaces, providing implementations for each method.

The clients (`PrintClient`, `ScanClient`, and `FaxClient`) are now able to depend on the interfaces that match their specific needs. This allows each client to utilize only the required methods without being forced to depend on unnecessary methods.

By following the Interface Segregation Principle, we achieve a more flexible and modular design. If new clients or functionality are added in the future, we can create additional interfaces tailored to their needs without affecting the existing interfaces or clients. This promotes better code organization, reduces unnecessary dependencies, and makes the system more maintainable.

###  Dependency Inversion Principle
The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules; both should depend on abstractions. It also states that abstractions should not depend on details; details should depend on abstractions. This principle promotes loose coupling, modularity, and easier maintainability.

To illustrate the Dependency Inversion Principle in Java, let's consider an example involving a `NotificationService` that sends notifications. Initially, the `NotificationService` depends directly on a specific implementation, `EmailSender`, to send email notifications. However, by applying the Dependency Inversion Principle, we can introduce an abstraction and invert the dependency.

```java
// EmailSender.java (Low-level module)
public class EmailSender {
    public void sendEmail(String recipient, String message) {
        // Code to send email
    }
}

// NotificationService.java (High-level module)
public class NotificationService {
    private EmailSender emailSender;

    public NotificationService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendNotification(String recipient, String message) {
        emailSender.sendEmail(recipient, message);
    }
}
```

In the initial implementation, the `NotificationService` directly depends on the `EmailSender` class. This tight coupling makes the `NotificationService` less flexible and difficult to replace or extend with other notification mechanisms.

By applying the Dependency Inversion Principle, we can introduce an abstraction, such as an `NotificationSender` interface, and modify the `NotificationService` to depend on the abstraction rather than the concrete implementation:

```java
// NotificationSender.java (Abstraction)
public interface NotificationSender {
    void sendNotification(String recipient, String message);
}

// EmailSender.java (Low-level module)
public class EmailSender implements NotificationSender {
    public void sendNotification(String recipient, String message) {
        // Code to send email
    }
}

// NotificationService.java (High-level module)
public class NotificationService {
    private NotificationSender notificationSender;

    public NotificationService(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void sendNotification(String recipient, String message) {
        notificationSender.sendNotification(recipient, message);
    }
}
```

In the refactored example, we introduce the `NotificationSender` interface, which serves as the abstraction. The `EmailSender` class now implements the `NotificationSender` interface and provides its own implementation for sending notifications via email.

The `NotificationService` class has been modified to depend on the `NotificationSender` interface instead of the `EmailSender` class directly. This allows the `NotificationService` to send notifications without being tightly coupled to a specific implementation. Now, we can easily substitute different implementations of the `NotificationSender` interface, such as a `SMSNotificationSender` or `PushNotificationSender`, without modifying the `NotificationService` class.

Applying the Dependency Inversion Principle allows for decoupling of components, improves modularity, and makes the system more flexible and maintainable. High-level modules can depend on abstractions, making it easier to substitute or extend functionality without impacting the existing code.




### References
- chatgpt

