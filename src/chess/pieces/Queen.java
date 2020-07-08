package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

import java.io.Serializable;

public class Queen extends ChessPiece implements Serializable {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (getColor().toString().equals("WHITE")){
            return "\u2655";
        }
        return "\u265B";
    }
}
