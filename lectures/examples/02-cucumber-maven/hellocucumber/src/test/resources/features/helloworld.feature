Feature: Hello World
    Scenario: Imprimir a mensagem "Hello, World!"
    Given usuario executou o programa
    When o programa inicia
    Then o programa imprime a mensagem "Hello, World!"


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