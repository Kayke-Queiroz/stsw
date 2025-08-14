package com.meuprojeto;

import java.util.Scanner;

public class Entrada {

    private Scanner scanner = new Scanner(System.in);

    public int lerValor(String nome) {

        // 1. Entrada de Dados
        int valor;

        while (true) {
            System.out.print("Digite " + nome + ": ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= 1 && valor <= 200) {
                    return valor;
                } else {
                    System.out.println("Erro: O valor não está entre 1 e 200.");
                }
            } else {
                System.err.println("Erro: o valor não é um inteiro válido. Por favor, digite outro número:");
                scanner.next();
            }
        }
    }
}
