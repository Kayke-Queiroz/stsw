package com.meuprojeto;

import java.util.Scanner;

public class Entrada {

    private Scanner scanner;

    public Entrada(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerValor(String nome) {

        int valor;

        while (true) {
            System.out.println("Digite " + nome + ": ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= 1 && valor <= 200) {
                    return valor;
                } else {
                    System.out.println("Erro: O valor não está entre 1 e 200.");
                }
            } else {
                System.out.println("Erro: o valor não é um inteiro válido. Por favor, digite outro número:");
                scanner.next();
            }
        }
    }
}
