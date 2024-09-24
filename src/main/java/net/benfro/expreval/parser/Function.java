package net.benfro.expreval.parser;


import com.google.common.collect.Lists;
import net.benfro.expreval.evaluator.NumericToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Function {

   ABS("abs", Math::abs),
   SIN("sin", Math::sin),
   COS("cos", Math::cos),
   TAN("tan", Math::tan),
   ATAN("atan", Math::atan),
   SQRT("sqrt", Math::sqrt),
   CBRT("cbrt", Math::cbrt),
   EEXP("eexp", c -> Math.pow(Math.E, c)),
   EXP("exp", c -> Math.pow(10, c)),
   LN("ln", Math::log),
   LOG10("log", Math::log10),
   PI("pi", c -> Math.PI),
   E("e", c -> Math.E),
   INV("inv", c -> 1/c),
   NOOP("", c -> c),
   ;

   private static Map<String, Function> makeMapping() {
      return Collections.unmodifiableMap(Arrays.stream(values())
              .collect(Collectors.toMap(Function::getFunctionName, e -> e)));
   }

   private static List<String> makeFunctionsList() {
      return Collections.unmodifiableList(
              Lists.newArrayList(STR_TO_FUNC.keySet())
      );
   }

   public static Function get(String opStr) {
      return STR_TO_FUNC.getOrDefault(opStr, NOOP);
   }

   public static boolean isFunction(String opStr) {
      return FUNCTIONS_LIST.contains(opStr);
   }

   @FunctionalInterface
   interface UnaryCalculator {
      double calculate(double value);
   }

   private static Map<String, Function> STR_TO_FUNC = makeMapping();
   private static List<String> FUNCTIONS_LIST = makeFunctionsList();
   private final String functionName;
   private final UnaryCalculator function;

   Function(String functionName, UnaryCalculator function) {
      this.functionName = functionName;
      this.function = function;
   }

   public NumericToken<Double> calculate(double in) {
      return new NumericToken.DoubleNumericToken(function.calculate(in));
   }

   public String getFunctionName() {
      return functionName;
   }
}
