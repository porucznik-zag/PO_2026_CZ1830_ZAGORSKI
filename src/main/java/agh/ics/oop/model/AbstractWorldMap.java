package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals;
    protected final MapVisualizer visualizer;
    private final List<MapChangeListener> observers = new ArrayList<>();

    protected AbstractWorldMap() {
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }


    @Override
    public void place(WorldElement element) {
        if (!(element instanceof Animal animal)) {
            throw new IllegalArgumentException("Only Animal objects can be placed on this map.");
        }
        if (!canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position " + animal.getPosition() + " is not correct");
        }

        animals.put(animal.getPosition(), animal);
        mapChanged("Animal placed at " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        if (animals.get(oldPosition) != animal) {
            return;
        }

        boolean result = animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (result) {
            if (!oldPosition.equals(newPosition)) {
                animals.remove(oldPosition);
                animals.put(newPosition, animal);
                mapChanged("Animal moved from %s to %s".formatted(oldPosition, newPosition));
            } else {
                mapChanged("Animal at %s rotated (%s)".formatted(newPosition, direction));
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position != null && !(objectAt(position) instanceof Animal);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
}
