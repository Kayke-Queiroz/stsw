package br.edu.idp.es.stsw.bva.domain;

public class CreditPolicy {

    private final int minAge;
    private final int maxAge;
    private final int minIncome;
    private final int maxIncome;

    public CreditPolicy(int minAge, int maxAge, int minIncome, int maxIncome) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
    }

    public boolean isApproved(int age, int income) {
        return isInside(age, minAge, maxAge) && isInside(income, minIncome, maxIncome);
    }

    private boolean isInside(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
