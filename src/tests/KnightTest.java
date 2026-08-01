package tests;

import boardgame.Board;
import boardgame.Position;
import chess.Color;
import chess.pieces.Knight;
import chess.pieces.Pawn;

public class KnightTest {
    public static void main(String[] args) {
        // Knight at center has 8 moves
        Board b = new Board(8,8);
        Knight n = new Knight(b, Color.WHITE);
        b.placePiece(n, new Position(3,3));
        boolean[][] m = n.possibleMoves();
        TestUtils.assertEquals(8, TestUtils.countTrue(m), "Knight at center should have 8 moves");
        TestUtils.assertTrue(m[2][1], "(2,1) valid");
        TestUtils.assertTrue(m[1][2], "(1,2) valid");
        TestUtils.assertTrue(m[1][4], "(1,4) valid");
        TestUtils.assertTrue(m[2][5], "(2,5) valid");
        TestUtils.assertTrue(m[4][5], "(4,5) valid");
        TestUtils.assertTrue(m[5][4], "(5,4) valid");
        TestUtils.assertTrue(m[5][2], "(5,2) valid");
        TestUtils.assertTrue(m[4][1], "(4,1) valid");

        // Knight ignores blockers: own piece on target blocks only the exact square
        Board b2 = new Board(8,8);
        Knight n2 = new Knight(b2, Color.WHITE);
        b2.placePiece(n2, new Position(0,1)); // near corner
        Pawn own = new Pawn(b2, Color.WHITE, null);
        b2.placePiece(own, new Position(2,2)); // one of its moves
        boolean[][] m2 = n2.possibleMoves();
        // From (0,1) legal targets are (1,3), (2,2), (2,0)
        TestUtils.assertTrue(m2[1][3], "(1,3) valid");
        TestUtils.assertTrue(m2[2][0], "(2,0) valid");
        TestUtils.assertFalse(m2[2][2], "Own piece square (2,2) not valid");

        // Opponent on target should be capturable
        Board b3 = new Board(8,8);
        Knight n3 = new Knight(b3, Color.WHITE);
        b3.placePiece(n3, new Position(0,1));
        Pawn opp = new Pawn(b3, Color.BLACK, null);
        b3.placePiece(opp, new Position(2,2));
        boolean[][] m3 = n3.possibleMoves();
        TestUtils.assertTrue(m3[2][2], "Opponent on (2,2) capturable");

        System.out.println("KnightTest passed");
    }
}
