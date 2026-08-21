package br.edu.idp.stsw.unittest;

import java.util.Arrays;

public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("Calculator demo");
        System.out.printf("2 + 3 = %d%n", calculator.add(2, 3));
        System.out.printf("10 / 2 = %d%n", calculator.divide(10, 2));
        System.out.printf("5! = %d%n", calculator.factorial(5));
        System.out.printf("Media de 2, 4 e 6 = %.2f%n", calculator.average(2, 4, 6));
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        return dividend / divisor;
    }

    public int power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be zero or positive.");
        }

        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }

    public long factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative values.");
        }

        long result = 1;
        for (int i = 2; i <= value; i++) {
            result *= i;
        }

        return result;
    }

    public int gcd(int a, int b) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("GCD is not defined when both values are zero.");
        }

        int x = Math.abs(a);
        int y = Math.abs(b);

        while (y != 0) {
            int remainder = x % y;
            x = y;
            y = remainder;
        }

        return x;
    }

    public double average(int... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("At least one value is required.");
        }

        return Arrays.stream(values).average().orElseThrow();
    }

    public boolean isEven(int value) {
        return value % 2 == 0;
    }
}
