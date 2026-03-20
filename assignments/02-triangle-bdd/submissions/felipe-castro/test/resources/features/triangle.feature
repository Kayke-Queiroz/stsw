# language: pt
Funcionalidade: Classificação de triângulos

  Cenário: Triangulo equilatero
    Dado que eu informo os lados 5, 5 e 5
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Equilatero"

  Cenário: Triangulo isosceles
    Dado que eu informo os lados 5, 5 e 3
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Isosceles"

  Cenário: Triangulo escaleno
    Dado que eu informo os lados 5, 4 e 3
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Escaleno"

  Cenário: Nao forma triangulo
    Dado que eu informo os lados 1, 2 e 3
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Nao eh um triangulo"

  Cenário: Lados invalidos
    Dado que eu informo os lados -5, 0 e 5
    Quando o sistema classifica o triangulo
    Então o resultado deve ser "Lados invalidos"