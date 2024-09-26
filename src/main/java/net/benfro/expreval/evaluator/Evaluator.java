package net.benfro.expreval.evaluator;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import net.benfro.expreval.OperationLookup;
import net.benfro.expreval.parser.Function;
import net.benfro.expreval.parser.Operator;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.Optional;

public class Evaluator {

   public double evaluate(String rpnExpression) {
      final LinkedList<NumericToken<Double>> stack = Lists.newLinkedList();
      String[] split = rpnExpression.split(" ");
      for (String part : split) {
         part = part.trim();
         if (NumberUtils.isParsable(part)) {
            stack.push(NumericToken.ofDouble(part));
         } else if(Operator.isOperator(part)) {
            final String temp = part;
            Operation operation = Optional.of(OperationLookup.get(Operator.get(part)))
                .orElseThrow(() -> new IllegalStateException("The Part " + temp + " has no Operation"));
            if (operation.getType().isUnary()) {
               NumericToken<Double> val = stack.pop();
               stack.push(operation.doOp(val));
            } else if (operation.getType().isBinary()) {
               NumericToken<Double> second = stack.pop();
               NumericToken<Double> first = stack.pop();
               stack.push(operation.doOp(first, second));
            } else {
               stack.push(operation.doOp());
            }
         }
      }
      return (stack.pop()).value();
   }

}
