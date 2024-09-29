package net.benfro.expreval.rpn;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;
import net.benfro.expreval.util.ListStack;

class ShuntAlgorithmTest {

    ShuntAlgorithm instance;

    @BeforeEach
    void setUp() {
        instance = new ShuntAlgorithm();
    }

    @Test
    void parse() {
    }

    @Nested
    @DisplayName("Incoming operator o1: " +
        "WHILE there is an operator o2 at the top of the stack which is not a left parenthesis AND " +
        "(o2 has greater precedence than o1 OR (o1 and o2 have equals precedence AND o1 is left associative)) " +
        "==>THEN pop o2 from stack into output queue" +
        "push o1 onto stack")
    class TestDoOnFunctionExecutor {

        ListStack<String> opStack;
        List<String> outBuffer;

        @BeforeEach
        void setUp() {
            opStack = new ListStack<>();
            outBuffer = Lists.newArrayList();
        }

        @Test
        @DisplayName("Empty stack, just push")
        void initialEmptyStack() {
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(0, outBuffer.size());
            assertEquals(1, opStack.size());
        }

        @Test
        @DisplayName("Operator with greater precedence on top of stack => pop to output, and push incoming")
        void higherPrecedence() {
            opStack.push("/");
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(1, outBuffer.size());
            assertEquals("/", outBuffer.get(0));
            assertEquals(1, opStack.size());
        }

        @Test
        @DisplayName("2 operator with greater precedence on top of stack => pop to output, and push incoming")
        void higherPrecedence2() {
            opStack.push("/");
            opStack.push("*");
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(2, outBuffer.size());
            assertEquals(List.of("*", "/"), outBuffer);
            assertEquals(1, opStack.size());
            assertEquals(List.of("+"), opStack.toReverseList());
        }

        @Test
        @DisplayName("2 operator with greater precedence on top of stack => pop to output, and push incoming")
        void equalPrecedence() {
            opStack.push("/");
            opStack.push("-");
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(2, outBuffer.size());
            assertEquals(List.of("-", "/"), outBuffer);
            assertEquals(1, opStack.size());
            assertEquals(List.of("+"), opStack.toReverseList());
        }

        @Test
        @DisplayName("1 operator with equal precedence on top of stack and then LP => stop popping on LP")
        void stopOnLeftParenthesis() {
            opStack.push("/");
            opStack.push("(");
            opStack.push("-");
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(1, outBuffer.size());
            assertEquals(List.of("-"), outBuffer);
            assertEquals(3, opStack.size());
            assertEquals(List.of("/","(","+"), opStack.toReverseList());
        }

        @Test
        @DisplayName("1 operator with equal precedence on top of stack and then LP => stop popping on LP")
        void stopOnLeftParenthesis2() {
            opStack.push("/");
            opStack.push("(");
            opStack.push("-");
            opStack.push("*");
            instance.doOnOperator("+", opStack, outBuffer);
            assertEquals(2, outBuffer.size());
            assertEquals(List.of("*","-"), outBuffer);
            assertEquals(3, opStack.size());
            assertEquals(List.of("/","(","+"), opStack.toReverseList());
        }
    }

    @Nested
    @DisplayName("WHILE the operator at top of the stack is not a left parenthesis" +
        "==>THEN pop from stack into the output queue" +
        "pop left parenthesis from stack and discard")
    class TestDoOnRightParenthesis {

        ListStack<String> opStack;
        List<String> outBuffer;

        @BeforeEach
        void setUp() {
            opStack = new ListStack<>();
            outBuffer = Lists.newArrayList();
        }

        @Test
        @DisplayName("Empty stack")
        void emptyStack() {
            instance.doOnRightParenthesis(opStack, outBuffer);
            assertEquals(0, outBuffer.size());
            assertEquals(0, opStack.size());
        }

        @Test
        @DisplayName("One left parenthesis")
        void oneLP() {
            opStack.push("(");
            instance.doOnRightParenthesis(opStack, outBuffer);
            assertEquals(0, outBuffer.size());
            assertEquals(0, opStack.size());
        }

        @Test
        @DisplayName("Pop to output while not left parenthesis, and then pop the left parenthesis")
        void popUntilLeftParenthesis() {
            opStack.push("/");
            opStack.push("(");
            opStack.push("-");
            opStack.push("*");
            instance.doOnRightParenthesis(opStack, outBuffer);
            assertEquals(2, outBuffer.size());
            assertEquals(List.of("*", "-"), outBuffer);
            assertEquals(1, opStack.size());
            assertEquals(List.of("/"), opStack.toReverseList());
        }
    }

}
