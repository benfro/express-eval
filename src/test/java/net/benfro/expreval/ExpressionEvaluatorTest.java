package net.benfro.expreval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ExpressionEvaluatorTest {

    @ParameterizedTest(name = "See: {0} = {1}")
    @CsvSource({
        "1 +1, 2",
        "1+3* 2, 7",
        "(1 + 3) * 2, 8",
        "7 - 3, 4",
        "3 - 7, -4",
//        "-3 - -3, 0",
        "25 / 5 / 5, 1",
        "25 / ( 5 / 5 ), 25",
        "5 ^ 2, 25",
        "5 ^ 2 + 5, 30",
        "5 +5 ^2, 30",
        "( 5 + 5 ) ^ 2, 100",
        "9 % 4, 1",
        "10 % 4 * 5, 10",
        "10 % ( 2 + 1 ), 1",
        "2^2+2, 6",
        "2 ^ ( 2 + 2 ), 16",
    })
    void testRealWorldInput(String expression, double expectedResult) {
        assertEquals(expectedResult, new ExpressionEvaluator().evaluate(expression));
    }

    @ParameterizedTest(name = "See: {0} = {1}")
    @CsvSource({
        "1 + 1, 2",
        "1 + 3 * 2, 7",
        "( 1 + 3 ) * 2, 8",
        "7 - 3, 4",
        "3 - 7, -4",
//        "-3 - -3, 0",
        "25 / 5 / 5, 1",
        "25 / ( 5 / 5 ), 25",
        "5 ^ 2, 25",
        "5 ^ 2 + 5, 30",
        "5 + 5 ^ 2, 30",
        "( 5 + 5 ) ^ 2, 100",
        "9 % 4, 1",
        "10 % 4 * 5, 10",
        "10 % ( 2 + 1 ), 1",
        "2 ^ 2 + 2, 6",
        "2 ^ ( 2 + 2 ), 16",
    })
    void testEvaluateAlgebraicCalculations(String expression, double expectedResult) {
        assertEquals(expectedResult, new ExpressionEvaluator().evaluate(expression));
    }

    @ParameterizedTest(name = "See: {0} = {1}")
    @CsvSource({
//        "abs ( -3 ), 3",
        "pow ( 10 3 ), 1000",
        "abs ( 3 - 7 ), 4",
        "pow 10 3, 1000",
        "pi, 3.141592653589793",
        "sin ( pi / 2 + 2 * pi ), 1",
        "inv 2, 0.5",
        "pi * 2, 6.283185307179586",
        "pow ( ( 1 + 1 / 10 ) 10 ), 2.5937424601000023",
        "loga 100 10, 2",
        "lg 100, 2",
        "ln ( 100 ) / ln ( 10 ), 2",
        "loga ( 100 10 ), 2"

    })
    void testEvaluateFunctions(String expression, double expectedResult) {
        assertEquals(expectedResult, new ExpressionEvaluator().evaluate(expression));
    }

    @Test
    void debug() {
//        assertEquals(2, new ExpressionEvaluator().evaluate("ln ( 100 ) / ln ( 10 )"));
//        assertEquals(2, new ExpressionEvaluator().evaluate("loga ( 100 10 )"));
    }
}
