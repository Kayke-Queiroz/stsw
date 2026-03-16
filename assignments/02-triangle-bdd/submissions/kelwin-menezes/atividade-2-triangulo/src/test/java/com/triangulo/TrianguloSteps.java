package com.triangulo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrianguloSteps {
    private Triangulo triangulo = new Triangulo();
    private int a, b, c;
    private String resultadoObtido;

    @Dado("que eu insiro os lados {int}, {int} e {int}")
    public void que_eu_insiro_os_lados(int l1, int l2, int l3) {
        this.a = l1;
        this.b = l2;
        this.c = l3;
    }

    @Quando("eu clico em classificar")
    public void eu_clico_em_classificar() {
        resultadoObtido = triangulo.classificar(a, b, c);
    }

    @Então("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String resultadoEsperado) {
        assertEquals(resultadoEsperado, resultadoObtido);
    }
}