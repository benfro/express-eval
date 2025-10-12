package net.benfro.expreval.core;

import java.util.List;

import net.benfro.expreval.core.evaluator.RPNExpressionEvaluatorImpl;
import net.benfro.expreval.core.function.DefaultFunctions;
import net.benfro.expreval.core.rpn.RPNParser;
import net.benfro.expreval.core.strparse.ExpressionStringParser;
import net.benfro.expreval.core.strparse.ExpressionStringParserImpl;

public class ExpressionEvaluator implements ArithmeticExpressionEvaluator {

   private final RPNExpressionEvaluatorImpl RPNExpressionEvaluatorImpl = new RPNExpressionEvaluatorImpl();
   private final RPNParser rpnParser = new RPNParser();
   private final ExpressionStringParser inputParser = new ExpressionStringParserImpl();

   public double evaluate(String expression) {
      List<String> inputToRpnParser = inputParser.parse(expression);
      String rpnString = rpnParser.parse(inputToRpnParser);
      return RPNExpressionEvaluatorImpl.evaluate(rpnString);
   }

   public static void main(String[] args) {
      DefaultFunctions.getInf0();

      ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

      System.out.println(expressionEvaluator.evaluate("1 + 2"));
      System.out.println(expressionEvaluator.evaluate("3/4"));

   }
}
