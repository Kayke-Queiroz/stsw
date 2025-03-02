package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.es.stsw.HelloWorld;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelloWorldSteps {
    
    private HelloWorld helloWorld;
    private String mensagem_retornada;

    @Given("usuario executou o programa")
    public void given_usuario_executou_programa() {
        this.helloWorld = new HelloWorld();
    }

    @When("o programa inicia")
    public void when_o_programa_inicia() {

        this.mensagem_retornada = this.helloWorld.getHelloWorld();
    }

    @When("o programa inicia e o usuario informa seu nome {string}")
    public void when_o_programa_inicia_e_usuario_informa_seu_nome(String nome) {
        this.mensagem_retornada = this.helloWorld.getHelloWorld(nome);
    }

    @Then("o programa imprime a mensagem {string}")
    public void then_o_programa_imprime_a_mensagem(String mensagem_esperada) {
        assertEquals(mensagem_esperada, this.mensagem_retornada);        
    }

}
