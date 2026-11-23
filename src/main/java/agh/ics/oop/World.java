package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    static void main(String[] args) {
        try {
            List<MoveDirection> animalDirections = OptionsParser.parse(args);
            MapChangeListener consoleMapDisplay = new ConsoleMapDisplay();

            // symulacja 1
            List<Vector2d> animalPositions1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
            AbstractWorldMap map1 = new RectangularMap();
            map1.addObserver(consoleMapDisplay);
            Simulation simulation1 = new Simulation(animalPositions1, animalDirections, map1);
            simulation1.run();

            // symulacja 2
            List<Vector2d> animalPositions2 = List.of(new Vector2d(2,2), new Vector2d(3,4));
            AbstractWorldMap map2 = new GrassField(10);
            map2.addObserver(consoleMapDisplay);
            Simulation simulation2 = new Simulation(animalPositions2, animalDirections, map2);
            simulation2.run();
        } catch (UnsupportedParameterException e) {
            IO.println(e.getMessage());
        }
    }
}
