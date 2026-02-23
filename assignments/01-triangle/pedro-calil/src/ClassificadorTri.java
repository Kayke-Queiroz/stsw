public class ClassificadorTri{

    public static String classificar(int a, int b, int c){
        if(!intervaloValido(a,b,c)) return "Lados inválidos";
        if(!formaTriangulo(a,b,c)) return "Não é um triângulo";

        if(a == b && b == c) return "Equilátero";
        if(a == b || a == c || b == c) return "Isósceles";
        return "Escaleno";
    }

    private static boolean intervaloValido(int a, int b, int c){
        return entre1e200(a) && entre1e200(b) && entre1e200(c);
    }

    public static boolean entre1e200(int x){
        return x >= 1 && x <= 200;
    }

    private static boolean formaTriangulo(int a, int b, int c){
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

}