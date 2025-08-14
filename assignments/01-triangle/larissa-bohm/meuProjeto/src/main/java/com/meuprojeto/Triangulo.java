package com.meuprojeto;

public class Triangulo {
    private int a, b, c;

    public Triangulo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Validação dos lados do triângulo
    public boolean validacao() {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    // Classificação do triângulo
    public String tipo() {
        if (!validacao()) {
            return "Não é triangulo";
        } else if (a == b && b == c) {
            return "Triangulo Equilatero";
        } else if (a == b || b == c || a == c) {
            return "Triangulo Isoceles";
        } else {
            return "Triangulo Escaleno";
        }
    }
}