package com.company;

import processing.core.PApplet;

import java.util.Arrays;

public class TicTacToeEngine extends PApplet {
    private int[][] board;
    public int currentPlayer = 0;

    public void newGame() {
        board = new int[3][3]; // default values are -1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = -1;
            }
        }
    }

    public TicTacToeEngine newGame(int[][] board, int currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        return this;
    }

    TicTacToeEngine(){
        newGame();
    }

    int[][] getBoard() {
        int[][] tempBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }
        return tempBoard;
    }

    int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean placeSymbol(int row, int collumn) {
        // returns false if there was an error
        if (board[row][collumn] != -1) {
            System.out.println("Spot is already assigned, try again");
            return false;
        }
        board[row][collumn] = currentPlayer;
        currentPlayer = (currentPlayer + 1) % 2;
        return true;
    }

    public char getSymbol() {
        if (currentPlayer == 1) {
            return 'O';
        } else {
            return 'X';
        }
    }

    public boolean isGameOver() {
        // check row
        for (int[] row : board) {
            int itemCounter = 0;
                int symbolToCheckFor = row[0];
            if (symbolToCheckFor != -1) {
                for (int item : row) {
                    if (item == symbolToCheckFor) {
                        itemCounter += 1;
                    }
                }
            }
            if (itemCounter >= 3) {
                return true;
            }
        }
        // check collumn
        for (int collumn = 0; collumn < 3; collumn++) {
            int itemCounter = 0;
            int symbolToCheckFor = board[0][collumn];
            if (symbolToCheckFor != -1) {
                for (int row = 0; row < 3; row++) {
                    if (board[row][collumn] == symbolToCheckFor) {
                        itemCounter += 1;
                    }
                }
                if (itemCounter >= 3) {
                    return true;
                }
            }
        }
        // check diagonal
        int symbolToCheckFor = board[1][1];
        if (symbolToCheckFor == -1) {
            return false;
        }
        if (board[0][2] == symbolToCheckFor) {
            if (board[2][0] == symbolToCheckFor) {
                return true;
            }
        }
        if (board[0][0] == symbolToCheckFor) {
            if (board[2][2] == symbolToCheckFor) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int[] row : board) {
            for (int item : row) {
                if (item == -1){
                    return false;
                }
            }
        }
        return true;
    }

    public TicTacToeEngine makeClone() {
        TicTacToeEngine cloneObject = new TicTacToeEngine();
        cloneObject.newGame(this.getBoard(), this.getCurrentPlayer());
        return cloneObject;
    }


    @Override
    public String toString() {
        String output = "";
        for (int[] row : board) {
            for (int item : row) {
                if (item == 0) {
                    output += "O";
                }
                else if (item == 1) {
                    output += "X";
                } else {
                    output += "#";
                }
            }
            output += "\n";
        }
        return output;
    }
}
