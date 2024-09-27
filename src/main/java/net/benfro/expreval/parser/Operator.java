package net.benfro.expreval.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Operator {

    ADD("+", 2, Type.OPERATOR),
    SUB("-", 2, Type.OPERATOR),
    DIV("/", 3, Type.OPERATOR),
    MULT("*", 3, Type.OPERATOR),
    POW("^", 4,  Type.OPERATOR,true),
    MOD("%", 3, Type.OPERATOR),
    SQRE("**", 3, Type.OPERATOR),
    LPAR("(", 0, Type.BRACKET),
    RPAR(")", 0, Type.BRACKET),
    NOOP("", -1, Type.BRACKET),
    ABS("abs", 4, Type.FUNCTION),
    SIN("sin", 1, Type.FUNCTION),
    COS("cos", 1, Type.FUNCTION),
    TAN("tan", 1, Type.FUNCTION),
    ATAN("atan", 1, Type.FUNCTION),
    SQRT("sqrt", 3, Type.FUNCTION),
    CBRT("cbrt", 3, Type.FUNCTION),
    EEXP("eexp", 2, Type.FUNCTION),
    EXP("exp", 2, Type.FUNCTION),
    LN("ln", 2, Type.FUNCTION),
    LOG10("log", 2, Type.FUNCTION),
    PI("pi", 1, Type.CONSTANT),
    E("e", 1, Type.CONSTANT),
    INV("inv", 2, Type.FUNCTION),
    ;

    public enum Type {
        OPERATOR,
        FUNCTION,
        CONSTANT,
        BRACKET;
    }

    public static final Map<String, Operator> SYMBOL_OPERATOR_MAP = makeSymbol2OperatorMap();
    public static final List<String> OPERATOR_SYMBOLS = makeOperatorSymbolList();

    private static Map<String, Operator> makeSymbol2OperatorMap() {
        return Arrays.stream(values())
            .filter(operator -> !"()".contains(operator.symbol))
            .collect(Collectors.toMap(Operator::getSymbol, e -> e));
    }

    private static List<String> makeOperatorSymbolList() {
        return SYMBOL_OPERATOR_MAP.keySet().stream().filter(k -> !"()".contains(k)).toList();
    }

    private final String symbol;
    private final int precedence;
    private final boolean rightAssociative;
    private final Type type;

    public static Operator get(String opStr) {
        return SYMBOL_OPERATOR_MAP.getOrDefault(opStr, Operator.NOOP);
    }

    public static boolean isOperatorSymbol(String string) {
        return OPERATOR_SYMBOLS.contains(string);
    }

    Operator(String symbol, int precedence, Type type) {
        this(symbol, precedence, type, false);
    }

    Operator(String symbol, int precedence, Type type, boolean rightAssociative) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.type = type;
        this.rightAssociative = rightAssociative;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPrecedence() {
        return precedence;
    }

    public Type getType() {
        return type;
    }

    public boolean isConstant() {
        return type == Type.CONSTANT;
    }

    public boolean isFunction() {
        return type == Type.FUNCTION;
    }

    public boolean isOperator() {
        return type == Type.OPERATOR;
    }

    public boolean isRightAssociative() {
        return rightAssociative;
    }

    public boolean hasHigherPrecedenceThan(Operator other) {
        return getPrecedence() > other.getPrecedence();
    }

    public boolean isPrecedenceEqual(Operator other) {
        return getPrecedence() == other.getPrecedence();
    }
}
