package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

import java.io.Serializable;

public abstract class ChessPiece extends Piece implements Serializable {
   private Color color;
   private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }


    protected boolean isThereOpponentPiece(Position position){
//        return color == getBoard().piece(position);
        return true;
    }




    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
