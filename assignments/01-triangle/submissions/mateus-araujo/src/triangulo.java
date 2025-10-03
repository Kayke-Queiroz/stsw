package src;

public class Triangulo  {
    public static String classificarTriangulo(int a, int b, int c){

      if(a <= 0 || b <= 0 || c <= 0 || a > 200 || b > 200 || c > 200){
        return "Lados invalidos";
    }  

    if(a + b < c || a + c < b || b + c < a){
        return "Não é um triângulo";
    }


    if(a == b && b == c){
        return "Equilátero";
    }else if(a == b || a == c || b == c){
        return "Isósceles";
    }else{
        return "Escaleno";
    }
    }
    
    
}
