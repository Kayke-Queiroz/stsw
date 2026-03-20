Feature: Boundary Value Analysis (BVA) aplicado a classificacao de triangulos
  Como estudante de teste de software
  Quero validar os limites dos lados de um triangulo
  Para garantir que o sistema se comporta corretamente nas fronteiras

  Background:
    Given que o valor minimo de um lado e 1
    And que o valor maximo de um lado e 200
    And que o valor nominal e 5

  Scenario Outline: BVA classico e robusto para o lado A com B e C fixos
    When eu classifico um triangulo com lados <a>, 5, 5
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | resultado          |
      | 0   | Lados inválidos    |
      | 1   | Isósceles          |
      | 2   | Isósceles          |
      | 5   | Equilátero         |
      | 199 | Não é um triângulo |
      | 200 | Não é um triângulo |
      | 201 | Lados inválidos    |

  Scenario Outline: BVA classico e robusto para o lado B com A e C fixos
    When eu classifico um triangulo com lados 5, <b>, 5
    Then o resultado deve ser "<resultado>"

    Examples:
      | b   | resultado          |
      | 0   | Lados inválidos    |
      | 1   | Isósceles          |
      | 2   | Isósceles          |
      | 5   | Equilátero         |
      | 199 | Não é um triângulo |
      | 200 | Não é um triângulo |
      | 201 | Lados inválidos    |

  Scenario Outline: BVA combinatorio com valores validos
    When eu classifico um triangulo com lados <a>, <b>, <c>
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado   |
      | 1   | 1   | 1   | Equilátero  |
      | 200 | 200 | 200 | Equilátero  |
      | 1   | 200 | 200 | Isósceles   |
      | 200 | 1   | 200 | Isósceles   |
      | 200 | 200 | 1   | Isósceles   |
      | 2   | 3   | 4   | Escaleno    |

  Scenario Outline: BVA combinatorio robusto com valores fora da faixa
    When eu classifico um triangulo com lados <a>, <b>, <c>
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado       |
      | 0   | 5   | 5   | Lados inválidos |
      | 5   | 0   | 5   | Lados inválidos |
      | 5   | 5   | 0   | Lados inválidos |
      | 201 | 5   | 5   | Lados inválidos |
      | 5   | 201 | 5   | Lados inválidos |
      | 5   | 5   | 201 | Lados inválidos |
      | 0   | 0   | 0   | Lados inválidos |
      | 201 | 201 | 201 | Lados inválidos |

  Scenario Outline: BVA na desigualdade triangular
    When eu classifico um triangulo com lados <a>, <b>, <c>
    Then o resultado deve ser "<resultado>"

    Examples:
      | a | b | c | resultado          |
      | 1 | 2 | 3 | Não é um triângulo |
      | 2 | 2 | 3 | Isósceles          |
      | 3 | 4 | 7 | Não é um triângulo |
      | 3 | 4 | 6 | Escaleno           |
