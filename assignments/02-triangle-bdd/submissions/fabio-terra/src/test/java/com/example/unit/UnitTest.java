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
            "0, 0, 0, Invalido",
            "0, 0, 1, Invalido",
            "0, 0, 100, Invalido",
            "0, 0, 199, Invalido",
            "0, 0, 200, Invalido",
            "0, 0, 201, Invalido",

            "0, 1, 0, Invalido",
            "0, 1, 1, Invalido",
            "0, 1, 100, Invalido",
            "0, 1, 199, Invalido",
            "0, 1, 200, Invalido",
            "0, 1, 201, Invalido",

            "0, 100, 0, Invalido",
            "0, 100, 1, Invalido",
            "0, 100, 100, Invalido",
            "0, 100, 199, Invalido",
            "0, 100, 200, Invalido",
            "0, 100, 201, Invalido",

            "0, 199, 0, Invalido",
            "0, 199, 1, Invalido",
            "0, 199, 100, Invalido",
            "0, 199, 199, Invalido",
            "0, 199, 200, Invalido",
            "0, 199, 201, Invalido",

            "0, 200, 0, Invalido",
            "0, 200, 1, Invalido",
            "0, 200, 100, Invalido",
            "0, 200, 199, Invalido",
            "0, 200, 200, Invalido",
            "0, 200, 201, Invalido",

            "0, 201, 0, Invalido",
            "0, 201, 1, Invalido",
            "0, 201, 100, Invalido",
            "0, 201, 199, Invalido",
            "0, 201, 200, Invalido",
            "0, 201, 201, Invalido",

            "1, 0, 0, Invalido",
            "1, 0, 1, Invalido",
            "1, 0, 100, Invalido",
            "1, 0, 199, Invalido",
            "1, 0, 200, Invalido",
            "1, 0, 201, Invalido",

            "1, 1, 0, Invalido",
            "1, 1, 1, Equilatero",
            "1, 1, 100, Nao e um triangulo",
            "1, 1, 199, Nao e um triangulo",
            "1, 1, 200, Nao e um triangulo",
            "1, 1, 201, Invalido",

            "1, 100, 0, Invalido",
            "1, 100, 1, Nao e um triangulo",
            "1, 100, 100, Isosceles",
            "1, 100, 199, Nao e um triangulo",
            "1, 100, 200, Nao e um triangulo",
            "1, 100, 201, Invalido",

            "1, 199, 0, Invalido",
            "1, 199, 1, Nao e um triangulo",
            "1, 199, 100, Nao e um triangulo",
            "1, 199, 199, Isosceles",
            "1, 199, 200, Nao e um triangulo",
            "1, 199, 201, Invalido",

            "1, 200, 0, Invalido",
            "1, 200, 1, Nao e um triangulo",
            "1, 200, 100, Nao e um triangulo",
            "1, 200, 199, Nao e um triangulo",
            "1, 200, 200, Isosceles",
            "1, 200, 201, Invalido",

            "1, 201, 0, Invalido",
            "1, 201, 1, Invalido",
            "1, 201, 100, Invalido",
            "1, 201, 199, Invalido",
            "1, 201, 200, Invalido",
            "1, 201, 201, Invalido",

            "100, 0, 0, Invalido",
            "100, 0, 1, Invalido",
            "100, 0, 100, Invalido",
            "100, 0, 199, Invalido",
            "100, 0, 200, Invalido",
            "100, 0, 201, Invalido",

            "100, 1, 0, Invalido",
            "100, 1, 1, Nao e um triangulo",
            "100, 1, 100, Isosceles",
            "100, 1, 199, Nao e um triangulo",
            "100, 1, 200, Nao e um triangulo",
            "100, 1, 201, Invalido",

            "100, 100, 0, Invalido",
            "100, 100, 1, Isosceles",
            "100, 100, 100, Equilatero",
            "100, 100, 199, Isosceles",
            "100, 100, 200, Nao e um triangulo",
            "100, 100, 201, Invalido",

            "100, 199, 0, Invalido",
            "100, 199, 1, Nao e um triangulo",
            "100, 199, 100, Isosceles",
            "100, 199, 199, Isosceles",
            "100, 199, 200, Escaleno",
            "100, 199, 201, Invalido",

            "100, 200, 0, Invalido",
            "100, 200, 1, Nao e um triangulo",
            "100, 200, 100, Nao e um triangulo",
            "100, 200, 199, Escaleno",
            "100, 200, 200, Isosceles",
            "100, 200, 201, Invalido",

            "100, 201, 0, Invalido",
            "100, 201, 1, Invalido",
            "100, 201, 100, Invalido",
            "100, 201, 199, Invalido",
            "100, 201, 200, Invalido",
            "100, 201, 201, Invalido",

            "199, 0, 0, Invalido",
            "199, 0, 1, Invalido",
            "199, 0, 100, Invalido",
            "199, 0, 199, Invalido",
            "199, 0, 200, Invalido",
            "199, 0, 201, Invalido",

            "199, 1, 0, Invalido",
            "199, 1, 1, Nao e um triangulo",
            "199, 1, 100, Nao e um triangulo",
            "199, 1, 199, Isosceles",
            "199, 1, 200, Nao e um triangulo",
            "199, 1, 201, Invalido",

            "199, 100, 0, Invalido",
            "199, 100, 1, Nao e um triangulo",
            "199, 100, 100, Isosceles",
            "199, 100, 199, Isosceles",
            "199, 100, 200, Escaleno",
            "199, 100, 201, Invalido",

            "199, 199, 0, Invalido",
            "199, 199, 1, Isosceles",
            "199, 199, 100, Isosceles",
            "199, 199, 199, Equilatero",
            "199, 199, 200, Isosceles",
            "199, 199, 201, Invalido",

            "199, 200, 0, Invalido",
            "199, 200, 1, Nao e um triangulo",
            "199, 200, 100, Escaleno",
            "199, 200, 199, Isosceles",
            "199, 200, 200, Isosceles",
            "199, 200, 201, Invalido",

            "199, 201, 0, Invalido",
            "199, 201, 1, Invalido",
            "199, 201, 100, Invalido",
            "199, 201, 199, Invalido",
            "199, 201, 200, Invalido",
            "199, 201, 201, Invalido",

            "200, 0, 0, Invalido",
            "200, 0, 1, Invalido",
            "200, 0, 100, Invalido",
            "200, 0, 199, Invalido",
            "200, 0, 200, Invalido",
            "200, 0, 201, Invalido",

            "200, 1, 0, Invalido",
            "200, 1, 1, Nao e um triangulo",
            "200, 1, 100, Nao e um triangulo",
            "200, 1, 199, Nao e um triangulo",
            "200, 1, 200, Isosceles",
            "200, 1, 201, Invalido",

            "200, 100, 0, Invalido",
            "200, 100, 1, Nao e um triangulo",
            "200, 100, 100, Nao e um triangulo",
            "200, 100, 199, Escaleno",
            "200, 100, 200, Isosceles",
            "200, 100, 201, Invalido",

            "200, 199, 0, Invalido",
            "200, 199, 1, Nao e um triangulo",
            "200, 199, 100, Escaleno",
            "200, 199, 199, Isosceles",
            "200, 199, 200, Isosceles",
            "200, 199, 201, Invalido",

            "200, 200, 0, Invalido",
            "200, 200, 1, Isosceles",
            "200, 200, 100, Isosceles",
            "200, 200, 199, Isosceles",
            "200, 200, 200, Equilatero",
            "200, 200, 201, Invalido",

            "200, 201, 0, Invalido",
            "200, 201, 1, Invalido",
            "200, 201, 100, Invalido",
            "200, 201, 199, Invalido",
            "200, 201, 200, Invalido",
            "200, 201, 201, Invalido",

            "201, 0, 0, Invalido",
            "201, 0, 1, Invalido",
            "201, 0, 100, Invalido",
            "201, 0, 199, Invalido",
            "201, 0, 200, Invalido",
            "201, 0, 201, Invalido",

            "201, 1, 0, Invalido",
            "201, 1, 1, Invalido",
            "201, 1, 100, Invalido",
            "201, 1, 199, Invalido",
            "201, 1, 200, Invalido",
            "201, 1, 201, Invalido",

            "201, 100, 0, Invalido",
            "201, 100, 1, Invalido",
            "201, 100, 100, Invalido",
            "201, 100, 199, Invalido",
            "201, 100, 200, Invalido",
            "201, 100, 201, Invalido",

            "201, 199, 0, Invalido",
            "201, 199, 1, Invalido",
            "201, 199, 100, Invalido",
            "201, 199, 199, Invalido",
            "201, 199, 200, Invalido",
            "201, 199, 201, Invalido",

            "201, 200, 0, Invalido",
            "201, 200, 1, Invalido",
            "201, 200, 100, Invalido",
            "201, 200, 199, Invalido",
            "201, 200, 200, Invalido",
            "201, 200, 201, Invalido",

            "201, 201, 0, Invalido",
            "201, 201, 1, Invalido",
            "201, 201, 100, Invalido",
            "201, 201, 199, Invalido",
            "201, 201, 200, Invalido",
            "201, 201, 201, Invalido",
    })

    public void testTriangulosRequisitados(int a, int b, int c, String resultadoEsperado) {
        String resultadoReal = triangleService.identificarTriangulo(a, b, c);
        Assertions.assertEquals(resultadoEsperado, resultadoReal);
    }
}