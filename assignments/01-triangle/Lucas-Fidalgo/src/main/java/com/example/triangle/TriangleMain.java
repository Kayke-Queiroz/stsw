package com.example.triangle;

import java.util.Scanner;

/**
 * Classe principal para demonstrar o uso do classificador de triângulos.
 * Permite entrada interativa do usuário via console.
 */
public class TriangleMain {
    
    public static void main(String[] args) {
        TriangleClassifier classifier = new TriangleClassifier();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Classificador de Triângulos ===");
        System.out.println("Digite os três lados do triângulo (valores inteiros entre 1 e 200):");
        
        try {
            System.out.print("Lado A: ");
            int a = scanner.nextInt();
            
            System.out.print("Lado B: ");
            int b = scanner.nextInt();
            
            System.out.print("Lado C: ");
            int c = scanner.nextInt();
            
            String resultado = classifier.classify(a, b, c);
            
            System.out.println("\n=== Resultado ===");
            System.out.println("Lados fornecidos: " + a + ", " + b + ", " + c);
            System.out.println("Classificação: " + resultado);
            
            // Demonstração adicional com casos de exemplo
            demonstrarExemplos(classifier);
            
        } catch (Exception e) {
            System.out.println("Erro: Por favor, digite apenas números inteiros.");
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Demonstra alguns exemplos de classificação de triângulos.
     */
    private static void demonstrarExemplos(TriangleClassifier classifier) {
        System.out.println("\n=== Exemplos de Classificação ===");
        
        int[][] exemplos = {
            {5, 5, 5},      // Equilátero
            {5, 5, 3},      // Isósceles  
            {5, 4, 3},      // Escaleno
            {1, 2, 3},      // Não é triângulo
            {-1, 5, 5},     // Lados inválidos
            {10, 10, 8},    // Isósceles
            {3, 4, 5},      // Escaleno
            {200, 200, 200} // Equilátero (limite superior)
        };
        
        for (int[] exemplo : exemplos) {
            String resultado = classifier.classify(exemplo[0], exemplo[1], exemplo[2]);
            System.out.printf("Lados (%d, %d, %d) -> %s%n", 
                exemplo[0], exemplo[1], exemplo[2], resultado);
        }
    }
}
