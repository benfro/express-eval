package net.benfro.expreval;

import net.benfro.expreval.function.ExecutableInfo;
import net.benfro.expreval.function.FunctionInfo;

public interface LookupService {

    boolean isOperator(String symbol);

    boolean isFunction(String symbol);

    FunctionInfo findInfo(String symbol);

    ExecutableInfo findExecutor(String part);

    boolean isConstant(String part);
}
