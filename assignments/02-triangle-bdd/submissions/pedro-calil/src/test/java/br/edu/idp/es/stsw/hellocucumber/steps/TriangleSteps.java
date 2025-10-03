package br.edu.idp.es.stsw.hellocucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.idp.es.stsw.hellocucumber.TriangleClassifier;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

public class TriangleSteps {

    private String sa, sb, sc;
    private String resultado;

    @Dado("que informo os lados {string}, {string} e {string}")
    public void queInformoOsLados(String a, String b, String c) {
        this.sa = a; this.sb = b; this.sc = c;
    }

    @Quando("eu classifico o triângulo")
    public void euClassificoOTriangulo() {
        try {
            int a = Integer.parseInt(sa.trim());
            int b = Integer.parseInt(sb.trim());
            int c = Integer.parseInt(sc.trim());
            resultado = TriangleClassifier.classificar(a, b, c);
        } catch (Exception e) {
            resultado = "Lados inválidos";
        }
    }

    @Entao("o resultado deve ser {string}")
    public void oResultadoDeveSer(String esperado) {
        assertEquals(esperado, resultado);
    }
}
