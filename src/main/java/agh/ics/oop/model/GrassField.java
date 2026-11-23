package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    public static final int DEFAULT_AMOUNT_OF_GRASS_CLUMPS = 10;

    private final Map<Vector2d, Grass> grassClumps;

    public GrassField(int amountOfGrassClumps) {
        this.grassClumps = new HashMap<>();

        Vector2d start = new Vector2d(0, 0);
        Vector2d stop = new Vector2d((int) sqrt(amountOfGrassClumps * 10), (int) sqrt(amountOfGrassClumps * 10));
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(start, stop, amountOfGrassClumps);

        for(Vector2d grassPosition : randomPositionGenerator) {
            Grass grass = new Grass(grassPosition);
            place(grass);
        }
    }

    public GrassField() {
        this(DEFAULT_AMOUNT_OF_GRASS_CLUMPS);
    }

    @Override
    public boolean place(WorldElement element) {
        // przypadek dla Grass
        if (element instanceof Grass grass) {
            if (isOccupied(grass.getPosition())) {
                return false;
            }
            grassClumps.put(grass.getPosition(), grass);
            return true;
        }

        // domyślny przypadek dla Animal z klasy AbstractWorldMap
        return super.place(element);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animalAtPosition = super.objectAt(position);
        if (animalAtPosition != null) {
            return animalAtPosition;
        }
        else {
            return grassClumps.get(position);
        }
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grassClumps.values());

        return elements;
    }

    @Override
    public MapConstraints getCurrentConstraints() {
        List<WorldElement> elements = getElements();

        // przypadek, w którym mapa jest pusta
        if (elements.isEmpty()) {
            return new MapConstraints(new Vector2d(0, 0), new Vector2d(0, 0));
        }

        Vector2d lowerLeftConstraint = elements.getFirst().getPosition();
        Vector2d upperRightConstraint = lowerLeftConstraint;

        for (WorldElement element : elements) {
            Vector2d position = element.getPosition();
            lowerLeftConstraint = lowerLeftConstraint.lowerLeft(position);
            upperRightConstraint = upperRightConstraint.upperRight(position);
        }

        return new MapConstraints(lowerLeftConstraint, upperRightConstraint);
    }
}
