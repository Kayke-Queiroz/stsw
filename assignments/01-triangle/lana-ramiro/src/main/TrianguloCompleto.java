import java.util.Scanner;

public class TrianguloCompleto {
    
    // Método principal para usar o classificador
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
    
    // Método para usar o classificador
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
    
    // Método que classifica o triângulo
    public static String classificarTriangulo(int a, int b, int c) {
        // Verifica se os lados são válidos (positivos e entre 1 e 200)
        if (a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200) {
            return "Lados invalidos";
        }
        
        // Verifica se forma um triângulo válido
        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            return "Nao e um triangulo";
        }
        
        // Classifica o tipo de triângulo
        if (a == b && b == c) {
            return "Equilatero";
        } else if (a == b || a == c || b == c) {
            return "Isosceles";
        } else {
            return "Escaleno";
        }
    }
    
    // Método para executar todos os testes
    public static void executarTestes() {
        System.out.println("\n=== Executando Testes ===");
        
        int testesPassaram = 0;
        int totalTestes = 0;
        
        // Teste 1: Triângulo Equilátero
        totalTestes++;
        if (testarEquilatero()) {
            System.out.println("✓ Teste Equilatero: PASSOU");
            testesPassaram++;
        } else {
            System.out.println("✗ Teste Equilatero: FALHOU");
        }
        
        // Teste 2: Triângulo Isósceles
        totalTestes++;
        if (testarIsosceles()) {
            System.out.println("✓ Teste Isosceles: PASSOU");
            testesPassaram++;
        } else {
            System.out.println("✗ Teste Isosceles: FALHOU");
        }
        
        // Teste 3: Triângulo Escaleno
        totalTestes++;
        if (testarEscaleno()) {
            System.out.println("✓ Teste Escaleno: PASSOU");
            testesPassaram++;
        } else {
            System.out.println("✗ Teste Escaleno: FALHOU");
        }
        
        // Teste 4: Não é triângulo
        totalTestes++;
        if (testarNaoTriangulo()) {
            System.out.println("✓ Teste Nao e Triangulo: PASSOU");
            testesPassaram++;
        } else {
            System.out.println("✗ Teste Nao e Triangulo: FALHOU");
        }
        
        // Teste 5: Lados inválidos
        totalTestes++;
        if (testarLadosInvalidos()) {
            System.out.println("✓ Teste Lados Invalidos: PASSOU");
            testesPassaram++;
        } else {
            System.out.println("✗ Teste Lados Invalidos: FALHOU");
        }
        
        System.out.println("\n=== Resultado dos Testes ===");
        System.out.println("Testes que passaram: " + testesPassaram + "/" + totalTestes);
        
        if (testesPassaram == totalTestes) {
            System.out.println("Todos os testes passaram! ✓");
        } else {
            System.out.println("Alguns testes falharam! ✗");
        }
    }
    
    // Métodos de teste individuais
    public static boolean testarEquilatero() {
        String resultado = classificarTriangulo(5, 5, 5);
        return "Equilatero".equals(resultado);
    }
    
    public static boolean testarIsosceles() {
        String resultado1 = classificarTriangulo(5, 5, 3);
        String resultado2 = classificarTriangulo(5, 3, 5);
        String resultado3 = classificarTriangulo(3, 5, 5);
        
        return "Isosceles".equals(resultado1) && 
               "Isosceles".equals(resultado2) && 
               "Isosceles".equals(resultado3);
    }
    
    public static boolean testarEscaleno() {
        String resultado = classificarTriangulo(5, 4, 3);
        return "Escaleno".equals(resultado);
    }
    
    public static boolean testarNaoTriangulo() {
        String resultado1 = classificarTriangulo(1, 2, 3);
        String resultado2 = classificarTriangulo(1, 1, 5);
        
        return "Nao e um triangulo".equals(resultado1) && 
               "Nao e um triangulo".equals(resultado2);
    }
    
    public static boolean testarLadosInvalidos() {
        String resultado1 = classificarTriangulo(-1, 5, 5);
        String resultado2 = classificarTriangulo(0, 5, 5);
        String resultado3 = classificarTriangulo(201, 5, 5);
        
        return "Lados invalidos".equals(resultado1) && 
               "Lados invalidos".equals(resultado2) && 
               "Lados invalidos".equals(resultado3);
    }
}