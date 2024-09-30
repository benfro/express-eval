package net.benfro.expreval.rpn;

import java.util.List;
import java.util.Objects;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.benfro.expreval.LookupService;
import net.benfro.expreval.function.FunctionInfo;
import net.benfro.expreval.util.ListStack;

@Slf4j
public class ShuntAlgorithm {

    private final LookupService lookup = new DefaultLookupService();
    private final ListStack<String> opStack = new ListStack<>();

    public List<String> parse(List<String> strings) {
        if (Objects.isNull(strings)) {
            return List.of();
        }

        List<String> outBuffer = Lists.newArrayList();
        for (String nextToken : strings) {
            if (lookup.isFunction(nextToken)) {
                log.debug("========> Function block::nextToken {}", nextToken);
                opStack.push(nextToken);
            } else if (isLeftParenthesis(nextToken)) {
                log.debug("========> Left parenthesis block::nextToken {}", nextToken);
                opStack.push(nextToken);
            } else if (lookup.isOperator(nextToken)) {
                log.debug("========> Operator block::nextToken {}", nextToken);
                doOnOperator(nextToken, opStack, outBuffer);
            } else if (isRightParenthesis(nextToken)) {
                log.debug("========> Right parenthesis block::nextToken {}", nextToken);
                doOnRightParenthesis(opStack, outBuffer);
            } else {
                log.debug("========> Operand block - nextToken => {}", nextToken);
                outBuffer.add(nextToken);
                log.debug("outbuffer::add {} at {}", nextToken, "");
            }
            log.debug("output:: {} <=|", outBuffer);
            opStack.debug();
        }

        while (!opStack.isEmpty()) {
            String pop = opStack.pop();
            outBuffer.add(pop);
            log.debug("outbuffer::add {} at {}", pop, "final");
        }
        log.debug("output:: {} <=|", outBuffer);
        opStack.debug();

        List<String> output = outBuffer.stream().toList();
        opStack.clear();
        log.debug("output:: [{}]", output);
        return output;
    }

    @VisibleForTesting
    void doOnOperator(String nextToken, ListStack<String> opStack, List<String> outBuffer) {

        FunctionInfo nextOperator = lookup.findInfo(nextToken);

        if (!opStack.isEmpty() && !isLeftParenthesis(opStack.peek())) {
            FunctionInfo topOfStackOperator = lookup.findInfo(opStack.peek());

            while (!opStack.isEmpty() && Objects.nonNull(topOfStackOperator) &&
                ((topOfStackOperator.comparePrecedenceWith(nextOperator) > 0 ||
                    (topOfStackOperator.comparePrecedenceWith(nextOperator) == 0 &&
                        nextOperator.isLeftAssociative())))) {
                outBuffer.add(opStack.pop());

                if (!opStack.isEmpty() && isLeftParenthesis(opStack.peek())) {
                    break;
                }

                if (Objects.nonNull(opStack.peek())) {
                    topOfStackOperator = lookup.findInfo(opStack.peek());
                }
            }
        }

        opStack.push(nextToken);
    }

    @VisibleForTesting
    void doOnRightParenthesis(ListStack<String> opStack, List<String> outBuffer) {

        if (!opStack.isEmpty() && !isLeftParenthesis(opStack.peek())) {
            FunctionInfo topOfStackOperator = lookup.findInfo(opStack.peek());
            while (!opStack.isEmpty() && Objects.nonNull(topOfStackOperator)) {
                if (Objects.nonNull(opStack.peek()) && isLeftParenthesis(opStack.peek())) {
                    break;
                }
                outBuffer.add(opStack.pop());
            }
        }

        if (!opStack.isEmpty()) {
            opStack.pop();
        }

        if (!opStack.isEmpty() && lookup.isFunction(opStack.peek())) {
            outBuffer.add(opStack.pop());
        }
    }

    private static boolean isRightParenthesis(String nextToken) {
        return ")".equals(nextToken);
    }

    private boolean isLeftParenthesis(String nextToken) {
        return "(".equals(nextToken);
    }
}
