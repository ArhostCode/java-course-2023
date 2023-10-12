package edu.hw2.task1;

public record Exponent(Expr expr, Expr degree) implements Expr {

    public Exponent(Expr expr, double degree) {
        this(expr, new Constant(degree));
    }

    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), degree.evaluate());
    }
}
