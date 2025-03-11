Feature: Classificação de triângulos

  Scenario: Triângulo equilátero
    Given os lados do triângulo são 5, 5 e 5
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Equilátero"

  Scenario: Triângulo isósceles
    Given os lados do triângulo são 5, 5 e 3
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Isósceles"

  Scenario: Triângulo escaleno
    Given os lados do triângulo são 3, 4 e 5
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Escaleno"

  Scenario: Não é um triângulo
    Given os lados do triângulo são 1, 2 e 8
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Não é um triângulo"

  Scenario: Lados inválidos
    Given os lados do triângulo são 0, 5 e 5
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Lados inválidos"
