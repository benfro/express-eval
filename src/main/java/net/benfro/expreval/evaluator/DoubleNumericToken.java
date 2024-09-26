package net.benfro.expreval.evaluator;

import static java.lang.Math.pow;

import net.benfro.expreval.parser.Operator;

public record DoubleNumericToken extends NumericToken<Double> {

    static NumericToken<Double> ofDoubleToken(String numberString) {
        return new DoubleNumericToken(Double.parseDouble(numberString));
    }

    static NumericToken<Double> ofDoubleToken(Double numberString) {
        return new DoubleNumericToken(numberString);
    }

    static class DoubleBiToken extends BiToken<Double> {
        public DoubleBiToken(NumericToken<Double> one, NumericToken<Double> two) {
            super(one, two);
        }

        @Override
        public NumericToken<Double> doOperation(String op) {
            return switch (Operator.get(op)) {
                case PLUS -> new net.benfro.expreval.evaluator.DoubleNumericToken(one.get() + two.get());
                case MINUS -> new net.benfro.expreval.evaluator.DoubleNumericToken(one.get() - two.get());
                case MULT -> new net.benfro.expreval.evaluator.DoubleNumericToken(one.get() * two.get());
                case DIV -> new net.benfro.expreval.evaluator.DoubleNumericToken(one.get() / two.get());
                case POW -> new net.benfro.expreval.evaluator.DoubleNumericToken(pow(one.get(), two.get()));
                case MOD -> new net.benfro.expreval.evaluator.DoubleNumericToken(one.get() % two.get());
                default -> new net.benfro.expreval.evaluator.DoubleNumericToken(Double.NaN);
            };
        }

    }

    public DoubleNumericToken(Double value) {
        super(value);
    }

    @Override
    public Double get() {
        return value;
    }

    @Override
    public BiToken<Double> with(NumericToken<Double> other) {
        return new DoubleBiToken(this, other);
    }
}
