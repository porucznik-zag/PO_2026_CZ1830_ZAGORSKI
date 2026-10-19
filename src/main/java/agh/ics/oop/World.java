package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        IO.println("Start");
        run(OptionsParser.parse(args));
        IO.println("Stop");
    }

    // Version 1
//    static void run(String[] args) {
//        StringBuilder result = new StringBuilder();
//
//        for(int i = 0; i < args.length; i++) {
//           result.append(args[i]);
//           if (i < args.length - 1 ){
//               result.append(",");
//            }
//        }
//
//        IO.println(result.toString());
//    }

    // Version 2
//    static void run(String[] args) {
//        for (String arg: args) {
//            String message = switch (arg) {
//                case "f" -> "Zwierzak idzie do przodu";
//                case "b" -> "Zwierzak idzie do tyłu";
//                case "r" -> "Zwierzak skręca w prawo";
//                case "l" -> "Zwierzak skręca w lewo";
//                default -> null;
//            };
//
//            if (message != null) {
//                IO.println(message);
//            }
//        }
//    }


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
