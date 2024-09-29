package net.benfro.expreval.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

class DefaultFunctionExecutorTest {

//   @Test
//   void testHasHigherPrecedence() {
//      assertTrue(DefaultFunctions.MULT.hasHigherPrecedenceThan(DefaultFunctions.ADD));
//   }

//   @Test
//   void testHasHigherPrecedenceStatic() {
//      assertTrue(DefaultFunctions.get("*").hasHigherPrecedenceThan(DefaultFunctions.get("+")));
//   }

   @Test
   void testOperatorNamesHasNotParantheses() {
      assertFalse(DefaultFunctions.OPERATOR_SYMBOLS.contains("("));
      assertFalse(DefaultFunctions.OPERATOR_SYMBOLS.contains(")"));
   }

   @Test
   void testOperatorMapsHasNotParantheses() {
      Set<String> keySet = DefaultFunctions.SYMBOL_OPERATOR_MAP.keySet();
      assertFalse(keySet.contains("("));
      assertFalse(keySet.contains(")"));
   }


}
