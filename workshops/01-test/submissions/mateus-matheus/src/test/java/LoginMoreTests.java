import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginMoreTests {

    LoginSteps steps = new LoginSteps();

    @Test
    void usuarioConsegueFazerLoginComUsuarioPerformance() {
        // Given - Usuário performance glow
        String usuario = "performance_glitch_user";
        String senha = "secret_sauce";
        
        // When - Executa o login (mais lento)
        steps.logInAs(usuario, senha);
        
        // Then - Verifica se conseguiu fazer login
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }

    @Test
    void usuarioConsegueFazerLoginComUsuarioProblematico() {
        // Given - Usuário problemático
        String usuario = "problem_user";
        String senha = "secret_sauce";
        
        // When - Executa o login
        steps.logInAs(usuario, senha);
        
        // Then - Verifica se conseguiu fazer login
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }

    @Test
    void usuarioNaoConsegueFazerLoginComSenhaErradaSimples() {
        // Given - Usuário válido com senha errada
        String usuario = "standard_user";
        String senha = "senha_errada";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se apareceu mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
        String mensagem = steps.obterMensagemErro();
        Assertions.assertThat(mensagem).contains("Username and password do not match");
    }

    @Test
    void usuarioNaoConsegueFazerLoginComUsuarioInexistente() {
        // Given - Usuário inexistente
        String usuario = "usuario_nao_existe";
        String senha = "secret_sauce";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se apareceu mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
        String mensagem = steps.obterMensagemErro();
        Assertions.assertThat(mensagem).contains("Username and password do not match");
    }

    @Test
    void usuarioConsegueLogarComUsuarioVisualParaApresentacao() {
        // Given - Usuário visual para demonstrar interface
        String usuario = "visual_user";
        String senha = "secret_sauce";
        
        // When - Executa o login
        steps.logInAs(usuario, senha);
        
        // Then - Verifica se conseguiu fazer login
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }
}
