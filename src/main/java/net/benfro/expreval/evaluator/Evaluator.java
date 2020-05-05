package net.benfro.expreval.evaluator;

import com.google.common.collect.Lists;
import net.benfro.expreval.parser.Function;
import net.benfro.expreval.parser.Operator;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;

public class Evaluator {

   public double evaluate(String rpnExpression) {
      final LinkedList<NumericToken<Double>> stack = Lists.newLinkedList();
      String[] split = rpnExpression.split(" ");
      for (String part : split) {
         part = part.trim();
         if (NumberUtils.isParsable(part)) {
            stack.push(NumericToken.DoubleNumericToken.ofDoubleToken(part));
         } else if(Operator.OPERATOR_STRINGS.contains(part)){
            NumericToken<Double> second = stack.pop();
            NumericToken<Double> first = stack.pop();
            stack.push(first.with(second).doOperation(part));
         } else if (Function.isFunction(part)) {
            NumericToken<Double> first = stack.pop();
            stack.push(Function.get(part).calculate(first.value));
         }
      }
      return (stack.pop()).get();
   }

}
