Feature: Classificação do Triângulo com BVA
  Como usuário do programa Triangle
  Eu quero classificar triângulos informando três lados
  Para saber se é Equilátero, Isósceles, Escaleno ou inválido

  # Triângulo Equilátero - todos os lados iguais
  Scenario: Triângulo equilátero com lados iguais
    Given os lados do triângulo são 10, 10 e 10
    When eu classifico o triângulo
    Then o resultado deve ser "Equilátero"

  # Triângulo Isósceles - dois lados iguais
  Scenario: Triângulo isósceles com dois lados iguais (a == b)
    Given os lados do triângulo são 10, 10 e 5
    When eu classifico o triângulo
    Then o resultado deve ser "Isósceles"

  Scenario: Triângulo isósceles com dois lados iguais (a == c)
    Given os lados do triângulo são 10, 5 e 10
    When eu classifico o triângulo
    Then o resultado deve ser "Isósceles"

  Scenario: Triângulo isósceles com dois lados iguais (b == c)
    Given os lados do triângulo são 5, 10 e 10
    When eu classifico o triângulo
    Then o resultado deve ser "Isósceles"

  # Triângulo Escaleno - todos os lados diferentes
  Scenario: Triângulo escaleno com lados diferentes
    Given os lados do triângulo são 3, 4 e 5
    When eu classifico o triângulo
    Then o resultado deve ser "Escaleno"

  # Não é um triângulo - desigualdade triangular violada
  Scenario: Não é triângulo quando a + b == c
    Given os lados do triângulo são 1, 2 e 3
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"

  Scenario: Não é triângulo quando a + b < c
    Given os lados do triângulo são 1, 2 e 10
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"

  Scenario: Não é triângulo quando a + c <= b
    Given os lados do triângulo são 1, 10 e 2
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"

  Scenario: Não é triângulo quando b + c <= a
    Given os lados do triângulo são 10, 1 e 2
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"

  # BVA - Limite inferior do domínio (1)
  Scenario: Limite inferior válido - todos os lados com valor mínimo 1
    Given os lados do triângulo são 1, 1 e 1
    When eu classifico o triângulo
    Then o resultado deve ser "Equilátero"

  Scenario: Limite inferior inválido - lado com valor 0
    Given os lados do triângulo são 0, 1 e 1
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"

  Scenario: Limite inferior inválido - lado negativo
    Given os lados do triângulo são -1, 1 e 1
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"

  # BVA - Limite superior do domínio (200)
  Scenario: Limite superior válido - todos os lados com valor máximo 200
    Given os lados do triângulo são 200, 200 e 200
    When eu classifico o triângulo
    Then o resultado deve ser "Equilátero"

  Scenario: Limite superior inválido - lado com valor 201
    Given os lados do triângulo são 201, 200 e 200
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"

  Scenario: Limite superior válido - escaleno com valores próximos ao máximo
    Given os lados do triângulo são 198, 199 e 200
    When eu classifico o triângulo
    Then o resultado deve ser "Escaleno"

  Scenario: Limite superior válido - isósceles com valores no máximo
    Given os lados do triângulo são 200, 200 e 199
    When eu classifico o triângulo
    Then o resultado deve ser "Isósceles"

  # BVA - Limites da desigualdade triangular
  Scenario Outline: Valores limítrofes da desigualdade triangular
    Given os lados do triângulo são <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      | 1   | 1   | 2   | Não é um triângulo |
      | 1   | 1   | 1   | Equilátero         |
      | 100 | 100 | 199 | Isósceles          |
      | 100 | 100 | 200 | Não é um triângulo |
      | 2   | 3   | 4   | Escaleno           |

  # Todos os lados inválidos
  Scenario: Todos os lados com valor zero
    Given os lados do triângulo são 0, 0 e 0
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"

  Scenario: Todos os lados negativos
    Given os lados do triângulo são -5, -3 e -1
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"

  Scenario: Todos os lados acima do máximo
    Given os lados do triângulo são 201, 201 e 201
    When eu classifico o triângulo
    Then o resultado deve ser "Lados inválidos"
