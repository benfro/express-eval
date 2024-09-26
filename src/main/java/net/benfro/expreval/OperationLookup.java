package net.benfro.expreval;

import java.util.EnumMap;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.benfro.expreval.evaluator.Operation;
import net.benfro.expreval.parser.Operator;

public class OperationLookup {
    private final EnumMap<Operator, @Nullable Operation> registry;

    public OperationLookup(EnumMap<Operator, @Nullable Operation> registry) {
        this.registry = Maps.newEnumMap(Operator.class);
        registry.put(Operator.PLUS, Operation.ADDITION);
        registry.put(Operator.MINUS, Operation.SUBTRACTION);
        registry.put(Operator.DIV, Operation.DIVISION);
        registry.put(Operator.MULT, Operation.MULTIPLICATION);

    }

    public void setN() {
//        registry = Maps.newEnumMap(Operator.class);
    }
}
