package net.benfro.expreval.rpn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.google.common.collect.Lists;

/**
 * https://www.web4college.com/converters/infix-to-postfix-prefix.php
 */

class RPNParserTest {

    RPNParser RPNParser;

    @BeforeEach
    void setUp() {
        RPNParser = new RPNParser();
    }

    @ParameterizedTest
    @CsvSource(value = {
        "22 , 22",
        "* , *",
        "2 + 3 , 2 3 +",
        "1 + 2 * 3, 1 2 3 * +",
//           "sin ( max ( 2 3 ) ÷ 3 × π ), 2 3 max 3 ÷ π × sin",
        "1 + ( 2 * 3 ), 1 2 3 * +",
        "( 1 + 2 ) * 3, 1 2 + 3 *",
        "1 * 2 + 3, 1 2 * 3 +",
        "( 1 * 2 ) + 3, 1 2 * 3 +",
        "1 * ( 2 + 3 ), 1 2 3 + *",
        "7 - 2 - 5, 7 2 - 5 -",
        "6 / 2 + 4, 6 2 / 4 +",
        "6 / ( 2 + 4 ), 6 2 4 + /",
//       NOK    "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3, 3 4 2 * 1 5 - / 2 3 ^ ^ +",
//       "sin ( abs ( -3 ) / 5 * pi ), -3 abs 5 / pi * sin",
//        "π ^ 2.5, π 2.5 ^ ",
        "2.0 ^ 2.5, 2.0 2.5 ^ ",
        "2.0 ^ 2.5 ^ 3.0, 2.0 2.5 3.0 ^ ^",
        "2 ^ 3 ^ 4 ^ 5, 2 3 4 5 ^ ^ ^",
        "( 2 ^ 3 ) ^ ( 4 ^ 5 ), 2 3 ^ 4 5 ^ ^",
        "2 ^ ( 3 ^ ( 4 ^ 5 ) ), 2 3 4 5 ^ ^ ^",
        "2 * ( 3 * ( 4 * 5 ) ), 2 3 4 5 * * *",
        "( ( ( 2 * 3 ) * 4 ) * 5 ), 2 3 * 4 * 5 * ",
        "2 * 3 * 4 * 5, 2 3 * 4 * 5 * ",
        "( 2 ^ 3 ) ^ ( 4 ^ 5 ), 2 3 ^ 4 5 ^ ^",
        "( ( 2 ^ 3 ) ^ 4 ) ^ 5 , 2 3 ^ 4 ^ 5 ^",
        "4 / ( 1 - 5 ) * 2, 4 1 5 - / 2 *",
        "4 / ( 1 - 5 ), 4 1 5 - /",
        "4 / 1 - 5 ^ 2, 4 1 / 5 2 ^ -",
        "4 / 3 ^ 2, 4 3 2 ^ /",
        "7 ^ 2 + 2 * 7 * 10 + 10 ^ 2, 7 2 ^ 2 7 * 10 * + 10 2 ^ +",
        "7 ^ 2 + 2 , 7 2 ^ 2 +",
        "4 / ( 1 - 5 ) ^ 2, 4 1 5 - 2 ^ /",
        "( 4 / ( 1 - 5 ) ) ^ 2, 4 1 5 - / 2 ^",
        "( 4 * ( 1 - 5 ) ) ^ 2, 4 1 5 - * 2 ^",
        "( 4 + ( 1 - 5 ) ) ^ 2, 4 1 5 - + 2 ^",
        "4 + ( 1 - 5 ) ^ 2, 4 1 5 - 2 ^ +",
        "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3, 3 4 2 * 1 5 - 2 3 ^ ^ / +",
        "( 4 * 2 ) / ( 1 - 5 ) ^ ( 2 ^ 3 ),  4 2 * 1 5 - 2 3 ^ ^ / ",
        "3 + 4 * 2 / ( 1 - 5 ) ^ ( 2 ^ 3 ), 3 4 2 * 1 5 - 2 3 ^ ^ / +",

    })
    void test(String indata, String expected) {
        List<String> s = Lists.newArrayList(indata.split(" "));
        assertEquals(expected, RPNParser.parse(s));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testNullEmpty(List<String> indata) {
        assertNotNull(RPNParser.parse(indata));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "4 / ( 1 - 5 ) ^ 2 ^ 3, 4 1 5 - 2 3 ^ ^ /",
        "4 / ( 1 - 5 ) ^ 2 ^ 3, 4 1 5 - 2 3 ^ ^ /",
        "4 / ( 1 - 5 ) ^ ( 2 ^ 3 ), 4 1 5 - 2 3 ^ ^ /",
        "4 / ( ( 1 - 5 ) ^ 2 ) ^ 3, 4 1 5 - 2 ^ 3 ^ /",
        "4 / ( ( ( 1 - 5 ) ^ 2 ) ^ 3 ), 4 1 5 - 2 ^ 3 ^ /",
        "4 / ( 1 - 5 ) ^ 2, 4 1 5 - 2 ^ /",
        "( 4 / ( 1 - 5 ) ) ^ 2, 4 1 5 - / 2 ^",
        "4 / 3 ^ 2, 4 3 2 ^ /",
        "( 4 / 3 ) ^ 2, 4 3 / 2 ^",
        "( 4 * 2 ) / ( 1 - 5 ) ^ ( 2 ^ 3 ), 4 2 * 1 5 - 2 3 ^ ^ / ",  // Weird. Two expressions and same value
        " ( ( 4 * 2 ) / ( 1 - 5 ) ) ^ ( 2 ^ 3 ), 4 2 * 1 5 - / 2 3 ^ ^ ",  // Weird. Two expressions and same value
        "( 4 * 2 ) / ( ( 1 - 5 ) ^ ( 2 ^ 3 ) ), 4 2 * 1 5 - 2 3 ^ ^ / ",  // Weird. Two expressions and same value
    })
    void testBugg(String indata, String expected) {
        List<String> s = Lists.newArrayList(indata.split(" "));
        assertEquals(expected, RPNParser.parse(s));
    }
}
