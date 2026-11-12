package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void simulationShouldRunAndEndsAtCorrectPositions() {
        // given
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f"};
        List<MoveDirection> animalsDirections = OptionsParser.parse(args);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(2, 2)),
                new Animal(new Vector2d(3, 4))
        );
        WorldMap<Animal, Vector2d> map = new RectangularMap<>();
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, animalsDirections, map);

        // when
        simulation.run();

        // then
        List<Animal> result = simulation.getObjects();
        Animal animal1 = result.get(0);
        Animal animal2 = result.get(1);

        assertTrue(animal1.isAt(new Vector2d(2, 2)));
        assertEquals("S", animal1.toString());

        assertTrue(animal2.isAt(new Vector2d(3, 4)));
        assertEquals("N", animal2.toString());
    }

}