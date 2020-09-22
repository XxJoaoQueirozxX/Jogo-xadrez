package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;
import exceptions.BoardException;
import exceptions.ChessException;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();




    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
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

    public int getTurn() {
        return turn;
    }


    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void validateSourcePosition(Position source){
        if (!board.thereIsAPiece(source)){
            throw new ChessException("There is no piece on source position.");
        }

        if (currentPlayer != ((ChessPiece) board.piece(source)).getColor()){
            throw new ChessException("The chosen piece is not yours.");
        }

        if (!board.piece(source).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece");
        }

    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();;
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);

        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece cant move to target position. ");
        }
    }

    public void nextTurn(){
        turn ++;
        currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK:Color.WHITE;
    }


    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){

//                Pocionamento dos peões
//        for (int i = 0; i < 8; i++){
//            placeNewPiece((char) ('a' + i), 2,new Pawn(board, Color.WHITE));
//            placeNewPiece((char) ('a' + i), 7,new Pawn(board, Color.BLACK));
//        }


        //        Posicionamento das torres
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));


        //        Posicionamento dos cavalos
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));

        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));


        //        Posicionamento dos bispos
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));

        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));


        //        Posicionamento dos reis
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('d', 8, new King(board, Color.BLACK));


        //        Posicionamento das rainhas
        placeNewPiece('e', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 8, new Queen(board, Color.BLACK));
    }


}
