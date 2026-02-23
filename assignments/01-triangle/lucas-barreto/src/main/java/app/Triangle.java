package app;
public class Triangle {

   
    public static void main(String[] args) {
        int a = 3, b = 4, c = 5;
        System.out.println(teste(a, b, c));  
    }

public static String teste(int a, int b, int c){
            if(a < 0 || b < 0 || c < 0) {
                return("Lados inválidos");
               }

            

            if (a + b > c &&  a+ c > b && b + c > a) {

                if (a == b && a == c) {
                    return "Equilátero";
                }
                else if (a == b || a == c || b == c ) {
                    return "Isósceles";
                }
                else {
                    return "Escaleno";
                }

            }
            else {
                return "Não é um Triângulo";
            }
    }

}
