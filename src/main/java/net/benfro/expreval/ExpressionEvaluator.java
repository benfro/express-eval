package net.benfro.expreval;

import java.util.List;

import net.benfro.expreval.evaluator.Evaluator;
import net.benfro.expreval.function.DefaultFunctions;
import net.benfro.expreval.rpn.RPNParser;
import net.benfro.expreval.strparse.StringParser;

public class ExpressionEvaluator {

   private final Evaluator evaluator = new Evaluator();
   private final RPNParser rpnParser = new RPNParser();
   private final StringParser inputParser = new StringParser();

   public double evaluate(String expression) {
      List<String> inputToRpnParser = inputParser.parse(expression);
      String rpnString = rpnParser.parse(inputToRpnParser);
      return evaluator.evaluate(rpnString);
   }

   public static void main(String[] args) {
      DefaultFunctions.getInf0();

      ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

      System.out.println(expressionEvaluator.evaluate("1 + 2"));
      System.out.println(expressionEvaluator.evaluate("3/4"));

   }
}
