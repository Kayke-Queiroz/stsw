Feature: Teste de Piramide

  Scenario: Triangulo Equilátero
    Given o usuário esta na página inicial
    When ele digita os valores 5 5 e 5 para os lados a b e c
    Then ele deve ver o tipo do triângulo: Equilatero

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 5 5 e 3 para os lados a b e c
    Then ele deve ver o tipo do triângulo: Isosceles
 
 Scenario: Triangulo Escaleno
    Given o usuário esta na página inicial
    When ele digita os valores 5 4 e 3 para os lados a b e c
    Then ele deve ver o tipo do triângulo: Escaleno

  Scenario: Não é um triângulo
    Given o usuário esta na página inicial
    When ele digita os valores 1 2 e 3 para os lados a b e c
    Then ele deve ver o tipo do triângulo: Não é um triângulo
   
  Scenario: Lados invalidos
    Given o usuário esta na página inicial
    When ele digita os valores -5 0 e 5 para os lados a b e c
    Then ele deve ver o tipo do triângulo: Lados inválidos  