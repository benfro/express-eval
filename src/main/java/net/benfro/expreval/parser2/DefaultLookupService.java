package net.benfro.expreval.parser2;

public class DefaultLookupService implements LookupService {

    @Override
    public boolean isOperator(String symbol) {
        return DefaultOperator.isOperator(symbol);
    }

    @Override
    public DefaultOperator find(String symbol) {
        return DefaultOperator.find(symbol);
    }
}