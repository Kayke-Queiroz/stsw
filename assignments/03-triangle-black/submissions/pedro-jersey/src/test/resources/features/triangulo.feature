# language: pt
Funcionalidade: Classificação de triângulos

  Cenário: Classificação de triângulos usando tabela
    | a   | b   | c   | resultado           |
    | 5   | 5   | 5   | Equilátero          |
    | 5   | 5   | 3   | Isósceles           |
    | 5   | 4   | 3   | Escaleno            |
    | 1   | 2   | 3   | Não é um triângulo  |
    | -5  | 0   | 5   | Lados inválidos     |
    | 200 | 199 | 198 | Escaleno           |
    | 0   | 5   | 5   | Lados inválidos     |
