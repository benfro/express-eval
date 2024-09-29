package net.benfro.expreval.util;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.benfro.expreval.function.FunctionExecutor;

@Deprecated
class OperationLookup {

    static final EnumMap<net.benfro.expreval.function.DefaultFunctions, @Nullable FunctionExecutor> registry = Maps.newEnumMap(
        net.benfro.expreval.function.DefaultFunctions.class);
    static final EnumMap<FunctionExecutor, net.benfro.expreval.function.DefaultFunctions> inverseRegistry = Maps.newEnumMap(
        FunctionExecutor.class);
    static Map<String, net.benfro.expreval.function.DefaultFunctions> symbol2OperatorMap;

    static {
        init();
        initInverseRegistry();
//        symbol2OperatorMap = Maps.asMap(
//            new HashSet<>(net.benfro.expreval.function.DefaultFunctions.OPERATOR_SYMBOLS),
//            net.benfro.expreval.function.DefaultFunctions::get);
    }

    static void init() {
        registry.put(net.benfro.expreval.function.DefaultFunctions.ADD, FunctionExecutor.ADDITION);
        registry.put(net.benfro.expreval.function.DefaultFunctions.SUB, FunctionExecutor.SUBTRACTION);
        registry.put(net.benfro.expreval.function.DefaultFunctions.DIV, FunctionExecutor.DIVISION);
        registry.put(net.benfro.expreval.function.DefaultFunctions.MULT, FunctionExecutor.MULTIPLICATION);
        registry.put(net.benfro.expreval.function.DefaultFunctions.MOD, FunctionExecutor.MODULUS);
        registry.put(net.benfro.expreval.function.DefaultFunctions.INV, FunctionExecutor.INVERSE);
        registry.put(net.benfro.expreval.function.DefaultFunctions.POW, FunctionExecutor.POWER);
        registry.put(net.benfro.expreval.function.DefaultFunctions.SQRE, FunctionExecutor.SQRE);
        registry.put(net.benfro.expreval.function.DefaultFunctions.EXP, FunctionExecutor.EXP);
        registry.put(net.benfro.expreval.function.DefaultFunctions.LOG10, FunctionExecutor.LOG10);
        registry.put(net.benfro.expreval.function.DefaultFunctions.EEXP, FunctionExecutor.E_EXP);
        registry.put(net.benfro.expreval.function.DefaultFunctions.LN, FunctionExecutor.LN);
        registry.put(net.benfro.expreval.function.DefaultFunctions.SQRT, FunctionExecutor.SQUARE_ROOT);
        registry.put(net.benfro.expreval.function.DefaultFunctions.CBRT, FunctionExecutor.CUBE_ROOT);
        registry.put(net.benfro.expreval.function.DefaultFunctions.SIN, FunctionExecutor.SINE);
        registry.put(net.benfro.expreval.function.DefaultFunctions.COS, FunctionExecutor.COSINE);
        registry.put(net.benfro.expreval.function.DefaultFunctions.TAN, FunctionExecutor.TANGENT);
        registry.put(net.benfro.expreval.function.DefaultFunctions.ABS, FunctionExecutor.ABSOLUTE);
        registry.put(net.benfro.expreval.function.DefaultFunctions.PI, FunctionExecutor.PI);
        registry.put(net.benfro.expreval.function.DefaultFunctions.E, FunctionExecutor.E);
    }

    static void initInverseRegistry() {
        HashBiMap<net.benfro.expreval.function.DefaultFunctions, @Nullable FunctionExecutor> tempBiMap = HashBiMap.create(registry);
        BiMap<@Nullable FunctionExecutor, net.benfro.expreval.function.DefaultFunctions> inverse = tempBiMap.inverse();
        inverseRegistry.putAll(inverse);
    }

    public static FunctionExecutor get(net.benfro.expreval.function.DefaultFunctions defaultFunctions) {
        return registry.get(defaultFunctions);
    }

    public static FunctionExecutor getOperation(String symbol) {
        return registry.getOrDefault(getOperator(symbol), null);
    }

    private OperationLookup() {

    }

    public static net.benfro.expreval.function.DefaultFunctions getOperator(String symbol) {
        return symbol2OperatorMap.getOrDefault(symbol, null);
    }
}
