Feature: Calcular Prêmio do Seguro
    Scenario: Calcula prêmio com sucesso
    Given o segurado deseja contratar o seguro
    When o segurado tem 16 anos
    And o segurado tem 2 pontos 
    Then o sistema calcula o valor de R$ 1400 de prêmio


    Scenario: Imprimir a mensagem personalizada
    Given usuario executou o programa
    When o programa inicia e o usuario informa seu nome "Fabricio Santana"
    Then o programa imprime a mensagem "Hello, Fabricio Santana!"


    Scenario Outline: Imprimir a mensagem personalizada para varios usuarios
    Given usuario executou o programa
    When o programa inicia e o usuario informa seu nome "<nome>"
    Then o programa imprime a mensagem "Hello, <nome>!"
    Examples:
        | nome |
        | Fabricio  |
        | Matheus  |
        | Gustavo  |
        | Lucas  |