import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginTest {

    LoginSteps steps = new LoginSteps();

    @Test
    void usuarioConsegueFazerLogin() {
        // Given - Usuário com credenciais válidas
        String usuario = "standard_user";
        String senha = "secret_sauce";
        
        // When - Executa o login
        steps.logInAs(usuario, senha);
        
        // Then - Verifica se conseguiu fazer login
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }
}
