package net.benfro.expreval.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import net.benfro.expreval.function.FunctionExecutor;


@Disabled
class OperationLookupTest {

    @Test
    void testInit() {
        assertFalse(OperationLookup.registry.isEmpty());
        assertEquals(OperationLookup.registry.size(), OperationLookup.inverseRegistry.size());
        assertFalse(OperationLookup.symbol2OperatorMap.isEmpty());
    }

//    @Test
//    void testSymbolToOperatorMap() {
//        assertEquals(net.benfro.expreval.function.DefaultFunctions.ADD, OperationLookup.symbol2OperatorMap.get("+"));
//    }

    @Test
    void testGetOperationFromSymbol() {
        assertEquals(FunctionExecutor.ADDITION, OperationLookup.getOperation("+"));
    }

    @Test
    void testGetOperationFromSymbol2() {
        assertEquals(FunctionExecutor.ABSOLUTE, OperationLookup.getOperation("abs"));
    }

    @Test
    void testGetOperatorFromSymbol() {
        assertEquals(net.benfro.expreval.function.DefaultFunctions.ABS, OperationLookup.getOperator("abs"));
    }

//    @Test
//    void name() {
//        assertEquals(net.benfro.expreval.function.DefaultFunctions.ADD, OperationLookup.getOperator("+"));
//        assertTrue(net.benfro.expreval.function.DefaultFunctions.ADD.isOperator());
//    }
}
