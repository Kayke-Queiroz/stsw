package com.meuprojeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Entrada entrada = new Entrada(scanner);

            int a = entrada.lerValor("a");
            int b = entrada.lerValor("b");
            int c = entrada.lerValor("c");

            Triangulo t = new Triangulo(a, b, c);

            System.out.println(t.tipo());
        }

    }

}
