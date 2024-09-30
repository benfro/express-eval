package net.benfro.expreval.strparse;

import static net.benfro.expreval.strparse.StringParser.CharClass.BREAK;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringParserTest {

    StringParser instance;

    @BeforeEach
    void setUp() {
        instance = new StringParser();
    }

    @ParameterizedTest
    @CsvSource(value = {
//        "A , A",
//        "B , B",
        "3+1 |3 + 1",
        "31+1 |31 + 1",
        "31.7+14.8|31.7 + 14.8",
        "max(1,2)|max ( 1 2 )",
        "max ( 1 , 2 )|max ( 1 2 )",
    }, delimiter='|')
    void testOne(String input, String expected) {
        List<String> list = Arrays.stream(expected.split(" ")).map(String::trim).toList();
        assertEquals(list, instance.parse(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "NUM |1234567890.",
        "LETTER |ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz",
        "SIGN |+-/*%^",
//        "BREAK |' ',",
    }, delimiter = '|')
    void testTwo(StringParser.CharClass clazz, String expected) {
        Stream<Character> characterStream = expected.chars().mapToObj(i -> (char) i);
        characterStream.forEach(character -> {
            assertEquals(clazz,
                StringParser.CharClass.classify(character),
                () -> String.format("Char %s vas not class %s", character, clazz));
        });

    }

    @Test
    void test5() {
        assertEquals(BREAK,StringParser.CharClass.classify(' '));
        assertEquals(BREAK,StringParser.CharClass.classify(','));
    }
}
