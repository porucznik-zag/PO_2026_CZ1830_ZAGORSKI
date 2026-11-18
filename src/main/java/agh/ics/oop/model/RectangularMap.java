package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;

    private final Vector2d mapStart;
    private final Vector2d mapStop;

    public RectangularMap(int width, int height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Map width and height cannot be less than 0.");
        this.mapStart = new Vector2d(0, 0);
        this.mapStop = mapStart.add(new Vector2d(width - 1, height - 1));
    }

    public RectangularMap() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(mapStart) && position.precedes(mapStop);
    }

    @Override
    public MapConstraints getCurrentConstraints() {
        return new MapConstraints(mapStart, mapStop);
    }
}
