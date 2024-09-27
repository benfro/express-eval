package net.benfro.expreval.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.common.collect.Lists;

class EvenSimplerParserTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1 + 2 * 3, 1 2 3 * +", //
//           "sin ( max ( 2 3 ) ÷ 3 × π ), 2 3 max 3 ÷ π × sin",
        "1 + ( 2 * 3 ), 1 2 3 * +",
        "( 1 + 2 ) * 3, 1 2 + 3 *", //
        "1 * 2 + 3, 1 2 * 3 +", //
        "( 1 * 2 ) + 3, 1 2 * 3 +", //
        "1 * ( 2 + 3 ), 1 2 3 + *",
        "7 - 2 - 5, 7 2 - 5 -", //
        "6 / 2 + 4, 6 2 / 4 +", //
        "6 / ( 2 + 4 ), 6 2 4 + /", //
//           "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3, 3 4 2 * 1 5 - 2 3 ^ ^ / +",
//           "sin ( abs ( -3 ) / 5 * pi ), -3 abs 5 / pi * sin",
//        "pi ^ 2.5, pi 2.5 ^ ",
        "2.0 ^ 2.5, 2.0 2.5 ^ ",//
        "2.0 ^ 2.5 ^ 3.0, 2.0 2.5 3.0 ^ ^",//
        "2 ^ 3 ^ 4 ^ 5, 2 3 4 5 ^ ^ ^",//
        "( 2 ^ 3 ) ^ ( 4 ^ 5 ), 2 3 ^ 4 5 ^ ^",//
        "( ( 2 ^ 3 ) ^ 4 ) ^ 5 , 2 3 ^ 4 ^ 5 ^",//
    })
    void test(String indata, String expected) {
        EvenSimplerParser evenSimplerParser = new EvenSimplerParser();
        List<String> s = Lists.newArrayList(indata.split(" "));
        assertEquals(expected, evenSimplerParser.shuntAlgoritm(s));
    }
}
