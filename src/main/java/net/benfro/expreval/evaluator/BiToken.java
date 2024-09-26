package net.benfro.expreval.evaluator;

public class BiToken<T extends Number> {

    public final NumericToken<T> one;
    public final NumericToken<T> two;

    protected BiToken(NumericToken<T> one, NumericToken<T> two) {
        this.one = one;
        this.two = two;
    }

}
