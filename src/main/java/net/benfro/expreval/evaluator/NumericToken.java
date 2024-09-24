package net.benfro.expreval.evaluator;

import static java.lang.Math.pow;

import net.benfro.expreval.parser.Operator;

public abstract class NumericToken<T extends Number> implements Token {

   public abstract static class BiToken<T extends Number> {

      public final NumericToken<T> one;
      public final NumericToken<T> two;

      public abstract NumericToken<T> doOperation(String op);

      protected BiToken(NumericToken<T> one, NumericToken<T> two) {
         this.one = one;
         this.two = two;
      }
   }

   public static class DoubleNumericToken extends NumericToken<Double> {

      static class DoubleBiToken extends BiToken<Double> {
         public DoubleBiToken(NumericToken<Double> one, NumericToken<Double> two) {
            super(one, two);
         }

         @Override
         public NumericToken<Double> doOperation(String op) {
            return switch (Operator.get(op)) {
                 case PLUS -> new DoubleNumericToken(one.get() + two.get());
                 case MINUS -> new DoubleNumericToken(one.get() - two.get());
                 case MULT -> new DoubleNumericToken(one.get() * two.get());
                 case DIV -> new DoubleNumericToken(one.get() / two.get());
                 case POW -> new DoubleNumericToken(pow(one.get(), two.get()));
                 case MOD -> new DoubleNumericToken(one.get() % two.get());
                 default -> new DoubleNumericToken(Double.NaN);
             };
         }

      }

      public DoubleNumericToken(Double value) {
         super(value);
      }

      @Override
      public Double get() {
         return value;
      }

      @Override
      public BiToken<Double> with(NumericToken<Double> other) {
         return new DoubleBiToken(this, other);
      }
   }

   static NumericToken<Double> ofDoubleToken(String numberString) {
      return new DoubleNumericToken(Double.parseDouble(numberString));
   }

   static NumericToken<Double> ofDoubleToken(Double numberString) {
      return new DoubleNumericToken(numberString);
   }

   protected final T value;

   protected NumericToken(T value) {
      this.value = value;
   }

   public T get() {
      return value;
   }

   @Override
   public boolean isNumeric() {
      return true;
   }

   public abstract BiToken<T> with(NumericToken<T> other);
}
