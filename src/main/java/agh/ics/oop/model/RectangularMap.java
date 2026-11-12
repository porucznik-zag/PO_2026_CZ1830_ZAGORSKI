package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap<T extends WorldObject> implements WorldMap<T, Vector2d> {
    private static final int DEFAULT_WIDTH = 4;
    private static final int DEFAULT_HEIGHT = 4;

    private final Map<Vector2d, T> objects;
    private final Vector2d mapStart;
    private final Vector2d mapStop;
    private final MapVisualizer<T> visualizer;

    public RectangularMap(int width, int height) {
        objects = new HashMap<>();
        this.mapStart = new Vector2d(0, 0);
        this.mapStop = mapStart.add(new Vector2d(width, height));
        this.visualizer = new MapVisualizer<>(this);
    }

    public RectangularMap() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public boolean place(T object) {
        if (!canMoveTo(object.getPosition()))
            return false;

        objects.put(object.getPosition(), object);
        return true;
    }

    @Override
    public void move(T object, MoveDirection direction) {
        if (object instanceof Animal animal) {
            Vector2d oldPosition = animal.getPosition();
            boolean result = animal.move(direction, this);
            Vector2d newPosition = animal.getPosition();

            if (result && !oldPosition.equals(newPosition)) {
                objects.remove(oldPosition);
                objects.put(newPosition, object);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objects.containsKey(position);
    }

    @Override
    public T objectAt(Vector2d position) {
        return objects.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position != null && position.follows(mapStart) && position.precedes(mapStop) && !isOccupied(position);
    }

    @Override
    public String toString() {
        return visualizer.draw(this.mapStart, this.mapStop);
    }
}
