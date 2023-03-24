package com.designing.snakeladder.models;

import java.util.HashMap;
import java.util.Map;

public class Cell {
    private int cellNumer;
    static Map<Integer, Cell> cache = new HashMap<>();

    private Cell(int cellNumer) {
        this.cellNumer = cellNumer;
    }

    public int getCellNumer() {
        return cellNumer;
    }

    public Cell moveForward(int moveBy) {
        return valueOf(cellNumer + moveBy);
    }

    public Cell moveBackward(int backBy) {
        return valueOf(cellNumer - backBy);
    }

    public static Cell valueOf(int cellNumer) {
        return cache.containsKey(cellNumer) ? cache.get(cellNumer) : new Cell(cellNumer);
    }
}
