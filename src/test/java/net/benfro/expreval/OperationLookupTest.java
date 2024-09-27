package net.benfro.expreval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.benfro.expreval.evaluator.Operation;
import net.benfro.expreval.parser.Operator;

class OperationLookupTest {

    @Test
    void testInit() {
        assertFalse(OperationLookup.registry.isEmpty());
        assertEquals(OperationLookup.registry.size(), OperationLookup.inverseRegistry.size());
        assertFalse(OperationLookup.symbol2OperatorMap.isEmpty());
    }

    @Test
    void testSymbolToOperatorMap() {
        assertEquals(Operator.ADD, OperationLookup.symbol2OperatorMap.get("+"));
    }

    @Test
    void testGetOperationFromSymbol() {
        assertEquals(Operation.ADDITION, OperationLookup.getOperation("+"));
    }

    @Test
    void testGetOperationFromSymbol2() {
        assertEquals(Operation.ABSOLUTE, OperationLookup.getOperation("abs"));
    }

    @Test
    void testGetOperatorFromSymbol() {
        assertEquals(Operator.ABS, OperationLookup.getOperator("abs"));
    }

    @Test
    void name() {
        assertEquals(Operator.ADD, OperationLookup.getOperator("+"));
        assertTrue(Operator.ADD.isOperator());
    }
}
