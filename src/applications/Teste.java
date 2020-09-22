package applications;

import boardgame.Board;
import chess.ChessMatch;
import  chess.ChessPiece;
import chess.ChessPosition;
import exceptions.BoardException;
import exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch chess = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while (true){
            try {
                UI.clearScreen();
                UI.PrintMatch(chess, captured);
                System.out.printf("%nSource: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chess.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chess.getPieces() ,possibleMoves);


                System.out.printf("%nTarget: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chess.performeChessMove(source, target);

                if (capturedPiece != null){
                    captured.add(capturedPiece);
                }
            }
            catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
