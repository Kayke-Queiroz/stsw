package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By productsTitle = By.className("title");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public void inserirCredenciais(String usuario, String senha) {
        $(usernameField).type(usuario);
        $(passwordField).type(senha);
    }

    public void clicarLogin() {
        $(loginButton).click();
    }

    public boolean estaNaPaginaProdutos() {
        return $(productsTitle).isVisible() && $(productsTitle).getText().equals("Products");
    }

    public String obterMensagemErro() {
        return $(errorMessage).getText();
    }

    public boolean temMensagemErro() {
        return $(errorMessage).isVisible();
    }
}
