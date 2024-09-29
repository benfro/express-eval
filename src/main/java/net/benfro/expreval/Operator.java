package net.benfro.expreval;

public interface Operator {
    boolean isLeftAssociative();

    boolean isRightAssociative();

    int getPrecedence();

    int comparePrecedenceWith(Operator otherOperator    );
}
