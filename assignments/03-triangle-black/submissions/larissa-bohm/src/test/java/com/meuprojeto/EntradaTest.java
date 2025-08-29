package com.meuprojeto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EntradaTest {

    @Test
    @DisplayName("Deve ler um valor inteiro válido dentro da faixa")
    void deveLerValorValido() {

        String inputSimulado = "150";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(150, valorLido);
    }

    @Test
    @DisplayName("Deve aceitar valor no limite inferior (1)")
    void deveAceitarLimiteInferior() {

        String inputSimulado = "1";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(1, valorLido);
    }

    @Test
    @DisplayName("Deve aceitar valor no limite superior (200)")
    void deveAceitarLimiteSuperior() {

        String inputSimulado = "200";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(200, valorLido);
    }

    @Test
    @DisplayName("Deve lidar com entradas inválidas (texto) e depois ler um valor válido")
    void deveLidarComEntradaInvalidaEContinuar() {

        String inputSimulado = "abc\n201\n100";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(100, valorLido);
    }

    @Test
    @DisplayName("Deve lidar com valor fora da faixa inferior e depois ler um valor válido")
    void deveLidarComValorForaDaFaixaInferior() {

        String inputSimulado = "0\n50";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(50, valorLido);
    }

    @Test
    @DisplayName("Deve lidar com valor fora da faixa superior e depois ler um valor válido")
    void deveLidarComValorForaDaFaixaSuperior() {

        String inputSimulado = "201\n180";
        Scanner scanner = new Scanner(inputSimulado);
        Entrada entrada = new Entrada(scanner);

        int valorLido = entrada.lerValor("lado A");

        assertEquals(180, valorLido);
    }
}