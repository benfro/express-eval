package net.benfro.expreval.parser;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.benfro.expreval.OperationLookup;
import net.benfro.expreval.util.StringStack;

/**
 * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
public class SimpleParser implements ExprParser {
    @Override
    public String parse(String expression) {
        List<String> outputQueue = Lists.newArrayList();
        StringStack operatorStack = new StringStack();
        List<String> exprList = Splitter.on(' ').splitToList(expression);

        for (String term : exprList) {
            term = term.trim();
            if (NumberUtils.isParsable(term)) {
                outputQueue.add(term);
            } else if (getOperator(term).isFunction()) {
                operatorStack.push(term);
            } else if (isLeftParenthesis(term)) {
                operatorStack.push(term);
            } else if (isRightParenthesis(term)) {
                // Right parenthesis
                while (!isLeftParenthesis(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());
                }
                if (!isLeftParenthesis(operatorStack.peek())) {
                    throw new RuntimeException("Should be a Left Parenthesis");
                } else {
                    operatorStack.pop();
                }

                if (!operatorStack.isEmpty() && getOperator(operatorStack.peek()).isFunction()) {
                    outputQueue.add(operatorStack.pop());
                }
            } else if (getOperator(term).isOperator()) {

                while (isAllowedPop(term, operatorStack)) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(term);

            }
//            else if (isLeftParenthesis(term)) {
//                operatorStack.push(term);
//            } else if (isRightParenthesis(term)) {
//                while (!isLeftParenthesis(operatorStack.peek())) {
//                    outputQueue.add(operatorStack.pop());
//                }
//                if (isLeftParenthesis(operatorStack.peek())) {
//                    operatorStack.pop();
//                }
//            }
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }
        return Joiner.on(' ').join(outputQueue);
    }

    private static Operator getOperator(String term) {
        return OperationLookup.getOperator(term);
    }

    private boolean isRightParenthesis(String term) {
        return term.equals(")");
    }

    private boolean isLeftParenthesis(String term) {
        return term.equals("(");
    }

    private boolean hasNext(String peek) {
        return Objects.nonNull(peek);
    }

    /**
     * there is an operator o2 at the top of the operator stack which is not a left parenthesis,
     * and (o2 has greater precedence than o1 or (o1 and o2 have the same precedence and o1 is left-associative))
     */
    private boolean isAllowedPop(String term, final StringStack stack) {
        if (!stack.isEmpty()) {
            Operator o1 = getOperator(term);
            // there is an operator on top of the stack
            String peek = stack.peek();
            Operator o2 = getOperator(peek);
            boolean isOperator = o2.isOperator();
            // which is NOT a left paren
            boolean isLeftParenthesis = o2.getSymbol().equals(peek);

            boolean o2_higher_than_o1 = o2.hasHigherPrecedenceThan(o1);
            boolean o2_equals_o1 = o2.isPrecedenceEqual(o1);
            boolean o1_left_associative = !o1.isRightAssociative();

            boolean top_stack_not_left_parente = isOperator && !isLeftParenthesis;

            boolean precedence = o2_higher_than_o1 || o2_equals_o1;

            boolean thisshit = top_stack_not_left_parente &&
                (precedence && o1_left_associative);

            return thisshit;

            //Operator peekOperator = Operator.get(peek);
            //Operator termOperator = Operator.get(term);
            //return hasNext(peek) && (Operator.isOperatorSymbol(peek) ||
            //    (peekOperator.hasHigherPrecedenceThan(termOperator)) ||
            //    (peekOperator.isPrecedenceEqual(termOperator) && !peekOperator.isRightAssociative())
            //) && peekOperator != Operator.LPAR;
        } else {
            return false;
        }
    }

    private boolean isOperator(final String s) {
        return Operator.isOperatorSymbol(s);
    }
}
