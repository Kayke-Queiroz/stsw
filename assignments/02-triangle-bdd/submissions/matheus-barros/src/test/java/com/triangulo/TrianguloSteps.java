package com.triangulo;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class TrianguloSteps {

    private Triangulo triangulo;
    private String resultado;

    @Given("os lados do triângulo são {int}, {int} e {int}")
    public void os_lados_do_triangulo_sao(int a, int b, int c) {
        if (a < 1 || a > 200 || b < 1 || b > 200 || c < 1 || c > 200) {
            resultado = "Lados inválidos";
        } else {
            triangulo = new Triangulo(a, b, c);
        }
    }

    @When("eu solicito a classificação do triângulo")
    public void eu_solicito_a_classificacao_do_triangulo() {
        if (triangulo != null) {
            resultado = triangulo.getTipo();
        }
    }

    @Then("o sistema deve retornar {string}")
    public void o_sistema_deve_retornar(String resultadoEsperado) {
        assertEquals(resultadoEsperado, resultado);
    }
}
