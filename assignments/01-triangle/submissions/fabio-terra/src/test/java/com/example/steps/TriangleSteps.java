package com.example.steps;

import com.example.TriangleService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.junit.jupiter.api.Assertions;

public class TriangleSteps {

    private TriangleService triangleService = new TriangleService();
    private int a, b, c;
    private String resultadoReal;

    @Dado("que os lados informados são {int}, {int} e {int}")
    public void que_os_lados_informados_são(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Quando("eu executo a identificação")
    public void eu_executo_a_identificação() {
        this.resultadoReal = triangleService.identificarTriangulo(a, b, c);
    }

    @Então("o sistema retorna {string}")
    public void o_sistema_retorna(String resultadoEsperado) {
        Assertions.assertEquals(resultadoEsperado, resultadoReal);
    }
}