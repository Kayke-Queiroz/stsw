Feature: Testes caixa-preta para classificacao de triangulos

  Scenario Outline: Classes de equivalencia de triangulos validos
    Given que eu informo os lados <a>, <b> e <c>
    When o sistema classifica o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado   |
      | 5   | 5   | 5   | Equilátero  |
      | 10  | 10  | 5   | Isósceles   |
      | 3   | 4   | 5   | Escaleno    |
      | 198 | 199 | 200 | Escaleno    |

  Scenario Outline: Classe de equivalencia para entradas que nao formam triangulo
    Given que eu informo os lados <a>, <b> e <c>
    When o sistema classifica o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      | 1   | 2   | 3   | Não é um triângulo |
      | 10  | 1   | 2   | Não é um triângulo |
      | 100 | 100 | 200 | Não é um triângulo |

  Scenario Outline: Classe de equivalencia para lados fora do dominio
    Given que eu informo os lados <a>, <b> e <c>
    When o sistema classifica o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado       |
      | 0   | 5   | 5   | Lados inválidos |
      | -1  | 5   | 5   | Lados inválidos |
      | 201 | 5   | 5   | Lados inválidos |
      | 5   | 0   | 5   | Lados inválidos |
      | 5   | 5   | 201 | Lados inválidos |

  Scenario Outline: BVA do dominio de entrada
    Given que eu informo os lados <a>, <b> e <c>
    When o sistema classifica o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado       |
      | 1   | 1   | 1   | Equilátero      |
      | 2   | 2   | 1   | Isósceles       |
      | 199 | 199 | 199 | Equilátero      |
      | 200 | 200 | 200 | Equilátero      |
      | 201 | 200 | 200 | Lados inválidos |

  Scenario Outline: BVA da desigualdade triangular
    Given que eu informo os lados <a>, <b> e <c>
    When o sistema classifica o triangulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      | 1   | 1   | 2   | Não é um triângulo |
      | 1   | 2   | 2   | Isósceles          |
      | 100 | 100 | 199 | Isósceles          |
      | 100 | 100 | 200 | Não é um triângulo |
