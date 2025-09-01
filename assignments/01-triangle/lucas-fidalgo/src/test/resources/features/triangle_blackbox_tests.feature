#language: pt
Funcionalidade: Testes Black Box - Classificação de Triângulos
  Como um testador usando técnicas Black Box
  Eu quero validar todos os comportamentos do classificador de triângulos
  Para garantir que atende aos requisitos sem conhecer a implementação

  # ═══════════════════════════════════════════════════════════════════
  # 🟦 PARTICIONAMENTO DE EQUIVALÊNCIA
  # ═══════════════════════════════════════════════════════════════════
  
  Esquema do Cenário: Partições válidas - Triângulos Equiláteros
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 1     | 1     | 1     | # Valor mínimo
      | 10    | 10    | 10    | # Valor médio
      | 100   | 100   | 100   | # Valor alto
      | 999   | 999   | 999   | # Valor muito alto

  Esquema do Cenário: Partições válidas - Triângulos Isósceles
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 2     | 2     | 3     | # Dois primeiros iguais
      | 3     | 2     | 2     | # Dois últimos iguais
      | 2     | 3     | 2     | # Primeiro e terceiro iguais
      | 10    | 10    | 15    | # Valores médios
      | 50    | 30    | 50    | # Valores altos

  Esquema do Cenário: Partições válidas - Triângulos Escalenos
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 3     | 4     | 5     | # Triângulo retângulo clássico
      | 2     | 3     | 4     | # Pequenos consecutivos
      | 5     | 7     | 9     | # Médios não consecutivos
      | 13    | 14    | 15    | # Grandes consecutivos
      | 10    | 15    | 20    | # Proporção 2:3:4

  # ═══════════════════════════════════════════════════════════════════
  # 🔴 PARTIÇÕES INVÁLIDAS - LADOS NEGATIVOS/ZERO
  # ═══════════════════════════════════════════════════════════════════

  Esquema do Cenário: Partições inválidas - Lados não positivos
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Lados inválidos"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 0     | 5     | 5     | # Um lado zero
      | 5     | 0     | 5     | # Segundo lado zero
      | 5     | 5     | 0     | # Terceiro lado zero
      | 0     | 0     | 5     | # Dois lados zero
      | 0     | 0     | 0     | # Todos zero
      | -1    | 5     | 5     | # Um lado negativo
      | 5     | -1    | 5     | # Segundo lado negativo
      | 5     | 5     | -1    | # Terceiro lado negativo
      | -1    | -2    | 5     | # Dois lados negativos
      | -1    | -2    | -3    | # Todos negativos

  # ═══════════════════════════════════════════════════════════════════
  # ⚠️ PARTIÇÕES INVÁLIDAS - DESIGUALDADE TRIANGULAR
  # ═══════════════════════════════════════════════════════════════════

  Esquema do Cenário: Partições inválidas - Não formam triângulo
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 1     | 2     | 5     | # Soma < maior lado
      | 1     | 1     | 3     | # Soma = maior lado
      | 2     | 3     | 7     | # Claramente inválido
      | 10    | 5     | 20    | # Valores médios
      | 1     | 10    | 20    | # Um lado muito pequeno

  # ═══════════════════════════════════════════════════════════════════
  # 📏 ANÁLISE DE VALOR LIMITE (BOUNDARY VALUE ANALYSIS)
  # ═══════════════════════════════════════════════════════════════════

  Cenário: Limite inferior - Triângulo mínimo válido
    Dado que eu tenho os lados 1, 1 e 1
    Quando eu classifico o triângulo
    Então o resultado deve ser "Equilátero"

  Cenário: Limite da desigualdade triangular - Caso limite válido
    Dado que eu tenho os lados 3, 4 e 6
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Limite da desigualdade triangular - Caso limite inválido
    Dado que eu tenho os lados 1, 2 e 3
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: Boundary exato - Soma igual ao maior lado
    Dado que eu tenho os lados 5, 5 e 10
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"

  Cenário: Boundary válido - Soma maior por 1
    Dado que eu tenho os lados 5, 5 e 9
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  # ═══════════════════════════════════════════════════════════════════
  # 🔢 CASOS EXTREMOS E ESPECIAIS
  # ═══════════════════════════════════════════════════════════════════

  Cenário: Valores muito grandes - Teste de overflow
    Dado que eu tenho os lados 2000000000, 1999999999 e 1999999998
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Isósceles com diferença mínima
    Dado que eu tenho os lados 1000, 1000 e 1999
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Escaleno com lados próximos
    Dado que eu tenho os lados 100, 101 e 102
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  # ═══════════════════════════════════════════════════════════════════
  # 🔄 TESTES DE PERMUTAÇÃO (Black Box - diferentes ordens)
  # ═══════════════════════════════════════════════════════════════════

  Esquema do Cenário: Permutações de entrada - Isósceles
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 5     | 5     | 8     | # AA-B
      | 5     | 8     | 5     | # A-B-A
      | 8     | 5     | 5     | # B-AA

  Esquema do Cenário: Permutações de entrada - Não triângulo
    Dado que eu tenho os lados <lado1>, <lado2> e <lado3>
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"
    
    Exemplos:
      | lado1 | lado2 | lado3 | # Comentário
      | 1     | 2     | 4     | # Pequeno-Médio-Grande
      | 2     | 1     | 4     | # Médio-Pequeno-Grande  
      | 4     | 1     | 2     | # Grande-Pequeno-Médio
      | 1     | 4     | 2     | # Pequeno-Grande-Médio
      | 2     | 4     | 1     | # Médio-Grande-Pequeno
      | 4     | 2     | 1     | # Grande-Médio-Pequeno

  # ═══════════════════════════════════════════════════════════════════
  # 🎯 CASOS DE DECISÃO CRÍTICA (Critical Decision Points)
  # ═══════════════════════════════════════════════════════════════════

  Cenário: Decisão crítica - Quase equilátero mas isósceles
    Dado que eu tenho os lados 10, 10 e 11
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Decisão crítica - Quase isósceles mas escaleno
    Dado que eu tenho os lados 10, 11 e 12
    Quando eu classifico o triângulo
    Então o resultado deve ser "Escaleno"

  Cenário: Decisão crítica - Boundary da invalidade
    Dado que eu tenho os lados 10, 10 e 19
    Quando eu classifico o triângulo
    Então o resultado deve ser "Isósceles"

  Cenário: Decisão crítica - Exatamente no limite da invalidade
    Dado que eu tenho os lados 10, 10 e 20
    Quando eu classifico o triângulo
    Então o resultado deve ser "Não é um triângulo"
