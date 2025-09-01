package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import br.edu.idp.es.stsw.hellocucumber.Triangulo;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class TrianguloSteps {

    private String resultado;

    @Quando("eu classifico o triângulo com lados {int}, {int} e {int}")
    public void eu_classifico_o_triangulo_com_lados(Integer a, Integer b, Integer c) {
        this.resultado = Triangulo.classificar(a, b, c);
    }

    @Entao("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        assertEquals(esperado, resultado);
    }
}
