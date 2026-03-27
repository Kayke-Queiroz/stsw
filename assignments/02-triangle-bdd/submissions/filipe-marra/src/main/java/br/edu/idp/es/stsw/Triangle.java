package br.edu.idp.es.stsw;

public class Triangle {

    private static final int MIN_SIDE = 1;
    private static final int MAX_SIDE = 200;

    public String classify(int sideA, int sideB, int sideC) {
        if (!isWithinAllowedRange(sideA) || !isWithinAllowedRange(sideB) || !isWithinAllowedRange(sideC)) {
            return "Lados inválidos";
        }

        if (!formsTriangle(sideA, sideB, sideC)) {
            return "Não é um triângulo";
        }

        if (sideA == sideB && sideB == sideC) {
            return "Equilátero";
        }

        if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return "Isósceles";
        }

        return "Escaleno";
    }

    private boolean isWithinAllowedRange(int side) {
        return side >= MIN_SIDE && side <= MAX_SIDE;
    }

    private boolean formsTriangle(int sideA, int sideB, int sideC) {
        return (long) sideA + sideB > sideC
            && (long) sideA + sideC > sideB
            && (long) sideB + sideC > sideA;
    }
}
