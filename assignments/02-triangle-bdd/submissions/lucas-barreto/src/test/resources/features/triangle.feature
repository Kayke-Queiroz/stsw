Feature: Teste de Piramide

  Scenario: Triangulo Equilátero
    Given o usuário esta na página inicial
    When ele digita os valores 5 5 e 5 para os lados a b e c
    Then ele deve ver o tipo do triangulo Equilatero

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 5 5 e 3 para os lados a b e c
    Then ele deve ver o tipo do triangulo Isosceles
 
 Scenario: Triangulo Escaleno
    Given o usuário esta na página inicial
    When ele digita os valores 5 4 e 3 para os lados a b e c
    Then ele deve ver o tipo do triangulo Escaleno

  Scenario: Não é um triângulo
    Given o usuário esta na página inicial
    When ele digita os valores 1 2 e 3 para os lados a b e c
    Then ele deve ver a mensagem Nao e um triangulo 
   
  Scenario: Lados invalidos
    Given o usuário esta na página inicial
    When ele digita os valores -5 0 e 5 para os lados a b e c
    Then ele deve ver a mensagem Lados invalidos  