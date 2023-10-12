package edu.hw2.task1;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Expr;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExprTest {

    @Test
    @DisplayName("Тест Expr#evaluate() константы")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenConstant() {
        Expr constant = new Constant(10);
        Assertions.assertThat(constant.evaluate()).isEqualTo(10.0);
    }

    @Test
    @DisplayName("Тест Expr#evaluate() возведения в степень")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenExponent() {
        Expr exponent = new Exponent(new Constant(10), 2);
        Assertions.assertThat(exponent.evaluate()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("Тест Expr#evaluate() отрицания")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenNegate() {
        Expr negate = new Negate(new Constant(10));
        Assertions.assertThat(negate.evaluate()).isEqualTo(-10.0);
    }

    @Test
    @DisplayName("Тест Expr#evaluate() сложения")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenSum() {
        Expr addition = new Addition(new Constant(10), new Constant(5));
        Assertions.assertThat(addition.evaluate()).isEqualTo(15.0);
    }

    @Test
    @DisplayName("Тест Expr#evaluate() умножения")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenMultiplying() {
        Expr mult = new Multiplication(new Constant(10), new Constant(5));
        Assertions.assertThat(mult.evaluate()).isEqualTo(50.0);
    }

    @Test
    @DisplayName("Тест Expr#evaluate() на несколько выражений")
    public void evaluate_shouldReturnCorrectAnswer_whenGivenExpression() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        Assertions.assertThat(res.evaluate()).isEqualTo(37.0);
    }

}
