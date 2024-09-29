package net.benfro.expreval.rpn;

import java.util.List;

import com.google.common.collect.Lists;
import net.benfro.expreval.RPNExpressionParser;

public class RPNParser implements RPNExpressionParser {

    public String parse(List<String> strings) {
        return String.join(" ", new ShuntAlgorithm().parse(strings));
    }

    @Override
    public String parse(String expression) {
        return parse(Lists.newArrayList(expression.split(" ")));
    }
}
