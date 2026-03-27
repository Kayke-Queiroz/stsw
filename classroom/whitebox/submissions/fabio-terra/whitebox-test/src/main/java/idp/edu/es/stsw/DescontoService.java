package idp.edu.es.stsw;

public class DescontoService {

    public double calcularDesconto(boolean clientePremium,
            double compraValor,
            boolean cupomValido,
            boolean blackFriday,
            double desconto) {

        // 1. Se o valor da compra for maior ou igual a 100, adicionar 10 pontos de
        // desconto.
        if (compraValor >= 100) {
            desconto += 0.1f;
        }

        // 2. Se o cliente for premium, adicionar 5 pontos de desconto.
        if (clientePremium) {
            desconto += 0.05f;
        }

        // 3. Se o cupom for valido e o valor da compra for maior ou igual a 200,
        // adicionar 15 pontos de desconto.
        if (cupomValido || compraValor >= 200) {
            desconto += 0.15f;
        }

        // 4. Se for Black Friday ou se o cliente for premium e o valor da compra for
        // maior ou igual a 300, adicionar 20 pontos de desconto.
        if ((blackFriday || clientePremium) && compraValor >= 300) {
            desconto += 0.2f;
        }

        // 5. O desconto maximo permitido e 40.
        if (desconto > 0.4f) {
            desconto = 0.4f;
        }

        return compraValor * (1 - desconto);
    }
}