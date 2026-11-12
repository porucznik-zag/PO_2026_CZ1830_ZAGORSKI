package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> objects;
    private final List<MoveDirection> objectsMoves;
    private final WorldMap<T, P> map;


    public Simulation(List<T> objects, List<MoveDirection> objectsMoves, WorldMap<T, P> map) {
        this.objects = new ArrayList<>();
        this.objectsMoves = objectsMoves;
        this.map = map;

        for (T object: objects) {
            if (this.map.place(object)) {
                this.objects.add(object);
            }
        }
    }

    public List<T> getObjects() {
        return this.objects;
    }

    public void run() {
        int numberOfAnimals = objects.size();
        int currentMoveIndex = 0;

        if (numberOfAnimals == 0) {
            return;
        }

        for (MoveDirection direction: objectsMoves) {
            int currentAnimalIndex = currentMoveIndex % numberOfAnimals;
            T currentObject = objects.get(currentAnimalIndex);

            map.move(currentObject, direction);
            IO.println(map.toString());

            currentMoveIndex++;
        }
    }
}
