package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.es.stsw.hellocucumber.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private String mensagem;
    private Login login;
    private String usuario;
    private String senha;

    @Given("usuário está na página de login")
    public void given_usuario_acessou_pagina_de_login() {
    }

    @And("informa o login {string} e senha {string}")
    public void and_informa_login_e_senha(String usuario, String senha){
        login = new Login(usuario, senha);
    }

    @When("usuário clica no botão logar")
    public void when_usuario_clica_no_botao_logar() {

        mensagem = login.autenticar();
        
    }

    @Then("usuário deve receber a mensagem {string}")
    public void then_o_usuario_deve_receber_a_mensagem(String mensagem) {
        assertEquals(mensagem, this.mensagem);
    }
}