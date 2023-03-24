package com.designing.snakeladder.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board {
    private List<Cell> cellList;
    private List<Jump> snakeList;
    private List<Jump> ladderList;
    Map<Integer, Jump> snakeLadderMap ;
    public Board(int V) {
        this.ladderList = new LinkedList<>();
        this.snakeList = new LinkedList<>();
        // construct cells
        this.cellList = new LinkedList<>();
        for(int i=1; i<=V*V; i++){
            cellList.add(Cell.valueOf(i));
        }
    }
    public List<Cell> getCellList() {
        return cellList;
    }

    public void addSnake(Cell snakeMouthCell, Jump snake){
        snakeLadderMap.put(snakeMouthCell.getCellNumer(), snake);
        this.snakeList.add(snake);
    }

    public void addLadder(Cell ladderBottonCell, Jump ladder){
        snakeLadderMap.put(ladderBottonCell.getCellNumer(), ladder);
        this.ladderList.add(ladder);
    }

    public Cell moveForward(Cell cell, int moveBy){
       return this.cellList.get(cell.getCellNumer()+moveBy-1);
    }

    public Cell moveBackward(Cell cell, int backBy){
        return this.cellList.get(cell.getCellNumer()-backBy-1);
    }

    public boolean ifSnakeOrLadderPresent(Cell cell){
        return snakeLadderMap.containsKey(cell.getCellNumer());
    }

    // bitting snake action or climb ladder
    public Cell bitOrClimb(Cell cell){
        Jump jump = snakeLadderMap.get(cell.getCellNumer());
        return jump.getEndPos();
    }
}
