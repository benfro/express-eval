package net.benfro.expreval.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

class OperatorTest {

   @Test
   void testHasHigherPrecedence() {
      assertTrue(Operator.MULT.hasHigherPrecedenceThan(Operator.ADD));
   }

   @Test
   void testHasHigherPrecedenceStatic() {
      assertTrue(Operator.get("*").hasHigherPrecedenceThan(Operator.get("+")));
   }

   @Test
   void testOperatorNamesHasNotParantheses() {
      assertFalse(Operator.OPERATOR_SYMBOLS.contains("("));
      assertFalse(Operator.OPERATOR_SYMBOLS.contains(")"));
   }

   @Test
   void testOperatorMapsHasNotParantheses() {
      Set<String> keySet = Operator.SYMBOL_OPERATOR_MAP.keySet();
      assertFalse(keySet.contains("("));
      assertFalse(keySet.contains(")"));
   }


}
