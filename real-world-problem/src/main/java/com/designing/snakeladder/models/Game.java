package com.designing.snakeladder.models;

import com.designing.bookmyshow.model.Theatre;
import com.designing.snakeladder.exception.CellNotFoundException;
import com.designing.snakeladder.exception.GameNotStarted;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> playerList;
    private Dice dice;
    private int winningCellNumber;
    Player winner;

    public Game(Board board, LinkedList<Player> playerList, Dice dice) {
        this.board = board;
        this.playerList = playerList;
        this.dice = dice;
        this.winningCellNumber = (int) Math.pow(this.board.getV(), 2);
    }

    private void checkValidPosition(Cell playerMovedPosition, Player player) {
        if (playerMovedPosition.getCellNumer() > winningCellNumber || playerMovedPosition.getCellNumer() < 0) {
            throw new CellNotFoundException(String.format("Exception: %s tried to move at %d",player.getName(), playerMovedPosition.getCellNumer()));
        }
    }

    public void playGame() {

        while (Objects.isNull(winner)) {
            Player player = playerList.poll();

            int score = dice.rollDice();

            // move the position of player
            Cell playerCurrPos = player.moveForward(score);

            playerList.offer(player);

            try{
                checkValidPosition(playerCurrPos, player);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
                continue;
            }
            // after checking the validity of position, printing the place
            System.out.println(String.format("%s rolled at %d and move from %d to %d",player.getName(), score, player.getPos().getCellNumer(), playerCurrPos.getCellNumer()  ) );

            // move the player if snake or ladder is present
            if (board.ifSnakeOrLadderPresent(playerCurrPos)) {
                JumpType jumpType = board.checkJumpType(playerCurrPos);
                String log = String.format("%s moved %s from %d to", jumpType.toString(),  player.getName(), playerCurrPos.getCellNumer());
                playerCurrPos = board.bitOrClimb(playerCurrPos);
                System.out.println(log.format("%s %d",log, playerCurrPos.getCellNumer()  ) );
            }

            player.setPos(playerCurrPos);


            if (player.getPos().equals(Cell.valueOf(winningCellNumber))) {
                winner = player;
            }

            // to give some pause in execution
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException interruptedException) {
//                System.out.println("Interrupted exception");
//            }
        }
    }

    public Player getWinner() {
        if (Objects.isNull(winner)) {
            throw new GameNotStarted("Game not started");
        }
        return winner;
    }
}
