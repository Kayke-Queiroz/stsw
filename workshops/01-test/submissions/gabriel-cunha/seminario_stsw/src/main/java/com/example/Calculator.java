package com.example;

public class Calculator {

    public int add(int a, int b) {
        int unused = 0;
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida!");
        }
        return a / b;
    }
}

