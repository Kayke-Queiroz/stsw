package com.meuprojeto.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class TrianguloSteps {

    private Triangulo triangulo;
    private String resultado;

    @Given("que os lados do triangulo são {int}, {int} e {int}")
    public void ladosTriangulo(int a, int b, int c) {
        triangulo = new Triangulo(a, b, c);
    }

    @When("eu classifico o triangulo")
    public void classificoTriangulo() {
        resultado = triangulo.tipo();
    }

    @Then("o resultado deve ser {string}")
    public void resultadoDeveSer(String tipoTriangulo) {
        assertEquals(tipoTriangulo, resultado);
    }

}