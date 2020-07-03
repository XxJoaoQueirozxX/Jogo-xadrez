package applications;

import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] chessPieces) {
        int i = chessPieces.length;
        for (ChessPiece[] row : chessPieces) {
            System.out.print(i + " ");
            for (ChessPiece piece : row) {
                printPiece(piece);
            }
            System.out.println();
            i -= 1;
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printPiece (ChessPiece piece){
        System.out.print((piece != null) ? piece : "-");
        System.out.print(" ");
    }
}
