import java.util.Scanner;

public class TrianguloApp {

    public static String classificarTriangulo(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200) {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o lado a: ");
        int a = sc.nextInt();

        System.out.print("Digite o lado b: ");
        int b = sc.nextInt();

        System.out.print("Digite o lado c: ");
        int c = sc.nextInt();

        String resultado = classificarTriangulo(a, b, c);
        System.out.println("Resultado: " + resultado);

        sc.close();
    }
}
