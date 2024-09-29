package net.benfro.expreval.function;

import java.lang.reflect.Executable;

public interface ExecutableInfo {

    FunctionInfo info();

    ExecutableFunction exec();
}
