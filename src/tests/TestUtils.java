package tests;

public class TestUtils {
    public static void assertTrue(boolean cond, String message) {
        if (!cond) throw new AssertionError("Assertion failed: " + message);
    }
    public static void assertFalse(boolean cond, String message) {
        if (cond) throw new AssertionError("Assertion failed (expected false): " + message);
    }
    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) throw new AssertionError("Assertion failed: expected=" + expected + ", actual=" + actual + ". " + message);
    }
    public static int countTrue(boolean[][] mat) {
        int c = 0;
        for (boolean[] row : mat) {
            for (boolean b : row) if (b) c++;
        }
        return c;
    }
}
