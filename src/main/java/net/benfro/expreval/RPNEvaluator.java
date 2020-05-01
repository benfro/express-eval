package net.benfro.expreval;

import com.google.common.collect.Lists;

import java.util.LinkedList;

public class RPNEvaluator {

   static class NumericTokenImpl implements NumericToken, Token {

      final double value;

      NumericTokenImpl(String i) {
         this.value = Double.valueOf(i);
      }

      NumericTokenImpl(double i) {
         this.value = i;
      }

      @Override
      public double get() {
         return value;
      }
   }

   private static class Adder implements Operation.Add { /* Empty */ }

   private static class Subtractor implements Operation.Subtract { /* Empty */ }

   private static class Multiplyer implements Operation.Multiply { /* Empty */ }

   private static class Divider implements Operation.Divide { /* Empty */ }

   enum Ops {
      ADD(new Adder()),
      SUBTRACT(new Subtractor()),
      MULTIPLY(new Multiplyer()),
      DIVIDE(new Divider());

      final Operation op;

      Ops(Operation op) {
         this.op = op;
      }

      public double doOp(NumericToken a, NumericToken b) {
         return op.doOp(a, b);
      }
   }

   public double evaluate(String rpnExpression) {
      final LinkedList<Token> stack = Lists.newLinkedList();
      String[] split = rpnExpression.split(" ");
      for (String part : split) {
         if (part.matches("(-)?\\d+(\\.\\d+)?")) {
            stack.push(new NumericTokenImpl(part));
         } else {
            NumericToken latestAdded = (NumericToken) stack.pop();
            NumericToken secondAdded = (NumericToken) stack.pop();
            switch (part) {
               case "+":
                  stack.push(new NumericTokenImpl(Ops.ADD.doOp(latestAdded, secondAdded)));
                  break;
               case "-":
                  stack.push(new NumericTokenImpl(Ops.SUBTRACT.doOp(latestAdded, secondAdded)));
                  break;
               case "*":
                  stack.push(new NumericTokenImpl(Ops.MULTIPLY.doOp(latestAdded, secondAdded)));
                  break;
               case "/":
                  stack.push(new NumericTokenImpl(Ops.DIVIDE.doOp(latestAdded, secondAdded)));
                  break;
               default:
                  throw new AssertionError("Poff!");
            }

         }
      }
      return ((NumericToken) stack.pop()).get();
   }

}
