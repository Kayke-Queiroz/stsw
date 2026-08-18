package br.edu.idp.es.stsw.ecp.steps;

import br.edu.idp.es.stsw.ecp.domain.EnrollmentDecision;
import br.edu.idp.es.stsw.ecp.domain.EnrollmentPolicy;
import br.edu.idp.es.stsw.ecp.domain.PaymentStatus;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentPolicySteps {

    private final EnrollmentPolicy policy = new EnrollmentPolicy();
    private EnrollmentDecision decision;

    @When("eu avalio uma solicitação com idade {int}, nota {int}, vagas {int} e pagamento {string}")
    public void avaliarSolicitacao(int age, int score, int seats, String paymentStatus) {
        decision = policy.evaluate(age, score, seats, PaymentStatus.from(paymentStatus));
    }

    @Then("a decisão da matrícula deve ser {string}")
    public void validarDecisao(String expectedDecision) {
        EnrollmentDecision expected = EnrollmentDecision.valueOf(expectedDecision.trim().toUpperCase());
        assertEquals(expected, decision);
    }
}
