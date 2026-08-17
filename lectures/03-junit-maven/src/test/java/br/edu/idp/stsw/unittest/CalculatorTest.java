package br.edu.idp.stsw.unittest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.Duration;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Calculator")
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        assumeTrue(Runtime.version().feature() >= 21, "Este exemplo usa Java 21 ou superior.");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        calculator = new Calculator();
        assertTrue(testInfo.getDisplayName().length() > 0);
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Nested
    @DisplayName("operacoes basicas")
    class BasicOperations {

        @Test
        @Tag("fast")
        @DisplayName("agrupa varias assercoes em um unico comportamento")
        void groupedAssertionsForBasicOperations() {
            assertAll("operacoes aritmeticas",
                    () -> assertEquals(5, calculator.add(2, 3)),
                    () -> assertEquals(-1, calculator.subtract(2, 3)),
                    () -> assertEquals(6, calculator.multiply(2, 3)),
                    () -> assertEquals(2, calculator.divide(7, 3)));
        }

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvSource({
            "1, 1, 2",
            "2, 2, 4",
            "3, 5, 8",
            "-5, 2, -3",
            "0, 0, 0"
        })
        @DisplayName("soma com @CsvSource")
        void addWithCsvSource(int a, int b, int expected) {
            assertEquals(expected, calculator.add(a, b));
        }

        @ParameterizedTest(name = "{0} + 0 = {0}")
        @ValueSource(ints = { -10, -1, 0, 1, 10, 100 })
        @DisplayName("elemento neutro da soma com @ValueSource")
        void additiveIdentity(int value) {
            assertEquals(value, calculator.add(value, 0));
        }

        @Test
        @DisplayName("divisao por zero dispara excecao")
        void divisionByZeroThrowsException() {
            ArithmeticException exception = assertThrows(
                    ArithmeticException.class,
                    () -> calculator.divide(10, 0));

            assertEquals("Division by zero is not allowed.", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("operacoes avancadas")
    class AdvancedOperations {

        @ParameterizedTest(name = "{0}! = {1}")
        @MethodSource("br.edu.idp.stsw.unittest.CalculatorTest#factorialCases")
        @DisplayName("fatorial com @MethodSource")
        void factorialWithMethodSource(int value, long expected) {
            assertEquals(expected, calculator.factorial(value));
        }

        @ParameterizedTest(name = "mdc({0}, {1}) = {2}")
        @CsvSource({
            "54, 24, 6",
            "24, 54, 6",
            "-54, 24, 6",
            "17, 13, 1"
        })
        @DisplayName("mdc lida com ordem e numeros negativos")
        void gcdUsesAbsoluteValues(int a, int b, int expected) {
            assertEquals(expected, calculator.gcd(a, b));
        }

        @Test
        @DisplayName("entradas invalidas disparam excecoes de dominio")
        void invalidInputsThrowDomainExceptions() {
            assertAll("entradas invalidas",
                    () -> assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1)),
                    () -> assertThrows(IllegalArgumentException.class, () -> calculator.power(2, -1)),
                    () -> assertThrows(IllegalArgumentException.class, () -> calculator.average()),
                    () -> assertThrows(IllegalArgumentException.class, () -> calculator.gcd(0, 0)));
        }

        @Test
        @DisplayName("operacoes validas nao disparam excecoes")
        void validInputsDoNotThrow() {
            assertDoesNotThrow(() -> calculator.average(2, 4, 6));
            assertDoesNotThrow(() -> calculator.power(2, 10));
        }
    }

    @Nested
    @DisplayName("comportamentos repetidos e dinamicos")
    class RepeatedAndDynamicTests {

        @RepeatedTest(value = 3, name = "repeticao {currentRepetition} de {totalRepetitions}")
        @DisplayName("multiplicacao permanece deterministica")
        void multiplicationIsDeterministic() {
            assertEquals(42, calculator.multiply(6, 7));
        }

        @TestFactory
        @DisplayName("testes dinamicos para numeros pares")
        Stream<DynamicTest> dynamicTestsForEvenNumbers() {
            return IntStream.of(-2, 0, 2, 4, 10)
                    .mapToObj(value -> dynamicTest(value + " deve ser par",
                            () -> assertTrue(calculator.isEven(value))));
        }

        @TestFactory
        @DisplayName("testes dinamicos para numeros impares")
        Stream<DynamicTest> dynamicTestsForOddNumbers() {
            return IntStream.of(-3, -1, 1, 3, 9)
                    .mapToObj(value -> dynamicTest(value + " deve ser impar",
                            () -> assertFalse(calculator.isEven(value))));
        }

        @Test
        @DisplayName("calculo simples respeita limite de tempo")
        void calculationCompletesWithinTimeout() {
            assertTimeout(Duration.ofMillis(100), () -> calculator.power(2, 20));
        }
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> factorialCases() {
        return Stream.of(
                arguments(0, 1L),
                arguments(1, 1L),
                arguments(2, 2L),
                arguments(3, 6L),
                arguments(5, 120L),
                arguments(10, 3_628_800L));
    }
}
