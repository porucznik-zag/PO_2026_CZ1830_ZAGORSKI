package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {
    static void main(String[] args) {

        List<MoveDirection> animalDirections = OptionsParser.parse(args);
        List<Vector2d> animalPositions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap();
        Simulation simulation = new Simulation(animalPositions, animalDirections, map);
        simulation.run();
    }
}
