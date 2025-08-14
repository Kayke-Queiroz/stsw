package br.edu.idp.stsw.unittest.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import br.edu.idp.stsw.unittest.Calculator;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void simpleAdditionTest() {
        assertEquals(2, calculator.add(1, 1));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,2,4",
        "3,5,8",
        "10,10,20"
    })
    void parametrizedAdditionTest(int a, int b, int result) {
        assertEquals(result, calculator.add(a, b));
    }

    @ParameterizedTest
    @ValueSource( ints = {
        1,
        2,
        10,
        100
    })
    void parametrizedAdditionTest(int a) {
        assertEquals(a, calculator.add(a, 0));
    }

}

