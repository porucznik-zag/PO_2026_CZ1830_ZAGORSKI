package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void eachLetterIsParsedIndividually() {
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD}, OptionsParser.parse(new String[]{"f"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.BACKWARD}, OptionsParser.parse(new String[]{"b"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.RIGHT}, OptionsParser.parse(new String[]{"r"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.LEFT}, OptionsParser.parse(new String[]{"l"}));
        assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(new String[]{"?"}));
    }

    @Test
    void aLotOfLettersAreParsed() {
        assertArrayEquals(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
        }, OptionsParser.parse(new String[]{"f", "b", "r", "l", "!", "r", "r", "b", "?"}));
    }

    @Test
    void incorrectArgsParsing() {
        assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(new String[]{"k", "a", "m", "y", "k", "?", "TAK", "a co", "?"}));
    }

}