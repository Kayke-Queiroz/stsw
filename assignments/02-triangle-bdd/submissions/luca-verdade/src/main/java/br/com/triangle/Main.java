package br.com.triangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a, b, c;

        // 1) Preferir argumentos: mvn ... -Dexec.args="3 4 5"
        if (args.length == 3) {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            c = Integer.parseInt(args[2]);
        } else {
            // 2) Sem args: só tenta interativo se houver console
            if (System.console() == null) {
                System.err.println("Uso: mvn -DskipTests exec:java -Dexec.mainClass=\"br.com.triangle.Main\" -Dexec.args=\"a b c\"");
                System.exit(2);
                return;
            }
            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("Lado a: ");
                a = sc.nextInt();
                System.out.print("Lado b: ");
                b = sc.nextInt();
                System.out.print("Lado c: ");
                c = sc.nextInt();
            }
        }

        System.out.println(TriangleClassifier.classify(a, b, c));
    }
}
