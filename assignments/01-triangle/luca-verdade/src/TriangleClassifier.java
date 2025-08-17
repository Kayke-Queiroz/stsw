import java.util.Scanner;

public class TriangleClassifier {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o lado a: ");
        int a = scanner.nextInt();

        System.out.print("Digite o lado b: ");
        int b = scanner.nextInt();

        System.out.print("Digite o lado c: ");
        int c = scanner.nextInt();

        String resultado = classificarTriangulo(a, b, c);
        System.out.println(resultado);

        scanner.close();
    }

    public static String classificarTriangulo(int a, int b, int c) {

        if (a <= 0 || b <= 0 || c <= 0) {
            return "Lados inválidos";
        }

      
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "Não é um triângulo";
        }

     
        if (a == b && b == c) {
            return "Equilátero";
        } else if (a == b || a == c || b == c) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }
}
