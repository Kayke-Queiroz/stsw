# language: pt
Funcionalidade: Identificar tipo de triângulo com BVA
  Como um analista de qualidade
  Eu quero validar a classificação de triângulos usando Análise de Valores Limite
  Para garantir que o domínio 1–200 e todas as classes de equivalência estão corretos

  # ===========================================================================
  # ANÁLISE DE CLASSES DE EQUIVALÊNCIA
  # ---------------------------------------------------------------------------
  # CE1 - Entrada inválida: qualquer lado <= 0 ou > 200  → "Invalido"
  # CE2 - Não é triângulo: um lado >= soma dos outros dois → "Nao e um triangulo"
  # CE3 - Equilátero: a == b == c (e todos válidos)       → "Equilatero"
  # CE4 - Isósceles: exatamente dois lados iguais         → "Isosceles"
  # CE5 - Escaleno: todos os lados diferentes             → "Escaleno"
  #
  # ANÁLISE DE VALORES LIMITE (domínio 1–200)
  # ---------------------------------------------------------------------------
  # Limites inferiores do domínio : 0 (abaixo), 1 (mín), 2 (mín+1)
  # Limites superiores do domínio : 199 (máx-1), 200 (máx), 201 (acima)
  # Limite da desigualdade triangular: lado = soma dos outros (fronteira exata)
  # ===========================================================================

  # ---------------------------------------------------------------------------
  # CE1 — ENTRADAS INVÁLIDAS (valores fora do domínio 1–200)
  # ---------------------------------------------------------------------------

  Cenário: Lado zero é inválido (limite inferior abaixo do domínio)
    Dado que os lados informados são 0, 5 e 5
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Lado negativo é inválido (abaixo do limite inferior)
    Dado que os lados informados são -1, 5 e 5
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Lado 201 é inválido (acima do limite superior)
    Dado que os lados informados são 201, 100 e 100
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Todos os lados iguais a zero são inválidos
    Dado que os lados informados são 0, 0 e 0
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Segundo lado zero é inválido
    Dado que os lados informados são 5, 0 e 5
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Terceiro lado zero é inválido
    Dado que os lados informados são 5, 5 e 0
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Segundo lado 201 é inválido
    Dado que os lados informados são 100, 201 e 100
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  Cenário: Terceiro lado 201 é inválido
    Dado que os lados informados são 100, 100 e 201
    Quando eu executo a identificação
    Então o sistema retorna "Invalido"

  # ---------------------------------------------------------------------------
  # CE2 — NÃO É TRIÂNGULO (desigualdade triangular violada)
  # ---------------------------------------------------------------------------

  Cenário: Lado igual à soma dos outros dois não forma triângulo (fronteira exata)
    Dado que os lados informados são 1, 2 e 3
    Quando eu executo a identificação
    Então o sistema retorna "Nao e um triangulo"

  Cenário: Lado maior que a soma dos outros dois não forma triângulo
    Dado que os lados informados são 1, 1 e 3
    Quando eu executo a identificação
    Então o sistema retorna "Nao e um triangulo"

  Cenário: Primeiro lado domina (100 >= 1 + 2)
    Dado que os lados informados são 100, 1 e 2
    Quando eu executo a identificação
    Então o sistema retorna "Nao e um triangulo"

  Cenário: Limite superior — a = b+c exato (200 = 100+100)
    Dado que os lados informados são 200, 100 e 100
    Quando eu executo a identificação
    Então o sistema retorna "Nao e um triangulo"

  Cenário: Limite mínimo do domínio — dois lados 1 e terceiro 2 (1+1=2, fronteira)
    Dado que os lados informados são 1, 1 e 2
    Quando eu executo a identificação
    Então o sistema retorna "Nao e um triangulo"

  # ---------------------------------------------------------------------------
  # CE3 — EQUILÁTERO (a == b == c, todos válidos)
  # ---------------------------------------------------------------------------

  Cenário: Equilátero com valor mínimo do domínio (BVA limite inferior)
    Dado que os lados informados são 1, 1 e 1
    Quando eu executo a identificação
    Então o sistema retorna "Equilatero"

  Cenário: Equilátero com valor mínimo+1 (BVA próximo ao limite inferior)
    Dado que os lados informados são 2, 2 e 2
    Quando eu executo a identificação
    Então o sistema retorna "Equilatero"

  Cenário: Equilátero com valor nominal intermediário
    Dado que os lados informados são 100, 100 e 100
    Quando eu executo a identificação
    Então o sistema retorna "Equilatero"

  Cenário: Equilátero com valor máximo-1 do domínio (BVA próximo ao limite superior)
    Dado que os lados informados são 199, 199 e 199
    Quando eu executo a identificação
    Então o sistema retorna "Equilatero"

  Cenário: Equilátero com valor máximo do domínio (BVA limite superior)
    Dado que os lados informados são 200, 200 e 200
    Quando eu executo a identificação
    Então o sistema retorna "Equilatero"

  # ---------------------------------------------------------------------------
  # CE4 — ISÓSCELES (exatamente dois lados iguais)
  # ---------------------------------------------------------------------------

  Cenário: Isósceles com primeiro e segundo lados iguais, próximos ao mínimo
    Dado que os lados informados são 2, 2 e 1
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com primeiro e terceiro lados iguais, próximos ao mínimo
    Dado que os lados informados são 2, 1 e 2
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com segundo e terceiro lados iguais, próximos ao mínimo
    Dado que os lados informados são 1, 2 e 2
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com lados próximos ao limite superior (199 e 200)
    Dado que os lados informados são 200, 200 e 199
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com lados no limite superior (200) e base mínima (1)
    Dado que os lados informados são 200, 200 e 1
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com 100 e 100, base 1 (próximo ao limite da desigualdade)
    Dado que os lados informados são 100, 100 e 1
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  Cenário: Isósceles com 100 e 100, base 199 (próximo ao limite superior de base)
    Dado que os lados informados são 100, 100 e 199
    Quando eu executo a identificação
    Então o sistema retorna "Isosceles"

  # ---------------------------------------------------------------------------
  # CE5 — ESCALENO (todos os lados diferentes)
  # ---------------------------------------------------------------------------

  Cenário: Escaleno clássico com valores pequenos
    Dado que os lados informados são 3, 4 e 5
    Quando eu executo a identificação
    Então o sistema retorna "Escaleno"

  Cenário: Escaleno próximo ao limite superior do domínio
    Dado que os lados informados são 198, 199 e 200
    Quando eu executo a identificação
    Então o sistema retorna "Escaleno"

  Cenário: Escaleno com lados mínimo+1, mínimo+2, mínimo+3 (próximo ao limite inferior)
    Dado que os lados informados são 2, 3 e 4
    Quando eu executo a identificação
    Então o sistema retorna "Escaleno"

  Cenário: Escaleno com valores no limite da desigualdade (base = soma - 1)
    Dado que os lados informados são 100, 101 e 198
    Quando eu executo a identificação
    Então o sistema retorna "Escaleno"

  # ---------------------------------------------------------------------------
  # TABELA DE CENÁRIOS BVA — cobrindo limites de forma compacta
  # ---------------------------------------------------------------------------

  Esquema do Cenário: BVA — variações sistemáticas nos limites do domínio
    Dado que os lados informados são <a>, <b> e <c>
    Quando eu executo a identificação
    Então o sistema retorna "<resultado>"

    Exemplos:
      | a   | b   | c   | resultado          |
      # --- Limite inferior: a=0 (inválido) ---
      | 0   | 1   | 1   | Invalido           |
      # --- Limite inferior: a=1 (válido mínimo) ---
      | 1   | 1   | 1   | Equilatero         |
      # --- Limite inferior: a=2 (mínimo+1) ---
      | 2   | 2   | 2   | Equilatero         |
      # --- Limite superior: a=199 (máximo-1) ---
      | 199 | 199 | 199 | Equilatero         |
      # --- Limite superior: a=200 (máximo) ---
      | 200 | 200 | 200 | Equilatero         |
      # --- Limite superior: a=201 (acima do máximo, inválido) ---
      | 201 | 200 | 200 | Invalido           |
      # --- Fronteira da desigualdade: c = a+b (exato, não triângulo) ---
      | 100 | 100 | 200 | Nao e um triangulo |
      # --- Dentro da desigualdade: c = a+b-1 (isósceles) ---
      | 100 | 100 | 199 | Isosceles          |
      # --- Escaleno nos limites ---
      | 1   | 2   | 2   | Isosceles          |
      | 199 | 200 | 198 | Escaleno           |
