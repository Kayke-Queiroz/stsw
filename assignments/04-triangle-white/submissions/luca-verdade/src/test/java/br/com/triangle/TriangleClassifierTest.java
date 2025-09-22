package br.com.triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleClassifierTest {

    @Nested
    @DisplayName("Lados inválidos (a<=0 || b<=0 || c<=0)")
    class LadosInvalidos {
        @ParameterizedTest(name = "a={0}, b={1}, c={2} -> Lados inválidos")
        @CsvSource({
            "0,1,1", "1,0,1", "1,1,0",
            "-1,2,3", "2,-1,3", "2,3,-1"
        })
        void rejetaZeroOuNegativo(int a, int b, int c) {
            assertEquals("Lados inválidos",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }
    }

    @Nested
    @DisplayName("Não é triângulo (a+b<=c || a+c<=b || b+c<=a)")
    class NaoTriangulo {
        @ParameterizedTest(name = "Limite igualdade: {0},{1},{2}")
        @CsvSource({ "1,1,2", "1,2,1", "2,1,1" })
        void limiteIgualdade(int a, int b, int c) {
            assertEquals("Não é um triângulo",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }

        @ParameterizedTest(name = "Soma menor: {0},{1},{2}")
        @CsvSource({ "2,3,6", "3,6,2", "6,2,3" })
        void somaMenorQueTerceiro(int a, int b, int c) {
            assertEquals("Não é um triângulo",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }
    }

    @Nested
    @DisplayName("Classificações válidas")
    class TriangulosValidos {
        @Test
        void equilatero() {
            assertEquals("Equilátero",
                    TriangleClassifier.classificarTriangulo(3,3,3));
        }

        @ParameterizedTest(name = "Isósceles: {0},{1},{2}")
        @CsvSource({ "3,3,2", "3,2,3", "2,3,3", "5,4,5" })
        void isosceles(int a, int b, int c) {
            assertEquals("Isósceles",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }

        @ParameterizedTest(name = "Escaleno: {0},{1},{2}")
        @CsvSource({ "3,4,5", "4,6,7", "5,7,9" })
        void escaleno(int a, int b, int c) {
            assertEquals("Escaleno",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }
    }

    @Nested
    @DisplayName("Robustez (permutações)")
    class Robustez {
        @ParameterizedTest(name = "Isósceles permutado: {0},{1},{2}")
        @CsvSource({ "4,4,5", "4,5,4", "5,4,4" })
        void permutaIsosceles(int a, int b, int c) {
            assertEquals("Isósceles",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }

        @ParameterizedTest(name = "Escaleno permutado: {0},{1},{2}")
        @CsvSource({ "5,6,7", "6,7,5", "7,5,6" })
        void permutaEscaleno(int a, int b, int c) {
            assertEquals("Escaleno",
                    TriangleClassifier.classificarTriangulo(a,b,c));
        }
    }
}
