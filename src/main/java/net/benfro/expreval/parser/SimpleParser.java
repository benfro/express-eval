package net.benfro.expreval.parser;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
public class SimpleParser implements ExprParser {
   @Override
   public String parse(String expression) {
      List<String> out = Lists.newArrayList();
      LinkedList<String> stack = Lists.newLinkedList();
      List<String> exprList = Splitter.on(' ').splitToList(expression);
      for (String s : exprList) {
         if (NumberUtils.isDigits(s)) {
            out.add(s);
         } else if (isOperator(s)) {
            stack.push(s);
         }
      }
      while (isAllowedPop(stack)) {
         out.add(stack.pop());
      }
      return Joiner.on(' ').join(out);
   }

   private boolean isAllowedPop(LinkedList<String> stack) {
      return Objects.nonNull(stack.peek());
   }

   private boolean isOperator(String s) {
      return "+-/*^()".contains(s);
   }
}
