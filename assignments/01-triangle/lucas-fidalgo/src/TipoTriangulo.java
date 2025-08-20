import java.util.Scanner;

public class TipoTriangulo {

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            System.out.print("Informe o valor do lado 1: ");
            int lado1 = entrada.nextInt();

            System.out.print("Informe o valor do lado 2: ");
            int lado2 = entrada.nextInt();

            System.out.print("Informe o valor do lado 3: ");
            int lado3 = entrada.nextInt();

            String tipo = determinarTipo(lado1, lado2, lado3);
            System.out.println("Resultado: " + tipo);
        }
    }

    public static String determinarTipo(int lado1, int lado2, int lado3) {
        int[] lados = {lado1, lado2, lado3};
        java.util.Arrays.sort(lados);
        // Validação de lados positivos
        if (lados[0] <= 0) {
            return "Lados inválidos";
        }
        // Verificação se forma triângulo
        long somaMenores = (long) lados[0] + (long) lados[1];
        if (somaMenores <= lados[2]) {
            return "Não é um triângulo";
        }
        // Classificação por igualdade de lados
        if (lados[0] == lados[1] && lados[1] == lados[2]) {
            return "Equilátero";
        } else if (lados[0] == lados[1] || lados[1] == lados[2] || lados[0] == lados[2]) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }

}
