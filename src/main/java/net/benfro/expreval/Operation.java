package net.benfro.expreval;


public interface Operation extends Token {

   double doOp(NumericToken a, NumericToken b);

   interface Add extends Operation {
      default double doOp(NumericToken a, NumericToken b) {
         return a.get() + b.get();
      }
   }

   interface Subtract extends Operation {
      default double doOp(NumericToken a, NumericToken b) {
         return a.get() - b.get();
      }
   }

   interface Multiply extends Operation {
      default double doOp(NumericToken a, NumericToken b) {
         return a.get() * b.get();
      }
   }

   interface Divide extends Operation {
      default double doOp(NumericToken a, NumericToken b) {
         return a.get() / b.get();
      }
   }
}
