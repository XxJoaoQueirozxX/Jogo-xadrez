package applications;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPosition;

public class Teste {
    public static void main(String[] args) {
        Board board = new Board(8, 8);
        ChessMatch chess = new ChessMatch();

        UI.printBoard(chess.getPieces());
    }
}
