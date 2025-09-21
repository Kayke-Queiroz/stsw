package steps;

import static org.junit.Assert.assertEquals;
import io.cucumber.java.pt.*;

public class TrianguloSteps {

    private int a, b, c;
    private String resultado;

    @Dado("os lados são {int}, {int}, {int}")
    public void os_lados_sao(Integer lado1, Integer lado2, Integer lado3) {
        this.a = lado1;
        this.b = lado2;
        this.c = lado3;
    }

    @Quando("classifico o triângulo")
    public void classifico_o_triangulo() {
        resultado = TrianguloApp.classificarTriangulo(a, b, c);
    }

    @Então("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}
