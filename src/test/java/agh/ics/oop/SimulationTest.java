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
        List<Vector2d> animalsPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        WorldMap map = new RectangularMap();
        Simulation simulation = new Simulation(animalsPositions, animalsDirections, map);

        // when
        simulation.run();

        // then
        List<Animal> animals = simulation.getAnimals();
        Animal animal1 = animals.get(0);
        Animal animal2 = animals.get(1);

        assertTrue(animal1.isAt(new Vector2d(2, 2)));
        assertEquals("S", animal1.toString());

        assertTrue(animal2.isAt(new Vector2d(3, 4)));
        assertEquals("N", animal2.toString());
    }

}