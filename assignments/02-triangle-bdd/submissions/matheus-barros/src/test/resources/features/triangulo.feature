Feature: Classificação do Triângulo com RBVT

  # Cenários de Limites (RBVT)

  Scenario: BVA - Lados inválidos (0, 100, 100)
    Given os lados do triângulo são 0, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Lados inválidos"

  Scenario: BVA - Lados válidos (1, 100, 100)
    Given os lados do triângulo são 1, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Isósceles"

  Scenario: BVA - Lados válidos (2, 100, 100)
    Given os lados do triângulo são 2, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Isósceles"

  Scenario: BVA - Triângulo Equilátero (100, 100, 100)
    Given os lados do triângulo são 100, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Equilátero"

  Scenario: BVA - Lados válidos (199, 100, 100)
    Given os lados do triângulo são 199, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Isósceles"

  Scenario: BVA - Lados válidos (200, 110, 110)
    Given os lados do triângulo são 200, 110 e 110
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Isósceles"

  Scenario: BVA - Lados inválidos (201, 100, 100)
    Given os lados do triângulo são 201, 100 e 100
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Lados inválidos"

  # Cenário para Triângulo Escaleno

  Scenario: Triângulo Escaleno (3, 4, 5)
    Given os lados do triângulo são 3, 4 e 5
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Escaleno"

  # Cenários para limites da condição de desigualdade dos lados

  Scenario: Bordas da Desigualdade - Caso 1 (10, 20, 30)
    Given os lados do triângulo são 10, 20 e 30
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Não é um triângulo"

  Scenario: Bordas da Desigualdade - Caso 2 (9, 20, 30)
    Given os lados do triângulo são 9, 20 e 30
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Não é um triângulo"

  Scenario: Bordas da Desigualdade - Caso 3 (11, 20, 30)
    Given os lados do triângulo são 11, 20 e 30
    When eu solicito a classificação do triângulo
    Then o sistema deve retornar "Escaleno"
