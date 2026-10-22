package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    static void main(String[] args) {
        IO.println("Start");
        run(OptionsParser.parse(args));
        IO.println("Stop");
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
