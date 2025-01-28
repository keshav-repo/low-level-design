package com.example;

public class Square {
    private int size;

    public Square(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int area() {
        return size * size;
    }

    public int perimeter() {
        return 4 * size;
    }
}

class SquareGraph{
    public void draw(Square square) {
        int size = square.getSize();
        System.out.println("Drawing square of size " + size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public void rotate(Square square, int degrees) {
        System.out.println("Rotating square of size " + square.getSize() + " by " + degrees + " degrees.");
    }
}