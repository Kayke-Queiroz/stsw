package idp.edu.es.stsw;

import com.example.DescontoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.junit.jupiter.api.Assertions;

public class DescontoSteps {

    private DescontoService descontoService = new DescontoService();
    private boolean clientePremium;
    private double compraValor;
    private boolean cupomValido;
    private boolean blackFriday;
    private double desconto;

    @Dado("os dados da compra são informados {boolean}, {double}, {boolean}, {boolean}, {double}")
    public void dados_da_compra(boolean a, double b, boolean c, boolean d, double e) {
        this.clientePremium = a;
        this.compraValor = b;
        this.cupomValido = c;
        this.blackFriday = d;
        this.desconto = e;
    }

    @Quando("calculo o desconto")
    public void calculo_o_desconto() {
        this.resultadoReal = descontoService.calcularDesconto(clientePremium, compraValor, cupomValido, blackFriday,
                desconto);
    }

    @Então("o sistema retorna o novo  valor da compra {double}")
    public void o_sistema_retorna(double resultadoEsperado) {
        Assertions.assertEquals(resultadoEsperado, resultadoReal);
    }
}
