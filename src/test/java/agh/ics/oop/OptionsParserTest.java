package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.UnsupportedParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    // stream danych do testu parametryzowanego
    static Stream<Arguments> provideIndividualLetters() {
        return Stream.of(
                Arguments.of(new String[]{"f"}, new ArrayList<>(List.of(MoveDirection.FORWARD))),
                Arguments.of(new String[]{"b"}, new ArrayList<>(List.of(MoveDirection.BACKWARD))),
                Arguments.of(new String[]{"r"}, new ArrayList<>(List.of(MoveDirection.RIGHT))),
                Arguments.of(new String[]{"l"}, new ArrayList<>(List.of(MoveDirection.LEFT))),
                Arguments.of(new String[]{"?"}, new ArrayList<>())
        );
    }

    // test parametryzowany wykorzystujący stream
    @ParameterizedTest(name = "{0} should be parsed to {1}")
    @DisplayName("Parsing each letter individually")
    @MethodSource("provideIndividualLetters")
    void eachLetterIsParsedIndividually(String[] input, List<MoveDirection> expectedOutput) throws UnsupportedParameterException {
        // when
        // List<MoveDirection> result = assertDoesNotThrow(() -> OptionsParser.parse(input)); // można też i tak
        List<MoveDirection> result = OptionsParser.parse(input);

        // then
        assertEquals(expectedOutput, result);
    }

    @Test
    void differentLettersAreParsed() throws UnsupportedParameterException {
        assertEquals(new ArrayList<>(List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        )), OptionsParser.parse(new String[]{"f", "b", "r", "l", "!", "r", "r", "b", "?"}));
    }

    @Test
    void incorrectArgsParsing() throws UnsupportedParameterException {
        assertEquals(new ArrayList<>(), OptionsParser.parse(new String[]{"k", "a", "m", "y", "k", "?", "TAK", "a co", "?"}));
    }

}