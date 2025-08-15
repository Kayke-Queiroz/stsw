# language: pt
Funcionalidade: Classificação de Triângulos
  Como um usuário do sistema
  Eu quero classificar triângulos baseado em seus lados
  Para que eu possa identificar o tipo de triângulo corretamente

  Contexto:
    Dado que eu tenho um classificador de triângulos

  Cenário: Classificar triângulo equilátero
    Quando eu forneço os lados 5, 5 e 5
    Então o resultado deve ser "Equilátero"

  Cenário: Classificar triângulo isósceles
    Quando eu forneço os lados 5, 5 e 3
    Então o resultado deve ser "Isósceles"

  Cenário: Classificar triângulo escaleno
    Quando eu forneço os lados 5, 4 e 3
    Então o resultado deve ser "Escaleno"

  Cenário: Lados que não formam triângulo
    Quando eu forneço os lados 1, 2 e 3
    Então o resultado deve ser "Não é um triângulo"

  Cenário: Lados inválidos com valores negativos ou zero
    Quando eu forneço os lados -5, 0 e 5
    Então o resultado deve ser "Lados inválidos"

  Esquema do Cenário: Múltiplos casos de teste
    Quando eu forneço os lados <lado1>, <lado2> e <lado3>
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | lado1 | lado2 | lado3 | resultado           |
      | 10    | 10    | 10    | Equilátero          |
      | 8     | 8     | 5     | Isósceles           |
      | 6     | 8     | 10    | Escaleno            |
      | 1     | 1     | 3     | Não é um triângulo  |
      | 0     | 5     | 5     | Lados inválidos     |
      | 200   | 200   | 200   | Equilátero          |
      | 201   | 200   | 200   | Lados inválidos     |
      | 3     | 4     | 5     | Escaleno            |
      | 13    | 13    | 24    | Isósceles           |

  Cenário: Validação de limites
    Quando eu forneço os lados 1, 1 e 1
    Então o resultado deve ser "Equilátero"

  Cenário: Casos extremos
    Quando eu forneço os lados 100, 100 e 199
    Então o resultado deve ser "Isósceles"
