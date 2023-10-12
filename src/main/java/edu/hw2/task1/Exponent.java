package edu.hw2.task1;

public record Exponent(Expr expr, double degree) implements Expr {

    public Exponent {
        if (expr.evaluate() == 0 && degree <= 0) {
            throw new IllegalArgumentException("For 0 degree must be greater than 0");
        }
    }

    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), degree);
    }
}
