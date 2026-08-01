package tests;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.Color;
import chess.pieces.Pawn;

public class PawnTest {
    public static void main(String[] args) {
        ChessMatch match = new ChessMatch();

        // White pawn basic forward moves from (6,3)
        Board b1 = new Board(8,8);
        Pawn wp = new Pawn(b1, Color.WHITE, match);
        b1.placePiece(wp, new Position(6,3));
        boolean[][] m1 = wp.possibleMoves();
        TestUtils.assertTrue(m1[5][3], "White pawn can move one forward");
        TestUtils.assertTrue(m1[4][3], "White pawn initial two-square advance allowed when clear");
        // Blocked forward by own piece at (5,3)
        Board b2 = new Board(8,8);
        Pawn wp2 = new Pawn(b2, Color.WHITE, match);
        b2.placePiece(wp2, new Position(6,3));
        Pawn block = new Pawn(b2, Color.WHITE, match);
        b2.placePiece(block, new Position(5,3));
        boolean[][] m2 = wp2.possibleMoves();
        TestUtils.assertFalse(m2[5][3], "White pawn cannot move forward when blocked");
        TestUtils.assertFalse(m2[4][3], "White pawn cannot jump over a blocking piece");
        // Diagonal capture for white at (5,2) and (5,4)
        Board b3 = new Board(8,8);
        Pawn wp3 = new Pawn(b3, Color.WHITE, match);
        b3.placePiece(wp3, new Position(6,3));
        Pawn oppL = new Pawn(b3, Color.BLACK, match);
        Pawn oppR = new Pawn(b3, Color.BLACK, match);
        b3.placePiece(oppL, new Position(5,2));
        b3.placePiece(oppR, new Position(5,4));
        boolean[][] m3 = wp3.possibleMoves();
        TestUtils.assertTrue(m3[5][2], "White pawn can capture diagonally left");
        TestUtils.assertTrue(m3[5][4], "White pawn can capture diagonally right");

        // Black pawn basic forward moves from (1,4)
        Board b4 = new Board(8,8);
        Pawn bp = new Pawn(b4, Color.BLACK, match);
        b4.placePiece(bp, new Position(1,4));
        boolean[][] m4 = bp.possibleMoves();
        TestUtils.assertTrue(m4[2][4], "Black pawn can move one forward (downwards in matrix)");
        TestUtils.assertTrue(m4[3][4], "Black pawn initial two-square advance allowed when clear");
        // Blocked forward by own piece at (2,4)
        Board b5 = new Board(8,8);
        Pawn bp2 = new Pawn(b5, Color.BLACK, match);
        b5.placePiece(bp2, new Position(1,4));
        Pawn block2 = new Pawn(b5, Color.BLACK, match);
        b5.placePiece(block2, new Position(2,4));
        boolean[][] m5 = bp2.possibleMoves();
        TestUtils.assertFalse(m5[2][4], "Black pawn cannot move forward when blocked");
        TestUtils.assertFalse(m5[3][4], "Black pawn cannot jump over a blocking piece");
        // Diagonal capture for black at (2,3) and (2,5)
        Board b6 = new Board(8,8);
        Pawn bp3 = new Pawn(b6, Color.BLACK, match);
        b6.placePiece(bp3, new Position(1,4));
        Pawn wOppL = new Pawn(b6, Color.WHITE, match);
        Pawn wOppR = new Pawn(b6, Color.WHITE, match);
        b6.placePiece(wOppL, new Position(2,3));
        b6.placePiece(wOppR, new Position(2,5));
        boolean[][] m6 = bp3.possibleMoves();
        TestUtils.assertTrue(m6[2][3], "Black pawn can capture diagonally left (from black perspective)");
        TestUtils.assertTrue(m6[2][5], "Black pawn can capture diagonally right (from black perspective)");

        System.out.println("PawnTest passed");
    }
}
