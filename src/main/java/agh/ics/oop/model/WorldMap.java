package agh.ics.oop.model;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that position class and MoveDirection classes are defined.
 *
 * @author apohllo, idzik, porucznik-zag
 */
public interface WorldMap<T, P> extends MoveValidator<P> {

    /**
     * Place a new object on the map.
     *
     * @param object The object to be placed on the map.
     * @return True if the object was placed. The rules for valid placement are the same as for movement.
     */
    boolean place(T object);

    /**
     * Moves an object (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(T object, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMoveTo since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(P position);

    /**
     * Return an object at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    T objectAt(P position);
}