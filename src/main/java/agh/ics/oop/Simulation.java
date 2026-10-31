package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> animalsMoves;


    public Simulation(List<Vector2d> animalsPositions, List<MoveDirection> animalsMoves) {
        this.animals = new ArrayList<>();
        this.animalsMoves = animalsMoves;

        for (Vector2d position: animalsPositions) {
            this.animals.add(new Animal(position));
        }
    }

    public Simulation() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public void run() {
        int numberOfAnimals = animals.size();;
        int currentMoveIndex = 0;

        for (MoveDirection direction: animalsMoves) {
            int currentAnimalIndex = currentMoveIndex % numberOfAnimals;
            Animal currentAnimal = animals.get(currentAnimalIndex);

            boolean moveResult = currentAnimal.move(direction);

            if (moveResult) {
                IO.println("Zwierzę %d : %s".formatted(currentAnimalIndex, currentAnimal.toString()));
            }
            else {
                IO.println("Zwierzę %d : nieprawidłowy ruch".formatted(currentAnimalIndex));
            }

            currentMoveIndex++;
        }
    }
}
