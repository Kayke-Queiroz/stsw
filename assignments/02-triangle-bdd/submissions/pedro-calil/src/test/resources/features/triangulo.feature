# language: pt
Funcionalidade: Classificar triângulos
  Como usuário
  Quero informar três lados
  Para saber se é Equilátero, Isósceles, Escaleno ou inválido

  Cenário: Equilátero
    Dado que informo os lados "5", "5" e "5"
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: Isósceles
    Dado que informo os lados "5", "5" e "3"
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Escaleno
    Dado que informo os lados "5", "4" e "3"
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Não forma triângulo (exemplo)
    Dado que informo os lados "1", "2" e "3"
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Esquema do Cenário: BVA - Domínio (1..200 e fora)
    Dado que informo os lados "<a>", "<b>" e "<c>"
    Quando eu classifico o triângulo
    Então o resultado deve ser "<saida>"

    Exemplos:
      | a   | b   | c   | saida               |
      | 1   | 1   | 1   | Equilátero          |
      | 200 | 200 | 200 | Equilátero          |
      | 0   | 1   | 1   | Lados inválidos     |
      | 201 | 2   | 2   | Lados inválidos     |
      | -1  | 50  | 50  | Lados inválidos     |
      | 2.5 | 3   | 4   | Lados inválidos     |

  Esquema do Cenário: BVA - Desigualdade triangular
    Dado que informo os lados "<a>", "<b>" e "<c>"
    Quando eu classifico o triângulo
    Então o resultado deve ser "<saida>"

    Exemplos:
      | a   | b   | c   | saida               |
      | 1   | 1   | 2   | Não é um triângulo  | # a+b=c
      | 1   | 2   | 2   | Isósceles           | # a+b>c (logo acima)
      | 200 | 1   | 1   | Não é um triângulo  |
      | 199 | 2   | 200 | Escaleno            |
      | 2   | 3   | 4   | Escaleno            |
