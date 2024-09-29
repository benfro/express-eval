package net.benfro.expreval.function;

public interface FunctionInfo {

    enum Type {
        OPERATOR,
        FUNCTION,
        CONSTANT,
    }

    String getSymbol();

    String getDesignation();

    Type getType();

    int getPrecedence();

    boolean isLeftAssociative();

    FunctionExecutor.Arity getFunctionArity();

    default boolean isOperator() {
        return this.getType() == Type.OPERATOR;
    }

    default boolean isFunction() {
        return this.getType() == Type.FUNCTION;
    }

    default boolean isConstant() {
        return this.getType() == Type.CONSTANT;
    }

    default ExecutableFunction getExecutableFunction() {
        throw new IllegalStateException("Not implemented yet");
    }

    default int comparePrecedenceWith(FunctionInfo other) {
        return getPrecedence() - other.getPrecedence();
    }
}
