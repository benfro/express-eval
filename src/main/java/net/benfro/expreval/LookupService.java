package net.benfro.expreval;

import net.benfro.expreval.function.ExecutableFunction;
import net.benfro.expreval.function.FunctionInfo;

public interface LookupService {

    boolean isOperator(String symbol);

    boolean isFunction(String symbol);

    FunctionInfo findInfo(String symbol);

    ExecutableFunction findExecutor(String part);
}
