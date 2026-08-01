package tests;

import boardgame.Board;
import boardgame.Position;
import chess.Color;
import chess.pieces.Bishop;
import chess.pieces.Pawn;

public class BishopTest {
    public static void main(String[] args) {
        // Empty board, bishop at center (3,3)
        Board b = new Board(8,8);
        Bishop bp = new Bishop(b, Color.WHITE);
        b.placePiece(bp, new Position(3,3));
        boolean[][] m = bp.possibleMoves();
        int count = TestUtils.countTrue(m);
        TestUtils.assertEquals(13, count, "Bishop from center should have 13 moves on empty board");
        TestUtils.assertTrue(m[0][6], "Path up-right should reach (0,6)");
        TestUtils.assertTrue(m[6][6], "Path down-right should reach (6,6)");
        TestUtils.assertTrue(m[0][0], "Path up-left should reach (0,0)");
        TestUtils.assertTrue(m[6][0], "Path down-left should reach (6,0)");

        // Block with own piece at (5,5) should stop path and not include (5,5)
        Board b2 = new Board(8,8);
        Bishop bp2 = new Bishop(b2, Color.WHITE);
        b2.placePiece(bp2, new Position(3,3));
        // place own piece at (5,5)
        Pawn ownBlock = new Pawn(b2, Color.WHITE, null);
        b2.placePiece(ownBlock, new Position(5,5));
        boolean[][] m2 = bp2.possibleMoves();
        TestUtils.assertFalse(m2[5][5], "Own piece square (5,5) must not be a valid move");
        TestUtils.assertTrue(m2[4][4], "Square before own piece should be valid (4,4)");
        TestUtils.assertFalse(m2[6][6], "Squares beyond own piece should be invalid (6,6)");

        // Opponent at (1,5) should be capturable, but beyond (0,6) should be blocked
        Board b3 = new Board(8,8);
        Bishop bp3 = new Bishop(b3, Color.WHITE);
        b3.placePiece(bp3, new Position(3,3));
        Pawn opp = new Pawn(b3, Color.BLACK, null);
        b3.placePiece(opp, new Position(1,5));
        boolean[][] m3 = bp3.possibleMoves();
        TestUtils.assertTrue(m3[1][5], "Opponent at (1,5) should be capturable");
        TestUtils.assertFalse(m3[0][6], "Beyond captured piece should be blocked at (0,6)");

        System.out.println("BishopTest passed");
    }
}
