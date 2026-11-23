package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.UnsupportedParameterException;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    static List<MoveDirection> parse(String[] args) throws UnsupportedParameterException {
        List<MoveDirection> result = new ArrayList<>();

        for (String arg: args) {
            MoveDirection resultArg = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> throw new UnsupportedParameterException(arg);
            };

            result.add(resultArg);
        }

        return result;
    }
}
