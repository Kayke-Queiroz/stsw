package steps;

import pages.LoginPage;
import net.serenitybdd.annotations.Step;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Step("Abrir a página de login")
    public void acessarPaginaLogin() {
        loginPage.open();
        try {
            Thread.sleep(2000); // 2 segundos de delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Informar usuário {0} e senha")
    public void informarCredenciais(String usuario, String senha) {
        loginPage.inserirCredenciais(usuario, senha);
        try {
            Thread.sleep(1500); // 1.5 segundos de delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Confirmar login")
    public void confirmarLogin() {
        loginPage.clicarLogin();
        try {
            Thread.sleep(3000); // 3 segundos de delay para carregar a página
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Verificar se está na página de produtos")
    public boolean estaNaPaginaDeProdutos() {
        try {
            Thread.sleep(2000); // 2 segundos de delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return loginPage.estaNaPaginaProdutos();
    }

    @Step("Fazer login com usuário {0} e senha {1}")
    public void logInAs(String usuario, String senha) {
        acessarPaginaLogin();
        informarCredenciais(usuario, senha);
        confirmarLogin();
    }

    @Step("Verificar mensagem de erro")
    public String obterMensagemErro() {
        try {
            Thread.sleep(2000); // 2 segundos de delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return loginPage.obterMensagemErro();
    }

    @Step("Verificar se há mensagem de erro")
    public boolean temMensagemErro() {
        try {
            Thread.sleep(1500); // 1.5 segundos de delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return loginPage.temMensagemErro();
    }

    @Step("Tentar fazer login com credenciais inválidas")
    public void tentarLoginInvalido(String usuario, String senha) {
        acessarPaginaLogin();
        informarCredenciais(usuario, senha);
        confirmarLogin();
    }
}
