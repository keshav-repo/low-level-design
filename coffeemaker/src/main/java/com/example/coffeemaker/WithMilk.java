package com.example.coffeemaker;

public class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee coffeeDecorator) {
        super(coffeeDecorator);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", With milk";
    }

    @Override
    public double cost() {
        return coffeeDecorator.cost() + 10;
    }
}
