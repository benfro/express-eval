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
           "1 + 2 * 3, 1 2 3 * +", //
//           "sin ( max ( 2 3 ) ÷ 3 × π ), 2 3 max 3 ÷ π × sin",
           "1 + ( 2 * 3 ), 1 2 3 * +",
//           "( 1 + 2 ) * 3, 1 2 + 3 *", //
           "1 * 2 + 3, 1 2 * 3 +", //
           "( 1 * 2 ) + 3, 1 2 * 3 +", //
//           "1 * ( 2 + 3 ), 1 2 3 + *",
//           "7 - 2 - 5, 7 2 - 5 -", //
//           "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3, 3 4 2 * 1 5 - 2 3 ^ ^ / +",
//           "sin ( abs ( -3 ) / 5 * pi ), -3 abs 5 / pi * sin",
           "pi ^ 2.5, pi 2.5 ^ ",
           "2.0 ^ 2.5, 2.0 2.5 ^ ",//
           "2.0 ^ 2.5 ^ 3.0, 2.0 2.5 3.0 ^ ^",//
   })
   void testThreeArgExpressions(String indata, String expected) {
      assertEquals(expected, instance.parse(indata));
   }

   @ParameterizedTest
   @CsvSource(value = {
           "1 + 1, 1 1 +",
           "1 - 2, 1 2 -",
           "1 * 2, 1 2 *",
           "1.5 + 2.73, 1.5 2.73 +"
   })
   void testTwoArgExpressions(String indata, String expected) {
      assertEquals(expected, instance.parse(indata));
   }

}
