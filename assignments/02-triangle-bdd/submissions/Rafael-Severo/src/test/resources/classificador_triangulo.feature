# language: pt
Funcionalidade: Classificação de Triângulos
  Como um usuário do sistema de matemática
  Eu quero classificar triângulos com base no tamanho dos seus lados
  Para saber se é Equilátero, Isósceles, Escaleno, ou inválido

  Esquema do Cenário: Classificar diferentes combinações de lados
    Dado que eu informo os lados do triângulo como <lado_a>, <lado_b> e <lado_c>
    Quando eu peço para classificar
    Então o resultado retornado deve ser "<resultado_esperado>"

    Exemplos:
      | lado_a | lado_b | lado_c | resultado_esperado   |
      # Triângulos Válidos
      | 5      | 5      | 5      | Equilátero           |
      | 5      | 5      | 3      | Isósceles            |
      | 5      | 3      | 5      | Isósceles            |
      | 3      | 5      | 5      | Isósceles            |
      | 3      | 4      | 5      | Escaleno             |
      # Regra de Formação (Soma de dois lados deve ser maior que o terceiro)
      | 1      | 2      | 3      | Não é um triângulo   |
      | 10     | 2      | 2      | Não é um triângulo   |
      # Limites de Valores (1 a 200)
      | 0      | 5      | 5      | Lados inválidos      |
      | -5     | 5      | 5      | Lados inválidos      |
      | 201    | 5      | 5      | Lados inválidos      |
      | 200    | 200    | 200    | Equilátero           |
      | 1      | 1      | 1      | Equilátero           |
