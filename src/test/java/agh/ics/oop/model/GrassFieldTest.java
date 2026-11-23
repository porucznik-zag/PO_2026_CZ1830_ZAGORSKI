package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void shouldGenerateCorrectAmountOfGrassInCorrectConstraints() {
        // given
        GrassField map = new GrassField(10);
        Vector2d expectedMapStart = new Vector2d(0, 0);
        Vector2d expectedMapStop = new Vector2d(10, 10);

        // when and then
        assertEquals(10, map.getElements().size());
        for (WorldElement element : map.getElements()) {
            assertInstanceOf(Grass.class, element);
            assertTrue(element.getPosition().follows(expectedMapStart));
            assertTrue(element.getPosition().precedes(expectedMapStop));
        }
    }

    @Test
    void AnimalCanMoveToFieldWithGrassAndIsReturnedByObjectAtMethod() {
        // given
        GrassField map = new GrassField(0);
        Grass grass = new Grass(new Vector2d(1, 1));
        Animal animal = new Animal(new Vector2d(1, 0)); // NORTH
        map.place(grass);
        map.place(animal);

        // when and then
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));

        map.move(animal, MoveDirection.FORWARD);
        assertEquals(animal, map.objectAt(new Vector2d(1, 1)));
    }

    @Test
    void shouldAnimalMoveOnInfinityArea() {
        // given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        // when and then
        map.move(animal, MoveDirection.LEFT);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(-1, 0), animal.getPosition());

        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(-2, 0), animal.getPosition());
    }

    @Test
    void shouldGetCurrentConstraintsMethodWorkCorrectly() {
        GrassField map = new GrassField(0);
        map.place(new Grass(new Vector2d(-5, -4)));
        map.place(new Animal(new Vector2d(5, 4)));

        MapConstraints constraints = new MapConstraints(new Vector2d(-5, -4), new Vector2d(5, 4));
        assertEquals(constraints, map.getCurrentConstraints());
    }
}