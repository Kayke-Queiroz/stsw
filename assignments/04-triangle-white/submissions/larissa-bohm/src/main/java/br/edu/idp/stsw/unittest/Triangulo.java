package br.edu.idp.stsw.unittest;

public final class Triangulo {

    public static void main(String[] args) {
        int a = 5, b = 5, c = 3;
        String resultado = classificar(a, b, c);
        System.out.println("O triângulo é: " + resultado);
    }

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


    private static boolean entre1e200(int v){
        return v >= 1 && v <= 200;
    }

    private static boolean formaTriangulo(int a, int b, int c){
        return a + b > c && a + c > b  &&  b + c > a;
    }
}


