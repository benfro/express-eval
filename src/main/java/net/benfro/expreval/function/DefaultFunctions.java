package net.benfro.expreval.function;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.benfro.expreval.evaluator.NumericToken;

@RequiredArgsConstructor
@Getter
public enum DefaultFunctions implements FunctionInfo, ExecutableFunction<Double> {

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

    static final Map<String, DefaultFunctions> SYMBOL_OPERATOR_MAP = makeSymbol2OperatorMap();
    static final Set<String> OPERATOR_SYMBOLS = SYMBOL_OPERATOR_MAP.keySet();

    private static Map<String, DefaultFunctions> makeSymbol2OperatorMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(DefaultFunctions::getSymbol, e -> e));
    }

    public static boolean isOperator(String operand) {
        return OPERATOR_SYMBOLS.contains(operand.trim());
    }

    public static DefaultFunctions find(String operand) {
        return SYMBOL_OPERATOR_MAP.getOrDefault(operand.trim(), null);
    }

    public static DefaultFunctions get(String opStr) {
        return SYMBOL_OPERATOR_MAP.getOrDefault(opStr, null);
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
}
