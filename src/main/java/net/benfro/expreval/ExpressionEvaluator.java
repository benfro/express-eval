package net.benfro.expreval;

import com.google.common.collect.Lists;
import net.benfro.expreval.evaluator.Evaluator;
import net.benfro.expreval.parser2.EvenSimplerParser;

public class ExpressionEvaluator {

   private final Evaluator evaluator = new Evaluator();
   private final EvenSimplerParser parser = new EvenSimplerParser();

   public double evaluate(String expression) {
      return evaluator.evaluate(parser.parse(expression));
   }

}
