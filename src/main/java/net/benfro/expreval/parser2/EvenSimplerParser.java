package net.benfro.expreval.parser2;

import java.util.List;

public class EvenSimplerParser {

    public String shuntAlgoritm(List<String> strings) {
        return new ShuntAlgorithm().parse(strings);
    }

}
