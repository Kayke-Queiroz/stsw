Feature: Classificação de triângulos
  Para verificar se um triângulo é Equilátero, Isósceles, Escaleno ou inválido

  Scenario Outline: Classificar triângulo
    Given os lados <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a | b | c | resultado           |
      | 3 | 3 | 3 | Equilátero          |
      | 3 | 3 | 2 | Isósceles           |
      | 3 | 4 | 5 | Escaleno            |
      | 1 | 2 | 10| Não é um triângulo  |
      | 0 | 3 | 3 | Lados invalidos     |
