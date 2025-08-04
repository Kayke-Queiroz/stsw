package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAdd() {
        System.out.println("🔢 Executando testAdd (soma correta)");
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3)); // ✅ esperado: passa
    }

    @Test
    void testMultiply() {
        System.out.println("✖️ Executando testMultiply (multiplicação correta)");
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3)); // ✅ esperado: passa
    }

    @Test
    void testDivisaoPorZeroLancaExcecao() {
        System.out.println("⚠️ Executando testDivisaoPorZeroLancaExcecao");
        Calculator calc = new Calculator();

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, 0); // ⚠️ erro esperado
        });

        assertEquals("Divisão por zero não é permitida!", excecao.getMessage());
        System.out.println("✔️ Exceção capturada com sucesso!");
    }
    
    @Test
void testComAssertFalseComErroIntencional() {
    System.out.println("🚨 Executando testComAssertFalseComErroIntencional");

    Calculator calc = new Calculator();
    int resultado = calc.multiply(2, 3);

    
    assertFalse(resultado == 6); 
	}
}

