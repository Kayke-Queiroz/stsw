package steps;

import app.Login;
import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("o usuário está na página de login")
    public void usuarioNaPaginaDeLogin() {
        System.out.println("Usuário acessou a página de login.");
    }

    @When("ele digita usuário {string} e senha {string} e codigo de verificação {int}")
    public void usuarioEntraComCredenciais(String usuario, String senha, int cdig) {

        System.out.println("Usuário: " + usuario + " | Senha: " + senha);

        System.out.println("Código: " + cdig);

        Login login = new Login(usuario, senha);

        login.cdig = cdig;

        boolean autenticado = login.autenticar(usuario, "123456");

        if (autenticado) {
            System.out.println("Usuário autenticado com sucesso.");
        } else {
            System.out.println("Usuário não autenticado.");
            throw new RuntimeException("Usuário não autenticado.");
        }
    }

    @Then("ele deve ver a mensagem {string}")
    public void sistemaExibeMensagem(String mensagem) {
        System.out.println("Mensagem exibida: " + mensagem);
    }
}