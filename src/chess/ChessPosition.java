package chess;

import boardgame.Position;
import exceptions.BoardException;
import exceptions.ChessException;


public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (! "abcdefgh".contains(String.format("%s", column))){
            throw new ChessException("ChessException: column value error.");
        }
        this.column = column;
        this.row = row;
    }
    protected Position toPosition(){
        return new Position(this.row - 1, "abcdefgh".indexOf(this.column));
    }

    public static ChessPosition fromPosition(Position position){
        if (position.getRow() >= 8 || position.getRow() < 0){
            throw new BoardException("BoardException error: Row value is out of range (" +position.getRow()+ ")");
        }
        if (position.getColumn() >= 8 || position.getColumn()  < 0){
            throw new BoardException("BoardException error: Column value is out of range (" +position.getColumn()+ ")");
        }
        return new ChessPosition("abcdefgh".charAt(position.getColumn()), position.getRow() + 1);
    }

    @Override
    public String toString() {
        return String.format("%s, %d", column, row);
    }
}
