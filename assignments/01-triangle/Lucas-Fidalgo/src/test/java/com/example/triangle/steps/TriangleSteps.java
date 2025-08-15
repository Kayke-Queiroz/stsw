package com.example.triangle.steps;

import com.example.triangle.TriangleClassifier;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Step definitions para os testes BDD do classificador de triângulos.
 */
public class TriangleSteps {
    
    private TriangleClassifier classifier;
    private String resultado;

    @Dado("que eu tenho um classificador de triângulos")
    public void que_eu_tenho_um_classificador_de_triangulos() {
        classifier = new TriangleClassifier();
    }

    @Quando("eu forneço os lados {int}, {int} e {int}")
    public void eu_forneco_os_lados(int lado1, int lado2, int lado3) {
        resultado = classifier.classify(lado1, lado2, lado3);
    }

    @Então("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String resultadoEsperado) {
        assertEquals(resultadoEsperado, resultado);
    }
}
