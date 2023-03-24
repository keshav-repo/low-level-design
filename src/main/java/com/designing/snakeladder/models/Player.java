package com.designing.snakeladder.models;

public class Player {
    private Cell currentPos;
    public Player(Cell pos){
        this.currentPos = pos;
    }
    public Cell moveForward(int moveBy){
        return this.currentPos.moveForward(moveBy);
    }

    public Cell moveBackWard(int backBy){
        return this.currentPos.moveBackward(backBy);
    }

    public Cell getPos() {
        return currentPos;
    }

    public Cell setPos(Cell cell){
        this.currentPos = cell;
        return this.currentPos;
    }

}
