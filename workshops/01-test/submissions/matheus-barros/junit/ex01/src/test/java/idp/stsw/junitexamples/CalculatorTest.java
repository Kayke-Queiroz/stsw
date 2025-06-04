package idp.stsw.junitexamples;

/**
 *
 * @author matheus
 */

import org.junit.jupiter.api.BeforeEach; // Importação para setup antes de cada teste
import org.junit.jupiter.api.AfterEach;  // Importação para cleanup após cada teste
import org.junit.jupiter.api.Test;       // Importação para marcar métodos de teste
import org.junit.jupiter.api.DisplayName; // Importação para nomes de teste descritivos
import static org.junit.jupiter.api.Assertions.assertEquals; // Importação estática para asserções

class CalculatorTest {

    private Calculator calculator; // Instância da classe sob teste

    // Método executado ANTES de CADA método @Test nesta classe
    @BeforeEach
    void setUp() {
        System.out.println("Executando setUp()...");
        calculator = new Calculator(); // Cria uma nova instância para cada teste, garantindo isolamento
    }

    // Método executado DEPOIS de CADA método @Test nesta classe
    @AfterEach
    void tearDown() {
        System.out.println("Executando tearDown()...");
        calculator = null; // Limpa a instância (opcional, mas boa prática para liberar recursos)
    }

    // Um método de teste básico
    @Test
    @DisplayName("Deve somar dois números positivos corretamente") // Nome descritivo para este teste
    void testAddPositiveNumbers() {
        // Arrange: Preparação (já feita em parte no setUp)
        int numberA = 5;
        int numberB = 3;
        int expectedResult = 8;
        System.out.println("Executando testAddPositiveNumbers...");

        // Act: Execução da ação a ser testada
        int actualResult = calculator.add(numberA, numberB);

        // Assert: Verificação do resultado esperado vs. resultado atual
        // assertEquals(valorEsperado, valorAtual, "Mensagem em caso de falha (opcional)");
        assertEquals(expectedResult, actualResult, "A soma 5 + 3 deveria ser 8");
        System.out.println("Assert assertEquals executado.");
    }

    @Test
    @DisplayName("Deve somar um número positivo e zero")
    void testAddPositiveAndZero() {
        // Arrange
        int numberA = 7;
        int numberB = 0;
        int expectedResult = 7;
        System.out.println("Executando testAddPositiveAndZero...");

        // Act
        int actualResult = calculator.add(numberA, numberB);

        // Assert
        assertEquals(expectedResult, actualResult, "A soma 7 + 0 deveria ser 7");
        System.out.println("Assert assertEquals executado.");
    }
}