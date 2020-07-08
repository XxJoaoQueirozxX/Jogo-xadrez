package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

import java.io.Serializable;

public class Rook extends ChessPiece implements Serializable {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (getColor().toString().equals("WHITE")){
            return "\u2656";
        }
        return "\u265C";
    }
}
