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
      for (String term : exprList) {
         term = term.trim();
         if (NumberUtils.isParsable(term)) {
            out.add(term);
         } else if (Function.isFunction(term)) {
            stack.push(term);
         } else if (isOperator(term)) {
            while (isAllowedPop(term, stack)) {
               out.add(stack.pop());
            }
            stack.push(term);
         } else if (isLeftParenthesis(term)) {
            stack.push(term);
         } else if (isRightParenthesis(term)) {
            while (!isLeftParenthesis(stack.peek())) {
               out.add(stack.pop());
            }
            if (isLeftParenthesis(stack.peek())) {
               stack.pop();
            }
         }
      }
      while (hasNext(stack.peek())) {
         out.add(stack.pop());
      }
      return Joiner.on(' ').join(out);
   }

   private boolean isRightParenthesis(String term) {
      return Operator.get(term) == Operator.RPAR;
   }

   private boolean isLeftParenthesis(String term) {
      return Operator.get(term) == Operator.LPAR;
   }

   private boolean hasNext(String peek) {
      return Objects.nonNull(peek);
   }

   private boolean isAllowedPop(String term, final LinkedList<String> stack) {
      String peek = stack.peek();
      Operator peekOperator = Operator.get(peek);
      Operator termOperator = Operator.get(term);
      return hasNext(peek) && (
              Function.isFunction(peek) ||
              (peekOperator.hasHigherPrecedenceThan(termOperator)) ||
                      (peekOperator.isPrecedenceEqual(termOperator) && !peekOperator.isRightAssociative())
              ) && peekOperator != Operator.LPAR;
   }

   private boolean isOperator(final String s) {
      return Operator.isOperator(s);
   }
}
