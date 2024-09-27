package net.benfro.expreval.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EvenSimplerParser {

    enum Association {
        Right, Left;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Op {
        ADD("+", 1, Association.Left),
        SUBTRACT("-", 1, Association.Left),
        MULTIPLY("*", 4, Association.Left),
        DIVIDE("/", 5, Association.Left),
        MOD("%", 1, Association.Left),
        POW("^", 10, Association.Right),
        ;

        private final String name;
        private final int prio;
        private final Association association;

        public boolean precedenceLt(Op o2) {
            return (getPrio() - o2.getPrio()) < 0;
        }

        public boolean precedenceLtOrEq(Op o2) {
            return (getPrio() - o2.getPrio()) <= 0;
        }

        public boolean isRightAssociative() {
            return this.association == Association.Right;
        }

        public static boolean isOperator(String op) {
            return Arrays.stream(Op.values()).map(Op::getName).anyMatch(op::equals);
        }

        public static Op find(String op) {
            return Arrays.stream(Op.values())
                .filter(opin -> opin.getName().equals(op))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown operator: " + op));
        }

    }

    static boolean leftAssociativeAndLtOrEq(Op X, Op Y) {
        return !X.isRightAssociative() && X.precedenceLtOrEq(Y);
    }

    static boolean rightAssociativeAndLt(Op X, Op Y) {
        return X.isRightAssociative() && X.precedenceLt(Y);
    }

    public String shuntAlgoritm(List<String> strings) {
        List<String> outBuffer = Lists.newArrayList();
        LinkedList<String> opStack = Lists.newLinkedList();

        for (String token : strings) {

            if (Op.isOperator(token)) {
                Op X = Op.find(token);

                while (
                    !opStack.isEmpty() &&
                        Op.isOperator(opStack.peek()) &&
                        (
                            leftAssociativeAndLtOrEq(X, Op.find(opStack.peek())) ||
                                rightAssociativeAndLt(X, Op.find(opStack.peek()))
                        )
                ) {
                    outBuffer.add(opStack.pop());
                }
                opStack.push(token);
            } else if ("(".equals(token)) {
                opStack.push(token);
            } else if (")".equals(token)) {
                while (Objects.nonNull(opStack.peek()) && !opStack.peek().equals("(")) {
                    outBuffer.add(opStack.pop());
                }
                opStack.pop();
            } else {
                outBuffer.add(token);
            }
        }
        while(!opStack.isEmpty()) {
            outBuffer.add(opStack.pop());
        }

        return String.join(" ", outBuffer);
    }


}
