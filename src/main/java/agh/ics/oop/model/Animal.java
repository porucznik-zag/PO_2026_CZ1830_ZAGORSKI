package agh.ics.oop.model;

public class Animal implements WorldObject {
    protected static final MapDirection DEFAULT_ORIENTATION = MapDirection.NORTH;
    protected static final Vector2d DEFAULT_POSITION = new Vector2d(0, 0);

    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.orientation = DEFAULT_ORIENTATION;
        this.position = position;
    }

    public Animal() {
        this(DEFAULT_POSITION);
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean move(MoveDirection direction, MoveValidator<Vector2d> validator) {
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
        if (newPosition != null && validator.canMoveTo(newPosition)) {
            this.position = newPosition;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return  "%s".formatted(orientation.toStringSchematic());
    }
}
