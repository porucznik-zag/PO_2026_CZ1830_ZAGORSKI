package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private static final int DEFAULT_WIDTH = 4;
    private static final int DEFAULT_HEIGHT = 4;

    private final Map<Vector2d, Animal> animals;
    private final Vector2d mapStart;
    private final Vector2d mapStop;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height) {
        animals = new HashMap<>();
        this.mapStart = new Vector2d(0, 0);
        this.mapStop = mapStart.add(new Vector2d(width, height));
        this.visualizer = new MapVisualizer(this);
    }

    public RectangularMap() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        boolean result = animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (result && !oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position != null && position.follows(mapStart) && position.precedes(mapStop) && objectAt(position) == null;
    }

    @Override
    public String toString() {
        return visualizer.draw(this.mapStart, this.mapStop);
    }
}
