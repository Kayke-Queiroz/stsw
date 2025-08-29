package br.edu.idp.es.stsw.hellocucumber;

public final class Triangulo {

    private Triangulo() {}

    public static String classificar(int a, int b, int c) {
        if (!validaFaixa(a, b, c)) return "Invalid sides";
        if (!formaTriangulo(a, b, c)) return "Not a triangle";
        if (a == b && b == c) return "Equilateral";
        if (a == b || a == c || b == c) return "Isosceles";
        return "Scalene";
    }

    private static boolean validaFaixa(int a, int b, int c) {
        return entre1e200(a) && entre1e200(b) && entre1e200(c);
    }

    private static boolean entre1e200(int v) {
        return v >= 1 && v <= 200;
    }

    private static boolean formaTriangulo(int a, int b, int c) {
        return a + b > c && a + c > b && b + c > a;
    }
}

