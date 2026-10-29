package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    // stream danych do testu parametryzowanego
    static Stream<Arguments> provideIndividualLetters() {
        return Stream.of(
                Arguments.of(new String[]{"f"}, new MoveDirection[]{MoveDirection.FORWARD}),
                Arguments.of(new String[]{"b"}, new MoveDirection[]{MoveDirection.BACKWARD}),
                Arguments.of(new String[]{"r"}, new MoveDirection[]{MoveDirection.RIGHT}),
                Arguments.of(new String[]{"l"}, new MoveDirection[]{MoveDirection.LEFT}),
                Arguments.of(new String[]{"?"}, new MoveDirection[]{})
        );
    }

    // test parametryzowany wykorzystujący stream
    @ParameterizedTest(name = "{0} should be parsed to {1}")
    @DisplayName("Parsing each letter individually")
    @MethodSource("provideIndividualLetters")
    void eachLetterIsParsedIndividually(String[] input, MoveDirection[] expectedOutput) {
        // when
        MoveDirection[] result = OptionsParser.parse(input);

        // then
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    void differentLettersAreParsed() {
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