package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> animalsMoves;
    private final WorldMap map;


    public Simulation(List<Vector2d> animalsPositions, List<MoveDirection> animalsMoves, WorldMap map) {
        this.animals = new ArrayList<>();
        this.animalsMoves = animalsMoves;
        this.map = map;

        for (Vector2d position: animalsPositions) {
            Animal animal = new Animal(position);
            if (this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public Simulation() {
        this(new ArrayList<>(), new ArrayList<>(), new RectangularMap());
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public void run() {
        int numberOfAnimals = animals.size();
        int currentMoveIndex = 0;

        if (numberOfAnimals == 0) {
            return;
        }

        for (MoveDirection direction: animalsMoves) {
            int currentAnimalIndex = currentMoveIndex % numberOfAnimals;
            Animal currentAnimal = animals.get(currentAnimalIndex);

            map.move(currentAnimal, direction);
            IO.println(map.toString());

            currentMoveIndex++;
        }
    }
}
