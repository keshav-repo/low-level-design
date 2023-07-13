package com.designing.snakeladder;

import com.designing.snakeladder.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest_MockedDice {
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
                List.of(new Player(Cell.valueOf(0), "Player I"),
                        new Player(Cell.valueOf(0), "Player II")
                )
        );
        snakeList = List.of(
                new Jump(Cell.valueOf(42), Cell.valueOf(15))
        );
        ladderList = List.of(
                new Jump(Cell.valueOf(36), Cell.valueOf(79))
        );
        board = new Board(v, snakeList, ladderList);
        dice = new MockedDice();

        game = new Game(board, playerList, dice);
    }
    @Test
    public void playGame() {
        game.playGame();
        System.out.println(game.getWinner());
        assertEquals(game.getWinner().getName(), "Player I");
    }
}
