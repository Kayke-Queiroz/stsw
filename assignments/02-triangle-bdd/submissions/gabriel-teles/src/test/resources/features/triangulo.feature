Feature: Classificação de Triângulos
  Verificar a classificação dos triângulos conforme os lados informados

  Scenario: Triângulo equilátero
    Given os lados do triângulo são 5, 5, 5
    When eu classifico o triângulo
    Then a saída deve ser "EQUILÁTERO"

  Scenario: Triângulo isósceles
    Given os lados do triângulo são 5, 5, 3
    When eu classifico o triângulo
    Then a saída deve ser "ISÓSCELES"

  Scenario: Triângulo escaleno
    Given os lados do triângulo são 3, 4, 5
    When eu classifico o triângulo
    Then a saída deve ser "ESCALENO"

  Scenario: Lados inválidos (menor que 1)
    Given os lados do triângulo são 0, 10, 10
    When eu classifico o triângulo
    Then a saída deve ser "Lados inválidos"

  Scenario: Lados inválidos (maior que 200)
    Given os lados do triângulo são 201, 10, 10
    When eu classifico o triângulo
    Then a saída deve ser "Lados inválidos"

  Scenario: Não forma triângulo
    Given os lados do triângulo são 10, 5, 5
    When eu classifico o triângulo
    Then a saída deve ser "Não é um triângulo"