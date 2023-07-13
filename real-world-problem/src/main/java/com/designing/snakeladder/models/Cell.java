package com.designing.snakeladder.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cell implements Comparable<Cell>{
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
    @Override
    public String toString() {
        return String.format("cell: %s", getCellNumer());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return cellNumer == cell.cellNumer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellNumer);
    }
    @Override
    public int compareTo(Cell that) {
        return Integer.compare(this.cellNumer, that.cellNumer);
    }

}
