package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int numberOfFields;
    private final int fieldsToGenerate;
    private final Vector2d start;
    private final int width;

    public RandomPositionGenerator(Vector2d start, Vector2d stop, int amount) {
        if (start == null || stop == null) {
            throw new IllegalArgumentException("Vectors start and stop cannot be null.");
        }
        if (!start.precedes(stop)) {
            throw new IllegalArgumentException("Vector start %s must precedes vector stop %s.".formatted(start, stop));
        }

        int width = stop.getX() - start.getX() + 1;
        int height = stop.getY() - start.getY() + 1;
        int fields = width * height;

        if (amount < 0 || amount > fields) {
            throw new IllegalArgumentException("Argument amount must be in range [0, %d]".formatted(fields));
        }

        this.start = start;
        this.width = width;
        this.numberOfFields = fields;
        this.fieldsToGenerate = amount;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<>() {
            private final HashMap<Integer, Integer> usedIndexes = new HashMap<>();
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < fieldsToGenerate && i < numberOfFields;
            }

            @Override
            public Vector2d next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                // algorytm wzorowany na algorytmie Fishera-Yatesa
                int r = ThreadLocalRandom.current().nextInt(i, numberOfFields);
                int index = usedIndexes.getOrDefault(r, r);
                int head = usedIndexes.getOrDefault(i, i);
                usedIndexes.put(r, head);


                i += 1;
                return start.add(new Vector2d(index % width, index / width));
            }
        };
    }
}
