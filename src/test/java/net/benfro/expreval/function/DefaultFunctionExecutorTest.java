package net.benfro.expreval.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultFunctionExecutorTest {

   @Test
   void testHasHigherPrecedence() {
      assertTrue(DefaultFunctions.MULT.comparePrecedenceWith(DefaultFunctions.ADD) > 0);
   }

   @Test
   void testHasHigherPrecedenceStatic() {
      assertTrue(DefaultFunctions.find("*").comparePrecedenceWith(DefaultFunctions.find("+")) > 0);
   }

}
