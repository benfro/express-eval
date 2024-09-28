package net.benfro.expreval.parser2;

public interface LookupService {

    boolean isOperator(String symbol);

    Operator find(String symbol);
}
