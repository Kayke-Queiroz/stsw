Feature: Classificação de triângulos
  Para verificar se o triângulo é classificado corretamente
  Como usuário do sistema
  Quero que o programa classifique triângulos com base nos lados informados

  Scenario Outline: Classificar triângulos
    Given os lados são <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      | a  | b  | c  | resultado             |
      | 0  | 5  | 7  | Não é um triângulo    |
      | -1 | 2  | 3  | Não é um triângulo    |
      | 3  | 3  | 3  | Equilátero            |
      | 5  | 5  | 3  | Isósceles             |
      | 3  | 5  | 5  | Isósceles             |
      | 5  | 3  | 5  | Isósceles             |
      | 3  | 4  | 5  | Escaleno              |
      | 1  | 2  | 3  | Não é um triângulo    |
      | 2  | 2  | 4  | Não é um triângulo    |
