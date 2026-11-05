package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> result = new ArrayList<>();

        for (String arg: args) {
            MoveDirection resultArg = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (resultArg != null) {
                result.add(resultArg);
            }
        }

        return result;
    }
}
