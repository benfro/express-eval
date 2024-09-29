package net.benfro.expreval.parser2;

import java.util.List;

import com.google.common.collect.Lists;
import net.benfro.expreval.RPNExpressionParser;

public class EvenSimplerParser implements RPNExpressionParser {

    public String parse(List<String> strings) {
        return new ShuntAlgorithm().parse(strings);
    }

    @Override
    public String parse(String expression) {
        return parse(Lists.newArrayList(expression.split(" ")));
    }
}
