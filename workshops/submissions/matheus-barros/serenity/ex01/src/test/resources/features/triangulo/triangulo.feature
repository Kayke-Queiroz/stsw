# src/test/resources/features/triangulo.feature
Feature:Classificação do Triângulo

  Como usuário do sistema
  Eu quero que o programa classifique corretamente os triângulos
  Para que eu saiba se os lados formam um triângulo e qual o seu tipo

  # Cenário 1: Triângulo Equilátero no Limite Inferior
  Scenario: Triângulo Equilátero com o menor valor permitido para cada lado
    Given os lados do triangulo sao 1, 1, 1
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Equilátero"

  # Cenário 2: Triângulo Equilátero no Limite Superior
  Scenario: Triângulo Equilátero com o maior valor permitido para cada lado
    Given os lados do triangulo sao 200, 200, 200
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Equilátero"

  # Cenário 3: Triângulo Isósceles com extremos mistos
  Scenario: Triângulo Isósceles com um lado no limite inferior e os demais no limite superior
    Given os lados do triangulo sao 1, 200, 200
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Isósceles"

  # Cenário 4: Triângulo Escaleno com valores próximos aos limites
  Scenario: Triângulo Escaleno com dois lados próximos do limite superior e um pouco maior que o mínimo
    Given os lados do triangulo sao 2, 199, 200
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Escaleno"

  # Cenário 5: Triângulo inválido por violar a desigualdade triangular
  Scenario: Lados que não satisfazem a condição do triângulo
    Given os lados do triangulo sao 1, 1, 2
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Não é um triângulo"

  # Cenário 6: Lados inválidos com valor abaixo do mínimo permitido
  Scenario: Um dos lados está abaixo do limite mínimo
    Given os lados do triangulo sao 0, 50, 50
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Lados inválidos"

  # Cenário 7: Lados inválidos com valor acima do máximo permitido
  Scenario: Um dos lados está acima do limite máximo
    Given os lados do triangulo sao 201, 100, 100
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Lados inválidos"

  # Cenário 8: Múltiplos valores inválidos
  Scenario: Dois ou mais lados fora do intervalo permitido
    Given os lados do triangulo sao 0, 201, 100
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Lados inválidos"

  # Cenário 9: Triangulo Escaleno
  Scenario: Triângulo escaleno clássico
    Given os lados do triangulo sao 3, 4, 5
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Escaleno"

  # Cenários 10 - 12: Limites da condição de desigualdade dos lados

  Scenario: Bordas da Desigualdade - Caso 1 (10, 20, 30)
    Given os lados do triangulo sao 10, 20, 30
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Não é um triângulo"

  Scenario: Bordas da Desigualdade - Caso 2 (9, 20, 30)
    Given os lados do triangulo sao 9, 20, 30
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Não é um triângulo"

  Scenario: Bordas da Desigualdade - Caso 3 (11, 20, 30)
    Given os lados do triangulo sao 11, 20, 30
    When eu solicito a classificacao do triangulo
    Then o sistema deve retornar "Escaleno"
