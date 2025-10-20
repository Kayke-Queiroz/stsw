import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginEdgeCasesTest {

    LoginSteps steps = new LoginSteps();

    @Test
    void usuarioNaoConsegueFazerLoginComEspacosEmBranco() {
        // Given - Credenciais com espaços em branco
        String usuario = "   ";
        String senha = "   ";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Este teste vai falhar propositalmente
        Assertions.assertThat(steps.temMensagemErro()).isFalse(); // FALHA INTENCIONAL
    }

    @Test
    void usuarioNaoConsegueFazerLoginComCaracteresEspeciais() {
        // Given - Credenciais com caracteres especiais
        String usuario = "@#$%";
        String senha = "!@#$%^&*()";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se apareceu mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
    }

    @Test
    void usuarioConsegueLogarComNomeLongo() {
        // Given - Uma string muito longa como senha
        String usuario = "standard_user";
        String senha = "uma_senha_muito_longa_com_muitos_caracteres_para_testar_limites";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica mensagem de erro (esperado)
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
    }

    @Test
    void usuarioNaoConsegueFazerLoginComHTMLInjection() {
        // Given - Tentativa de injection
        String usuario = "<script>alert('xss')</script>";
        String senha = "<img src=x onerror=alert('xss')>";
        
        // When - Tenta fazer login
        steps.tentarLoginInvalido(usuario, senha);
        
        // Then - Verifica se apareceu mensagem de erro
        Assertions.assertThat(steps.temMensagemErro()).isTrue();
        String mensagem = steps.obterMensagemErro();
        Assertions.assertThat(mensagem).contains("Username and password do not match");
    }

    @Test
    void usuarioConsegueFazerLoginComCredenciaisCorretasEspeciais() {
        // Given - Usuário com credenciais válidas
        String usuario = "standard_user";
        String senha = "secret_sauce";
        
        // When - Executa o login
        steps.logInAs(usuario, senha);
        
        // Then - Este teste vai passar normalmente
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isFalse(); // FALHA INTENCIONAL PARA DEMO
    }
}
