import java.util.Scanner;

public class TrianguloCompleto {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escolha uma opcao:");
        System.out.println("1 - Classificar triangulo");
        System.out.println("2 - Executar testes");
        System.out.print("Digite sua escolha (1 ou 2): ");
        
        int escolha = scanner.nextInt();
        
        if (escolha == 1) {
            usarClassificador(scanner);
        } else if (escolha == 2) {
            executarTestes();
        } else {
            System.out.println("Opcao invalida!");
        }
        
        scanner.close();
    }
    

    public static void usarClassificador(Scanner scanner) {
        System.out.println("\n=== Classificador de Triangulos ===");
        System.out.print("Digite o primeiro lado: ");
        int a = scanner.nextInt();
        
        System.out.print("Digite o segundo lado: ");
        int b = scanner.nextInt();
        
        System.out.print("Digite o terceiro lado: ");
        int c = scanner.nextInt();
        
        String resultado = classificarTriangulo(a, b, c);
        System.out.println("Resultado: " + resultado);
    }
    
    public static String classificarTriangulo(int a, int b, int c) {
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

    //testes black box
    public static void executarTestes() {
        System.out.println("\n=== Executando Testes Black-Box ===");
        
        testarCaso( -1,  5,  5, "Lados invalidos");
        testarCaso(201, 100, 100, "Lados invalidos");
        testarCaso(  0, 10, 10, "Lados invalidos");
        
        testarCaso(  1,  2,  3, "Nao e um triangulo");
        testarCaso( 10,  4,  4, "Nao e um triangulo");
        
        testarCaso(  5,  5,  5, "Equilatero");
        
        testarCaso(  5,  5,  8, "Isosceles");
        testarCaso(  7, 10,  7, "Isosceles");
        
        testarCaso(  3,  4,  5, "Escaleno");
    }

    public static void testarCaso(int a, int b, int c, String esperado) {
        String resultado = classificarTriangulo(a, b, c);
        if (resultado.equals(esperado)) {
            System.out.println("OK -> (" + a + "," + b + "," + c + ") = " + resultado);
        } else {
            System.out.println("FALHOU -> (" + a + "," + b + "," + c + 
                               ") esperado: " + esperado + " | obtido: " + resultado);
        }
    }
}
