package agh.ics.oop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void testEquals() {
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(1, 2);
        Vector2d vector3 = new Vector2d(-1, 1);
        Vector2d vector4 = null;
        String vector5 = "(1, 2)";

        assertTrue(vector1.equals(vector1)); // czy to ma sens?
        assertTrue(vector1.equals(vector2));
        assertFalse(vector1.equals(vector3));
        assertFalse(vector1.equals(vector4));
        assertFalse(vector1.equals(vector5));
    }

    @Test
    void testToString() {
        // given
        Vector2d vector = new Vector2d(1, 2);

        // when
        String text = vector.toString();

        // then
        assertEquals("(1,2)", text);
    }

    @Test
    void precedes() {
        // given
        Vector2d vec1 = new Vector2d(2, 2);
        Vector2d vec2 = new Vector2d(1, 0);
        Vector2d vec3 = new Vector2d(1, 3);
        Vector2d vec4 = new Vector2d(3, 1);
        Vector2d vec5 = new Vector2d(3, 3);

        // when and then
        assertTrue(vec1.precedes(vec1));
        assertTrue(vec2.precedes(vec1));
        assertFalse(vec3.precedes(vec1));
        assertFalse(vec4.precedes(vec1));
        assertFalse(vec5.precedes(vec1));
    }

    @Test
    void follows() {
        // given, when and then
        assertTrue(new Vector2d(1, 2).follows(new Vector2d(1, 2)));
        assertTrue(new Vector2d(1, 2).follows(new Vector2d(0, 2)));
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(2, 2)));
        assertFalse(new Vector2d(3, 1).follows(new Vector2d(2, 2)));
        assertFalse(new Vector2d(2, 2).follows(new Vector2d(3, 3)));
    }

    @DisplayName("UpperRight returns correct results")
    @ParameterizedTest(name = "UpperRight: ({0}, {1}) and ({2}, {3}) should return ({4}, {5})")
    @CsvSource({
            "2, 3, 1, 0, 2, 3",
            "2, -1, 1, 2, 2, 2"
    })
    void upperRight(int x1, int y1, int x2, int y2, int expectedX, int expectedY) {
        // given
        Vector2d v1 = new Vector2d(x1, y1);
        Vector2d v2 = new Vector2d(x2, y2);
        Vector2d expected = new Vector2d(expectedX, expectedY);

        // when & then
        assertEquals(expected, v1.upperRight(v2));
    }


    @DisplayName("LowerLeft returns correct results")
    @ParameterizedTest(name = "LowerLeft: ({0}, {1}) and ({2}, {3}) should return ({4}, {5})")
    @CsvSource({
            "2, 3, 0, 2, 0, 2",
            "-1, 4, 1, 0, -1, 0"
    })
    void lowerLeft(int x1, int y1, int x2, int y2, int expectedX, int expectedY) {
        assertEquals(new Vector2d(expectedX, expectedY), new Vector2d(x1, y1).lowerLeft(new Vector2d(x2, y2)));
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