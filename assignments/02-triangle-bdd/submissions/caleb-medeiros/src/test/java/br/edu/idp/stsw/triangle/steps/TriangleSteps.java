package br.edu.idp.stsw.triangle.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.stsw.triangle.Triangle;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriangleSteps {

    private int ladoA;
    private int ladoB;
    private int ladoC;
    private String resultado;

    @Given("os lados do triângulo são {int}, {int} e {int}")
    public void os_lados_do_triangulo_sao(int a, int b, int c) {
        this.ladoA = a;
        this.ladoB = b;
        this.ladoC = c;
    }

    @When("eu classifico o triângulo")
    public void eu_classifico_o_triangulo() {
        this.resultado = Triangle.classify(ladoA, ladoB, ladoC);
    }

    @Then("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String resultadoEsperado) {
        assertEquals(resultadoEsperado, this.resultado);
    }
}
