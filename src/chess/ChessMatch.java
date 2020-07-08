package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.*;

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
        initialSetup();
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

    private void initialSetup(){

        //        Pocionamento dos peões
        for (int i = 0; i < 8; i++){
            board.placePiece(new Pawn(board, Color.WHITE), new Position(1, i));
            board.placePiece(new Pawn(board, Color.BLACK), new Position(6, i));
        }

        //        Posicionamento das torres
        board.placePiece(new Rook(board, Color.WHITE), new Position(0,0));
        board.placePiece(new Rook(board, Color.WHITE), new Position(0,7));

        board.placePiece(new Rook(board, Color.BLACK), new Position(7,0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(7,7));

        //        Posicionamento dos cavalos
        board.placePiece(new Knight(board, Color.WHITE), new Position(0,1));
        board.placePiece(new Knight(board, Color.WHITE), new Position(0,6));

        board.placePiece(new Knight(board, Color.BLACK), new Position(7,1));
        board.placePiece(new Knight(board, Color.BLACK), new Position(7,6));

        //        Posicionamento dos bispos
        board.placePiece(new Bishop(board, Color.WHITE), new Position(0,2));
        board.placePiece(new Bishop(board, Color.WHITE), new Position(0,5));

        board.placePiece(new Bishop(board, Color.BLACK), new Position(7,2));
        board.placePiece(new Bishop(board, Color.BLACK), new Position(7,5));

        //        Posicionamento dos reis
        board.placePiece(new King(board, Color.WHITE), new Position(0,3));

        board.placePiece(new King(board, Color.BLACK), new Position(7,3));

        //        Posicionamento das rainhas
        board.placePiece(new Queen(board, Color.WHITE), new Position(0,4));
        board.placePiece(new Queen(board, Color.BLACK), new Position(7,4));


    }
}
