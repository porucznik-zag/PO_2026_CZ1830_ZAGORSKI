package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void testEquals() {
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(1, 2);
        Vector2d vector3 = new Vector2d(-1, 1);

        assertTrue(vector1.equals(vector1)); // czy to ma sens?
        assertTrue(vector1.equals(vector2));
        assertFalse(vector1.equals(vector3));
    }

    @Test
    void testToString() {
        Vector2d vector = new Vector2d(1, 2);
        String text = vector.toString();
        assertEquals("(1,2)", text);
    }

    @Test
    void precedes() {
        assertTrue(new Vector2d(2, 2).precedes(new Vector2d(2, 2)));
        assertTrue(new Vector2d(1, 0).precedes(new Vector2d(2, 2)));
        assertFalse(new Vector2d(1, 3).precedes(new Vector2d(2, 2)));
        assertFalse(new Vector2d(3, 1).precedes(new Vector2d(2, 2)));
        assertFalse(new Vector2d(2, 2).precedes(new Vector2d(1, 1)));
    }

    @Test
    void follows() {
        assertTrue(new Vector2d(1, 2).follows(new Vector2d(1, 2)));
        assertTrue(new Vector2d(1, 2).follows(new Vector2d(0, 2)));
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(2, 2)));
        assertFalse(new Vector2d(3, 1).follows(new Vector2d(2, 2)));
        assertFalse(new Vector2d(2, 2).follows(new Vector2d(3, 3)));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(2,3), new Vector2d(1, 0).upperRight(new Vector2d(2, 3)));
        assertEquals(new Vector2d(2, 2), new Vector2d(2, -1).upperRight(new Vector2d(1, 2)));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(0,2), new Vector2d(2, 3).lowerLeft(new Vector2d(0, 2)));
        assertEquals(new Vector2d(-1, 0), new Vector2d(-1, 4).lowerLeft(new Vector2d(1, 0)));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(0,1), new Vector2d(2, -3).add(new Vector2d(-2, 4)));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(0,1), new Vector2d(-2, 2).subtract(new Vector2d(-2, 1)));
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(1,-3), new Vector2d(-1, 3).opposite());
    }
}