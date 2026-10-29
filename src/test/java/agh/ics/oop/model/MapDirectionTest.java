package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void nextReturnCorrectDirections() {
        // Implementacja wzorca given-when-then

        // given
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;

        // when
        MapDirection nextForNorth = north.next();
        MapDirection nextForEast = east.next();
        MapDirection nextForSouth = south.next();
        MapDirection nextForWest = west.next();

        // then
        assertEquals(MapDirection.EAST, nextForNorth);
        assertEquals(MapDirection.SOUTH, nextForEast);
        assertEquals(MapDirection.WEST, nextForSouth);
        assertEquals(MapDirection.NORTH, nextForWest);
    }

    @Test
    void previousReturnCorrectDirections() {
        assertAll(
                () -> assertEquals(MapDirection.WEST, MapDirection.NORTH.previous()),
                () -> assertEquals(MapDirection.NORTH, MapDirection.EAST.previous()),
                () -> assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous()),
                () -> assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous())
        );
    }
}
