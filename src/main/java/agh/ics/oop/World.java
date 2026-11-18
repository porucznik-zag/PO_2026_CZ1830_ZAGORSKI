package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    static void main(String[] args) {

        // symulacja 1
        List<MoveDirection> animalDirections = OptionsParser.parse(args);
        List<Vector2d> animalPositions1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map1 = new RectangularMap();
        Simulation simulation1 = new Simulation(animalPositions1, animalDirections, map1);
        simulation1.run();

        // symulacja 2
        List<Vector2d> animalPositions2 = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map2 = new GrassField(10);
        Simulation simulation2 = new Simulation(animalPositions2, animalDirections, map2);
        simulation2.run();
    }
}
