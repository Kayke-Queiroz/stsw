import java.util.Scanner;

public class ClassificadorTriangulo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Classificador de Triangulos ===");
        System.out.print("Digite o primeiro lado: ");
        int a = scanner.nextInt();

        System.out.print("Digite o segundo lado: ");
        int b = scanner.nextInt();

        System.out.print("Digite o terceiro lado: ");
        int c = scanner.nextInt();

        String resultado = classificar(a, b, c);

        System.out.println("Resultado: " + resultado);

        scanner.close();
    }

    public static String classificar(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200) {
            return "Lados invalidos";
        }

        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            return "Nao e um triangulo";
        }

        if (a == b && b == c) {
            return "Equilatero";
        } else if (a == b || a == c || b == c) {
            return "Isosceles";
        } else {
            return "Escaleno";
        }
    }
}