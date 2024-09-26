package net.benfro.expreval.evaluator;

public abstract class BiToken<T extends Number> {

    public final NumericToken<T> one;
    public final NumericToken<T> two;

    public abstract NumericToken<T> doOperation(String op);

    protected BiToken(NumericToken<T> one, NumericToken<T> two) {
        this.one = one;
        this.two = two;
    }
}
