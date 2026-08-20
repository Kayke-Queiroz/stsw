package br.edu.idp.es.stsw.bva.steps;

import br.edu.idp.es.stsw.bva.domain.CreditPolicy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryValueSteps {

    private int minAge;
    private int maxAge;
    private int minIncome;
    private int maxIncome;
    private CreditPolicy policy;
    private boolean approvalResult;

    @Given("que a idade aceita vai de {int} até {int}")
    public void configurarLimitesIdade(int min, int max) {
        this.minAge = min;
        this.maxAge = max;
    }

    @Given("que a renda aceita vai de {int} até {int}")
    public void configurarLimitesRenda(int min, int max) {
        this.minIncome = min;
        this.maxIncome = max;
        this.policy = new CreditPolicy(minAge, maxAge, minIncome, maxIncome);
    }

    @Given("que a renda nominal é {int}")
    public void configurarRendaNominal(int ignoredIncome) {
        this.policy = new CreditPolicy(minAge, maxAge, minIncome, maxIncome);
    }

    @When("eu avalio uma proposta com idade {int} e renda {int}")
    public void avaliarProposta(int age, int income) {
        approvalResult = policy.isApproved(age, income);
    }

    @Then("o resultado da proposta deve ser {string}")
    public void validarResultadoDaProposta(String expected) {
        boolean expectedApproval = switch (expected.trim().toUpperCase()) {
            case "APROVADA" -> true;
            case "REPROVADA" -> false;
            default -> throw new IllegalArgumentException("Resultado esperado inválido: " + expected);
        };
        assertEquals(expectedApproval, approvalResult, "Resultado da regra de negócio divergente.");
    }
}
