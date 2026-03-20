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

    @Given("que o valor mínimo de um lado é {int}")
    public void que_o_valor_minimo_de_um_lado_e(Integer min) {
    }

    @Given("que o valor máximo de um lado é {int}")
    public void que_o_valor_maximo_de_um_lado_e(Integer max) {
    }

    @Given("que o valor nominal é {int}")
    public void que_o_valor_nominal_e(Integer nominal) {
    }

    @When("eu classifico um triangulo com lados {int}, {int}, {int}")
    public void eu_classifico_um_triangulo(Integer a, Integer b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
        resultado = triangle.classify(a, b, c);
    }

    @Then("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}