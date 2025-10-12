package net.benfro.expreval.core.rpn;

import net.benfro.expreval.core.function.ExecutableInfo;
import net.benfro.expreval.core.function.FunctionInfo;

public interface LookupService {

    boolean isOperator(String symbol);

    boolean isFunction(String symbol);

    FunctionInfo findInfo(String symbol);

    ExecutableInfo findExecutor(String part);

    boolean isConstant(String part);
}
