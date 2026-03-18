package com.example.unit;

import com.example.TriangleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UnitTest {

    private TriangleService triangleService;

    @BeforeEach
    public void setup() {
        triangleService = new TriangleService();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 5, Equilatero",
            "5, 5, 3, Isosceles",
            "5, 4, 3, Escaleno",
            "1, 2, 3, Nao e um triangulo",
            "-5, 0, 5, Invalido"
    })
    public void testTriangulosRequisitados(int a, int b, int c, String resultadoEsperado) {
        String resultadoReal = triangleService.identificarTriangulo(a, b, c);
        Assertions.assertEquals(resultadoEsperado, resultadoReal);
    }
}