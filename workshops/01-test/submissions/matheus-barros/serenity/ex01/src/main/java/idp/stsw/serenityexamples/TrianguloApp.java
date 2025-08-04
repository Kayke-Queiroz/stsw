import java.util.Scanner;

import idp.stsw.serenityexamples.Triangulo;

public class TrianguloApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
        
            // Solicita os lados do triângulo ao usuário
            System.out.print("Digite o valor do lado a: ");
            int a = scanner.nextInt();
            System.out.print("Digite o valor do lado b: ");
            int b = scanner.nextInt();
            System.out.print("Digite o valor do lado c: ");
            int c = scanner.nextInt();
            
            // Validação: os lados devem estar entre 1 e 200
            if (a < 1 || a > 200 || b < 1 || b > 200 || c < 1 || c > 200) {
                System.out.println("Lados inválidos");
            } else {
                // Cria um objeto Triangulo e exibe o tipo do triângulo
                Triangulo triangulo = new Triangulo(a, b, c);
                System.out.println(triangulo.getTipo());
            }
        }
    }
}
