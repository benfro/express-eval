package net.benfro.expreval.evaluator;

import static java.lang.Math.pow;
import static net.benfro.expreval.parser.Operator.DIV;
import static net.benfro.expreval.parser.Operator.MINUS;
import static net.benfro.expreval.parser.Operator.MOD;
import static net.benfro.expreval.parser.Operator.MULT;
import static net.benfro.expreval.parser.Operator.PLUS;
import static net.benfro.expreval.parser.Operator.POW;

import net.benfro.expreval.OperationLookup;
import net.benfro.expreval.parser.Operator;

public record DoubleBiToken(NumericToken<Double> one, NumericToken<Double> two) {


    public static NumericToken<Double> doOperation(String op, DoubleBiToken token) {
        return switch (Operator.get(op)) {
            case PLUS -> OperationLookup.get(PLUS).doOp(token.one, token.two);
            case MINUS -> OperationLookup.get(MINUS).doOp(token.one, token.two);
            case MULT -> OperationLookup.get(MULT).doOp(token.one, token.two);
            case DIV -> OperationLookup.get(DIV).doOp(token.one, token.two);
            case POW -> OperationLookup.get(POW).doOp(token.one, token.two);
            case MOD -> OperationLookup.get(MOD).doOp(token.one, token.two);
            default -> NumericToken.ofDouble(Double.NaN);
        };
    }
}
