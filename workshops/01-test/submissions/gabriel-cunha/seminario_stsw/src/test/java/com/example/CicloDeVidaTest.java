package com.example;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CicloDeVidaTest {

    @BeforeAll
    void antesDeTodosOsTestes() {
        System.out.println("🔵 @BeforeAll - Antes de TODOS os testes");
    }

    @BeforeEach
    void antesDeCadaTeste() {
        System.out.println("🟢 @BeforeEach - Antes de CADA teste");
    }

    @Test
    void primeiroTeste() {
        System.out.println("⚪ @Test - Executando o PRIMEIRO teste");
    }

    @Test
    void segundoTeste() {
        System.out.println("⚪ @Test - Executando o SEGUNDO teste");
    }

    @AfterEach
    void depoisDeCadaTeste() {
        System.out.println("🟠 @AfterEach - Depois de CADA teste");
    }

    @AfterAll
    void depoisDeTodosOsTestes() {
        System.out.println("🔴 @AfterAll - Depois de TODOS os testes");
    }
}

