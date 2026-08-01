package tests;

import boardgame.Board;
import boardgame.Position;
import chess.Color;
import chess.pieces.Pawn;
import chess.pieces.Queen;

public class QueenTest {
    public static void main(String[] args) {
        Board b = new Board(8,8);
        Queen q = new Queen(b, Color.WHITE);
        b.placePiece(q, new Position(3,3));
        boolean[][] m = q.possibleMoves();
        int count = TestUtils.countTrue(m);
        TestUtils.assertEquals(27, count, "Queen from center should have 27 moves on empty board");
        TestUtils.assertTrue(m[0][3], "Vertical up reachable");
        TestUtils.assertTrue(m[7][3], "Vertical down reachable");
        TestUtils.assertTrue(m[3][0], "Horizontal left reachable");
        TestUtils.assertTrue(m[3][7], "Horizontal right reachable");
        TestUtils.assertTrue(m[0][0], "Diagonal up-left reachable");
        TestUtils.assertTrue(m[6][6], "Diagonal down-right reachable");

        // Own blocker on a diagonal
        Board b2 = new Board(8,8);
        Queen q2 = new Queen(b2, Color.WHITE);
        b2.placePiece(q2, new Position(3,3));
        Pawn own = new Pawn(b2, Color.WHITE, null);
        b2.placePiece(own, new Position(5,5));
        boolean[][] m2 = q2.possibleMoves();
        TestUtils.assertTrue(m2[4][4], "Before own blocker on diagonal should be valid");
        TestUtils.assertFalse(m2[5][5], "Own blocker square not valid");
        TestUtils.assertFalse(m2[6][6], "Beyond own blocker not valid");

        // Opponent on a file
        Board b3 = new Board(8,8);
        Queen q3 = new Queen(b3, Color.WHITE);
        b3.placePiece(q3, new Position(3,3));
        Pawn opp = new Pawn(b3, Color.BLACK, null);
        b3.placePiece(opp, new Position(1,3));
        boolean[][] m3 = q3.possibleMoves();
        TestUtils.assertTrue(m3[1][3], "Opponent on same file capturable");
        TestUtils.assertFalse(m3[0][3], "Beyond opponent blocked");

        System.out.println("QueenTest passed");
    }
}
