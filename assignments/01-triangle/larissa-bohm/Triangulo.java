public final class Triangulo {
    
    private Triangulo(){}

    public static String classificar(int a, int b, int c) {
        if (!validarFaixa(a,b,c)){
            return "Lados inválidos";
        }
        if (!formaTriangulo(a,b,c)){
            return "Não é triangulo";
        }
        if (a == b && b == c) return "Equilátero";
        if (a == b || a == c || b == c) return "Isósceles";
        return "Escaleno";
    }

    private static boolean validarFaixa(int a, int b, int c){
        return entre1e200(a) && entre1e200(b) && entre1e200(c);
    }

    private static entre1e200(int v){
        return v >= 1 && v <= 200;
    }

    private static boolean formaTriangulo(int a, int b, int c){
        return a + b > c && a + c > b  &&  b + c > a;
    }
}
