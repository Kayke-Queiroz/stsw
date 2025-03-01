package steps;

import main.java.*;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("o usuário está na página de login")
    public void usuarioNaPaginaDeLogin() {
        System.out.println("Usuário acessou a página de login.");
    }

    @When("ele digita usuário {string} e senha {string}")
    public void usuarioEntraComCredenciais(String usuario, String senha) {

        System.out.println("Usuário: " + usuario + " | Senha: " + senha);

        Login login = new Login(usuario, senha);

        login.autenticar(usuario, s enha);
    }

    @Then("ele deve ver a mensagem {string}")
    public void sistemaExibeMensagem(String mensagem) {
        System.out.println("Mensagem exibida: " + mensagem);
    }
}