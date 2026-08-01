package tests;

import boardgame.Board;
import boardgame.Position;
import chess.Color;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class RookTest {
    public static void main(String[] args) {
        // Empty board, rook at center (3,3)
        Board b = new Board(8,8);
        Rook r = new Rook(b, Color.WHITE);
        b.placePiece(r, new Position(3,3));
        boolean[][] m = r.possibleMoves();
        int count = TestUtils.countTrue(m);
        TestUtils.assertEquals(14, count, "Rook from center should have 14 moves on empty board");
        TestUtils.assertTrue(m[0][3], "Up should reach (0,3)");
        TestUtils.assertTrue(m[7][3], "Down should reach (7,3)");
        TestUtils.assertTrue(m[3][0], "Left should reach (3,0)");
        TestUtils.assertTrue(m[3][7], "Right should reach (3,7)");

        // Own blocker at (3,5) blocks and is not allowed
        Board b2 = new Board(8,8);
        Rook r2 = new Rook(b2, Color.WHITE);
        b2.placePiece(r2, new Position(3,3));
        Pawn own = new Pawn(b2, Color.WHITE, null);
        b2.placePiece(own, new Position(3,5));
        boolean[][] m2 = r2.possibleMoves();
        TestUtils.assertTrue(m2[3][4], "Square before own blocker (3,4) valid");
        TestUtils.assertFalse(m2[3][5], "Own blocker square (3,5) not valid");
        TestUtils.assertFalse(m2[3][6], "Beyond own blocker (3,6) not valid");

        // Opponent at (1,3) capturable; beyond blocked
        Board b3 = new Board(8,8);
        Rook r3 = new Rook(b3, Color.WHITE);
        b3.placePiece(r3, new Position(3,3));
        Pawn opp = new Pawn(b3, Color.BLACK, null);
        b3.placePiece(opp, new Position(1,3));
        boolean[][] m3 = r3.possibleMoves();
        TestUtils.assertTrue(m3[1][3], "Opponent at (1,3) should be capturable");
        TestUtils.assertFalse(m3[0][3], "Beyond opponent (0,3) should be blocked");

        System.out.println("RookTest passed");
    }
}
