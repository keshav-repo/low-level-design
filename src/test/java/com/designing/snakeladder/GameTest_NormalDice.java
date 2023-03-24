package com.designing.snakeladder;

import com.designing.snakeladder.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest_NormalDice {
    private Game game;
    Board board;
    LinkedList<Player> playerList;
    List<Jump> snakeList;
    List<Jump> ladderList;
    Dice dice;

    @BeforeEach
    public void setup() {
        int v = 10;
        playerList = new LinkedList<>(
                List.of(
                        new Player(Cell.valueOf(0), "Player I"),
                        new Player(Cell.valueOf(0), "Player II"),
                        new Player(Cell.valueOf(0), "Player III")
                )
        );
        snakeList = List.of(
                new Jump(Cell.valueOf(76), Cell.valueOf(32)),
                new Jump(Cell.valueOf(28), Cell.valueOf(10)),
                new Jump(Cell.valueOf(96), Cell.valueOf(42))
        );
        ladderList = List.of(
                new Jump(Cell.valueOf(4), Cell.valueOf(56)),
                new Jump(Cell.valueOf(54), Cell.valueOf(88)),
                new Jump(Cell.valueOf(41), Cell.valueOf(79))
        );
        board = new Board(v, snakeList, ladderList);
        dice = new NormalDice();

        game = new Game(board, playerList, dice);
    }
    @Test
    public void playGame() {
        game.playGame();
        System.out.println("WINNER PLAYER");
        System.out.println(game.getWinner());
    }
}
