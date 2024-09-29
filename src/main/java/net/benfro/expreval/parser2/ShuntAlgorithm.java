package net.benfro.expreval.parser2;

import java.util.List;
import java.util.Objects;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.benfro.expreval.util.ListStack;

@Slf4j
public class ShuntAlgorithm {

    private final LookupService lookup = new DefaultLookupService();
    private final List<String> outBuffer = Lists.newArrayList();
    private final ListStack<String> opStack = new ListStack<>();

    public String parse(List<String> strings) {
        if (Objects.isNull(strings)) {
            return "";
        }

        for (String nextToken : strings) {

            if (lookup.isOperator(nextToken)) {
                log.info("========> Operator block::nextToken {}", nextToken);
                doOnOperator(nextToken, opStack, outBuffer);
                stateDebug();
            } else if (isLeftParenthesis(nextToken)) {
                log.info("========> Left parenthesis block::nextToken {}", nextToken);
                opStack.push(nextToken);
                stateDebug();
            } else if (isRightParenthesis(nextToken)) {
                log.info("========> Right parenthesis block::nextToken {}", nextToken);
                doOnRightParenthesis(opStack, outBuffer);
                stateDebug();
            } else {
                log.info("========> Operand block - nextToken => {}", nextToken);
                addToBuffer(nextToken, "");
                stateDebug();
            }
        }

        while (stackIsNotEmpty()) {
            String pop = opStack.pop();
            addToBuffer(pop, "final");
        }

        String output = String.join(" ", outBuffer);
        clear();
        return output;
    }

    @VisibleForTesting
    void doOnOperator(String nextToken, ListStack<String> opStack, List<String> outBuffer) {

        Operator nextOperator = lookup.find(nextToken);
        Operator topOfStackOperator = lookup.find(opStack.peek());

        while (!opStack.isEmpty() && Objects.nonNull(topOfStackOperator) &&
            ((topOfStackOperator.comparePrecedenceWith(nextOperator) > 0 ||
                (topOfStackOperator.comparePrecedenceWith(nextOperator) == 0 && nextOperator.isLeftAssociative())))) {
            outBuffer.add(opStack.pop());

            if (Objects.nonNull(opStack.peek()) && isLeftParenthesis(opStack.peek())) {
                break;
            }

            topOfStackOperator = lookup.find(opStack.peek());
        }

        opStack.push(nextToken);
    }

    @VisibleForTesting
    void doOnRightParenthesis(ListStack<String> opStack, List<String> outBuffer) {

        Operator topOfStackOperator = lookup.find(opStack.peek());
        while (!opStack.isEmpty() && Objects.nonNull(topOfStackOperator)) {
            if (Objects.nonNull(opStack.peek()) && isLeftParenthesis(opStack.peek())) {
                break;
            }
            outBuffer.add(opStack.pop());
        }

        if (!opStack.isEmpty()) {
            opStack.pop();
        }
    }

    private void stateDebug() {
        debugOut();
        opStack.debug();
    }

    private void addToBuffer(String nextToken, String comment) {
        outBuffer.add(nextToken);
        log.info("outbuffer::add {} at {}", nextToken, comment);
    }

    private void clear() {
        opStack.clear();
        outBuffer.clear();
    }

    private static boolean isRightParenthesis(String nextToken) {
        return ")".equals(nextToken);
    }

    private boolean isLeftParenthesis(String nextToken) {
        return "(".equals(nextToken);
    }

    private boolean stackIsNotEmpty() {
        return !opStack.isEmpty();
    }

    private void debugOut() {
        log.info("output:: {} <=|", outBuffer);
    }
}
