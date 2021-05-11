package com.company;

import java.util.Scanner;

public class CommandlineGame {
    private static int fibonacci(int a){
        if (a==1||a==2) return 1;
        else return fibonacci(a-1)+fibonacci(a-2);
    }
    public static void main(String[] args) {
        System.out.println("fib = " +  fibonacci(30));
        /*
        TicTacToeEngine gameObject = new TicTacToeEngine();
        System.out.println(gameObject);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.println("Enter input");
            int row = Integer.parseInt(scanner.nextLine());
            int column = Integer.parseInt(scanner.nextLine());
            if (gameObject.placeSymbol(row, column)) {
                System.out.println(gameObject);
            }
            if (gameObject.isGameOver()) {
                System.out.println(gameObject);
                System.out.println("GameOver!");
                gameObject.newGame();
                System.out.println(gameObject);
            }
        }

         */
    }
}
