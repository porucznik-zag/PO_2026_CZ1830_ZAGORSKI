package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void shouldPlaceAnimalCorrectly() {
        // given
        WorldMap map = new RectangularMap(3, 3);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        Animal outOfBoundsAnimal = new Animal(new Vector2d(4, 4));

        // when and then
        assertDoesNotThrow(() -> map.place(animal1));
        assertThrows(IllegalArgumentException.class, () -> map.place(animal2));
        assertThrows(IllegalArgumentException.class, () -> map.place(outOfBoundsAnimal));
    }

    @Test
    void shouldAnimalMoveStopsOnMapBounds() {
        // given
        WorldMap map = new RectangularMap(2, 2);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        // when
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(0, 1), animal.getPosition());
        assertEquals(animal, map.objectAt(new Vector2d(0, 1)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));

        // then
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(0, 1), animal.getPosition());
    }

    @Test
    void shouldNotAnimalMoveToFieldWithAnotherAnimal() {
        // given
        WorldMap map = new RectangularMap(2, 2);
        Animal animal1 = new Animal(new Vector2d(1, 0));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        map.place(animal1);
        map.place(animal2);

        // when
        map.move(animal1, MoveDirection.FORWARD);

        // then
        assertEquals(new Vector2d(1, 0), animal1.getPosition());
        assertEquals(animal1, map.objectAt(new Vector2d(1, 0)));
        assertEquals(animal2, map.objectAt(new Vector2d(1, 1)));
    }

    @Test
    void canMoveToMethodWorksCorrectly() {
        // given
        WorldMap map = new RectangularMap(2, 2);
        map.place(new Animal(new Vector2d(1, 1)));

        // when and then
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(-1, -2)));
        assertFalse(map.canMoveTo(new Vector2d(3, 3)));
        assertFalse(map.canMoveTo(null));
    }
}