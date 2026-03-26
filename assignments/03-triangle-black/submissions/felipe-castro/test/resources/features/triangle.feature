# language: pt
Funcionalidade: Classificação de triângulos com técnicas de caixa-preta (BVA)

  # ==================================================================
  # Classe de equivalência: Lados inválidos (fora do domínio 1–200)
  # ==================================================================

  Esquema do Cenário: Lados fora do domínio resultam em lados inválidos
    Dado que eu informo os lados <a>, <b> e <c>
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | a   | b   | c   | resultado       |
      | 0   | 5   | 5   | Lados invalidos |
      | -1  | 5   | 5   | Lados invalidos |
      | 201 | 5   | 5   | Lados invalidos |
      | 5   | 0   | 5   | Lados invalidos |
      | 5   | -1  | 5   | Lados invalidos |
      | 5   | 201 | 5   | Lados invalidos |
      | 5   | 5   | 0   | Lados invalidos |
      | 5   | 5   | -1  | Lados invalidos |
      | 5   | 5   | 201 | Lados invalidos |

  # ==================================================================
  # BVA: Limite inferior do domínio (lado = 1)
  # ==================================================================

  Cenário: Lado abaixo do mínimo (0) é inválido
    Dado que eu informo os lados 0, 1 e 1
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Lados invalidos"

  Cenário: Lado no mínimo do domínio (1) é válido e forma triângulo equilátero
    Dado que eu informo os lados 1, 1 e 1
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

  Cenário: Lado logo acima do mínimo (2) é válido e forma triângulo equilátero
    Dado que eu informo os lados 2, 2 e 2
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

  # ==================================================================
  # BVA: Limite superior do domínio (lado = 200)
  # ==================================================================

  Cenário: Lado logo abaixo do máximo (199) é válido e forma triângulo equilátero
    Dado que eu informo os lados 199, 199 e 199
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

  Cenário: Lado no máximo do domínio (200) é válido e forma triângulo equilátero
    Dado que eu informo os lados 200, 200 e 200
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

  Cenário: Lado acima do máximo (201) é inválido
    Dado que eu informo os lados 201, 200 e 200
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Lados invalidos"

  # ==================================================================
  # BVA: Limite da desigualdade triangular (com lados no domínio)
  # ==================================================================

  Cenário: Soma de dois lados menor que o terceiro (claramente não forma triângulo)
    Dado que eu informo os lados 1, 1 e 3
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Nao eh um triangulo"

  Cenário: Soma de dois lados exatamente igual ao terceiro (fronteira - não forma triângulo)
    Dado que eu informo os lados 1, 1 e 2
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Nao eh um triangulo"

  Cenário: Soma de dois lados maior que o terceiro em um (fronteira - forma triângulo isósceles)
    Dado que eu informo os lados 1, 2 e 2
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Isosceles"

  # ==================================================================
  # Classes de equivalência: Triângulos válidos
  # ==================================================================

  Esquema do Cenário: Triângulos equiláteros com lados nos extremos do domínio
    Dado que eu informo os lados <a>, <b> e <c>
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

    Exemplos:
      | a   | b   | c   |
      | 5   | 5   | 5   |
      | 100 | 100 | 100 |

  Esquema do Cenário: Triângulos isósceles com dois lados iguais em diferentes posições
    Dado que eu informo os lados <a>, <b> e <c>
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Isosceles"

    Exemplos:
      | a   | b   | c   |
      | 5   | 5   | 3   |
      | 3   | 5   | 5   |
      | 3   | 3   | 5   |
      | 199 | 200 | 200 |

  Esquema do Cenário: Triângulos escalenos com lados todos diferentes
    Dado que eu informo os lados <a>, <b> e <c>
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Escaleno"

    Exemplos:
      | a   | b   | c   |
      | 3   | 4   | 5   |
      | 5   | 12  | 13  |
      | 198 | 199 | 200 |
