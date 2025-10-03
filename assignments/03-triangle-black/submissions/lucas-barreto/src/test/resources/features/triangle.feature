Feature: Teste de Piramide

#125 CASOS DE TESTE
  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 1 100 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 2 100 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Triangulo Equilátero
    Given o usuário esta na página inicial
    When ele digita os valores 100 100 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Equilátero"

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 199 100 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Não é um Triângulo
    Given o usuário esta na página inicial
    When ele digita os valores 200 100 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Não é um Triângulo"

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 1 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 2 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 199 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"

  Scenario: Não é um Triângulo
    Given o usuário esta na página inicial
    When ele digita os valores 100 200 e 100 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Não é um Triângulo"
    
  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 100 e 1 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"


  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 100 e 2 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"


  Scenario: Triangulo Isósceles
    Given o usuário esta na página inicial
    When ele digita os valores 100 100 e 199 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Isósceles"


  Scenario: Não é um Triângulo
    Given o usuário esta na página inicial
    When ele digita os valores 100 100 e 200 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Não é um Triângulo"
