package net.benfro.expreval.parser2;

import net.benfro.expreval.LookupService;
import net.benfro.expreval.function.DefaultFunctions;
import net.benfro.expreval.function.DefaultOperator;
import net.benfro.expreval.function.ExecutableFunction;
import net.benfro.expreval.function.FunctionInfo;

public class DefaultLookupService implements LookupService {

    @Override
    public boolean isOperator(String symbol) {
        return DefaultFunctions.isOperator(symbol);
    }

    @Override
    public FunctionInfo findInfo(String symbol) {
        return DefaultFunctions.find(symbol);
    }

    @Override
    public ExecutableFunction findExecutor(String part) {
        return DefaultFunctions.find(part);
    }
}
