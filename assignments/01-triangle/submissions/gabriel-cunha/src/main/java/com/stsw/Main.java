package com.stsw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o lado A: ");
        int a = scanner.nextInt();

        System.out.print("Digite o lado B: ");
        int b = scanner.nextInt();

        System.out.print("Digite o lado C: ");
        int c = scanner.nextInt();

        String resultado = Triangulo.classificar(a, b, c);
        System.out.println("Resultado: " + resultado);

        scanner.close();
    }
}
