package net.benfro.expreval.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

class CharStackTest {

    @Test
    void testEmptyOf() {
        ListStack<Character> stack = CharStack.of();
        assertNotNull(stack);
        assertTrue(stack.isEmpty());
    }

    @Test
    void testArrayInitOf() {
        ListStack<Character> stack = CharStack.of('a', 'b', 'c');
        assertNotNull(stack);
        assertEquals(3, stack.size());
    }

    @Test
    void testListInitOf() {
        ListStack<Character> stack = CharStack.of(Lists.newArrayList('a', 'b', 'c'));
        assertNotNull(stack);
        assertEquals(3, stack.size());
    }

    @Test
    void testPopWhile() {
        CharStack instance = create();
        assertEquals(2, instance.popWhile(c -> c >= 'c').size());
    }

    @Test
    void testReversedStack() {
        ListStack<Character> instance = create().reversed();

        assertEquals(4, instance.size());
        assertEquals('a', instance.pop());
        assertEquals(3, instance.size());
        assertEquals('b', instance.peek());
        instance.push('d');
        instance.push('e');
        assertTrue(instance.hasNext());
        assertEquals(5, instance.popWhile(Objects::nonNull).size());
        assertTrue(instance.isEmpty());
    }

    @Test
    void testBehavior() {
        CharStack instance = create();

        List<Character> reverseList = instance.toReverseList();
        assertEquals(4, reverseList.size());

        ListStack<Character> reversed = instance.reversed();
        assertEquals(4, reversed.size());

        List<Character> list = instance.toList();
        assertEquals(4, list.size());

        assertEquals(4, instance.size());
        assertEquals('d', instance.pop());
        assertEquals(3, instance.size());
        assertEquals('c', instance.peek());
        instance.push('d');
        instance.push('e');
        assertTrue(instance.hasNext());
        assertEquals(5, instance.popWhile(Objects::nonNull).size());
        assertTrue(instance.isEmpty());

        assertEquals('a', reverseList.getFirst());
        assertEquals('a', reversed.peek());
        assertEquals('d', Lists.newLinkedList(list).peek());
    }

    @Test
    void testPeekException() {
        ListStack<Character> stack = CharStack.of();
        assertNull(stack.peek());
    }

    private static CharStack create() {
        CharStack instance = new CharStack();
        instance.push('a');
        instance.push('b');
        instance.push('c');
        instance.push('d');
        return instance;
    }
}
