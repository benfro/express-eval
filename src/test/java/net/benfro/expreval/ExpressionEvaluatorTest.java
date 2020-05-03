package net.benfro.expreval;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionEvaluatorTest {

   @ParameterizedTest(name = "See: {0} = {1}")
   @CsvSource({
           "1 + 1, 2",
           "1 + 3 * 2, 7",
           "( 1 + 3 ) * 2, 8",
           "-3 - -3, 0",
           "25 / 5 / 5, 1",
           "25 / ( 5 / 5 ), 25",
   })
   void testEvaluate(String expression, double expectedResult) {
      assertEquals(expectedResult, new ExpressionEvaluator().evaluate(expression));
   }
}
