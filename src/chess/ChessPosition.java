package chess;

import boardgame.Position;
import exceptions.BoardException;
import exceptions.ChessException;


public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("ChessException: column value error, valid values are from a1 to h8.");
        }
        this.column = column;
        this.row = row;
    }
    protected Position toPosition(){
        return new Position(8 - this.row, this.column - 'a');
    }

    public static ChessPosition fromPosition(Position position){
        if (position.getRow() >= 8 || position.getRow() < 0){
            throw new BoardException("BoardException error: Row value is out of range (" +position.getRow()+ ")");
        }
        if (position.getColumn() >= 8 || position.getColumn()  < 0){
            throw new BoardException("BoardException error: Column value is out of range (" +position.getColumn()+ ")");
        }
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return String.format("%s%d", column, row);
    }
}
