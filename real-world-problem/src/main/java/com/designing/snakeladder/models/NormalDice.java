package com.designing.snakeladder.models;

import java.util.Random;

public class NormalDice implements Dice{

    Random random = new Random();
    @Override
    public int rollDice() {
        return 1+random.nextInt(6);
    }
}
