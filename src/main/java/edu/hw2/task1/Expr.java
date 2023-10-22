package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double number) implements Expr {
        @Override
        public double evaluate() {
            return number;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }
    }

    public record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

        @Override
        public String toString() {
            var value = -expr.evaluate();
            return String.valueOf(value);
        }
    }

    public record Exponent(Expr expr, double deg) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(expr().evaluate(), deg);
        }

        @Override
        public String toString() {
            return expr + "^" + deg;
        }
    }

    public record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + expr1 + " + " + expr2 + ")";
        }
    }

    public record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + expr1 + " * " + expr2 + ")";
        }
    }
}
