package steps;

import app.Triangle;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {

    private final Triangle triangle = new Triangle();

    private int a;
    private int b;
    private int c;
    private String result;

    @Dado("que eu informo os lados {int}, {int} e {int}")
    public void queEuInformoOsLados(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Quando("o sistema classifica o triangulo")
    public void oSistemaClassificaOTriangulo() {
        result = triangle.classify(a, b, c);
    }

    @Então("o resultado deve ser {string}")
    public void oResultadoDeveSer(String expected) {
        assertEquals(expected, result);
    }
}