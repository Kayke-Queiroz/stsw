package com.triangulo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TrianguloTest {

    Triangulo triangulo = new Triangulo();

    @Test
    void deveSerEquilatero() {
        assertEquals("Equilátero", triangulo.classificar(5, 5, 5));
    }

    @Test
    void deveSerIsosceles() {
        assertEquals("Isósceles", triangulo.classificar(5, 5, 3));
    }

    @Test
    void deveSerEscaleno() {
        assertEquals("Escaleno", triangulo.classificar(3, 4, 5));
    }

    @Test
    void deveDetectarQueNaoEhTriangulo() {
        assertEquals("Não é um triângulo", triangulo.classificar(1, 2, 3));
    }

    @Test
    void deveValidarLimitesDeEntrada() {
        assertEquals("Lados inválidos", triangulo.classificar(0, 5, 5));
        assertEquals("Lados inválidos", triangulo.classificar(201, 100, 100));
    }
}