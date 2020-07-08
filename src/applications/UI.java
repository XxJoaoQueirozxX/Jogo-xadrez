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
        System.out.println("  \u0041  \u0042 \u2009\u2009\u0043 \u2009\u2009\u0044 \u2009\u2009\u0045 \u2009\u0046 \u2009\u2009\u0047 \u2009\u2009\u2009\u0048");
    }

    public static void printPiece (ChessPiece piece){
        System.out.print((piece != null) ? piece : "\u2015");
        System.out.print(" ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
