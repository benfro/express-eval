package net.benfro.expreval;

import java.util.EnumMap;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.collect.Maps;
import net.benfro.expreval.evaluator.Operation;
import net.benfro.expreval.parser.Operator;

public class OperationLookup {
    private static final EnumMap<Operator, @Nullable Operation> registry = Maps.newEnumMap(Operator.class);
    static {
        init();
    }

    public static void init() {
        registry.put(Operator.PLUS, Operation.ADDITION);
        registry.put(Operator.MINUS, Operation.SUBTRACTION);
        registry.put(Operator.DIV, Operation.DIVISION);
        registry.put(Operator.MULT, Operation.MULTIPLICATION);
        registry.put(Operator.MOD, Operation.MODULUS);
        registry.put(Operator.INV, Operation.INVERSE);
        registry.put(Operator.POW, Operation.POWER);
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

    public static Operation get(Operator operator) {
        return registry.get(operator);
    }
}
