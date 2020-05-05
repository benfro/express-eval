package net.benfro.expreval.evaluator;

import net.benfro.expreval.parser.Operator;

public class NumericToken<T extends Number> implements Token {

   static abstract class BiToken<T extends Number> {

      public final NumericToken<T> one;
      public final NumericToken<T> two;

      public abstract NumericToken<T> doOperation(String op);

      public BiToken(NumericToken<T> one, NumericToken<T> two) {
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
               case POW -> new DoubleNumericToken(Math.pow(one.get(),two.get()));
               case MOD -> new DoubleNumericToken( one.get() % two.get());
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

   public NumericToken(T value) {
      this.value = value;
   }

   public T get() {
      return value;
   }

   @Override
   public boolean isNumeric() {
      return true;
   }

   public BiToken<T> with(NumericToken<T> other) {
      return null;
   }
}
