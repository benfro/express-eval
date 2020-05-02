package net.benfro.expreval.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperatorTest {

   @Test
   void testHasHigherPrecedence() {
      assertTrue(Operator.MULT.hasHigherPrecedenceThan(Operator.PLUS));
   }

   @Test
   void testHasHigherPrecedenceStatic() {
      assertTrue(Operator.get("*").hasHigherPrecedenceThan(Operator.get("+")));
   }

   @Test
   void testOperatorNamesHasNotParantheses() {
      assertFalse(Operator.OPERATOR_STRINGS.contains("("));
      assertFalse(Operator.OPERATOR_STRINGS.contains(")"));
   }
}
