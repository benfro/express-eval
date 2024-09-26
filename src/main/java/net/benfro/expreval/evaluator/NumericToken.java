package net.benfro.expreval.evaluator;

public record NumericToken<T extends Number> (T value)  {

   public static NumericToken<Double> ofDouble(String numberString) {
      return new NumericToken<>(Double.parseDouble(numberString));
   }

   public static NumericToken<Double> ofDouble(Double numberString) {
      return new NumericToken<>(numberString);
   }

   public String asString() {
      return value.toString();
   }

}
