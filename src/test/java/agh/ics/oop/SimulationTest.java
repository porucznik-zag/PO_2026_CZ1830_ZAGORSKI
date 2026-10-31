package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
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
        Simulation simulation = new Simulation(animalsPositions, animalsDirections);

        // when
        simulation.run();

        // then
        List<Animal> animals = simulation.getAnimals();
        Animal animal1 = animals.get(0);
        Animal animal2 = animals.get(1);

        assertTrue(animal1.isAt(new Vector2d(3, 2)));
        assertTrue(animal1.toString().contains("Południe"));

        assertTrue(animal2.isAt(new Vector2d(2, 4)));
        assertTrue(animal2.toString().contains("Północ"));
    }

}