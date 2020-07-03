package chess;

import boardgame.Board;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private Board board;


    public ChessMatch() {
        board = new Board(8, 8);
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getColumns(); j++){
                chessPieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return chessPieces;
    }


}
