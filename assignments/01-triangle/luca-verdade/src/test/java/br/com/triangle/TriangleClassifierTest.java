package br.com.triangle;
import br.com.triangle.TriangleClassifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassifierTest {

    @Test
    public void testEquilatero() {
        assertEquals("Equilátero", TriangleClassifier.classify(3, 3, 3));
    }

    @Test
    public void testIsosceles() {
        assertEquals("Isósceles", TriangleClassifier.classify(5, 5, 3));
    }

    @Test
    public void testEscaleno() {
        assertEquals("Escaleno", TriangleClassifier.classify(3, 4, 5));
    }

    @Test
    public void testNaoTrianguloLadosInvalidos() {
        assertEquals("Não é um triângulo", TriangleClassifier.classify(0, 4, 5));
    }

    @Test
    public void testNaoTrianguloSomaInvalida() {
        assertEquals("Não é um triângulo", TriangleClassifier.classify(1, 2, 3));
    }
}
