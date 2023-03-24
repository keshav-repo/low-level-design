package com.designing.snakeladder;

import com.designing.snakeladder.models.*;

import java.util.LinkedList;
import java.util.List;

public class GameTest {

    private Game game;
    Board board;
    LinkedList<Player> playerList;
    List<Jump> snakeList;
    List<Jump> ladderList;
    Dice dice;

    public GameTest() {
        int v = 10;
        board = new Board(v);


     //     this.game = new Game();
    }
}
