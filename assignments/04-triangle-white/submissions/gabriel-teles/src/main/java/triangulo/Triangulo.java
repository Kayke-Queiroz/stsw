package triangulo;

import java.util.Scanner;

public class Triangulo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Lado a: ");
        int a = scanner.nextInt();

        System.out.printf("Lado b: ");
        int b = scanner.nextInt();
        
        System.out.printf("Lado c: ");
        int c = scanner.nextInt();

        scanner.close();
        
        if (!tamanhoCorreto(a) || !tamanhoCorreto(b) || !tamanhoCorreto(c)) {
            System.out.println("Lados inválidos");
            return;
        }

        if (ehInvalido(a, b, c)) {
            System.out.println("Não é um triângulo");
            return;
        }

        classificaTriangulo(a, b, c);
    }
    
    public static boolean ehInvalido(int a, int b, int c) {
        if (a >= b + c || b >= a + c || c >= a + b) {
            return true;
        }
        return false;
    }
    
    public static boolean tamanhoCorreto(int lado) {
        if (lado < 1 || lado > 200) {
            return false;
        }
        return true;
    }

    public static void classificaTriangulo(int a, int b, int c) {
        if (a == b && b == c) {
            System.out.println("EQUILÁTERO");
            return;
        }

        if ((a == b && b != c) || (a == c && c != b) || (b == c && c != a)) {
            System.out.println("ISÓSCELES");
            return;
        }

        System.out.println("ESCALENO");
    }
}
