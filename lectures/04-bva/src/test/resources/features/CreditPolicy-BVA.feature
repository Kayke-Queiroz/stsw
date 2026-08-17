Feature: Boundary Value Analysis (BVA) aplicado a regra de negócio
  Como pessoa estudante de teste de software
  Quero validar decisões de negócio nos pontos de fronteira
  Para entender BVA normal, robusto, worst-case e robust worst-case

  Background:
    Given que a idade aceita vai de 18 até 65
    And que a renda aceita vai de 2000 até 10000
    And que a renda nominal é 5000

  Scenario Outline: BVA normal com suposição de falha única
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 40    | 5000  | APROVADA  |
      | 18    | 5000  | APROVADA  |
      | 19    | 5000  | APROVADA  |
      | 64    | 5000  | APROVADA  |
      | 65    | 5000  | APROVADA  |
      | 40    | 2000  | APROVADA  |
      | 40    | 2001  | APROVADA  |
      | 40    | 9999  | APROVADA  |
      | 40    | 10000 | APROVADA  |

  Scenario Outline: BVA robusto com suposição de falha única
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 40    | 5000  | APROVADA  |
      | 17    | 5000  | REPROVADA |
      | 18    | 5000  | APROVADA  |
      | 19    | 5000  | APROVADA  |
      | 64    | 5000  | APROVADA  |
      | 65    | 5000  | APROVADA  |
      | 66    | 5000  | REPROVADA |
      | 40    | 1999  | REPROVADA |
      | 40    | 2000  | APROVADA  |
      | 40    | 2001  | APROVADA  |
      | 40    | 9999  | APROVADA  |
      | 40    | 10000 | APROVADA  |
      | 40    | 10001 | REPROVADA |

  Scenario Outline: BVA worst-case com suposição de falha múltipla
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 18    | 2000  | APROVADA  |
      | 18    | 2001  | APROVADA  |
      | 18    | 5000  | APROVADA  |
      | 18    | 9999  | APROVADA  |
      | 18    | 10000 | APROVADA  |
      | 19    | 2000  | APROVADA  |
      | 19    | 2001  | APROVADA  |
      | 19    | 5000  | APROVADA  |
      | 19    | 9999  | APROVADA  |
      | 19    | 10000 | APROVADA  |
      | 40    | 2000  | APROVADA  |
      | 40    | 2001  | APROVADA  |
      | 40    | 5000  | APROVADA  |
      | 40    | 9999  | APROVADA  |
      | 40    | 10000 | APROVADA  |
      | 64    | 2000  | APROVADA  |
      | 64    | 2001  | APROVADA  |
      | 64    | 5000  | APROVADA  |
      | 64    | 9999  | APROVADA  |
      | 64    | 10000 | APROVADA  |
      | 65    | 2000  | APROVADA  |
      | 65    | 2001  | APROVADA  |
      | 65    | 5000  | APROVADA  |
      | 65    | 9999  | APROVADA  |
      | 65    | 10000 | APROVADA  |

  Scenario Outline: BVA robust worst-case com suposição de falha múltipla
    When eu avalio uma proposta com idade <idade> e renda <renda>
    Then o resultado da proposta deve ser "<resultado>"

    Examples:
      | idade | renda | resultado |
      | 17    | 1999  | REPROVADA |
      | 17    | 2000  | REPROVADA |
      | 17    | 2001  | REPROVADA |
      | 17    | 5000  | REPROVADA |
      | 17    | 9999  | REPROVADA |
      | 17    | 10000 | REPROVADA |
      | 17    | 10001 | REPROVADA |
      | 18    | 1999  | REPROVADA |
      | 18    | 2000  | APROVADA  |
      | 18    | 2001  | APROVADA  |
      | 18    | 5000  | APROVADA  |
      | 18    | 9999  | APROVADA  |
      | 18    | 10000 | APROVADA  |
      | 18    | 10001 | REPROVADA |
      | 19    | 1999  | REPROVADA |
      | 19    | 2000  | APROVADA  |
      | 19    | 2001  | APROVADA  |
      | 19    | 5000  | APROVADA  |
      | 19    | 9999  | APROVADA  |
      | 19    | 10000 | APROVADA  |
      | 19    | 10001 | REPROVADA |
      | 40    | 1999  | REPROVADA |
      | 40    | 2000  | APROVADA  |
      | 40    | 2001  | APROVADA  |
      | 40    | 5000  | APROVADA  |
      | 40    | 9999  | APROVADA  |
      | 40    | 10000 | APROVADA  |
      | 40    | 10001 | REPROVADA |
      | 64    | 1999  | REPROVADA |
      | 64    | 2000  | APROVADA  |
      | 64    | 2001  | APROVADA  |
      | 64    | 5000  | APROVADA  |
      | 64    | 9999  | APROVADA  |
      | 64    | 10000 | APROVADA  |
      | 64    | 10001 | REPROVADA |
      | 65    | 1999  | REPROVADA |
      | 65    | 2000  | APROVADA  |
      | 65    | 2001  | APROVADA  |
      | 65    | 5000  | APROVADA  |
      | 65    | 9999  | APROVADA  |
      | 65    | 10000 | APROVADA  |
      | 65    | 10001 | REPROVADA |
      | 66    | 1999  | REPROVADA |
      | 66    | 2000  | REPROVADA |
      | 66    | 2001  | REPROVADA |
      | 66    | 5000  | REPROVADA |
      | 66    | 9999  | REPROVADA |
      | 66    | 10000 | REPROVADA |
      | 66    | 10001 | REPROVADA |
