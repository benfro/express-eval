package net.benfro.expreval.evaluator;

import java.util.function.DoubleBinaryOperator;

public enum Operation implements BiOperation<Double> {
    ADDITION(Double::sum, false),
    SUBTRACTION((a,b) -> a - b, false),
    MULTIPLICATION((a,b) -> a * b, false),
    DIVISION((a,b) -> a / b, false),
    POWER(Math::pow, false),
    EXP((a,b) -> Math.pow(10.0, a), true),
    SQUARE_ROOT((a, b) -> Math.sqrt(a), true),
    CUBE_ROOT((a, b) -> Math.cbrt(a), true),
    SINE((a, b) -> Math.sin(a), true),
    COSINE((a, b) -> Math.cos(a), true),
    TANGENT((a, b) -> Math.tan(a), true),
    ;

    Operation(DoubleBinaryOperator exec, boolean unary) {
        this.exec = exec;
        this.unary = unary;
    }

    private final DoubleBinaryOperator exec;
    private final boolean unary;

    @Override
    public NumericToken<Double> doOp(NumericToken<Double> a, NumericToken<Double> b) {
        if(unary) {
            throw new IllegalArgumentException("Unary operations not supported for binary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), b.value()));
    }

    public NumericToken<Double> doOp(NumericToken<Double> a) {
        if(!unary) {
            throw new IllegalArgumentException("BiOperation not supported for unary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), Double.NaN));
    }

}
