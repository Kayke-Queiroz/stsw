package br.edu.idp.es.stsw.steps;

import br.edu.idp.es.stsw.Triangle;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {

    private Triangle triangle;
    private String actualResult;

    @Given("que o valor minimo de um lado e {int}")
    public void queOValorMinimoDeUmLadoE(int ignoredMinSide) {
        this.triangle = new Triangle();
    }

    @Given("que o valor maximo de um lado e {int}")
    public void queOValorMaximoDeUmLadoE(int ignoredMaxSide) {
    }

    @Given("que o valor nominal e {int}")
    public void queOValorNominalE(int ignoredNominalSide) {
    }

    @Given("que o classificador de triangulo esta disponivel")
    public void queOClassificadorDeTrianguloEstaDisponivel() {
        this.triangle = new Triangle();
    }

    @When("eu classifico o triangulo com lados {int}, {int} e {int}")
    public void euClassificoOTrianguloComLados(int sideA, int sideB, int sideC) {
        if (triangle == null) {
            this.triangle = new Triangle();
        }
        this.actualResult = triangle.classify(sideA, sideB, sideC);
    }

    @When("eu classifico um triangulo com lados {int}, {int}, {int}")
    public void euClassificoUmTrianguloComLados(int sideA, int sideB, int sideC) {
        if (triangle == null) {
            this.triangle = new Triangle();
        }
        this.actualResult = triangle.classify(sideA, sideB, sideC);
    }

    @Then("o resultado deve ser {string}")
    public void oResultadoDeveSer(String expectedResult) {
        assertEquals(expectedResult, actualResult);
    }
}
