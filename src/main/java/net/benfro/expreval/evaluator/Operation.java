package net.benfro.expreval.evaluator;

import java.util.function.DoubleBinaryOperator;

public enum Operation implements BiOperation<Double> {
    ADDITION(Double::sum, Type.BINARY),
    SUBTRACTION((a,b) -> a - b, Type.BINARY),
    MULTIPLICATION((a,b) -> a * b, Type.BINARY),
    DIVISION((a,b) -> a / b, Type.BINARY),
    MODULUS((a,b) -> a % b, Type.BINARY),
    POWER(Math::pow, Type.BINARY),
    LOG10((a,b) -> Math.log10(a), Type.UNARY),
    EXP((a,b) -> Math.pow(10.0, a), Type.UNARY),
    E_EXP((a, b) -> Math.pow(Math.E, a), Type.UNARY),
    LN((a,b) -> Math.log(a), Type.UNARY),
    SQUARE_ROOT((a, b) -> Math.sqrt(a), Type.UNARY),
    CUBE_ROOT((a, b) -> Math.cbrt(a), Type.UNARY),
    SINE((a, b) -> Math.sin(a), Type.UNARY),
    COSINE((a, b) -> Math.cos(a), Type.UNARY),
    TANGENT((a, b) -> Math.tan(a), Type.UNARY),
    INVERSE((a, b) -> 1.0/a, Type.UNARY),
    ABSOLUTE((a, b) -> Math.abs(a), Type.UNARY),
    PI((a, b) -> Math.PI, Type.CONSTANT),
    E((a, b) -> Math.E, Type.CONSTANT),
    ;

    public enum Type {
        BINARY,
        UNARY,
        CONSTANT;

        public boolean isBinary() {
            return this == BINARY;
        }

        public boolean isUnary() {
            return this == UNARY;
        }

        public boolean isConstant() {
            return this == CONSTANT;
        }
    }

    Operation(DoubleBinaryOperator exec, Type unary) {
        this.exec = exec;
        this.unary = unary;
    }

    private final DoubleBinaryOperator exec;
    private final Type unary;

    @Override
    public NumericToken<Double> doOp(NumericToken<Double> a, NumericToken<Double> b) {
        if(!getType().isBinary()) {
            throw new IllegalArgumentException("Unary operations not supported for binary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), b.value()));
    }

    public NumericToken<Double> doOp(NumericToken<Double> a) {
        if(!getType().isUnary()) {
            throw new IllegalArgumentException("BiOperation not supported for unary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), Double.NaN));
    }

    public NumericToken<Double> doOp() {
        if(!getType().isConstant()) {
            throw new IllegalArgumentException("COnstant");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(Double.NaN, Double.NaN));
    }

    public Type getType() {
        return unary;
    }

}
