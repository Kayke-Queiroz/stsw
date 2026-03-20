# language: pt

Funcionalidade: Classificação de Triângulos com BVA
  Como um usuário do sistema
  Quero informar três lados de um triângulo
  Para que o sistema classifique corretamente o tipo do triângulo
  aplicando a técnica de Análise de Valor Limite (BVA)

  Cenário: Triângulo Equilátero com lados iguais
    Dado que os lados são 5, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: BVA - Equilátero com lados no valor mínimo (1)
    Dado que os lados são 1, 1 e 1
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: BVA - Equilátero com lados no valor máximo (200)
    Dado que os lados são 200, 200 e 200
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: Triângulo Isósceles com a igual a b
    Dado que os lados são 5, 5 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Triângulo Isósceles com a igual a c
    Dado que os lados são 5, 3 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Triângulo Isósceles com b igual a c
    Dado que os lados são 3, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Triângulo Escaleno com lados diferentes
    Dado que os lados são 3, 4 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: BVA - Escaleno com lados próximos ao máximo
    Dado que os lados são 198, 199 e 200
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Lados que não formam triângulo
    Dado que os lados são 1, 2 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: BVA - Não forma triângulo com lados degenerados
    Dado que os lados são 1, 1 e 2
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: BVA - Não forma triângulo com diferença extrema
    Dado que os lados são 1, 1 e 200
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: BVA - Lado com valor zero
    Dado que os lados são 0, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"

  Cenário: BVA - Lado com valor negativo
    Dado que os lados são -1, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"

  Cenário: BVA - Lado acima do máximo (201)
    Dado que os lados são 201, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"

  Esquema do Cenário: BVA - Verificação tabular de casos limite
    Dado que os lados são <a>, <b> e <c>
    Quando eu classifico o triângulo
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | a   | b   | c   | resultado           |
      | 1   | 1   | 1   | Equilátero          |
      | 200 | 200 | 200 | Equilátero          |
      | 1   | 1   | 0   | Lados inválidos     |
      | 200 | 200 | 201 | Lados inválidos     |
      | 2   | 2   | 3   | Isósceles           |
      | 3   | 4   | 5   | Escaleno            |
      | 1   | 2   | 3   | Não é um triângulo  |
      | 100 | 100 | 1   | Isósceles           |
      | 199 | 200 | 200 | Isósceles           |
      | 198 | 199 | 200 | Escaleno            |