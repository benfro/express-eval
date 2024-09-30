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
    MAX(Math::max, Arity.BINARY),
    MIN(Math::min, Arity.BINARY),
    LOG10((a,ignore) -> Math.log10(a), Arity.UNARY),
    EXP((a,ignore) -> Math.exp(a), Arity.UNARY),
    SQUARE((a, ignore) -> a * a, Arity.UNARY),
//    E_EXP((a, b) -> Math.pow(Math.E, a), Arity.UNARY),
    LN((a,ignore) -> Math.log(a), Arity.UNARY),
    LOGA((a,b) -> Math.log(a) / Math.log(b), Arity.BINARY),
    SQUARE_ROOT((a, ignore) -> Math.sqrt(a), Arity.UNARY),
    CUBE_ROOT((a, ignore) -> Math.cbrt(a), Arity.UNARY),
    SINE((a, ignore) -> Math.sin(a), Arity.UNARY),
    COSINE((a, ignore) -> Math.cos(a), Arity.UNARY),
    TANGENT((a, ignore) -> Math.tan(a), Arity.UNARY),
    ATANGENT((a, ignore) -> Math.atan(a), Arity.UNARY),
    INVERSE((a, ignore) -> 1.0/a, Arity.UNARY),
    ABSOLUTE((a, ignore) -> Math.abs(a), Arity.UNARY),
    CIELING((a, ignore) -> Math.ceil(a), Arity.UNARY),
    FLOOR((a, ignore) -> Math.floor(a), Arity.UNARY),
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

    @Override
    public NumericToken<Double> execute(NumericToken<Double> a) {
        if(!getFunctionArity().isUnary()) {
            throw new IllegalArgumentException("BiOperation not supported for unary operations");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(a.value(), Double.NaN));
    }

    @Override
    public NumericToken<Double> execute() {
        if(!getFunctionArity().isConstant()) {
            throw new IllegalArgumentException("Constant");
        }
        return NumericToken.ofDouble(exec.applyAsDouble(Double.NaN, Double.NaN));
    }

}
