package net.benfro.expreval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RPNEvaluatorTest {

   private RPNEvaluator evaluator;

   @BeforeEach
   void setUp() {
      evaluator = new RPNEvaluator();
   }

   @DisplayName("Adding RPN")
   @ParameterizedTest(name = "\"{1}\" should result in {0}")
   @CsvSource({"2, 1 1 +", "3.5, 1.5 2 +", "6, 1 2 3 + +", "-2, -0.5 -1.5 +"})
   void testSimpleAddition(double result, String rpnExpression) {
      assertEquals(result, evaluator.evaluate(rpnExpression));
   }

   @DisplayName("Subtracting RPN")
   @ParameterizedTest(name = "\"{1}\" should result in {0}")
   @CsvSource({"0, 1 1 -", "-0.5, 2 1.5 -", "0, 1 3 2 - +", "4, 1 2 3 + -"})
   void testSimpleSubtractions(double result, String rpnExpression) {
      assertEquals(result, evaluator.evaluate(rpnExpression));
   }

   @DisplayName("Multiplicating RPN")
   @ParameterizedTest(name = "\"{1}\" should result in {0}")
   @CsvSource({"1, 1 1 *", "3, 2 1.5 *", "6, 1 3 2 * *", "35, 7 2 3 + *", "-5, -0.5 10 *"})
   void testSimpleMultiplications(double result, String rpnExpression) {
      assertEquals(result, evaluator.evaluate(rpnExpression));
   }

   @DisplayName("Dividing RPN")
   @ParameterizedTest(name = "\"{1}\" should result in {0}")
   @CsvSource({"1, 1 1 /", "2, 3 6 /", "6, 0.25 2 3 / /"})
   void testSimpleDivisions(double result, String rpnExpression) {
      assertEquals(result, evaluator.evaluate(rpnExpression));
   }
}
