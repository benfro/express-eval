package net.benfro.expreval.util;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListStack<T> {

    public static <T> ListStack<T> of() {
        return new ListStack<>();
    }

    public static <T> ListStack<T> of(T... elements) {
        return new ListStack<>(Lists.newArrayList(elements));
    }

    public static <T> ListStack<T> of(List<T> elements) {
        return new ListStack<>(elements);
    }

    private final LinkedList<T> stack;

    public ListStack() {
        this.stack = Lists.newLinkedList();
    }

    private ListStack(List<T> stack) {
        this.stack = Lists.newLinkedList(stack);
    }

    public void push(T value) {
        stack.push(value);
        log.info("stack::push {}", value);
        debug();
    }

    public T pop() {
        T pop = stack.pop();
        log.info("stack::pop {}", pop);
        debug();
        return pop;
    }

    public T peek() {
        if (stack.isEmpty()) {
            log.info("stack::peek is null");
            return null;
        }
        T peek = stack.peek();
        log.info("stack::peek {}", peek);
//        debug();
        return peek;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public List<T> toReverseList() {
        return ImmutableList.copyOf(Lists.reverse(stack));
    }

    public List<T> toList() {
        return ImmutableList.copyOf(stack);
    }

    public ListStack<T> reversed() {
        return new ListStack<>(stack.reversed());
    }

    public List<T> popWhile(Predicate<T> predicate) {
        List<T> result = Lists.newArrayList();
        while (predicate.test(peek())) {
            result.add(pop());
        }
        return result;
    }

    public void clear() {
        stack.clear();
    }

    public void debug() {
        log.info("stack:: {} <=|", toReverseList());
    }

}
