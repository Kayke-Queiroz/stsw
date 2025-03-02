package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.es.stsw.hellocucumber.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private String mensagem;

    @Given("usuário está na página de login")
    public void given_usuario_acessou_pagina_de_login() {
    }

    @When("usuário digita usuário {string} e senha {string}")
    public void when_ele_digita_usuario_e_senha(String usuario, String senha) {

        Login login = new Login(usuario, senha);

        mensagem = login.autenticar(usuario, senha);
        
    }

    @Then("usuário deve receber a mensagem {string}")
    public void then_o_usuario_deve_receber_a_mensagem(String mensagem) {
        assertEquals(mensagem, this.mensagem);
    }
}