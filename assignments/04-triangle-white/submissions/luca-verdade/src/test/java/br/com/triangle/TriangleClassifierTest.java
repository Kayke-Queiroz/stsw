package br.com.triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleClassifierTest {

    @Nested
    @DisplayName("Borda do domínio: lados não-positivos")
    class LadosInvalidos {
        @ParameterizedTest(name = "a={0}, b={1}, c={2} -> Não é um triângulo")
        @CsvSource({
            "0,1,1", "1,0,1", "1,1,0",
            "-1,2,3", "2,-1,3", "2,3,-1"
        })
        void rejeitaZeroOuNegativo(int a, int b, int c) {
            assertEquals("Não é um triângulo",
                    TriangleClassifier.classify(a, b, c));
        }
    }

    @Nested
    @DisplayName("Borda da desigualdade (a+b<=c etc.)")
    class NaoTriangulo {
        @ParameterizedTest(name = "Igualdade na borda: {0},{1},{2}")
        @CsvSource({ "1,1,2", "1,2,1", "2,1,1" })
        void limiteIgualdade(int a, int b, int c) {
            assertEquals("Não é um triângulo",
                    TriangleClassifier.classify(a, b, c));
        }

        @ParameterizedTest(name = "Soma menor que o terceiro: {0},{1},{2}")
        @CsvSource({ "2,3,6", "3,6,2", "6,2,3" })
        void somaMenorQueTerceiro(int a, int b, int c) {
            assertEquals("Não é um triângulo",
                    TriangleClassifier.classify(a, b, c));
        }
    }

    @Nested
    @DisplayName("Classificações válidas nas bordas mínimas")
    class TriangulosValidos {
        @Test
        void equilatero_minimo() {
            assertEquals("Equilátero",
                    TriangleClassifier.classify(1, 1, 1));
        }

        @ParameterizedTest(name = "Isósceles: {0},{1},{2}")
        @CsvSource({ "3,3,2", "3,2,3", "2,3,3", "5,4,5" })
        void isosceles(int a, int b, int c) {
            assertEquals("Isósceles",
                    TriangleClassifier.classify(a, b, c));
        }

        @ParameterizedTest(name = "Escaleno: {0},{1},{2}")
        @CsvSource({ "2,3,4", "3,4,5", "4,6,7" })
        void escaleno(int a, int b, int c) {
            assertEquals("Escaleno",
                    TriangleClassifier.classify(a, b, c));
        }
    }
}
