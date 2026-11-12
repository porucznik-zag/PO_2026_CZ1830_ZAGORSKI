package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    static void main(String[] args) {
        // symulacja dla Animals i RectangularMap
        List<MoveDirection> animalsDirections = OptionsParser.parse(args);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(2, 2)),
                new Animal(new Vector2d(3, 4))
        );
        WorldMap<Animal, Vector2d> map1 = new RectangularMap<>();

        Simulation<Animal, Vector2d> simulation1 = new Simulation<>(animals, animalsDirections, map1);
        simulation1.run();

        // symulacja dla Strings i TextsMap
        List<MoveDirection> textsDirections = OptionsParser.parse(args);
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldNumberPositionMap<String, Integer> map2 = new TextMap();

        Simulation<String, Integer> simulation2 = new Simulation<>(texts, textsDirections, map2);
        simulation2.run();
    }
}
