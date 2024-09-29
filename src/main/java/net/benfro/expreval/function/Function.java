package net.benfro.expreval.function;

import java.util.Arrays;
import java.util.List;

import net.benfro.expreval.evaluator.NumericToken;

@Deprecated
public record Function(FunctionInfo info) implements FunctionInfo, ExecutableFunction<Double> {

    public static final Function Add = new Function(DefaultFunctions.ADD);
    public static final Function Subtract = new Function(DefaultFunctions.SUB);
    public static final Function Multiply = new Function(DefaultFunctions.MULT);
    public static final Function Divide = new Function(DefaultFunctions.DIV);
    public static final Function Modulus = new Function(DefaultFunctions.MOD);
    public static final Function Sine = new Function(DefaultFunctions.SIN);
    public static final Function SquareRoot = new Function(DefaultFunctions.SQRT);
    public static final Function Exponent = new Function(DefaultFunctions.EXP);



    public static boolean isOperator(String operand) {
        return Arrays.stream(DefaultOperator.values()).map(DefaultOperator::getOperand).anyMatch(operand::equals);
    }

    public static DefaultOperator find(String operand) {
        return Arrays.stream(DefaultOperator.values())
            .filter(opin -> opin.getOperand().equals(operand))
            .findFirst()
            .orElse(null);
    }

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }

    @Override
    public String getDesignation() {
        return info.getDesignation();
    }

    @Override
    public Type getType() {
        return info.getType();
    }

    @Override
    public ExecutableFunction getExecutableFunction() {
        return info.getExecutableFunction();
    }

    @Override
    public NumericToken<Double> execute(NumericToken<Double> a, NumericToken<Double> b) {
        return getExecutableFunction().execute(a, b);
    }

    @Override
    public NumericToken<Double> execute(NumericToken<Double> a) {
        return getExecutableFunction().execute(a);
    }

    @Override
    public NumericToken<Double> execute() {
        return getExecutableFunction().execute();
    }

    @Override
    public int getPrecedence() {
        return info.getPrecedence();
    }

    @Override
    public boolean isLeftAssociative() {
        return info.isLeftAssociative();
    }

    @Override
    public FunctionExecutor.Arity getFunctionArity() {
        return null;
    }

    public String name() {
        return info.getDesignation();
    }
}
