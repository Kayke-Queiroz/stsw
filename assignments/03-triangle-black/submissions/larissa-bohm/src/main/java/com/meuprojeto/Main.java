package com.meuprojeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Entrada entrada = new Entrada(scanner);

            int valor;
            int a = 0;
            int b = 0;
            int c = 0;

            while (true) {
                System.out.println("Digite o lado do triângulo: ");
                if (scanner.hasNextInt()) {
                    valor = scanner.nextInt();
                    if (valor >= 1 && valor <= 200) {
                        a = valor;
                    } else {
                        System.out.println("Erro: O valor não está entre 1 e 200.");
                    }
                } else {
                    System.out.println("Erro: o valor não é um inteiro válido. Por favor, digite outro número:");
                    scanner.next();
                }

                System.out.println("Digite o lado do triângulo: ");
                if (scanner.hasNextInt()) {
                    valor = scanner.nextInt();
                    if (valor >= 1 && valor <= 200) {
                        b = valor;
                    } else {
                        System.out.println("Erro: O valor não está entre 1 e 200.");
                    }
                } else {
                    System.out.println("Erro: o valor não é um inteiro válido. Por favor, digite outro número:");
                    scanner.next();
                }

                System.out.println("Digite o lado do triângulo: ");
                if (scanner.hasNextInt()) {
                    valor = scanner.nextInt();
                    if (valor >= 1 && valor <= 200) {
                        c = valor;
                    } else {
                        System.out.println("Erro: O valor não está entre 1 e 200.");
                    }
                } else {
                    System.out.println("Erro: o valor não é um inteiro válido. Por favor, digite outro número:");
                    scanner.next();
                }
                Triangulo t = new Triangulo(a, b, c);
                System.out.println(t.tipo());
            }
        }
    }

}
