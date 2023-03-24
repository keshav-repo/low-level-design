package com.designing.snakeladder.models;

import javax.swing.plaf.PanelUI;
import java.util.*;

public class Board {
    private List<Cell> cellList;
    private List<Jump> snakeList;
    private List<Jump> ladderList;
    Map<Integer, Jump> snakeLadderMap ;
    private int V;
    public Board(int V, List<Jump> snakeList, List<Jump> ladderList) {
        this.V = V;
        this.ladderList = ladderList;
        this.snakeList = snakeList;
        // construct cells
        this.cellList = new LinkedList<>();
        initiateBoard();
    }

    public int getV() {
        return V;
    }

    private void initiateBoard() {
        for(int i=1; i<=V*V; i++){
            cellList.add(Cell.valueOf(i));
        }
        snakeLadderMap = new HashMap<>();
        this.snakeList.forEach(snake->{
            addSnake(snake);
        });
        this.ladderList.forEach(ladder->{
            addLadder(ladder);
        });
    }
    public List<Cell> getCellList() {
        return cellList;
    }

    private void addSnake( Jump snake){
        snakeLadderMap.put(snake.getStartPos().getCellNumer(), snake);
    }

    private void addLadder( Jump ladder){
        snakeLadderMap.put(ladder.getStartPos().getCellNumer(), ladder);
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

    public JumpType checkJumpType(Cell cell){
        Jump jump = snakeLadderMap.get(cell.getCellNumer());
        if( jump.getStartPos().compareTo(jump.getEndPos()) < 0 ){
            return JumpType.LADDER;
        }else {
            return JumpType.SNAKE;
        }
    }

    // bitting snake action or climb ladder
    public Cell bitOrClimb(Cell cell){
        Jump jump = snakeLadderMap.get(cell.getCellNumer());
        return jump.getEndPos();
    }
}
