/**
 * TESTES NEGATIVOS - LoginNegativeTest
 * 
 * Estes testes verificam comportamentos esperados quando algo dá errado:
 * - Credenciais inválidas → deve mostrar mensagem de erro
 * - Campos vazios → deve solicitar preenchimento
 * - Usuário bloqueado → deve impedir acesso
 * 
 * Objetivo: Garantir que a aplicação trata erros adequadamente
 */
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginNegativeTest {

    LoginSteps loginSteps = new LoginSteps();

    @Test
    void usuarioNaoConsegueFazerLoginComCredenciaisInvalidas() {
        // Given
        String usuarioInvalido = "usuario_inexistente";
        String senhaInvalida = "senha_errada";

        // When
        loginSteps.tentarLoginInvalido(usuarioInvalido, senhaInvalida);

        // Then
        Assertions.assertThat(loginSteps.temMensagemErro()).isTrue();
        String mensagemErro = loginSteps.obterMensagemErro();
        Assertions.assertThat(mensagemErro).contains("Username and password do not match");
    }

    @Test
    void usuarioNaoConsegueFazerLoginComUsuarioVazio() {
        // Given
        String usuarioVazio = "";
        String senha = "secret_sauce";

        // When
        loginSteps.tentarLoginInvalido(usuarioVazio, senha);

        // Then
        Assertions.assertThat(loginSteps.temMensagemErro()).isTrue();
        String mensagemErro = loginSteps.obterMensagemErro();
        Assertions.assertThat(mensagemErro).contains("Username is required");
    }

    @Test
    void usuarioNaoConsegueFazerLoginComSenhaVazia() {
        // Given
        String usuario = "standard_user";
        String senhaVazia = "";

        // When
        loginSteps.tentarLoginInvalido(usuario, senhaVazia);

        // Then
        Assertions.assertThat(loginSteps.temMensagemErro()).isTrue();
        String mensagemErro = loginSteps.obterMensagemErro();
        Assertions.assertThat(mensagemErro).contains("Password is required");
    }

    @Test
    void usuarioBloqueadoNaoConsegueFazerLogin() {
        // Given
        String usuarioBloqueado = "locked_out_user";
        String senha = "secret_sauce";

        // When
        loginSteps.tentarLoginInvalido(usuarioBloqueado, senha);

        // Then
        Assertions.assertThat(loginSteps.temMensagemErro()).isTrue();
        String mensagemErro = loginSteps.obterMensagemErro();
        Assertions.assertThat(mensagemErro).contains("Sorry, this user has been locked out");
    }
}



