package designPattern.bridgepattern;

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
public class ColorShapeBridge {
    public static void main(String[] args) {
        Color redColor = new Red();
        Color blueColor = new Blue();

        Shape circle = new Circle(redColor);
        Shape square = new Square(blueColor);

        circle.draw();
        square.draw();
    }
}