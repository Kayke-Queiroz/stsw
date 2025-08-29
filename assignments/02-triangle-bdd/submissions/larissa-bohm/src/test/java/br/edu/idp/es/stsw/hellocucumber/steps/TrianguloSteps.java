package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.Assert.assertEquals;

import br.edu.idp.es.stsw.hellocucumber.Triangulo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TrianguloSteps {

    private int a, b, c;
    private String result;

    @Given("I have the sides {int}, {int}, and {int}")
    public void i_have_the_sides(Integer a, Integer b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @When("I classify the triangle")
    public void i_classify_the_triangle() {
        result = Triangulo.classificar(a, b, c);
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String expected) {
        assertEquals(expected, result);
    }
}
