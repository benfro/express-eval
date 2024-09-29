package net.benfro.expreval.evaluator;

import com.google.common.collect.Lists;
import net.benfro.expreval.LookupService;
import net.benfro.expreval.function.ExecutableFunction;
import net.benfro.expreval.function.ExecutableInfo;
import net.benfro.expreval.function.FunctionInfo;
import net.benfro.expreval.rpn.DefaultLookupService;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.Optional;

// TODO Rework
public class Evaluator {

   private final LookupService lookup = new DefaultLookupService();

   public double evaluate(String rpnExpression) {
      final LinkedList<NumericToken<Double>> stack = Lists.newLinkedList();
      String[] split = rpnExpression.split(" ");
      for (String part : split) {
         part = part.trim();
         if (NumberUtils.isParsable(part)) {
            stack.push(NumericToken.ofDouble(part));
         } else if(lookup.isOperator(part)) {
            extracted(part, stack);
         } else if(lookup.isFunction(part)) {
            extracted(part, stack);
         }
      }
      return (stack.pop()).value();
   }

   private void extracted(String part, LinkedList<NumericToken<Double>> stack) {
      final String temp = part;
      ExecutableInfo functionExecutor = Optional.of(lookup.findExecutor(part))
          .orElseThrow(() -> new IllegalStateException("The Part " + temp + " has no Operation"));
      if (functionExecutor.info().getFunctionArity().isUnary()) {
         NumericToken<Double> val = stack.pop();
         stack.push(functionExecutor.exec().execute(val));
      } else if (functionExecutor.info().getFunctionArity().isBinary()) {
         NumericToken<Double> second = stack.pop();
         NumericToken<Double> first = stack.pop();
         stack.push(functionExecutor.exec().execute(first, second));
      } else {
         stack.push(functionExecutor.exec().execute());
      }
   }

}
