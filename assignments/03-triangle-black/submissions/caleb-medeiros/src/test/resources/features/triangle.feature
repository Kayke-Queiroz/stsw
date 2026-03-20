Feature: Teste caixa-preta do Triângulo com classes de equivalência e BVA
  Teste da classificação do triângulo usando técnicas de caixa-preta:
  classes de equivalência e análise de valores limite (BVA)

  # ============================================================
  # CLASSE DE EQUIVALÊNCIA 1: Triângulo Equilátero (a == b == c)
  # ============================================================
  Scenario: CE1 - Equilátero com valores pequenos
    Given os lados são 5, 5 e 5
    When classifico o triângulo
    Then o tipo deve ser "Equilátero"

  Scenario: CE1 - Equilátero com valores médios
    Given os lados são 100, 100 e 100
    When classifico o triângulo
    Then o tipo deve ser "Equilátero"

  # ============================================================
  # CLASSE DE EQUIVALÊNCIA 2: Triângulo Isósceles (2 lados iguais)
  # ============================================================
  Scenario: CE2 - Isósceles (a == b)
    Given os lados são 10, 10 e 5
    When classifico o triângulo
    Then o tipo deve ser "Isósceles"

  Scenario: CE2 - Isósceles (a == c)
    Given os lados são 10, 5 e 10
    When classifico o triângulo
    Then o tipo deve ser "Isósceles"

  Scenario: CE2 - Isósceles (b == c)
    Given os lados são 5, 10 e 10
    When classifico o triângulo
    Then o tipo deve ser "Isósceles"

  Scenario: CE2 - Isósceles com valores grandes
    Given os lados são 150, 150 e 100
    When classifico o triângulo
    Then o tipo deve ser "Isósceles"

  # ============================================================
  # CLASSE DE EQUIVALÊNCIA 3: Triângulo Escaleno (todos diferentes)
  # ============================================================
  Scenario: CE3 - Escaleno com valores pequenos
    Given os lados são 3, 4 e 5
    When classifico o triângulo
    Then o tipo deve ser "Escaleno"

  Scenario: CE3 - Escaleno com valores médios
    Given os lados são 50, 60 e 70
    When classifico o triângulo
    Then o tipo deve ser "Escaleno"

  Scenario: CE3 - Escaleno com valores grandes
    Given os lados são 198, 199 e 200
    When classifico o triângulo
    Then o tipo deve ser "Escaleno"

  # ============================================================
  # CLASSE DE EQUIVALÊNCIA 4: Não é triângulo (desigualdade violada)
  # ============================================================
  Scenario: CE4 - Não é triângulo (a + b == c)
    Given os lados são 1, 2 e 3
    When classifico o triângulo
    Then o tipo deve ser "Não é um triângulo"

  Scenario: CE4 - Não é triângulo (a + b < c)
    Given os lados são 1, 2 e 10
    When classifico o triângulo
    Then o tipo deve ser "Não é um triângulo"

  Scenario: CE4 - Não é triângulo (a + c <= b)
    Given os lados são 1, 10 e 2
    When classifico o triângulo
    Then o tipo deve ser "Não é um triângulo"

  Scenario: CE4 - Não é triângulo (b + c <= a)
    Given os lados são 10, 1 e 2
    When classifico o triângulo
    Then o tipo deve ser "Não é um triângulo"

  # ============================================================
  # CLASSE DE EQUIVALÊNCIA 5: Lados inválidos (fora do domínio 1-200)
  # ============================================================
  Scenario: CE5 - Lado com valor zero
    Given os lados são 0, 5 e 5
    When classifico o triângulo
    Then o tipo deve ser "Lados inválidos"

  Scenario: CE5 - Lado negativo
    Given os lados são -1, 5 e 5
    When classifico o triângulo
    Then o tipo deve ser "Lados inválidos"

  Scenario: CE5 - Lado acima do máximo
    Given os lados são 201, 5 e 5
    When classifico o triângulo
    Then o tipo deve ser "Lados inválidos"

  Scenario: CE5 - Todos os lados inválidos (zero)
    Given os lados são 0, 0 e 0
    When classifico o triângulo
    Then o tipo deve ser "Lados inválidos"

  Scenario: CE5 - Todos os lados acima do máximo
    Given os lados são 201, 201 e 201
    When classifico o triângulo
    Then o tipo deve ser "Lados inválidos"

  # ============================================================
  # BVA - Análise de Valores Limite do domínio [1, 200]
  # ============================================================
  Scenario Outline: BVA - Limites do domínio
    Given os lados são <a>, <b> e <c>
    When classifico o triângulo
    Then o tipo deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      # Limite inferior: 0 (inválido) e 1 (válido)
      | 0   | 1   | 1   | Lados inválidos    |
      | 1   | 0   | 1   | Lados inválidos    |
      | 1   | 1   | 0   | Lados inválidos    |
      | 1   | 1   | 1   | Equilátero         |
      | 2   | 2   | 1   | Isósceles          |
      # Limite superior: 200 (válido) e 201 (inválido)
      | 200 | 200 | 200 | Equilátero         |
      | 200 | 200 | 199 | Isósceles          |
      | 201 | 200 | 200 | Lados inválidos    |
      | 200 | 201 | 200 | Lados inválidos    |
      | 200 | 200 | 201 | Lados inválidos    |
      # Limites negativos
      | -1  | 5   | 5   | Lados inválidos    |
      | 5   | -1  | 5   | Lados inválidos    |
      | 5   | 5   | -1  | Lados inválidos    |

  # ============================================================
  # BVA - Limites da desigualdade triangular
  # ============================================================
  Scenario Outline: BVA - Limites da desigualdade triangular
    Given os lados são <a>, <b> e <c>
    When classifico o triângulo
    Then o tipo deve ser "<resultado>"

    Examples:
      | a   | b   | c   | resultado          |
      | 1   | 1   | 2   | Não é um triângulo |
      | 1   | 2   | 1   | Não é um triângulo |
      | 2   | 1   | 1   | Não é um triângulo |
      | 100 | 100 | 200 | Não é um triângulo |
      | 100 | 100 | 199 | Isósceles          |
      | 2   | 3   | 4   | Escaleno           |
      | 3   | 4   | 6   | Escaleno           |
