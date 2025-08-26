Feature: Classificação de Triângulos
  Testar limites de entrada para todos os lados

  Scenario Outline: Testar limites em todos os lados
    Given os lados <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      | 0   | 10  | 10  | Lados invalidos    |
      | -1  | 10  | 10  | Lados invalidos    |
      | 10  | 0   | 10  | Lados invalidos    |
      | 10  | -1  | 10  | Lados invalidos    |
      | 10  | 10  | 0   | Lados invalidos    |
      | 10  | 10  | -1  | Lados invalidos    |
      | 201 | 10  | 10  | Lados invalidos    |
      | 10  | 201 | 10  | Lados invalidos    |
      | 10  | 10  | 201 | Lados invalidos    |
      | 1   | 1   | 1   | Equilátero         |
      | 1   | 2   | 2   | Isósceles          |
      | 2   |  10| 4   | Não é um triângulo |
      | 200 | 200 | 200 | Equilátero         |
      | 200 | 200 | 199 | Isósceles          |
      | 200 | 199 | 198 | Escaleno           |
      | 3   | 4   | 5   | Escaleno           |
      | 5   | 5   | 8   | Isósceles          |
