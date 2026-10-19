package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    static MoveDirection[] parse(String[] args) {
        MoveDirection[] result = new MoveDirection[args.length];
        int counter = 0;

        for (String arg: args) {
            MoveDirection resultArg = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (resultArg != null) {
                result[counter] = resultArg;
                counter += 1;
            }
        }

        return Arrays.copyOfRange(result, 0, counter);
    }
}
