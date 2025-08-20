Feature: Classificação de triângulos
  Para verificar se a classificação do triângulo está correta
  Como usuário
  Quero que os lados de um triângulo sejam classificados corretamente

  Scenario: Triângulo equilátero
    Given os lados são 3, 3 e 3
    When eu classifico o triângulo
    Then o resultado deve ser "Equilátero"

  Scenario: Triângulo isósceles
    Given os lados são 5, 5 e 3
    When eu classifico o triângulo
    Then o resultado deve ser "Isósceles"

  Scenario: Triângulo escaleno
    Given os lados são 3, 4 e 5
    When eu classifico o triângulo
    Then o resultado deve ser "Escaleno"

  Scenario: Não é triângulo - lado inválido
    Given os lados são 0, 4 e 5
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"

  Scenario: Não é triângulo - soma dos lados inválida
    Given os lados são 1, 2 e 3
    When eu classifico o triângulo
    Then o resultado deve ser "Não é um triângulo"
