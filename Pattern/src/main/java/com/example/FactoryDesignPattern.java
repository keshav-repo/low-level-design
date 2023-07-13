package com.example;
import java.lang.reflect.InvocationTargetException;


 interface Shape {
    void draw();
}
 class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

 class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// old style
class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}


 class ShapeFactoryDynamic {
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }

        try {
            Class<?> shapeClass = Class.forName(shapeType);
            Object shapeObject = shapeClass.getDeclaredConstructor().newInstance();

            if (shapeObject instanceof Shape) {
                return (Shape) shapeObject;
            } else {
                throw new IllegalArgumentException("Invalid shape type: " + shapeType);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class FactoryDesignPattern {
    public static void main(String[] args) {
        Shape hexagon = ShapeFactoryDynamic.getShape("com.example.Rectangle");
        hexagon.draw();
    }
}
