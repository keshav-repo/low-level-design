package com.designing.snakeladder.models;

public class Player {
    private Cell currentPos;
    private String name;
    public Player(Cell pos, String name){
        this.currentPos = pos;
        this.name = name;
    }
    public Cell moveForward(int moveBy){
        return this.currentPos.moveForward(moveBy);
    }

    public Cell getPos() {
        return currentPos;
    }

    public Cell setPos(Cell cell){
        this.currentPos = cell;
        return this.currentPos;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return String.format("%s position: %s ", name, currentPos);
    }
}
