package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

import java.io.Serializable;

public class Pawn extends ChessPiece implements Serializable {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (getColor().toString().equals("WHITE")){
            return "\u2659";
        }
        return "\u265F";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
