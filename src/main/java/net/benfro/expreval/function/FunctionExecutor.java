package net.benfro.expreval.function;

import java.util.function.DoubleBinaryOperator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.benfro.expreval.evaluator.NumericToken;

@RequiredArgsConstructor
@Getter
public enum FunctionExecutor implements ExecutableFunction<Double> {
    ADDITION(Double::sum, Arity.BINARY),
    SUBTRACTION((a,b) -> a - b, Arity.BINARY),
    MULTIPLICATION((a,b) -> a * b, Arity.BINARY),
    DIVISION((a,b) -> a / b, Arity.BINARY),
    MODULUS((a,b) -> a % b, Arity.BINARY),
    POWER(Math::pow, Arity.BINARY),
    LOG10((a,b) -> Math.log10(a), Arity.UNARY),
    EXP((a,b) -> Math.pow(10.0, a), Arity.UNARY),
    SQRE((a,b) -> a * a, Arity.UNARY),
    E_EXP((a, b) -> Math.pow(Math.E, a), Arity.UNARY),
    LN((a,b) -> Math.log(a), Arity.UNARY),
    SQUARE_ROOT((a, b) -> Math.sqrt(a), Arity.UNARY),
    CUBE_ROOT((a, b) -> Math.cbrt(a), Arity.UNARY),
    SINE((a, b) -> Math.sin(a), Arity.UNARY),
    COSINE((a, b) -> Math.cos(a), Arity.UNARY),
    TANGENT((a, b) -> Math.tan(a), Arity.UNARY),
    ATANGENT((a, b) -> Math.atan(a), Arity.UNARY),
    INVERSE((a, b) -> 1.0/a, Arity.UNARY),
    ABSOLUTE((a, b) -> Math.abs(a), Arity.UNARY),
    PI((a, b) -> Math.PI, Arity.CONSTANT),
    E((a, b) -> Math.E, Arity.CONSTANT),
    ;

    public enum Arity {
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

    private final DoubleBinaryOperator exec;
    private final Arity functionArity;

    @Override
    public NumericToken<Double> execute(NumericToken<Double> a, NumericToken<Double> b) {
        if(!getFunctionArity().isBinary()) {
            throw new IllegalArgumentException("Unary operations not supported for binary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), b.value()));
    }

    public NumericToken<Double> execute(NumericToken<Double> a) {
        if(!getFunctionArity().isUnary()) {
            throw new IllegalArgumentException("BiOperation not supported for unary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), Double.NaN));
    }

    public NumericToken<Double> execute() {
        if(!getFunctionArity().isConstant()) {
            throw new IllegalArgumentException("Constant");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(Double.NaN, Double.NaN));
    }

}
