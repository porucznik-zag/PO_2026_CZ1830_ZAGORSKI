package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    static void main(String[] args) {

        List<MoveDirection> animalDirections = OptionsParser.parse(args);
        List<Vector2d> animalPositions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(animalPositions, animalDirections);
        simulation.run();
    }


    static void run(MoveDirection[] directions) {
        for (MoveDirection d: directions) {
            String message = switch (d) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT -> "Zwierzak skręca w lewo";
            };

            IO.println(message);
        }
    }

}
