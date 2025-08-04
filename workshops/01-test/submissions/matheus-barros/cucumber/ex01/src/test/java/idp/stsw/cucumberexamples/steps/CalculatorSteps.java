package idp.stsw.cucumberexamples.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import idp.stsw.cucumberexamples.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {

    final Calculator calculator;
    private int number1;
    private int number2;
    private int result;
 
    // Cucumber cria uma nova instância desta classe para cada cenário
    public CalculatorSteps() {
        this.calculator = new Calculator();
    }   

    @Given("o primeiro número é {int}")
    public void o_primeiro_número_é(Integer num) {
        this.number1 = num;
    }

    @Given("o segundo número é {int}")
    public void o_segundo_número_é(Integer num) {
        this.number2 = num;
    }

    @When("os dois números são somados")
    public void os_dois_números_são_somados() {
        // Executa a operação de soma utilizando o método add da classe Calculator.
        this.result = calculator.add(number1, number2);
    }

    @Then("o resultado deve ser {int}")
    public void o_resultado_deve_ser(Integer expected) {
        // Valida se o resultado obtido é igual ao esperado, usando assertEquals do JUnit.
        assertEquals(expected, result, "A soma deveria ser " + expected);
    }    
}
