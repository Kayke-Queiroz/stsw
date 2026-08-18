package br.edu.idp.es.stsw.ecp.domain;

public class EnrollmentPolicy {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 100;
    private static final int MIN_APPROVAL_SCORE = 70;

    public EnrollmentDecision evaluate(int age, int prerequisiteScore, int availableSeats, PaymentStatus paymentStatus) {
        if (hasInvalidData(age, prerequisiteScore, availableSeats, paymentStatus)) {
            return EnrollmentDecision.DADOS_INVALIDOS;
        }

        if (prerequisiteScore < MIN_APPROVAL_SCORE || isPaymentNotAccepted(paymentStatus)) {
            return EnrollmentDecision.RECUSADA;
        }

        if (availableSeats == 0) {
            return EnrollmentDecision.LISTA_DE_ESPERA;
        }

        return EnrollmentDecision.MATRICULA_CONFIRMADA;
    }

    private boolean hasInvalidData(int age, int prerequisiteScore, int availableSeats, PaymentStatus paymentStatus) {
        return age < MIN_AGE
                || age > MAX_AGE
                || prerequisiteScore < MIN_SCORE
                || prerequisiteScore > MAX_SCORE
                || availableSeats < 0
                || paymentStatus == null
                || paymentStatus == PaymentStatus.DESCONHECIDO;
    }

    private boolean isPaymentNotAccepted(PaymentStatus paymentStatus) {
        return paymentStatus == PaymentStatus.PENDENTE || paymentStatus == PaymentStatus.CANCELADO;
    }
}
