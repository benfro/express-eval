package net.benfro.expreval;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.benfro.expreval.evaluator.Operation;
import net.benfro.expreval.parser.Operator;

public class OperationLookup {

    static final EnumMap<Operator, @Nullable Operation> registry = Maps.newEnumMap(Operator.class);
    static final EnumMap<Operation, @Nullable Operator> inverseRegistry = Maps.newEnumMap(Operation.class);
    static Map<String, Operator> symbol2OperatorMap;

    static {
        init();
        initInverseRegistry();
        symbol2OperatorMap = Maps.asMap(
            new HashSet<>(Operator.OPERATOR_SYMBOLS),
            Operator::get);
    }

    static void init() {
        registry.put(Operator.ADD, Operation.ADDITION);
        registry.put(Operator.SUB, Operation.SUBTRACTION);
        registry.put(Operator.DIV, Operation.DIVISION);
        registry.put(Operator.MULT, Operation.MULTIPLICATION);
        registry.put(Operator.MOD, Operation.MODULUS);
        registry.put(Operator.INV, Operation.INVERSE);
        registry.put(Operator.POW, Operation.POWER);
        registry.put(Operator.SQRE, Operation.SQRE);
        registry.put(Operator.EXP, Operation.EXP);
        registry.put(Operator.LOG10, Operation.LOG10);
        registry.put(Operator.EEXP, Operation.E_EXP);
        registry.put(Operator.LN, Operation.LN);
        registry.put(Operator.SQRT, Operation.SQUARE_ROOT);
        registry.put(Operator.CBRT, Operation.CUBE_ROOT);
        registry.put(Operator.SIN, Operation.SINE);
        registry.put(Operator.COS, Operation.COSINE);
        registry.put(Operator.TAN, Operation.TANGENT);
        registry.put(Operator.ABS, Operation.ABSOLUTE);
        registry.put(Operator.PI, Operation.PI);
        registry.put(Operator.E, Operation.E);
    }

    static void initInverseRegistry() {
        HashBiMap<Operator, @Nullable Operation> tempBiMap = HashBiMap.create(registry);
        BiMap<@Nullable Operation, Operator> inverse = tempBiMap.inverse();
        inverseRegistry.putAll(inverse);
    }

    public static Operation get(Operator operator) {
        return registry.get(operator);
    }

    public static Operation getOperation(String symbol) {
        return registry.getOrDefault(getOperator(symbol), null);
    }

    private OperationLookup() {

    }

    public static Operator getOperator(String symbol) {
        return symbol2OperatorMap.getOrDefault(symbol, Operator.NOOP);
    }
}
