package com.designing.snakeladder.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
https://workat.tech/machine-coding/practice/snake-and-ladder-problem-zgtac9lxwntg
 */
public class GameDriver {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/java/com/designing/snakeladder/models/input.txt"));


        LinkedList playerList = new LinkedList<>();
        List<Jump> snakeList = new LinkedList<>();
        List<Jump> ladderList = new LinkedList<>();

        int snakeCount = Integer.parseInt(scanner.nextLine());
        while (snakeCount-- > 0) {
            String snakeLine = scanner.nextLine();
            String[] snakePos = snakeLine.split(" ");
            Cell headPos = Cell.valueOf(Integer.parseInt(snakePos[0]));
            Cell tailPos =  Cell.valueOf(Integer.parseInt(snakePos[1]));
            snakeList.add(new Jump( headPos, tailPos ));
        }

        int ladderCount = Integer.parseInt(scanner.nextLine());
        while (ladderCount-- > 0) {
            String ladderLine = scanner.nextLine();
            String[] ladderPos = ladderLine.split(" ");
            Cell ladderStart = Cell.valueOf(Integer.parseInt(ladderPos[0]));
            Cell ladderEnd =  Cell.valueOf(Integer.parseInt(ladderPos[1]));
            snakeList.add(new Jump(ladderStart, ladderEnd ));
        }

        int playerCount = Integer.parseInt(scanner.nextLine());
        while (playerCount-- >0){
            String playerName = scanner.nextLine();
            Player player = new Player(Cell.valueOf(0), playerName);
            playerList.add(player);
        }


        Dice dice = new NormalDice();

        Board board = new Board(10, snakeList, ladderList);

        Game game = new Game(board, playerList, dice);

        game.playGame();

        System.out.println(game.getWinner().getName());
    }

}
