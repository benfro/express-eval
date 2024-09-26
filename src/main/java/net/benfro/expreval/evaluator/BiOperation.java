package net.benfro.expreval.evaluator;


public interface BiOperation<T extends Number> {

   NumericToken<T> doOp(NumericToken<T> a, NumericToken<T> b);

}
