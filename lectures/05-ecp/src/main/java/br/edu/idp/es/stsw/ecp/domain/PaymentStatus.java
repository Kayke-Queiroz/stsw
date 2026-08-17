package br.edu.idp.es.stsw.ecp.domain;

public enum PaymentStatus {
    PAGO,
    BOLSA,
    PENDENTE,
    CANCELADO,
    DESCONHECIDO;

    public static PaymentStatus from(String value) {
        try {
            return PaymentStatus.valueOf(value.trim().toUpperCase());
        } catch (RuntimeException exception) {
            return DESCONHECIDO;
        }
    }
}
