package net.benfro.expreval.parser2;

public interface Operator {
    boolean isLeftAssociative();

    boolean isRightAssociative();

    int getPrecedence();

    int comparePrecedenceWith(Operator otherOperator    );
}
