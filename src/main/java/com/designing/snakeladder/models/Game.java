package com.designing.snakeladder.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> playerList;
    private List<Jump> snakeList;
    private List<Jump> ladderList;
    private Dice dice;
    private int v;

    public Game(Board board, LinkedList<Player> playerList, List<Jump> snakeList, List<Jump> ladderList, Dice dice) {
        this.board = board;
        this.playerList = playerList;
        this.snakeList = snakeList;
        this.ladderList = ladderList;
        this.dice = dice;
    }

    public void addPlayerList(Player player) {
        this.playerList.add(player);
    }

    // move the player
    public Cell movePlayer(Player player) {
        // get rolling score
        int score = dice.rollDice();

        // move the position of player
        Cell playerCurrPos = player.moveForward(score);

        // move the player if snake or ladder is present
        if (board.ifSnakeOrLadderPresent(playerCurrPos)) {
            playerCurrPos = board.bitOrClimb(playerCurrPos);
        }
        return playerCurrPos;
    }

}
