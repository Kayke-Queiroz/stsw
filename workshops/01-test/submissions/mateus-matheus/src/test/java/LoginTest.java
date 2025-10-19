/**
 * CLASSE DE TESTE BDD - LoginTest
 * 
 * Esta classe demonstra o padrão BDD (Behavior Driven Development) com Serenity:
 * - @ExtendWith: Integra com o framework Serenity BDD
 * - Given/When/Then: Estrutura clara dos cenários de teste
 * - Steps: Utiliza métodos reutilizáveis definidos em LoginSteps
 * - Assertions: Verificação dos resultados esperados
 */
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.LoginSteps;
import org.assertj.core.api.Assertions;

// Extensão do Serenity BDD para integração com JUnit 5
@ExtendWith(SerenityJUnit5Extension.class)
public class LoginTest {

    // Instância dos Steps - encapsula ações da aplicação
    LoginSteps steps = new LoginSteps();

    /**
     * TESTE PRINCIPAL - Login Bem-Sucedido
     * 
     * Demonstra o padrão AAA (Arrange-Act-Assert) com estruturas BDD:
     * - Given: Pré-condições do teste (usuário válido)
     * - When: Ações que testam a funcionalidade (executar login)
     * - Then: Verificação do resultado esperado (usuário chegou na página de produtos)
     */
    @Test
    void usuarioConsegueFazerLogin() {
        // Given - ARRANGE: Pré-condições - usuário com credenciais válidas
        String usuario = "standard_user";  // Usuário padrão do Sauce Demo
        String senha = "secret_sauce";     // Senha padrão do Sauce Demo
        
        // When - ACT: Ações - executa o fluxo de login completo
        steps.logInAs(usuario, senha);
        
        // Then - ASSERT: Resultado - verifica se o usuário chegou na página correta
        Assertions.assertThat(steps.estaNaPaginaDeProdutos()).isTrue();
    }
}
