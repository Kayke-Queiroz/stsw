package br.edu.idp.es.stsw.steps;

import br.edu.idp.es.stsw.Triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TriangleSteps {

    int a;
    int b;
    int c;
    String resultado;

    Triangle triangle = new Triangle();

    @Given("os lados do triangulo são {int}, {int} e {int}")
    public void os_lados_do_triangulo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @When("eu classifico o triangulo")
    public void eu_classifico_o_triangulo() {
        resultado = triangle.classify(a, b, c);
    }

    @Then("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}