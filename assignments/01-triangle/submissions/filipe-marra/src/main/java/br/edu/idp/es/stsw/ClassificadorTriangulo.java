package br.edu.idp.es.stsw;

public class ClassificadorTriangulo {

    public static void main(String[] args) {
        ClassificadorTriangulo classificador = new ClassificadorTriangulo();

        if (args.length != 3) {
            System.out.println("Lados inválidos");
            return;
        }

        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);
            System.out.println(classificador.classificar(a, b, c));
        } catch (NumberFormatException e) {
            System.out.println("Lados inválidos");
        }
    }

    public String classificar(int a, int b, int c) {
        if (!ladoValido(a) || !ladoValido(b) || !ladoValido(c)) {
            return "Lados inválidos";
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            return "Não é um triângulo";
        }

        if (a == b && b == c) {
            return "Equilátero";
        }

        if (a == b || a == c || b == c) {
            return "Isósceles";
        }

        return "Escaleno";
    }

    private boolean ladoValido(int lado) {
        return lado >= 1 && lado <= 200;
    }
}
