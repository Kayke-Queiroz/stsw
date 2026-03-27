package br.edu.idp.es.stsw.steps;

import br.edu.idp.es.stsw.ClassificadorTriangulo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassificadorTrianguloSteps {

    private final ClassificadorTriangulo classificador = new ClassificadorTriangulo();
    private int ladoA;
    private int ladoB;
    private int ladoC;
    private String resultado;

    @Given("que eu informo os lados {int}, {int} e {int}")
    public void queEuInformoOsLados(int ladoA, int ladoB, int ladoC) {
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    @When("o sistema classifica o triangulo")
    public void oSistemaClassificaOTriangulo() {
        this.resultado = classificador.classificar(ladoA, ladoB, ladoC);
    }

    @Then("o resultado deve ser {string}")
    public void oResultadoDeveSer(String resultadoEsperado) {
        assertEquals(resultadoEsperado, resultado);
    }
}
