package net.benfro.expreval.evaluator;

import com.google.common.collect.Lists;
import net.benfro.expreval.LookupService;
import net.benfro.expreval.function.ExecutableFunction;
import net.benfro.expreval.function.FunctionInfo;
import net.benfro.expreval.parser2.DefaultLookupService;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.Optional;

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
            final String temp = part;
            ExecutableFunction functionExecutor = Optional.of(lookup.findExecutor(part))
                .orElseThrow(() -> new IllegalStateException("The Part " + temp + " has no Operation"));
            if (((FunctionInfo)functionExecutor).getFunctionArity().isUnary()) {
               NumericToken<Double> val = stack.pop();
               stack.push(functionExecutor.execute(val));
            } else if (((FunctionInfo)functionExecutor).getFunctionArity().isBinary()) {
               NumericToken<Double> second = stack.pop();
               NumericToken<Double> first = stack.pop();
               stack.push(functionExecutor.execute(first, second));
            } else {
               stack.push(functionExecutor.execute());
            }
         }
      }
      return (stack.pop()).value();
   }

}
