# src/test/resources/features/calculator.feature
Feature: Calculator
  Como um usuário
  Eu quero realizar cálculos
  Para que eu não precise fazer contas manualmente

  Scenario: Adicionar dois números
    Given o primeiro número é 5
    And o segundo número é 3
    When os dois números são somados
    Then o resultado deve ser 8

  Scenario: Adicionar um número positivo e um negativo
    Given o primeiro número é 10
    And o segundo número é -4
    When os dois números são somados
    Then o resultado deve ser 6