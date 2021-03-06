package net.benfro.expreval.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Operator {
   PLUS("+", 2),
   MINUS("-", 2),
   DIV("/", 3),
   MULT("*", 3),
   POW("^", 4, true),
   MOD("%", 3),
   LPAR("(", 0),
   RPAR(")", 0),
   NOOP("", -1),
   ;

   private final String symbol;
   private final int precedence;
   private final boolean rightAssociative;
   public static final Map<String, Operator> STR_TO_OP = makeMapping();
   public static final List<String> OPERATOR_STRINGS = makeOperatorList();

   private static Map<String, Operator> makeMapping() {
      return Arrays.stream(values()).collect(Collectors.toMap(Operator::getSymbol, e -> e));
   }

   private static List<String> makeOperatorList() {
      return Collections.unmodifiableList(
              STR_TO_OP.keySet().stream().filter(k -> !"()".contains(k)).collect(Collectors.toList())
      );
   }

   public static Operator get(String opStr) {
      return STR_TO_OP.getOrDefault(opStr, Operator.NOOP);
   }

   public static boolean isOperator(String string) {
      return OPERATOR_STRINGS.contains(string);
   }

   Operator(String symbol, int precedence) {
      this(symbol, precedence, false);
   }

   Operator(String symbol, int precedence, boolean rightAssociative) {
      this.symbol = symbol;
      this.precedence = precedence;
      this.rightAssociative = rightAssociative;
   }

   public String getSymbol() {
      return symbol;
   }

   public int getPrecedence() {
      return precedence;
   }

   public boolean isRightAssociative() {
      return rightAssociative;
   }

   public boolean hasHigherPrecedenceThan(Operator other) {
      return getPrecedence() > other.getPrecedence();
   }

   public boolean isPrecedenceEqual(Operator other) {
      return getPrecedence() == other.getPrecedence();
   }
}
