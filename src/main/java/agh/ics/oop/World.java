package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    static void main(String[] args) {
        IO.println("Start");
        run(OptionsParser.parse(args));
        IO.println("Stop");

        IO.println();

        Vector2d position1 = new Vector2d(1,2);
        IO.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        IO.println(position2);
        IO.println(position1.add(position2));

        IO.println();

        MapDirection direction1 = MapDirection.NORTH;
        MapDirection previousDirection = direction1.previous();
        MapDirection nextDirection = direction1.next();
        IO.println("Wektor jednostkowy dla %s to %s".formatted(direction1.toString(), direction1.toUnitVector()));
        IO.println("Poprzedni kierunek dla %s to %s".formatted(direction1.toString(), previousDirection.toString()));
        IO.println("Następny kierunek dla %s to %s".formatted(direction1.toString(), nextDirection.toString()));
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
