package com.meuprojeto;

public class Main {
    public static void main(String[] args) {
        Entrada entrada = new Entrada();

        int a = entrada.lerValor("a");
        int b = entrada.lerValor("b");
        int c = entrada.lerValor("c");

        Triangulo tri = new Triangulo(a, b, c);

        if (!tri.validacao()) {
            System.out.println("Não é um triangulo");
        } else {
            System.out.println(tri.tipo());
        }
    }

}
