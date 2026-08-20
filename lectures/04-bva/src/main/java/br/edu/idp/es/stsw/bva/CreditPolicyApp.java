package br.edu.idp.es.stsw.bva;

import br.edu.idp.es.stsw.bva.domain.CreditPolicy;

import java.util.Scanner;

public class CreditPolicyApp {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 65;
    private static final int MIN_INCOME = 2000;
    private static final int MAX_INCOME = 10000;

    public static void main(String[] args) {
        CreditPolicy policy = new CreditPolicy(MIN_AGE, MAX_AGE, MIN_INCOME, MAX_INCOME);

        int age;
        int income;

        if (args.length == 2) {
            age = parseArgument(args[0], "idade");
            income = parseArgument(args[1], "renda");
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Politica de credito");
                System.out.printf("Idade aceita: %d a %d%n", MIN_AGE, MAX_AGE);
                System.out.printf("Renda aceita: %d a %d%n%n", MIN_INCOME, MAX_INCOME);

                age = readInt(scanner, "Informe a idade: ");
                income = readInt(scanner, "Informe a renda: ");
            }
        }

        boolean approved = policy.isApproved(age, income);
        String result = approved ? "APROVADA" : "REPROVADA";

        System.out.printf("Proposta com idade %d e renda %d: %s%n", age, income, result);
    }

    private static int parseArgument(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Valor invalido para " + fieldName + ": " + value, exception);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();

            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Informe um numero inteiro.");
            }
        }
    }
}
