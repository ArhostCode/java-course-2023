package edu.hw2.task1;

public record Addition(Expr expr1, Expr expr2) implements Expr {
    @Override
    public double evaluate() {
        return expr1.evaluate() + expr2.evaluate();
    }
}
