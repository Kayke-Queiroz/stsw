# language: pt
Funcionalidade: Classificação de triângulos
  Para evitar erros em fronteiras
  Como aluno
  Quero validar a classificação do triângulo com BVA

  # BVA de faixa (1..200)
  Esquema do Cenário: Faixa de lados (1..200)
    Quando eu classifico o triângulo com lados <a>, <b> e <c>
    Então o resultado deve ser "<esperado>"

    Exemplos:
      | a   | b   | c   | esperado         |
      | 0   | 1   | 1   | Lados inválidos  | # 0 é fora da faixa
      | -1  | 2   | 3   | Lados inválidos  | # negativo
      | 201 | 10  | 10  | Lados inválidos  | # acima do limite
      | 200 | 200 | 200 | Equilátero       | # no teto válido

  # BVA da desigualdade: x + y > z (degenerado na fronteira =)
  Esquema do Cenário: Existência do triângulo (degenerado vs válido)
    Quando eu classifico o triângulo com lados <a>, <b> e <c>
    Então o resultado deve ser "<esperado>"

    Exemplos:
      | a | b | c | esperado              |
      | 1 | 1 | 2 | Não é um triângulo    | # 1+1=2 (degenerado)
      | 2 | 3 | 5 | Não é um triângulo    | # 2+3=5
      | 2 | 3 | 4 | Escaleno              | # logo após virar válido

  # BVA de classes
  Esquema do Cenário: Transições entre classes
    Quando eu classifico o triângulo com lados <a>, <b> e <c>
    Então o resultado deve ser "<esperado>"

    Exemplos:
      | a  | b  | c  | esperado     |
      | 1  | 1  | 1  | Equilátero   |
      | 3  | 3  | 4  | Isósceles    |
      | 3  | 4  | 5  | Escaleno     |
      | 10 | 10 | 20 | Não é um triângulo | # fronteira 10+10=20

