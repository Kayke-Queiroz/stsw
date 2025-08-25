package steps;

import app.Triangle;
import io.cucumber.java.en.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {

    Triangle triangle = new Triangle();

    @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores 5 5 e 5 para os lados a b e c")
    public void when_ele_digita_os_valores_5_5_e_5_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        triangle.teste(a,b,c);
    }

    @Then("ele deve ver o tipo do triangulo: Equilatero")
    public void then_ele_deve_ver_o_tipo_do_triangulo_Equilatero(String mensagem, int a, int b, int c) {
        assertEquals(mensagem, triangle.teste(a,b,c));
    }

     @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores 5 5 e 3 para os lados a, b e c")
    public void when_ele_digita_os_valores_5_5_e_3_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        triangle.teste(a,b,c);
    }

    @Then("ele deve ver o tipo do triangulo Isosceles")
    public void then_ele_deve_ver_o_tipo_do_triangulo_Isosceles(String mensagem, int a, int b, int c) {
        assertEquals(mensagem, triangle.teste(a,b,c));
    }

     @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores 5 4 e 3 para os lados a b e c")
    public void when_ele_digita_os_valores_5_4_e_3_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        triangle.teste(a,b,c);
    }

    @Then("ele deve ver o tipo do triangulo Escaleno")
    public void then_ele_deve_ver_o_tipo_do_triangulo_Escaleno(String mensagem, int a, int b, int c) {
        assertEquals(mensagem, triangle.teste(a,b,c));
    }

     @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores 1 2 e 3 para os lados a b e c")
    public void when_ele_digita_os_valores_1_2_e_3_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        triangle.teste(a,b,c);
    }

    @Then("ele deve ver a mensagem Nao e um triangulo")
    public void then_ele_deve_ver_a_mensagem_Nao_e_um_triangulo(String mensagem, int a, int b, int c) {
        assertEquals(mensagem, triangle.teste(a,b,c));
    }

     @Given("o usuário esta na página inicial")
    public void given_o_usuario_esta_na_pagina_inicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores "{-5}" 0 e 5 para os lados a b e c")
    public void when_ele_digita_os_valores_{-5}_{0}_e_{5}_para_os_lados_a_b_e_c(int a, int b, int c) {
        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);
        triangle.teste(a,b,c);
    }

    @Then("ele deve ver a mensagem Lados invalidos")
    public void then_ele_deve_ver_a_mensagem_Lados_invalidos(String mensagem, int a, int b, int c) {
        assertEquals(mensagem, triangle.teste(a,b,c));
    }
}