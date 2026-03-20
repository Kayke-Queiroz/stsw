package br.edu.idp.stsw.triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Testes de caixa-branca para a classe Triangle.
 *
 * Análise estrutural do método classify(int a, int b, int c):
 *
 * Caminho 1: a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200 → "Lados inválidos"
 * Caminho 2: a + b <= c || a + c <= b || b + c <= a → "Não é um triângulo"
 * Caminho 3: a == b && b == c → "Equilátero"
 * Caminho 4: a == b || a == c || b == c → "Isósceles"
 * Caminho 5: else → "Escaleno"
 *
 * Cobertura-alvo: 100% de branches e condições compostas.
 */
class TriangleTest {

    // =========================================================================
    // Caminho 1: Validação de domínio - "Lados inválidos"
    // Condição composta: a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200
    // Cada subcondição deve ser testada individualmente (MC/DC)
    // =========================================================================
    @Nested
    @DisplayName("Caminho 1: Lados inválidos - validação de domínio")
    class LadosInvalidos {

        @Test
        @DisplayName("a <= 0 (a = 0)")
        void deveRetornarInvalidoQuandoAIgualZero() {
            assertEquals("Lados inválidos", Triangle.classify(0, 5, 5));
        }

        @Test
        @DisplayName("a <= 0 (a = -1)")
        void deveRetornarInvalidoQuandoANegativo() {
            assertEquals("Lados inválidos", Triangle.classify(-1, 5, 5));
        }

        @Test
        @DisplayName("b <= 0 (b = 0)")
        void deveRetornarInvalidoQuandoBIgualZero() {
            assertEquals("Lados inválidos", Triangle.classify(5, 0, 5));
        }

        @Test
        @DisplayName("b <= 0 (b = -1)")
        void deveRetornarInvalidoQuandoBNegativo() {
            assertEquals("Lados inválidos", Triangle.classify(5, -1, 5));
        }

        @Test
        @DisplayName("c <= 0 (c = 0)")
        void deveRetornarInvalidoQuandoCIgualZero() {
            assertEquals("Lados inválidos", Triangle.classify(5, 5, 0));
        }

        @Test
        @DisplayName("c <= 0 (c = -1)")
        void deveRetornarInvalidoQuandoCNegativo() {
            assertEquals("Lados inválidos", Triangle.classify(5, 5, -1));
        }

        @Test
        @DisplayName("a > 200 (a = 201)")
        void deveRetornarInvalidoQuandoAMaiorQue200() {
            assertEquals("Lados inválidos", Triangle.classify(201, 5, 5));
        }

        @Test
        @DisplayName("b > 200 (b = 201)")
        void deveRetornarInvalidoQuandoBMaiorQue200() {
            assertEquals("Lados inválidos", Triangle.classify(5, 201, 5));
        }

        @Test
        @DisplayName("c > 200 (c = 201)")
        void deveRetornarInvalidoQuandoCMaiorQue200() {
            assertEquals("Lados inválidos", Triangle.classify(5, 5, 201));
        }
    }

    // =========================================================================
    // Caminho 2: Desigualdade triangular - "Não é um triângulo"
    // Condição composta: a + b <= c || a + c <= b || b + c <= a
    // =========================================================================
    @Nested
    @DisplayName("Caminho 2: Não é um triângulo - desigualdade triangular")
    class NaoETriangulo {

        @Test
        @DisplayName("a + b <= c (soma igual)")
        void naoTrianguloQuandoASomaBIgualC() {
            assertEquals("Não é um triângulo", Triangle.classify(1, 2, 3));
        }

        @Test
        @DisplayName("a + b < c")
        void naoTrianguloQuandoASomaBMenorQueC() {
            assertEquals("Não é um triângulo", Triangle.classify(1, 2, 10));
        }

        @Test
        @DisplayName("a + c <= b (soma igual)")
        void naoTrianguloQuandoASomaCIgualB() {
            assertEquals("Não é um triângulo", Triangle.classify(1, 3, 2));
        }

        @Test
        @DisplayName("a + c < b")
        void naoTrianguloQuandoASomaCMenorQueB() {
            assertEquals("Não é um triângulo", Triangle.classify(1, 10, 2));
        }

        @Test
        @DisplayName("b + c <= a (soma igual)")
        void naoTrianguloQuandoBSomaCIgualA() {
            assertEquals("Não é um triângulo", Triangle.classify(3, 1, 2));
        }

        @Test
        @DisplayName("b + c < a")
        void naoTrianguloQuandoBSomaCMenorQueA() {
            assertEquals("Não é um triângulo", Triangle.classify(10, 1, 2));
        }
    }

    // =========================================================================
    // Caminho 3: Triângulo Equilátero - a == b && b == c
    // =========================================================================
    @Nested
    @DisplayName("Caminho 3: Equilátero")
    class Equilatero {

        @ParameterizedTest
        @DisplayName("Equilátero com diferentes valores válidos")
        @CsvSource({
            "1, 1, 1",
            "5, 5, 5",
            "100, 100, 100",
            "200, 200, 200"
        })
        void deveRetornarEquilatero(int a, int b, int c) {
            assertEquals("Equilátero", Triangle.classify(a, b, c));
        }
    }

    // =========================================================================
    // Caminho 4: Triângulo Isósceles - a == b || a == c || b == c
    // Cada subcondição testada individualmente
    // =========================================================================
    @Nested
    @DisplayName("Caminho 4: Isósceles")
    class Isosceles {

        @Test
        @DisplayName("a == b (e diferente de c)")
        void isoscelesQuandoAIgualB() {
            assertEquals("Isósceles", Triangle.classify(10, 10, 5));
        }

        @Test
        @DisplayName("a == c (e diferente de b)")
        void isoscelesQuandoAIgualC() {
            assertEquals("Isósceles", Triangle.classify(10, 5, 10));
        }

        @Test
        @DisplayName("b == c (e diferente de a)")
        void isoscelesQuandoBIgualC() {
            assertEquals("Isósceles", Triangle.classify(5, 10, 10));
        }

        @ParameterizedTest
        @DisplayName("Isósceles com valores variados")
        @CsvSource({
            "2, 2, 1",
            "50, 50, 30",
            "200, 200, 199",
            "100, 100, 199"
        })
        void deveRetornarIsosceles(int a, int b, int c) {
            assertEquals("Isósceles", Triangle.classify(a, b, c));
        }
    }

    // =========================================================================
    // Caminho 5: Triângulo Escaleno - else (todos diferentes e válido)
    // =========================================================================
    @Nested
    @DisplayName("Caminho 5: Escaleno")
    class Escaleno {

        @ParameterizedTest
        @DisplayName("Escaleno com diferentes valores válidos")
        @CsvSource({
            "3, 4, 5",
            "7, 8, 9",
            "50, 60, 70",
            "198, 199, 200",
            "2, 3, 4"
        })
        void deveRetornarEscaleno(int a, int b, int c) {
            assertEquals("Escaleno", Triangle.classify(a, b, c));
        }
    }

    // =========================================================================
    // Testes parametrizados cobrindo todos os caminhos em um único método
    // =========================================================================
    @ParameterizedTest
    @DisplayName("Cobertura completa de todos os caminhos")
    @CsvSource({
        // Lados inválidos (caminho 1 - cada subcondição)
        "0, 5, 5, Lados inválidos",
        "5, 0, 5, Lados inválidos",
        "5, 5, 0, Lados inválidos",
        "-1, 5, 5, Lados inválidos",
        "5, -1, 5, Lados inválidos",
        "5, 5, -1, Lados inválidos",
        "201, 5, 5, Lados inválidos",
        "5, 201, 5, Lados inválidos",
        "5, 5, 201, Lados inválidos",
        // Não é triângulo (caminho 2 - cada subcondição)
        "1, 2, 3, Não é um triângulo",
        "1, 3, 2, Não é um triângulo",
        "3, 1, 2, Não é um triângulo",
        // Equilátero (caminho 3)
        "1, 1, 1, Equilátero",
        "100, 100, 100, Equilátero",
        // Isósceles (caminho 4 - cada subcondição)
        "10, 10, 5, Isósceles",
        "10, 5, 10, Isósceles",
        "5, 10, 10, Isósceles",
        // Escaleno (caminho 5)
        "3, 4, 5, Escaleno",
        "198, 199, 200, Escaleno"
    })
    void testeTodosOsCaminhos(int a, int b, int c, String esperado) {
        assertEquals(esperado, Triangle.classify(a, b, c));
    }
}
