package net.benfro.expreval.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleParserTest {

   private SimpleParser instance;

   @BeforeEach
   void setUp() {
      instance = new SimpleParser();
   }

   @ParameterizedTest
   @CsvSource(value = {
           "1 + 2 * 3, 1 2 3 * +",
           "1 + ( 2 * 3 ), 1 2 3 * +",
           "( 1 + 2 ) * 3, 1 2 + 3 *",
           "1 * 2 + 3, 1 2 * 3 +",
           "( 1 * 2 ) + 3, 1 2 * 3 +",
           "1 * ( 2 + 3 ), 1 2 3 + *",
           //"3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3, 3 4 2 * 1 5 − 2 3 ^ ^ / +",
   })
   void testThreeArgExpressions(String indata, String expected) {
      assertEquals(expected, instance.parse(indata));
   }

   @ParameterizedTest
   @CsvSource(value = {"1 + 1, 1 1 +", "1 - 2, 1 2 -", "1 * 2, 1 2 *"})
   void testTwoArgExpressions(String indata, String expected) {
      assertEquals(expected, instance.parse(indata));
   }

}
