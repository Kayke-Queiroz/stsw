public class Triangle {

    public static String classify(int a, int b, int c) {
        // Validacao: lados devem ser inteiros positivos entre 1 e 200
        if (a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200) {
            return "Lados inv\u00e1lidos";
        }

        // Validacao da desigualdade triangular
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "N\u00e3o \u00e9 um tri\u00e2ngulo";
        }

        // Classificacao do triangulo
        if (a == b && b == c) {
            return "Equil\u00e1tero";
        } else if (a == b || a == c || b == c) {
            return "Is\u00f3sceles";
        } else {
            return "Escaleno";
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java Triangle <lado1> <lado2> <lado3>");
            return;
        }

        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);
            System.out.println(classify(a, b, c));
        } catch (NumberFormatException e) {
            System.out.println("Lados inv\u00e1lidos");
        }
    }
}
