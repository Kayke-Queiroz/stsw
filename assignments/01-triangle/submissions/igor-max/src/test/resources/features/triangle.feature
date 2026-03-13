Feature: Classificação de Triângulos

  Scenario: Triângulo Equilátero
    Given os lados do triangulo são 5, 5 e 5
    When eu classifico o triangulo
    Then o resultado deve ser "Equilátero"

  Scenario: Triângulo Isósceles
    Given os lados do triangulo são 5, 5 e 3
    When eu classifico o triangulo
    Then o resultado deve ser "Isósceles"

  Scenario: Triângulo Escaleno
    Given os lados do triangulo são 5, 4 e 3
    When eu classifico o triangulo
    Then o resultado deve ser "Escaleno"

  Scenario: Não é um triângulo
    Given os lados do triangulo são 1, 2 e 3
    When eu classifico o triangulo
    Then o resultado deve ser "Não é um triângulo"

  Scenario: Lados inválidos
    Given os lados do triangulo são -5, 0 e 5
    When eu classifico o triangulo
    Then o resultado deve ser "Lados inválidos"