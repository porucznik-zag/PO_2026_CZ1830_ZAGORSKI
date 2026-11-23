package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int amountsOfUpdates = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        amountsOfUpdates++;

        IO.println(message);
        IO.println(worldMap);
        IO.println("Total updates so far: " + amountsOfUpdates);
    }
}
