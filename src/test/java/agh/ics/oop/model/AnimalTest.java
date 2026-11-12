package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    // Stub (zaślepka) dla MoveValidator, który imituje nieskończoną mapę, bez zajętych pól
    private final MoveValidator<Vector2d> alwaysTrueValidator = _ -> true;

    @Test
    void newCreatedAnimaShouldHaveCorrectState() {
        // given and when
        Animal animal = new Animal();

        // then
        assertTrue(animal.isAt(Animal.DEFAULT_POSITION));
        assertEquals(Animal.DEFAULT_ORIENTATION.toStringSchematic(), animal.toString());
    }

    @Test
    void animalShouldRotateCorrectly() {
        // given and when
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT, alwaysTrueValidator);
        animal.move(MoveDirection.RIGHT, alwaysTrueValidator);
        animal.move(MoveDirection.LEFT, alwaysTrueValidator);

        // then
        assertEquals("E", animal.toString());
    }

    @Test
    void animalShouldMoveCorrectlyOnEmptyInfinityMap() {
        // given
        Animal animal = new Animal();

        // when
        animal.move(MoveDirection.FORWARD, alwaysTrueValidator);
        animal.move(MoveDirection.FORWARD, alwaysTrueValidator);
        animal.move(MoveDirection.BACKWARD, alwaysTrueValidator);

        // then
        assertTrue(animal.isAt(new Vector2d(0, 1)));
    }
}