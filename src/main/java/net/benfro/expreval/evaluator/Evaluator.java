package net.benfro.expreval.evaluator;

import com.google.common.collect.Lists;
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
         } else {
            NumericToken<Double> second = stack.pop();
            NumericToken<Double> first = stack.pop();
            if (Operator.OPERATOR_STRINGS.contains(part)) {
               stack.push(first.with(second).doOperation(part));
            } else {
               throw new IllegalArgumentException("Poff!");
            }
         }
      }
      return (stack.pop()).get();
   }

}
