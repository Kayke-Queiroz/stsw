package br.edu.idp.es.stsw.test;

import br.Triangle;
import io.cucumber.java.en.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {

    int a,b,c;
    String resultado;
    Triangle triangle = new Triangle();

    @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores {int} {int} e {int} para os lados a b e c")
    public void when_ele_digita_os_valores_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        this.a = a;
        this.b = b;
        this.c = c;
        resultado = triangle.teste(a,b,c);
    }

    @Then("ele deve ver o tipo do triângulo: {string}")
    public void then_ele_deve_ver_o_tipo_do_triangulo(String mensagem) {
        assertEquals(mensagem, this.resultado);
    }
}
