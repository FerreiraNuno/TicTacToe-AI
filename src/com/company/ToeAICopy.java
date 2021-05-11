package com.company;


import java.util.ArrayList;
import java.util.Random;

public class ToeAICopy {
    TicTacToeEngine game;
    int symbolPlaying;
    int depth;

    ToeAICopy(TicTacToeEngine game, int symbolPlaying) {
        this.game = game;
        this.symbolPlaying = symbolPlaying;
    }

    public Move makeRandomMove() {
        ArrayList<Move> moves = getPossibleMoves(game);
        Move moveToMake = moves.get(new Random().nextInt(moves.size()));
        return moveToMake;
    }

    public void makeBestMove() {
        ArrayList<Move> moves = getPossibleMoves(game);
        Move bestMove = moves.get(0);
        for (Move move : moves){
            //if current move is better than bestMove
            bestMove = move;
        }
        game.placeSymbol(bestMove.row, bestMove.collumn);
    }

    public int makeCalculatedEvaluation(TicTacToeEngine evalGame, boolean isMax) {
        if (evalGame.isGameOver()) {
            return -1;
        }
        if (evalGame.isFull()) {
            return 0;
        }
        ArrayList<Move> moves = getPossibleMoves(evalGame);

        int bestEvaluation;
        Move bestMove;

        if (isMax) {
            bestEvaluation = -999;
            bestMove = moves.get(0);
            for (Move move : moves) {
                TicTacToeEngine testGame = evalGame.makeClone();
                testGame.placeSymbol(move.row, move.collumn);
                int evaluation = makeCalculatedEvaluation(testGame.makeClone(), false);
                if (evaluation >= bestEvaluation) {
                    bestEvaluation = evaluation;
                    bestMove = move;
                }
            }
        } else {
            bestEvaluation = 999;
            bestMove = moves.get(0);
            for (Move move : moves) {
                TicTacToeEngine testGame = evalGame.makeClone();
                testGame.placeSymbol(move.row, move.collumn);
                int evaluation = makeCalculatedEvaluation(testGame.makeClone(), true);
                if (evaluation >= bestEvaluation) {
                    bestEvaluation = evaluation;
                    bestMove = move;
                }
            }
        }


        TicTacToeEngine printGame = evalGame.makeClone();
        printGame.placeSymbol(bestMove.row, bestMove.collumn);
        System.out.println("printing best move:");
        System.out.println(printGame);
        evalGame.placeSymbol(bestMove.row, bestMove.collumn);
        return bestEvaluation;
    }


    ArrayList<Move> getPossibleMoves(TicTacToeEngine iterGame) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int collumn = 0; collumn < 3; collumn++) {
                if (iterGame.getBoard()[row][collumn] == -1) {
                    moves.add(new Move(row, collumn));
                }
            }
        }
        return moves;
    }
}
