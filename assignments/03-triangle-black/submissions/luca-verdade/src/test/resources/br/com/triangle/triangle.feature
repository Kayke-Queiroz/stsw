Feature: Classificação de triângulos
  Para verificar se o triângulo é classificado corretamente
  Como usuário do sistema
  Quero que o programa classifique triângulos com base nos lados informados

  Scenario Outline: Classificar triângulos
    Given os lados são <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

  Examples:
    | a | b | c | resultado          |
    # Domínio: 0 e negativo na borda
    | 0 | 1 | 1 | Não é um triângulo |
    | -1| 2 | 3 | Não é um triângulo |

    # Desigualdade na borda (igualdade) e mínimo válido (+1)
    | 1 | 2 | 3 | Não é um triângulo |  # a+b=c (borda inválida)
    | 2 | 3 | 4 | Escaleno            |  # a+b=c+1 (mínimo válido)

    # Classes mínimo válidas
    | 1 | 1 | 1 | Equilátero          |
    | 2 | 2 | 3 | Isósceles           |

