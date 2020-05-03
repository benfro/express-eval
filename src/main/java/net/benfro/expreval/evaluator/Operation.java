package net.benfro.expreval.evaluator;


public interface Operation<T extends Number> extends Token {

   NumericToken<T> doOp(NumericToken<T> a, NumericToken<T> b);

}
