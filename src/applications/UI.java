package applications;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import javax.swing.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static ChessPosition readChessPosition(Scanner sc){
        try{
            String pos = sc.nextLine().trim();

            char column = pos.charAt(0);
            int row = Integer.parseInt(pos.substring(1));
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Erro reading ChessPosition. Valid values are from a1 to h8.");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println("Turn: " + chessMatch.getTurn());
        if (!chessMatch.getCheckMate()){
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.getCheck()){
                System.out.println("CHECK!");
            }
        }else{
            System.out.println("CheckMate!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }



    }
    public static void printBoard(ChessPiece[][] chessPieces) {
        int i = chessPieces.length;
        for (ChessPiece[] row : chessPieces) {
            System.out.print(i + " ");
            for (ChessPiece piece : row) {
                printPiece(piece,false);
            }
            System.out.println();
            i -= 1;
        }
        System.out.println("  \u0041  \u0042 \u2009\u2009\u0043 \u2009\u2009\u0044 \u2009\u2009\u0045 \u2009\u0046 \u2009\u2009\u0047 \u2009\u2009\u2009\u0048");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for(int i=0; i< pieces.length; i++){
            System.out.print(8 - i + " ");
            for (int j = 0; j<pieces.length; j++){
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  \u0041  \u0042 \u2009\u2009\u0043 \u2009\u2009\u0044 \u2009\u2009\u0045 \u2009\u0046 \u2009\u2009\u0047 \u2009\u2009\u2009\u0048");
    }


    public static void printPiece (ChessPiece piece, boolean background){
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null){
            System.out.print("\u2015" );
        }else {
            if (piece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + piece);
            }else{
                System.out.print(ANSI_YELLOW + piece);
            }
        }
        System.out.print(ANSI_RESET+ " ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());

        System.out.println("Captured pieces: ");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));


        System.out.print(ANSI_RESET + "Black:");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

}
