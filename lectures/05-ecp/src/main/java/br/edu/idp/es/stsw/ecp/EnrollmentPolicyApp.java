package br.edu.idp.es.stsw.ecp;

import br.edu.idp.es.stsw.ecp.domain.EnrollmentDecision;
import br.edu.idp.es.stsw.ecp.domain.EnrollmentPolicy;
import br.edu.idp.es.stsw.ecp.domain.PaymentStatus;

import java.util.Scanner;

public class EnrollmentPolicyApp {

    public static void main(String[] args) {
        EnrollmentPolicy policy = new EnrollmentPolicy();

        int age;
        int score;
        int seats;
        PaymentStatus paymentStatus;

        if (args.length == 4) {
            age = parseInt(args[0], "idade");
            score = parseInt(args[1], "nota");
            seats = parseInt(args[2], "vagas");
            paymentStatus = PaymentStatus.from(args[3]);
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Politica de matricula em disciplina avancada");
                System.out.println("Situacoes de pagamento: PAGO, BOLSA, PENDENTE, CANCELADO");
                age = readInt(scanner, "Informe a idade: ");
                score = readInt(scanner, "Informe a nota do pre-requisito: ");
                seats = readInt(scanner, "Informe a quantidade de vagas disponiveis: ");
                System.out.print("Informe a situacao de pagamento: ");
                paymentStatus = PaymentStatus.from(scanner.nextLine());
            }
        }

        EnrollmentDecision decision = policy.evaluate(age, score, seats, paymentStatus);
        System.out.printf("Resultado da solicitacao de matricula: %s%n", decision);
    }

    private static int parseInt(String value, String fieldName) {
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
