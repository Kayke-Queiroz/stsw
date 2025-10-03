import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginBoundaryTest {

    LoginSteps steps = new LoginSteps();

    @Test
    void usuarioNaoConsegueFazerLoginComCredenciaisInvalidasTestCase1() {
        // Given - Teste de limite com caracteres especiais
        String usuario = "test!@#";
        String senha = "pass%^&*";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
    }

    @Test
    void usuarioNaoConsegueFazerLoginComCredenciaisInvalidasTestCase2() {
        // Given - Teste com números
        String usuario = "123456";
        String senha = "654321";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se aparece mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
    }

    @Test
    void usuarioNaoConsegueFazerLoginComCredenciaisInvalidasTestCase3() {
        // Given - Teste de caso extremo
        String usuario = "";
        String senha = "test";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se aparece mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
        String mensagem = steps.obterMensagemErro();
        Assertions.assertThat(mensagem).contains("Username is required");
    }

    @Test
    void usuarioConsegueLogarComCredenciaisValidasParaDemonstracao() {
        // Given - Teste que deve passar
        String usuario = "standard_user";
        String senha = "secret_sauce";
        
        // When - Executa login
        steps.logInAs(usuario, senha);
        
        // Then - Verifica sucesso
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }

    @Test
    void boundaryTestQueVaiFalharIntencionalmente() {
        // Given - Teste preparado para falhar
        String usuario = "standard_user";
        String senha = "secret_sauce";
        
        // When - Executa login válido
        steps.logInAs(usuario, senha);
        
        // Then - Verifica algo que vai falhar
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isFalse(); // FALHA INTENCIONAL
    }
}
