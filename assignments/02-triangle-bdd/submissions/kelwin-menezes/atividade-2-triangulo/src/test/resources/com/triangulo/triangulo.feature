# language: pt
Funcionalidade: Classificação de Triângulos com BVA

  Esquema do Cenário: Validar classificação e limites dos lados
    Dado que eu insiro os lados <l1>, <l2> e <l3>
    Quando eu clico em classificar
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | l1  | l2  | l3  | resultado           | CT (Técnica BVA)    |
      | 1   | 1   | 1   | Equilátero          | Limite Mínimo (1)   |
      | 0   | 100 | 100 | Lados inválidos     | Abaixo do Limite (0)|
      | 2   | 2   | 2   | Equilátero          | Mínimo + 1 (2)      |
      | 200 | 200 | 200 | Equilátero          | Limite Máximo (200) |
      | 201 | 100 | 100 | Lados inválidos     | Acima do Limite (201)|
      | 199 | 199 | 199 | Equilátero          | Máximo - 1 (199)    |
      | 10  | 10  | 15  | Isósceles           | Isósceles comum     |
      | 3   | 4   | 5   | Escaleno            | Escaleno comum      |
      | 1   | 2   | 3   | Não é um triângulo  | Triângulo Inválido  |