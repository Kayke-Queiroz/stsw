package steps;

import app.Triangle;
import io.cucumber.java.en.*;


public class TriangleTest {

    Triangle triangle = new Triangle();

    @Given("o usuário está na página inicial")
    public void usuarioNaPaginaInicial() {
        System.out.println("Usuário acessou a página inicial.");
    }

    @When("ele digita os valores 5, 5 e 5 para os lados a, b e c")
    public void LadosTriângulo(int a, int b, int c) {

        System.out.println("Valor A: " + a + " | Valor B: " + b + " | Valor C: " + c);

    }

    @Then("ele deve ver o tipo do triangulo")
    public void sistemaExibeMensagem(String mensagem, int a, int b, int c) {
        System.out.println("Triângulo: " + triangle.teste(a, b, c));
    }
}
