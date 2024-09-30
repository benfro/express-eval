package net.benfro.expreval.rpn;

import net.benfro.expreval.LookupService;
import net.benfro.expreval.function.DefaultFunctions;
import net.benfro.expreval.function.ExecutableInfo;
import net.benfro.expreval.function.FunctionInfo;

public class DefaultLookupService implements LookupService {

    @Override
    public boolean isOperator(String symbol) {
        return DefaultFunctions.isOperator(symbol);
    }

    @Override
    public boolean isFunction(String symbol) {
        return DefaultFunctions.isFunction(symbol);
    }

    @Override
    public FunctionInfo findInfo(String symbol) {
        return DefaultFunctions.find(symbol).info();
    }

    @Override
    public ExecutableInfo findExecutor(String part) {
        return DefaultFunctions.find(part);
    }

    @Override
    public boolean isConstant(String symbol) {
        return DefaultFunctions.isConstant(symbol);
    }

}
