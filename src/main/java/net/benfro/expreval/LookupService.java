package net.benfro.expreval;

public interface LookupService {

    boolean isOperator(String symbol);

    Operator find(String symbol);
}
