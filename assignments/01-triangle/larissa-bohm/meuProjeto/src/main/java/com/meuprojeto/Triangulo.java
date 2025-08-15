package com.meuprojeto;

public class Triangulo {
    private int a, b, c;

    public Triangulo(int a, int b, int c) {
        validarFaixa(a, b, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void validarFaixa(int a, int b, int c) {
        if (a < 1 || a > 200 || b < 1 || b > 200 || c < 1 || c > 200) {
            throw new IllegalArgumentException("Valores devem estar entre 1 e 200");
        }
    }

    // Validação dos lados do triângulo
    public boolean validacao() {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    // Classificação do triângulo
    public String tipo() {
        if (!validacao()) {
            return "Nao e triangulo";
        } else if (a == b && b == c) {
            return "Equilatero";
        } else if (a == b || b == c || a == c) {
            return "Isoceles";
        } else {
            return "Escaleno";
        }
    }
}