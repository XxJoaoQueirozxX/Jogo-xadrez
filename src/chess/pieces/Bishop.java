package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

import java.io.Serializable;

public class Bishop extends ChessPiece implements Serializable {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (getColor().toString().equals("WHITE")){
            return "\u2657";
        }
        return "\u265D";
    }
}
