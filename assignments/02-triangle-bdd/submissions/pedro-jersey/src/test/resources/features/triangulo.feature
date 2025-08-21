Feature: Classificacao de Triangulos

  Scenario Outline: Limites do dominio dos lados (BVA)
    Given que o usuario informa os lados <a>, <b>, <c>
    When eu classificar o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado       |
      | 0   | 10  | 10  | Lados invalidos |
      | -1  | 5   | 5   | Lados invalidos |
      | 1   | 1   | 1   | Equilatero      |
      | 200 | 200 | 200 | Equilatero      |

  Scenario Outline: Triangulos Isosceles
    Given que o usuario informa os lados <a>, <b>, <c>
    When eu classificar o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado |
      | 5   | 5   | 3   | Isosceles |
      | 5   | 3   | 5   | Isosceles |
      | 3   | 5   | 5   | Isosceles |

  Scenario Outline: Triangulos Escaleno
    Given que o usuario informa os lados <a>, <b>, <c>
    When eu classificar o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado |
      | 4   | 5   | 6   | Escaleno |
      | 10  | 15  | 12  | Escaleno |
