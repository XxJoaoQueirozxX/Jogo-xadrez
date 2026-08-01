package tests;

import boardgame.Board;
import boardgame.Position;
import chess.Color;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.ChessMatch;

public class KingTest {
    public static void main(String[] args) {
        // Use a dedicated board via ChessMatch to avoid null references used inside King
        ChessMatch match = new ChessMatch();
        // Clear the board by removing all pieces
        // The Board API does not expose a clear method; we will place our own fresh Board and pieces for testing.
        Board b = new Board(8,8);
        // Place a white king at center
        King k = new King(b, Color.WHITE, match);
        b.placePiece(k, new Position(3,3));

        boolean[][] m = k.possibleMoves();
        // King should have up to 8 surrounding squares on empty board
        int expected = 8;
        int count = TestUtils.countTrue(m);
        TestUtils.assertEquals(expected, count, "King at center should have 8 moves on empty board");
        TestUtils.assertTrue(m[2][3], "Up");
        TestUtils.assertTrue(m[4][3], "Down");
        TestUtils.assertTrue(m[3][2], "Left");
        TestUtils.assertTrue(m[3][4], "Right");
        TestUtils.assertTrue(m[2][2], "Up-left");
        TestUtils.assertTrue(m[2][4], "Up-right");
        TestUtils.assertTrue(m[4][2], "Down-left");
        TestUtils.assertTrue(m[4][4], "Down-right");

        // Own piece adjacent blocks
        Board b2 = new Board(8,8);
        King k2 = new King(b2, Color.WHITE, match);
        b2.placePiece(k2, new Position(3,3));
        Pawn own = new Pawn(b2, Color.WHITE, match);
        b2.placePiece(own, new Position(2,3));
        boolean[][] m2 = k2.possibleMoves();
        TestUtils.assertFalse(m2[2][3], "Own piece square should not be allowed");
        TestUtils.assertTrue(m2[2][2], "Other directions still allowed");

        // Opponent adjacent is capturable
        Board b3 = new Board(8,8);
        King k3 = new King(b3, Color.WHITE, match);
        b3.placePiece(k3, new Position(3,3));
        Pawn opp = new Pawn(b3, Color.BLACK, match);
        b3.placePiece(opp, new Position(2,3));
        boolean[][] m3 = k3.possibleMoves();
        TestUtils.assertTrue(m3[2][3], "Opponent piece square should be allowed (capture)");

        System.out.println("KingTest passed");
    }
}
