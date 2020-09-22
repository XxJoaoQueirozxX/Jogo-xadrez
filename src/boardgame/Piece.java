package boardgame;

import boardgame.Position;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }


    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        for (boolean[] row : possibleMoves()){
            for (boolean col: row){
                if (col){ return true; }
            }
        }
        return false;
    }
}
