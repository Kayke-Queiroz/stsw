Feature: Classificação de triângulos usando BVA

  Scenario Outline: Testar classificação com valores limite
    Given que eu tenho lados <a>, <b> e <c>
    When eu classifico o triângulo
    Then o resultado deve ser "<resultado>"

    Examples:
      # Valores inválidos (BVA)
      | a   | b   | c   | resultado             |
      | 0   | 10  | 10  | Lados inválidos       |
      | -1  | 10  | 10  | Lados inválidos       |
      | 201 | 10  | 10  | Lados inválidos       |

      # Não forma triângulo (limite da soma)
      | 1   | 2   | 3   | Não é um triângulo   |
      | 10  | 1   | 1   | Não é um triângulo   |

      # Casos válidos
      | 1   | 1   | 1   | Equilátero           |
      | 2   | 2   | 3   | Isósceles            |
      | 3   | 4   | 5   | Escaleno             |

      # Limite superior
      | 200 | 200 | 200 | Equilátero           |