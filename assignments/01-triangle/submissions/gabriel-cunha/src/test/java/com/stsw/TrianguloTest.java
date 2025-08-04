package com.stsw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrianguloTest {

    @Test
    void testEquilatero() {
        assertEquals("Equilátero", Triangulo.classificar(5, 5, 5));
    }

    @Test
    void testIsosceles() {
        assertEquals("Isósceles", Triangulo.classificar(5, 5, 3));
    }

    @Test
    void testEscaleno() {
        assertEquals("Escaleno", Triangulo.classificar(5, 4, 3));
    }

    @Test
    void testNaoTriangulo() {
        assertEquals("Não é um triângulo", Triangulo.classificar(1, 2, 3));
    }

    @Test
    void testLadosInvalidos() {
        assertEquals("Lados inválidos", Triangulo.classificar(-5, 0, 5));
    }

    @Test
    void testLimiteSuperior() {
        assertEquals("Equilátero", Triangulo.classificar(200, 200, 200));
    }

    @Test
    void testAcimaDoLimite() {
        assertEquals("Lados inválidos", Triangulo.classificar(201, 100, 100));
    }
}
