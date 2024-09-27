package net.benfro.expreval.dead;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

enum Function {

   ABS("abs"),
   SIN("sin"),
   COS("cos"),
   TAN("tan"),
   ATAN("atan"),
   SQRT("sqrt"),
   CBRT("cbrt"),
   EEXP("eexp"),
   EXP("exp"),
   LN("ln"),
   LOG10("log"),
   PI("pi"),
   E("e"),
   INV("inv"),
   POW("^"),
   NOOP(""),
   ;

   private static final Map<String, Function> STR_TO_FUNC = makeMapping();
   private static final List<String> FUNCTIONS_LIST = makeFunctionsList();

   private static Map<String, Function> makeMapping() {
      return Collections.unmodifiableMap(Arrays.stream(values())
              .collect(Collectors.toMap(Function::getFunctionName, e -> e)));
   }

   private static List<String> makeFunctionsList() {
      return Collections.unmodifiableList(
              Lists.newArrayList(STR_TO_FUNC.keySet())
      );
   }

   private static Function get(String opStr) {
      return STR_TO_FUNC.getOrDefault(opStr, NOOP);
   }

   private static boolean isFunction(String opStr) {
      return FUNCTIONS_LIST.contains(opStr);
   }

   @FunctionalInterface
   interface UnaryCalculator {
      double calculate(double value);
   }

   private final String functionName;

   Function(String functionName) {
      this.functionName = functionName;
   }

   private String getFunctionName() {
      return functionName;
   }
}
