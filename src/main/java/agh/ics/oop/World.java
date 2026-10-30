package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    static void main(String[] args) {
        Animal animal = new Animal();
        IO.println("Pozycja zwierzaka to: " + animal.toString());
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
