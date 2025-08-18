import java.util.Scanner;


public class App {

    public static String classificarEntradaTexto(String sa, String sb, String sc){
        Integer a = parse(sa), b = parse(sb), c = parse(sc);
        if (a == null || b == null || c == null) return "Lados inválidos";
        return ClassificadorTri.classificar(a, b, c);
    }

    private static Integer parse(String s){
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return null; }
    }

    public static void main(String[] args){
        if (args.length == 3){
            System.out.println(classificarEntradaTexto(args[0], args[1], args[2]));
            return;
        }

        System.out.println("Informe três lados (inteiros de 1 a 200):");
        try (Scanner sc = new Scanner(System.in)){
            String a = sc.hasNext() ? sc.next() : "";
            String b = sc.hasNext() ? sc.next() : "";
            String c = sc.hasNext() ? sc.next() : "";
            System.out.println(classificarEntradaTexto(a, b, c));
        }
    }
}