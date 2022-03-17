package com.company;

import processing.core.PApplet;

public class TicTacToeProcessing extends PApplet {
    public static void main(String[] args) {
        String[] appArgs = {"TicTacToeProcessing"};
        PApplet.runSketch(appArgs, new TicTacToeProcessing());
    }
    public static int blockSize = 200;
    TicTacToeEngine gameEngine = new TicTacToeEngine();
    ToeAI toeAI;
    boolean waitForReset = false;
    boolean makeAIMove = false;
    int timerTrigger;

    public void settings() {
        size(3 * blockSize, 3 * blockSize);
    }

    public void setup() {
        gameEngine = new TicTacToeEngine();
        toeAI = new ToeAI(gameEngine, gameEngine.getCurrentPlayer() + 1);
        toeAI.makeCalculatedMove(gameEngine);
        drawBackground();
    }

    public void mousePressed() {
        int x = mouseX / blockSize;
        int y = mouseY / blockSize;
        if (waitForReset) {
            waitForReset = false;
            toeAI.makeCalculatedMove(gameEngine);
            drawBackground();
        } else if (!makeAIMove) {
            gameEngine.placeSymbol(y, x);
            drawBackground();
            makeAIMove = true;
            waitTime(800);
            checkPlay();
        }
    }

    public void checkPlay() {
        // returns false if game is over
        drawBackground();
        if (gameEngine.isGameOver()) {
            drawBackground();
            gameEngine.newGame();
            waitForReset = true;
            textSize(100);
            fill(0, 102, 153);
            textAlign(CENTER);
            text(gameEngine.getSymbol() + " won!", 1.5F * blockSize, 1.7F * blockSize);
        }
        if (gameEngine.isFull()) {
            drawBackground();
            System.out.println("Game Over!");
            gameEngine.newGame();
            waitForReset = true;
            textSize(80);
            fill(0, 102, 153);
            textAlign(CENTER);
            text("no one won", 1.5F * blockSize, 1.7F * blockSize);
        }
    }

    public void drawBackground() {
        background(color(255, 255, 255));
        // creates horizontal lines for visual orientation
        for (int i = 1; i < 3; i++) {
            strokeWeight(2);
            line(blockSize * i, 0, blockSize * i, blockSize * height);
        }
        // creates vertical lines for visual orientation
        for (int i = 1; i < 3; i++) {
            strokeWeight(2);
            line(0, blockSize * i, blockSize * height , blockSize * i);
        }
        int[][] board = gameEngine.getBoard();
        for (int row = 0; row < 3; row++) {
            for (int collumn = 0; collumn < 3; collumn++) {
                drawShape(board[row][collumn], row, collumn);
            }
        }
    }

    void drawShape(int shape, int row, int collumn) {
        if (shape == 0) {
            int xCoordinates = collumn * blockSize + (blockSize / 2);
            int yCoordinates = row * blockSize + (blockSize / 2);
            strokeWeight(5);
            fill(255, 255, 255);
            circle(xCoordinates, yCoordinates, blockSize - 20);
        } else if (shape == 1) {
            strokeWeight(5);
            line (collumn  * blockSize + 20, row  * blockSize + 20 , collumn * blockSize + blockSize - 20, row * blockSize + blockSize - 20);
            line (collumn  * blockSize + 20, row  * blockSize + blockSize - 20 , collumn * blockSize + blockSize - 20, row * blockSize + 20);
        }
    }

    public void waitTime(int milliseconds) {
        timerTrigger = millis() + milliseconds;
    }

    public void draw() {
        if (makeAIMove && millis() >= timerTrigger) {
            toeAI.makeCalculatedMove(gameEngine);
            makeAIMove = false;
            checkPlay();
        }

    }
}

