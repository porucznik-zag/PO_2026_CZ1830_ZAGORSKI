package agh.ics.oop.model;

public class UnsupportedParameterException extends Exception {
    public UnsupportedParameterException(String parameter) {
        super("Unsupported parameter: " + parameter);
    }
}
