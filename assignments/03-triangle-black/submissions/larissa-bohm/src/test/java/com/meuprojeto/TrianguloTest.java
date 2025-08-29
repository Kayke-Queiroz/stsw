package com.meuprojeto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    @DisplayName("Deve criar um triângulo válido e classificá-lo como Equilátero")
    void deveClassificarTrianguloEquilatero() {
        Triangulo triangulo = new Triangulo(10, 10, 10);
        assertEquals("Equilatero", triangulo.tipo());
    }

    @Test
    @DisplayName("Deve classificar um triângulo como Isósceles")
    void deveClassificarTrianguloIsoceles() {
        Triangulo triangulo = new Triangulo(15, 15, 20);
        assertEquals("Isoceles", triangulo.tipo());
    }

    @Test
    @DisplayName("Deve classificar um triângulo como Escaleno")
    void deveClassificarTrianguloEscaleno() {
        Triangulo triangulo = new Triangulo(3, 4, 5);
        assertEquals("Escaleno", triangulo.tipo());
    }

    @Test
    @DisplayName("Deve classificar como 'Não é triângulo' quando a soma de dois lados é menor que o terceiro")
    void naoDeveSerTriangulo() {
        Triangulo triangulo = new Triangulo(10, 2, 5);
        assertEquals("Nao e triangulo", triangulo.tipo());
    }

    @Test
    @DisplayName("Deve lançar exceção quando um lado é menor que o limite (1)")
    void deveLancarExcecaoComValorMenorQueUm() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(0, 10, 10);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando um lado é maior que o limite (200)")
    void deveLancarExcecaoComValorMaiorQueDuzentos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(201, 10, 10);
        });
    }

    @Test
    @DisplayName("Deve validar corretamente os lados do triângulo")
    void deveValidarLadosDoTriangulo() {
        Triangulo trianguloValido = new Triangulo(5, 5, 8);
        assertTrue(trianguloValido.validacao());

        Triangulo trianguloInvalido = new Triangulo(1, 2, 10);
        assertFalse(trianguloInvalido.validacao());
    }
}