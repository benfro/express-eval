package net.benfro.expreval.util;

import java.util.Stack;
import java.util.stream.Collector;


public class CharStack extends ListStack<Character> {
    public CharStack() {

    }

    public String compile() {
        String collect = toReverseList().stream().collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString));
        clear();
        return collect;
    }

}
