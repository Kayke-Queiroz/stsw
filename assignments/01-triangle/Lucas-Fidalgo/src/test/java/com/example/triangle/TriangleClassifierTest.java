package com.example.triangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe TriangleClassifier.
 * Inclui testes para todos os casos de uso especificados.
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
class TriangleClassifierTest {

    private TriangleClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new TriangleClassifier();
    }

    @Test
    @DisplayName("Teste 1: Triângulo Equilátero")
    void testEquilateralTriangle() {
        assertEquals("Equilátero", classifier.classify(5, 5, 5));
        assertEquals("Equilátero", classifier.classify(10, 10, 10));
        assertEquals("Equilátero", classifier.classify(1, 1, 1));
        assertEquals("Equilátero", classifier.classify(100, 100, 100));
    }

    @Test
    @DisplayName("Teste 2: Triângulo Isósceles")
    void testIsoscelesTriangle() {
        assertEquals("Isósceles", classifier.classify(5, 5, 3));
        assertEquals("Isósceles", classifier.classify(5, 3, 5));
        assertEquals("Isósceles", classifier.classify(3, 5, 5));
        assertEquals("Isósceles", classifier.classify(10, 10, 8));
        assertEquals("Isósceles", classifier.classify(7, 10, 7));
    }

    @Test
    @DisplayName("Teste 3: Triângulo Escaleno")
    void testScaleneTriangle() {
        assertEquals("Escaleno", classifier.classify(5, 4, 3));
        assertEquals("Escaleno", classifier.classify(6, 8, 10));
        assertEquals("Escaleno", classifier.classify(13, 14, 15));
        assertEquals("Escaleno", classifier.classify(3, 4, 5));
    }

    @Test
    @DisplayName("Teste 4: Não é um triângulo")
    void testInvalidTriangle() {
        assertEquals("Não é um triângulo", classifier.classify(1, 2, 3));
        assertEquals("Não é um triângulo", classifier.classify(1, 1, 3));
        assertEquals("Não é um triângulo", classifier.classify(10, 5, 3));
        assertEquals("Não é um triângulo", classifier.classify(1, 10, 12));
    }

    @Test
    @DisplayName("Teste 5: Lados inválidos - valores negativos ou zero")
    void testInvalidSides() {
        assertEquals("Lados inválidos", classifier.classify(-5, 0, 5));
        assertEquals("Lados inválidos", classifier.classify(0, 5, 5));
        assertEquals("Lados inválidos", classifier.classify(5, -1, 5));
        assertEquals("Lados inválidos", classifier.classify(5, 5, 0));
        assertEquals("Lados inválidos", classifier.classify(-1, -2, -3));
    }

    @Test
    @DisplayName("Teste 6: Valores extremos - limites superiores")
    void testUpperBoundaryValues() {
        assertEquals("Equilátero", classifier.classify(200, 200, 200));
        assertEquals("Isósceles", classifier.classify(200, 200, 100));
        assertEquals("Escaleno", classifier.classify(198, 199, 200));
    }

    @Test
    @DisplayName("Teste 7: Valores extremos - limites inferiores")
    void testLowerBoundaryValues() {
        assertEquals("Equilátero", classifier.classify(1, 1, 1));
        assertEquals("Não é um triângulo", classifier.classify(1, 1, 2));
        assertEquals("Isósceles", classifier.classify(2, 2, 1));
    }

    @Test
    @DisplayName("Teste 8: Valores acima do limite")
    void testValuesAboveLimit() {
        assertEquals("Lados inválidos", classifier.classify(201, 200, 200));
        assertEquals("Lados inválidos", classifier.classify(200, 201, 200));
        assertEquals("Lados inválidos", classifier.classify(200, 200, 201));
        assertEquals("Lados inválidos", classifier.classify(250, 250, 250));
    }

    @ParameterizedTest
    @DisplayName("Teste 9: Múltiplos casos de triângulos equiláteros")
    @ValueSource(ints = {1, 5, 10, 50, 100, 150, 200})
    void testMultipleEquilateralTriangles(int side) {
        assertEquals("Equilátero", classifier.classify(side, side, side));
    }

    @ParameterizedTest
    @DisplayName("Teste 10: Múltiplos casos usando CSV")
    @CsvSource({
        "3, 4, 5, Escaleno",
        "5, 5, 5, Equilátero",
        "5, 5, 8, Isósceles",
        "1, 2, 3, Não é um triângulo",
        "-1, 5, 5, Lados inválidos",
        "10, 24, 26, Escaleno",
        "13, 13, 24, Isósceles"
    })
    void testMultipleCasesWithCsv(int a, int b, int c, String expected) {
        assertEquals(expected, classifier.classify(a, b, c));
    }

    @Test
    @DisplayName("Teste 11: Casos especiais - triângulos quase inválidos")
    void testEdgeCases() {
        // Casos onde a soma de dois lados é exatamente igual ao terceiro
        assertEquals("Não é um triângulo", classifier.classify(1, 2, 3));
        assertEquals("Não é um triângulo", classifier.classify(5, 5, 10));
        assertEquals("Não é um triângulo", classifier.classify(3, 7, 10));
        
        // Casos válidos próximos ao limite
        assertEquals("Isósceles", classifier.classify(5, 5, 9));
        assertEquals("Escaleno", classifier.classify(3, 4, 6));
    }
}
