package net.benfro.expreval.parser;

public enum Constant {
   PI("pi", Math.PI),
   E("e", Math.E);

   private final String name;
   private final double value;

   Constant(String name, double value) {
      this.name = name;
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public double getValue() {
      return value;
   }
}
