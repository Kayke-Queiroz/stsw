public class Triangulo {
    // Atributos privados para armazenar os lados
    private int a, b, c;
    
    // Construtor que inicializa os lados do triângulo
    public Triangulo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    // Método que verifica se os lados formam um triângulo válido
    public boolean isValid() {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
    
    // Método que classifica o triângulo
    public String getTipo() {
        if (!isValid()) {
            return "Não é um triângulo";
        } else if (a == b && b == c) {
            return "Equilátero";
        } else if (a == b || a == c || b == c) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }
}
