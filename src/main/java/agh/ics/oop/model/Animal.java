package agh.ics.oop.model;

public class Animal {
    protected static final MapDirection DEFAULT_ORIENTATION = MapDirection.NORTH;
    protected static final Vector2d DEFAULT_POSITION = new Vector2d(2, 2);
    protected static final Vector2d MAP_START = new Vector2d(0, 0);
    protected static final Vector2d MAP_STOP = new Vector2d(4, 4);

    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.orientation = DEFAULT_ORIENTATION;
        this.position = position;
    }

    public Animal() {
        this(DEFAULT_POSITION);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean move(MoveDirection direction) {
        Vector2d newPosition = null;
        MapDirection newOrientation = null;

        switch (direction) {
            case FORWARD-> newPosition = position.add(orientation.toUnitVector());
            case BACKWARD -> newPosition = position.add(orientation.toUnitVector().opposite());
            case RIGHT -> newOrientation = orientation.next();
            case LEFT -> newOrientation = orientation.previous();
        }

        // orientation
        if (newOrientation != null) {
            this.orientation = newOrientation;
            return true;
        }

        // position
        if (newPosition != null && newPosition.follows(MAP_START) && newPosition.precedes(MAP_STOP)) {
            this.position = newPosition;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return  "[%d,%d][%s]".formatted(position.getX(), position.getY(), orientation.toString());
    }
}
