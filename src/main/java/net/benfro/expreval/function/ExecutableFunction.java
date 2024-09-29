package net.benfro.expreval.function;


import net.benfro.expreval.evaluator.NumericToken;

public interface ExecutableFunction<T extends Number> {

    NumericToken<T> execute(NumericToken<T> a, NumericToken<T> b);

    NumericToken<T> execute(NumericToken<T> a);

    NumericToken<T> execute();

}
