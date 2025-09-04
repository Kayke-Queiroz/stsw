package br.edu.idp.es.stsw.triangle;
public class Triangle {

    public static void main() {
        int a = 0, b = 0, c = 0;
        Triangle.teste(a, b, c);
}

public static String teste(int a, int b, int c){
            if(a <= 0 || b <= 0 || c <= 0) {
                return "Lados inválidos";
                //System.exit(1);
            }

            

            if (a + b > c &&  a+ c > b && b + c > a) {

                if (a == b && a == c) {
                    return "Equilátero";
                }
                else if (a == b || a == c || b == c ) {
                    System.out.println("Isósceles");
                }
                else {
                    return "Escaleno";
                }

            }
            else {
                return "Não é um Triângulo";
            }
            
            return "Finish";
    }

}
