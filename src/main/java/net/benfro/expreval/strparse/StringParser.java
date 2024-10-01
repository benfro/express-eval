package net.benfro.expreval.strparse;

import java.util.List;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.benfro.expreval.function.DefaultFunctions;
import net.benfro.expreval.util.CharStack;
import net.benfro.expreval.util.ListStack;

@Slf4j
public class StringParser {

    enum CharClass {
        NUM, LETTER, SIGN, BREAK, NONE;

        public static CharClass classify(char ch) {
            if (Character.isDigit(ch) || ch == '.') {
                return CharClass.NUM;
            } else if (Character.isLetter(ch)) {
                return CharClass.LETTER;
            } else if (DefaultFunctions.isOperator(String.valueOf(ch))) {
                return CharClass.SIGN;
            } else if (Character.isSpaceChar(ch) || ch == ',') {
                return CharClass.BREAK;
            } else {
                log.debug("Unknown class for char {}", ch);
            }
            return null;
        }
    }

    public List<String> parse(String str) {
        CharStack tempStack = new CharStack();
        ListStack<Character> dataStack = ListStack.of(str.chars().mapToObj(i -> (char) i));
        List<String> result = Lists.newArrayList();
        CharClass currentClass = CharClass.NONE;
        while (dataStack.hasNext()) {

            Character next = dataStack.pop();
            CharClass tempClass = CharClass.classify(next);

            if (tempClass == CharClass.BREAK) {
                currentClass = tempClass;
                continue;
            }

            if (tempClass != currentClass) {
                if (!tempStack.isEmpty()) {
                    String item = tempStack.flush();
                    result.add(item);
                }

                tempStack.push(next);

                currentClass = tempClass;
            } else {
                tempStack.push(next);
            }

            if (dataStack.isEmpty() && !tempStack.isEmpty()) {
                String item = tempStack.flush();
                result.add(item);
            }
        }

        return List.copyOf(result);
    }

}
