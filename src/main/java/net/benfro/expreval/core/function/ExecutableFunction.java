package net.benfro.expreval.core.function;


import net.benfro.expreval.core.evaluator.NumericToken;

public interface ExecutableFunction<T extends Number> {

    NumericToken<T> execute(NumericToken<T> a, NumericToken<T> b);

    NumericToken<T> execute(NumericToken<T> a);

    NumericToken<T> execute();

}
