package com.triangulo;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.pt.*;

public class TrianguloSteps {
    private int a, b, c;
    private String resultado;

    @Dado("que o usuário informa os lados {int}, {int}, {int}")
    public void que_o_usuario_informa_os_lados(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Quando("eu classificar o triângulo")
    public void eu_classificar_o_triangulo() {
        resultado = TrianguloApp.classificarTriangulo(a, b, c);
    }

    @Entao("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}
