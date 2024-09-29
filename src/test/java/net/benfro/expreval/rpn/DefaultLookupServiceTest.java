package net.benfro.expreval.rpn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefaultLookupServiceTest {

    @Test
    void testOperator() {
        DefaultLookupService service = new DefaultLookupService();
        assertTrue(service.isOperator("+"));
        assertFalse(service.isOperator("sin"));
    }

    @Test
    void testFunction() {
        DefaultLookupService service = new DefaultLookupService();
        assertFalse(service.isFunction("+"));
        assertTrue(service.isFunction("sin"));
    }
}
