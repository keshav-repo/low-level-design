package com.example.coffeemaker;

public abstract class CoffeeDecorator implements Coffee{
    protected final Coffee coffeeDecorator;

    public CoffeeDecorator(Coffee coffeeDecorator) {
        this.coffeeDecorator = coffeeDecorator;
    }

    @Override
    public String getDescription() {
        return coffeeDecorator.getDescription();
    }
}
