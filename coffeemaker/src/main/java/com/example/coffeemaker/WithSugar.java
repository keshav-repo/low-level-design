package com.example.coffeemaker;

public class WithSugar extends CoffeeDecorator{
    public WithSugar(Coffee coffeeDecorator) {
        super(coffeeDecorator);
    }

    @Override
    public String getDescription() {
        return coffeeDecorator.getDescription()+",With sugar";
    }

    @Override
    public double cost() {
        return coffeeDecorator.cost()+5;
    }
}
