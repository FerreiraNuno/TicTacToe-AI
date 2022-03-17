package com.company;


import java.util.ArrayList;

public class ToeAI {
    TicTacToeEngine game;
    int symbolPlaying;
    int depth;

    ToeAI(TicTacToeEngine game, int symbolPlaying) {
        this.game = game;
        this.symbolPlaying = symbolPlaying;
    }


    public int makeCalculatedMove(TicTacToeEngine evalGame) {
        if (evalGame.isGameOver()) {
            return -1;
        }
        if (evalGame.isFull()) {
            return 0;
        }
        ArrayList<Move> moves = getPossibleMoves(evalGame);
        int bestEvaluation;
        Move bestMove;
        bestEvaluation = -999;
        bestMove = moves.get(0);
        for (Move move : moves) {
            TicTacToeEngine testGame = evalGame.makeClone();
            testGame.placeSymbol(move.row, move.collumn);
            int evaluation = -makeCalculatedMove(testGame.makeClone());
            if (evaluation >= bestEvaluation) {
                bestEvaluation = evaluation;
                bestMove = move;
            }
        }
        TicTacToeEngine printGame = evalGame.makeClone();
        printGame.placeSymbol(bestMove.row, bestMove.collumn);
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
