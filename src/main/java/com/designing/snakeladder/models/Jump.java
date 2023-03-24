package com.designing.snakeladder.models;

public  class Jump {
    private Cell startPos;
    private Cell endPos;
    public Jump(Cell startPos, Cell endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }
    public Cell getStartPos() {
        return startPos;
    }
    public Cell getEndPos() {
        return endPos;
    }
}
