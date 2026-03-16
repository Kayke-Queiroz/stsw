package com.triangulo;

public class Triangulo {

    public String classificar(int a, int b, int c) {
        // BVA: Validação de limites (1 a 200)
        if (a < 1 || a > 200 || b < 1 || b > 200 || c < 1 || c > 200) {
            return "Lados inválidos";
        }

        // Regra de existência do triângulo
        if (!(a + b > c && a + c > b && b + c > a)) {
            return "Não é um triângulo";
        }

        if (a == b && b == c) {
            return "Equilátero";
        } else if (a == b || a == c || b == c) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }
}