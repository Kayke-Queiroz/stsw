Feature: Boundary Value Analysis (BVA) aplicado a regra de negócio
  Como pessoa estudante de teste de software
  Quero validar decisões de negócio nos pontos de fronteira
  Para entender BVA normal, robusto, worst-case e robust worst-case

  Background:
    Given que a idade aceita vai de 18 até 65
    And que a renda aceita vai de 2000 até 10000
    And que a renda nominal é 5000

  Scenario Outline: BVA normal e robusto para idade (renda nominal fixa)
    When eu avalio uma proposta com idade <idade> e renda 5000
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | resultado |
      | 17    | REPROVADA |
      | 18    | APROVADA  |
      | 19    | APROVADA  |
      | 40    | APROVADA  |
      | 64    | APROVADA  |
      | 65    | APROVADA  |
      | 66    | REPROVADA |

  Scenario Outline: BVA normal e robusto para renda (idade nominal fixa)
    When eu avalio uma proposta com idade 40 e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | renda | resultado |
      | 1999  | REPROVADA |
      | 2000  | APROVADA  |
      | 2001  | APROVADA  |
      | 5000  | APROVADA  |
      | 9999  | APROVADA  |
      | 10000 | APROVADA  |
      | 10001 | REPROVADA |

  Scenario Outline: BVA combinatório (worst-case) em duas variáveis
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 18    | 2000  | APROVADA  |
      | 18    | 10000 | APROVADA  |
      | 65    | 2000  | APROVADA  |
      | 65    | 10000 | APROVADA  |
      | 17    | 5000  | REPROVADA |
      | 66    | 5000  | REPROVADA |
      | 40    | 1999  | REPROVADA |
      | 40    | 10001 | REPROVADA |

  Scenario Outline: BVA combinatório robust worst-case com fora da faixa em ambas entradas
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 17    | 1999  | REPROVADA |
      | 17    | 2000  | REPROVADA |
      | 18    | 1999  | REPROVADA |
      | 66    | 10001 | REPROVADA |
      | 65    | 10001 | REPROVADA |
      | 66    | 10000 | REPROVADA |
