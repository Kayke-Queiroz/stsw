package br.com.triangle;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private int a, b, c;
    private String resultado;

    @Given("os lados são {int}, {int} e {int}")
    public void os_lados_sao(int ladoA, int ladoB, int ladoC) {
        this.a = ladoA;
        this.b = ladoB;
        this.c = ladoC;
    }

    @When("eu classifico o triângulo")
    public void eu_classifico_o_triangulo() {
        resultado = TriangleClassifier.classify(a, b, c);
    }

    @Then("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}
