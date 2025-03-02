package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.es.stsw.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private String mensagem;

    @Given("o usuário está na página de login")
    public void usuarioNaPaginaDeLogin() {
        System.out.println("Usuário acessou a página de login.");
    }

    @When("ele digita usuário {string} e senha {string}")
    public void usuarioEntraComCredenciais(String usuario, String senha) {

        System.out.println("Usuário: " + usuario + " | Senha: " + senha);

        Login login = new Login(usuario, senha);

        mensagem = login.autenticar(usuario, senha);
        
    }

    @Then("ele deve ver a mensagem {string}")
    public void sistemaExibeMensagem(String mensagem) {

        assertEquals(mensagem, this.mensagem);
        System.out.println("Mensagem exibida: " + mensagem);
    }
}