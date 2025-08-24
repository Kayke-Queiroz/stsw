import static org.junit.Assert.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class TrianguloSteps {

    private int ladoA;
    private int ladoB;
    private int ladoC;
    private String resultado;

    @Dado("que eu tenho os lados {int}, {int} e {int}")
    public void que_eu_tenho_os_lados(Integer a, Integer b, Integer c) {
        this.ladoA = a;
        this.ladoB = b;
        this.ladoC = c;
    }

    @Quando("eu classifico o triangulo")
    public void eu_classifico_o_triangulo() {
        this.resultado = ClassificadorTriangulo.classificar(ladoA, ladoB, ladoC);
    }

    @Entao("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String resultadoEsperado) {
        assertEquals(resultadoEsperado, this.resultado);
    }
}