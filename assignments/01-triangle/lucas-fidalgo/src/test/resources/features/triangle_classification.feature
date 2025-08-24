#language: pt
Funcionalidade: Classificação de Triângulos
  Como um usuário
  Eu quero classificar triângulos pelos seus lados
  Para que eu possa identificar se é equilátero, isósceles ou escaleno

  Cenário: Triângulo Equilátero
    Dado que eu tenho os lados 5, 5 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: Triângulo Isósceles com dois lados iguais
    Dado que eu tenho os lados 5, 5 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Triângulo Escaleno
    Dado que eu tenho os lados 3, 4 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Lados inválidos com valores negativos
    Dado que eu tenho os lados -1, 5 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"

  Cenário: Lados inválidos com zero
    Dado que eu tenho os lados 0, 5 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"

  Cenário: Não forma triângulo - soma menor
    Dado que eu tenho os lados 1, 2 e 5
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: Triângulo com números grandes (teste de overflow)
    Dado que eu tenho os lados 2000000000, 1500000000 e 1000000000
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Esquema do Cenário: Múltiplos casos de teste
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | lado1 | lado2 | lado3 | resultado           |
      | 3     | 3     | 3     | Equilátero          |
      | 10    | 10    | 5     | Isósceles           |
      | 6     | 8     | 10    | Escaleno            |
      | 1     | 1     | 3     | Não é um triângulo  |
      | -5    | 10    | 10    | Lados inválidos     |