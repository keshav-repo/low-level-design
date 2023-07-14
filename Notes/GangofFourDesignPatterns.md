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

2. 

