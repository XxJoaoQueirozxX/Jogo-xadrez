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


    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }


    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }


    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }
}
