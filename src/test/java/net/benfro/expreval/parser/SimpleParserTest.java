package net.benfro.expreval.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleParserTest {

   @Test
   void testOnePlusOne() {
      assertEquals("1 1 +", new SimpleParser().parse("1 + 1"));
   }

   @Test
   void testOnePlusTwo() {
      assertEquals("1 2 +", new SimpleParser().parse("1 + 2"));
   }
}
