# Exemplo: Boundary Value Analysis com Java + Cucumber

Este exemplo demonstra a técnica **Boundary Value Analysis (BVA)** e suas variações:

- **BVA clássico**: usa os pontos `min`, `min+1`, `nominal`, `max-1`, `max`
- **BVA robusto**: adiciona `min-1` e `max+1`
- **Worst-case**: combina todos os pontos clássicos entre múltiplas variáveis
- **Robust worst-case**: combina todos os pontos robustos entre múltiplas variáveis

## Regra de negócio usada no exemplo

Uma solicitação de crédito é **aprovada** somente se:

- `idade` estiver entre **18** e **65** (inclusive)
- `renda` estiver entre **2000** e **10000** (inclusive)

## Estratégia de teste adotada

Em vez de validar a quantidade de testes gerados, este exemplo valida diretamente
o **resultado da regra de negócio** em valores de fronteira.

Cobrimos:

- Fronteiras de `idade` com `renda` nominal
- Fronteiras de `renda` com `idade` nominal
- Combinações representativas para **worst-case**
- Combinações com valores fora da faixa para **robust worst-case**

## Estrutura

- `src/main/java/.../domain/CreditPolicy.java`: regra de negócio
- `src/test/resources/features/boundary-value-analysis.feature`: cenários BDD
- `src/test/java/.../steps/BoundaryValueSteps.java`: execução da regra e validação do resultado esperado

## Como executar

```bash
cd lectures/examples/04-bva
mvn test
```

## Objetivo

Mostrar que a técnica BVA não é só "testar min e max", mas sim validar decisões:

1. O **clássico** valida fronteiras inclusivas.
2. O **robusto** valida rejeição imediata fora da faixa.
3. O **worst-case** exercita combinações de fronteiras em múltiplas variáveis.
4. O **robust worst-case** reforça comportamento com combinações inválidas.
