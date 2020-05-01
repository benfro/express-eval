package net.benfro.expreval.parser;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Operator {
   PLUS("+", 2),
   MINUS("-", 2),
   DIV("/", 3),
   MULT("*", 3),
   POW("^", 2),
   LPAR("(", 0),
   RPAR(")", 0),
   NOOP("", -1),
   ;

   private final String symbol;
   private final int precedence;
   public static final Map<String, Operator> STR_TO_OP = makeMapping();

   private static Map<String, Operator> makeMapping() {
      return Arrays.stream(values()).collect(Collectors.toMap(Operator::getSymbol, e -> e));
   }

   public static Operator get(String opStr) {
      return STR_TO_OP.getOrDefault(opStr, Operator.NOOP);
   }

   Operator(String symbol, int precedence) {
      this.symbol = symbol;
      this.precedence = precedence;
   }

   public String getSymbol() {
      return symbol;
   }

   public int getPrecedence() {
      return precedence;
   }

   public boolean hasHigherPrecedenceThan(Operator other) {
      return getPrecedence() > other.getPrecedence();
   }


}
