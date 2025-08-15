package com.example.triangle;

/**
 * Classificador de triângulos que determina o tipo de triângulo
 * baseado nas medidas de seus três lados.
 */
public class TriangleClassifier {

    /**
     * Classifica um triângulo baseado nos três lados fornecidos.
     * 
     * @param a primeiro lado do triângulo
     * @param b segundo lado do triângulo
     * @param c terceiro lado do triângulo
     * @return String indicando o tipo do triângulo ou mensagem de erro
     */
    public String classify(int a, int b, int c) {
        // Validação dos lados
        if (!isValidInput(a, b, c)) {
            return "Lados inválidos";
        }

        // Verificação se forma um triângulo válido
        if (!isValidTriangle(a, b, c)) {
            return "Não é um triângulo";
        }

        // Classificação do triângulo
        return getTriangleType(a, b, c);
    }

    /**
     * Valida se os valores de entrada são números inteiros positivos entre 1 e 200.
     * 
     * @param a primeiro lado
     * @param b segundo lado
     * @param c terceiro lado
     * @return true se todos os lados são válidos, false caso contrário
     */
    private boolean isValidInput(int a, int b, int c) {
        return a > 0 && a <= 200 && 
               b > 0 && b <= 200 && 
               c > 0 && c <= 200;
    }

    /**
     * Verifica se três lados podem formar um triângulo válido.
     * Um triângulo é válido se a soma de dois lados é sempre maior que o terceiro lado.
     * 
     * @param a primeiro lado
     * @param b segundo lado
     * @param c terceiro lado
     * @return true se os lados formam um triângulo válido, false caso contrário
     */
    private boolean isValidTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    /**
     * Determina o tipo do triângulo baseado nos três lados.
     * 
     * @param a primeiro lado
     * @param b segundo lado
     * @param c terceiro lado
     * @return String indicando o tipo do triângulo
     */
    private String getTriangleType(int a, int b, int c) {
        if (a == b && b == c) {
            return "Equilátero";
        } else if (a == b || a == c || b == c) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }
}
