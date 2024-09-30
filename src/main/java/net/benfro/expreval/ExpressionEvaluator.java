package net.benfro.expreval;

import net.benfro.expreval.evaluator.Evaluator;
import net.benfro.expreval.function.DefaultFunctions;
import net.benfro.expreval.rpn.RPNParser;

public class ExpressionEvaluator {

   private final Evaluator evaluator = new Evaluator();
   private final RPNParser parser = new RPNParser();

   public double evaluate(String expression) {
      return evaluator.evaluate(parser.parse(expression));
   }

   public static void main(String[] args) {
      DefaultFunctions.getInf0();

   }
}
