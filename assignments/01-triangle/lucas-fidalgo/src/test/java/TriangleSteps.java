import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {
    
    private int lado1, lado2, lado3;
    private String resultado;
    
    @Dado("que eu tenho os lados {int}, {int} e {int}")
    public void que_eu_tenho_os_lados(int lado1, int lado2, int lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        System.out.println("Dado: Os lados do triângulo são " + lado1 + ", " + lado2 + ", " + lado3);
    }
    
    @Quando("eu classifico o triângulo")
    public void eu_classifico_o_triangulo() {
        this.resultado = TipoTriangulo.determinarTipo(lado1, lado2, lado3);
        System.out.println("Quando: Classificando triângulo, resultado é: " + this.resultado);
    }
    
    @Então("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String resultadoEsperado) {
        System.out.println("Então: Esperado '" + resultadoEsperado + "', obtido '" + this.resultado + "'");
        assertEquals(resultadoEsperado, this.resultado, 
            "Falha na classificação do triângulo com lados: " + lado1 + ", " + lado2 + ", " + lado3);
    }
}