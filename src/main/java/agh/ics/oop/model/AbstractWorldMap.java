package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals;
    protected final MapVisualizer visualizer;

    protected AbstractWorldMap() {
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }


    @Override
    public boolean place(WorldElement element) {
        if (!(element instanceof Animal animal) || !canMoveTo(element.getPosition()))
            return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        if (animals.get(oldPosition) != animal) {
            return;
        }

        boolean result = animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (result && !oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
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

    public abstract MapConstraints getCurrentConstraints();

    @Override
    public String toString() {
        MapConstraints constraints = getCurrentConstraints();
        return visualizer.draw(constraints.lowerLeft(), constraints.upperRight());
    }
}
