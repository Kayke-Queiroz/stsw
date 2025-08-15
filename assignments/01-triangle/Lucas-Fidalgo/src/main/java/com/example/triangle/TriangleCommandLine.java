package com.example.triangle;

/**
 * Versão da aplicação que aceita argumentos de linha de comando
 * para permitir testes automatizados externos (ex: Python)
 */
public class TriangleCommandLine {
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Erro: Forneça exatamente 3 argumentos (lados do triângulo)");
            System.err.println("Uso: java TriangleCommandLine <lado1> <lado2> <lado3>");
            System.exit(1);
        }
        
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);
            
            TriangleClassifier classifier = new TriangleClassifier();
            String resultado = classifier.classify(a, b, c);
            
            // Saída limpa para facilitar parsing pelos testes
            System.out.println(resultado);
            
        } catch (NumberFormatException e) {
            System.err.println("Erro: Todos os argumentos devem ser números inteiros");
            System.exit(1);
        }
    }
}
