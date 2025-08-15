Feature: Teste de Piramide;

  Scenario: Triangulo Equilátero
    Given o usuário está na página inicial
    When ele digita s valores 5, 5 e 5 para os lados a, b e c
    Then ele deve ver o tipo do triangulo: Equilátero

  Scenario: Triangulo Isósceles
    Given o usuário está na página inicial
    When ele digita s valores 5, 5 e 3 para os lados a, b e c
    Then ele deve ver o tipo do triangulo: Isósceles
 
 Scenario: Triangulo Escaleno
    Given o usuário está na página inicial
    When ele digita s valores 5, 4 e 3 para os lados a, b e c
    Then ele deve ver o tipo do triangulo: Escaleno

  Scenario: Não é um triângulo
    Given o usuário está na página inicial
    When ele digita s valores 1, 2 e 3 para os lados a, b e c
    Then ele deve ver a mensagem "Não é um triângulo" 
   
  Scenario: Lados inválidos
    Given o usuário está na página inicial
    When ele digita s valores -5, 0 e 5 para os lados a, b e c
    Then ele deve ver a mensagem: Lados inválidos  
