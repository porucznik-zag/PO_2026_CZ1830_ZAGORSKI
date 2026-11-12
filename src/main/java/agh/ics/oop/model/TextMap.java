package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer>{
    private final List<String> texts;

    public TextMap() {
        this.texts = new ArrayList<>();
    }

    @Override
    public boolean place(String text) {
        texts.add(text);
        return true;
    }

    @Override
    public void move(String text, MoveDirection direction) {
        int position = texts.indexOf(text);
        int newPosition = position;

        switch (direction) {
            case FORWARD, RIGHT -> newPosition += 1;
            case BACKWARD, LEFT -> newPosition -= 1;
        }

        if (canMoveTo(newPosition)) {
            texts.set(position, texts.get(newPosition));
            texts.set(newPosition, text);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position < texts.size();
    }

    @Override
    public String objectAt(Integer position) {
        return texts.get(position);
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return isOccupied(position) && position >= 0;
    }

    @Override
    public String toString() {
        return texts.toString();
    }
}
