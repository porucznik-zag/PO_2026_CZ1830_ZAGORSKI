package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void newCreatedAnimaShouldHaveCorrectState() {
        // given and when
        Animal animal = new Animal();

        // then
        assertTrue(animal.isAt(new Vector2d(2, 2)));
        assertEquals("[2,2][Północ]", animal.toString());
    }

    @Test
    void animalShouldRotateCorrectly() {
        // given and when
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.LEFT);

        // then
        assertEquals("[2,2][Wschód]", animal.toString());
    }


    @Test
    void animalShouldNotMoveOutsideTheMap() {
        // given
        Animal animalInUpperCorner = new Animal(Animal.MAP_STOP);
        Animal animalInLowerCorner = new Animal(Animal.MAP_START);

        // when
        animalInUpperCorner.move(MoveDirection.FORWARD);
        animalInUpperCorner.move(MoveDirection.RIGHT);
        animalInUpperCorner.move(MoveDirection.FORWARD);

        animalInLowerCorner.move(MoveDirection.LEFT);
        animalInLowerCorner.move(MoveDirection.FORWARD);
        animalInLowerCorner.move(MoveDirection.LEFT);
        animalInLowerCorner.move(MoveDirection.FORWARD);


        // then
        assertEquals("[4,4][Wschód]", animalInUpperCorner.toString());
        assertEquals("[0,0][Południe]", animalInLowerCorner.toString());
    }

}