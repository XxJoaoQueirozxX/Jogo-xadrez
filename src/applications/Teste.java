package applications;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
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
        while (!chess.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chess, captured);
                System.out.printf("%nSource: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chess.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chess.getPieces(), possibleMoves);


                System.out.printf("%nTarget: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chess.performeChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if (chess.getPromoted() != null) {
                    System.out.print("Enter piece for promotion \n\t< B > - Bishop \n\t< N > - Knight \n\t< R > - Rook \n\t< Q > - Queen\n:| ");
                    String type = sc.nextLine().toUpperCase();
                    chess.replacePromotedPiece(type);
                }

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }


        UI.clearScreen();
        UI.printMatch(chess, captured);


    }
}
