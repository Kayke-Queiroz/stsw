Feature: Classificação de Triângulos

  Scenario Outline: Valores limites dos lados (BVA)
    Given o usuário fornece os lados <a>, <b>, <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado         |
      | 0   | 10  | 10  | Lados inválidos   |
      | -1  | 5   | 5   | Lados inválidos   |
      | 1   | 1   | 1   | Equilátero        |
      | 200 | 200 | 200 | Equilátero        |

  Scenario Outline: Triângulos Isósceles
    Given o usuário fornece os lados <a>, <b>, <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado |
      | 5   | 5   | 3   | Isósceles |
      | 5   | 3   | 5   | Isósceles |
      | 3   | 5   | 5   | Isósceles |

  Scenario Outline: Triângulos Escalenos
    Given o usuário fornece os lados <a>, <b>, <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado |
      | 4   | 5   | 6   | Escaleno  |
      | 10  | 15  | 12  | Escaleno  |
