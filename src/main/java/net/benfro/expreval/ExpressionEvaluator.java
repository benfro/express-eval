package net.benfro.expreval;

import net.benfro.expreval.evaluator.Evaluator;
import net.benfro.expreval.parser.ExprParser;
import net.benfro.expreval.parser.SimpleParser;

public class ExpressionEvaluator {

   private final Evaluator evaluator = new Evaluator();
   private final ExprParser parser = new SimpleParser();

   public double evaluate(String expression) {
      return evaluator.evaluate(parser.parse(expression));
   }

}
