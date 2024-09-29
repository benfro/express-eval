package net.benfro.expreval.function;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.benfro.expreval.Operator;

@RequiredArgsConstructor
@Getter
@Deprecated
public enum DefaultOperator implements Operator, FunctionInfo {
    ADD("+", 1, Association.LEFT),
    SUBTRACT("-", 1, Association.LEFT),
    MULTIPLY("*", 5, Association.LEFT),
    DIVIDE("/", 5, Association.LEFT),
    MOD("%", 5, Association.LEFT),
    POW("^", 10, Association.RIGHT),
    ;

    enum Association {
        RIGHT, LEFT, NOT_APPLICABLE;
    }

    private final String operand;
    private final int precedence;
    private final Association association;

    @Override
    public boolean isRightAssociative() {
        return this.association == Association.RIGHT;
    }

    @Override
    public boolean isLeftAssociative() {
        return this.association == Association.LEFT;
    }

    @Override
    public FunctionExecutor.Arity getFunctionArity() {
        return null;
    }

    @Override
    public int comparePrecedenceWith(Operator otherOperator) {
        return getPrecedence() - otherOperator.getPrecedence();
    }

    @Override
    public String getSymbol() {
        return operand;
    }

    @Override
    public String getDesignation() {
        return name();
    }

    @Override
    public Type getType() {
        return Type.OPERATOR;
    }

    public static boolean isOperator(String operand) {
        return Arrays.stream(DefaultOperator.values()).map(DefaultOperator::getOperand).anyMatch(operand::equals);
    }

    public static DefaultOperator find(String operand) {
        return Arrays.stream(DefaultOperator.values())
            .filter(opin -> opin.getOperand().equals(operand))
            .findFirst()
            .orElse(null);
    }

}
