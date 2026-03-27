package br.edu.idp.es.stsw;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassificadorTrianguloTest {

    private final ClassificadorTriangulo classificador = new ClassificadorTriangulo();

    @Test
    void deveClassificarTrianguloEquilatero() {
        assertEquals("Equilátero", classificador.classificar(5, 5, 5));
    }

    @Test
    void deveClassificarTrianguloIsosceles() {
        assertEquals("Isósceles", classificador.classificar(5, 5, 3));
    }

    @Test
    void deveClassificarTrianguloEscaleno() {
        assertEquals("Escaleno", classificador.classificar(5, 4, 3));
    }

    @Test
    void deveRetornarQuandoNaoFormaTriangulo() {
        assertEquals("Não é um triângulo", classificador.classificar(1, 2, 3));
    }

    @Test
    void deveRetornarLadosInvalidosQuandoHaZeroOuNegativo() {
        assertEquals("Lados inválidos", classificador.classificar(-5, 0, 5));
    }

    @Test
    void deveRetornarLadosInvalidosQuandoUltrapassaLimiteMaximo() {
        assertEquals("Lados inválidos", classificador.classificar(201, 10, 10));
    }

    @Test
    void deveAceitarLimiteInferiorValido() {
        assertEquals("Equilátero", classificador.classificar(1, 1, 1));
    }

    @Test
    void deveAceitarLimiteSuperiorValido() {
        assertEquals("Equilátero", classificador.classificar(200, 200, 200));
    }

    @Test
    void deveExibirResultadoNoMain() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            ClassificadorTriangulo.main(new String[]{"5", "5", "3"});
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Isósceles" + System.lineSeparator(), output.toString());
    }

    @Test
    void deveExibirLadosInvalidosNoMainQuandoEntradaForInvalida() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            ClassificadorTriangulo.main(new String[]{"abc", "5", "3"});
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Lados inválidos" + System.lineSeparator(), output.toString());
    }
}
