package br.edu.idp.es.stsw.bva.steps;

import br.edu.idp.es.stsw.bva.domain.CreditPolicy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundaryValueSteps {

    private int minAge;
    private int maxAge;
    private int minIncome;
    private int maxIncome;
    private int nominalIncome;

    private CreditPolicy policy;
    private List<BoundaryCase> generatedCases = new ArrayList<>();

    @Given("que a idade aceita vai de {int} até {int}")
    public void configurarLimitesIdade(int min, int max) {
        this.minAge = min;
        this.maxAge = max;
    }

    @Given("que a renda aceita vai de {int} até {int}")
    public void configurarLimitesRenda(int min, int max) {
        this.minIncome = min;
        this.maxIncome = max;
    }

    @Given("que a renda nominal é {int}")
    public void configurarRendaNominal(int income) {
        this.nominalIncome = income;
        this.policy = new CreditPolicy(minAge, maxAge, minIncome, maxIncome);
    }

    @When("eu gero casos de {string} para idade com nominal {int}")
    public void gerarCasosUnidimensionais(String variationRaw, int nominalAge) {
        BoundaryVariation variation = BoundaryVariation.from(variationRaw);
        List<Integer> ages = BoundaryValueGenerator.points(minAge, maxAge, nominalAge, variation);

        generatedCases = ages.stream()
                .map(age -> new BoundaryCase(
                        age,
                        nominalIncome,
                        policy.expectedByRule(age, nominalIncome),
                        policy.isApproved(age, nominalIncome)))
                .toList();
    }

    @When("eu gero casos combinatórios de {string} com nominais idade {int} e renda {int}")
    public void gerarCasosCombinatorios(String variationRaw, int nominalAge, int nominalIncomeLocal) {
        BoundaryVariation variation = BoundaryVariation.from(variationRaw);

        BoundaryVariation baseVariation = switch (variation) {
            case WORST_CASE -> BoundaryVariation.CLASSICO;
            case ROBUST_WORST_CASE -> BoundaryVariation.ROBUSTO;
            default -> throw new IllegalArgumentException("Use worst-case ou robust worst-case neste passo.");
        };

        List<Integer> ages = BoundaryValueGenerator.points(minAge, maxAge, nominalAge, baseVariation);
        List<Integer> incomes = BoundaryValueGenerator.points(minIncome, maxIncome, nominalIncomeLocal, baseVariation);

        List<BoundaryCase> cases = new ArrayList<>();
        for (int age : ages) {
            for (int income : incomes) {
                cases.add(new BoundaryCase(
                        age,
                        income,
                        policy.expectedByRule(age, income),
                        policy.isApproved(age, income)
                ));
            }
        }
        generatedCases = cases;
    }

    @Then("devem existir {int} casos gerados")
    public void validarQuantidade(int expectedSize) {
        assertEquals(expectedSize, generatedCases.size(), "Quantidade de casos BVA gerados está incorreta.");
    }

    @Then("todos os casos devem respeitar o resultado esperado")
    public void validarResultados() {
        assertTrue(
                generatedCases.stream().allMatch(c -> c.expected() == c.actual()),
                "Existe caso de fronteira com resultado diferente do esperado."
        );
    }
}
