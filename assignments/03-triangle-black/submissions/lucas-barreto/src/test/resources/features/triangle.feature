Feature: Teste de Piramide

  Scenario: Lados invalidos
    Given o usuário esta na página inicial
    When ele digita os valores -1 -1 e -1 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Lados inválidos"

  Scenario: Lados invalidos
    Given o usuário esta na página inicial
    When ele digita os valores 0 0 e 0 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Lados inválidos"
 
 Scenario: Triangulo Escaleno
    Given o usuário esta na página inicial
    When ele digita os valores 5 4 e 3 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Escaleno"

  Scenario: Triangulo Equilátero
    Given o usuário esta na página inicial
    When ele digita os valores 999 999 e 999 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Equilátero"
   
   
  Scenario: Triangulo Equilátero 
    Given o usuário esta na página inicial
    When ele digita os valores 1000 1000 e 1000 para os lados a b e c
    Then ele deve ver o tipo do triângulo: "Equilátero"  

  #Gherkin references palavra example()
