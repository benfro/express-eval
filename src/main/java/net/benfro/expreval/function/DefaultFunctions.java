package net.benfro.expreval.function;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Functions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.benfro.expreval.evaluator.NumericToken;

@RequiredArgsConstructor
@Getter
public enum DefaultFunctions implements FunctionInfo, ExecutableFunction<Double> , ExecutableInfo{

    ADD("+", 1, Type.OPERATOR, FunctionExecutor.ADDITION),
    SUB("-", 1, Type.OPERATOR, FunctionExecutor.SUBTRACTION),
    DIV("/", 10, Type.OPERATOR, FunctionExecutor.DIVISION),
    MULT("*", 10, Type.OPERATOR, FunctionExecutor.MULTIPLICATION),
    POW("^", 20, Type.OPERATOR, FunctionExecutor.POWER, false),
    MOD("%", 10, Type.OPERATOR, FunctionExecutor.MODULUS),
    SQRE("**", 10, Type.OPERATOR, FunctionExecutor.SQRE),
    ABS("abs", 4, Type.FUNCTION, FunctionExecutor.ABSOLUTE),
    SIN("sin", 1, Type.FUNCTION, FunctionExecutor.SINE),
    COS("cos", 1, Type.FUNCTION, FunctionExecutor.COSINE),
    TAN("tan", 1, Type.FUNCTION, FunctionExecutor.TANGENT),
    ATAN("atan", 1, Type.FUNCTION, FunctionExecutor.ATANGENT),
    POWTO("pow", 1, Type.FUNCTION, FunctionExecutor.POWER),
    SQRT("sqrt", 3, Type.FUNCTION, FunctionExecutor.SQUARE_ROOT),
    CBRT("cbrt", 3, Type.FUNCTION, FunctionExecutor.CUBE_ROOT),
    EEXP("eexp", 2, Type.FUNCTION, FunctionExecutor.E_EXP),
    EXP("exp", 2, Type.FUNCTION, FunctionExecutor.EXP),
    LN("ln", 2, Type.FUNCTION, FunctionExecutor.LN),
    LOG10("log", 2, Type.FUNCTION, FunctionExecutor.LOG10),
    INV("inv", 2, Type.FUNCTION, FunctionExecutor.INVERSE),
    PI("pi", 1, Type.CONSTANT, FunctionExecutor.PI),
    E("e", 1, Type.CONSTANT, FunctionExecutor.E),
    ;

    static final Map<String, DefaultFunctions> SYMBOL_OPERATOR_MAP =
        Arrays.stream(values()).collect(Collectors.toMap(DefaultFunctions::getSymbol, Functions.identity()));
    static final Map<Type, List<String>> OPERATOR_SYMBOLS =
        Arrays.stream(values())
            .collect(groupingBy(DefaultFunctions::getType, mapping(DefaultFunctions::getSymbol, Collectors.toList())));

    public static boolean isOperator(String operand) {
        return OPERATOR_SYMBOLS.get(Type.OPERATOR).contains(operand.trim());
    }

    public static boolean isFunction(String operand) {
        return OPERATOR_SYMBOLS.get(Type.FUNCTION).contains(operand.trim());
    }

    public static boolean isConstant(String operand) {
        return OPERATOR_SYMBOLS.get(Type.CONSTANT).contains(operand.trim());
    }

    public static ExecutableInfo find(String operand) {
        return SYMBOL_OPERATOR_MAP.getOrDefault(operand.trim(), null);
    }

    private final String symbol;
    private final int precedence;
    private final Type type;
    private final FunctionExecutor exec;
    private final boolean leftAssociative;

    DefaultFunctions(String symbol, int precedence, Type type, FunctionExecutor exec) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.type = type;
        this.exec = exec;
        this.leftAssociative = true;
    }

    @Override
    public String getDesignation() {
        return this.name();
    }

    @Override
    public FunctionExecutor.Arity getFunctionArity() {
        return exec.getFunctionArity();
    }

    @Override
    public ExecutableFunction getExecutableFunction() {
        return exec;
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
    public FunctionInfo info() {
        return this;
    }

    @Override
    public ExecutableFunction exec() {
        return this;
    }
}
