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

## Casos gerados

Para `idade` (1 variável):

- Clássico: **5 casos**
- Robusto: **7 casos**

Para `idade + renda` (2 variáveis):

- Worst-case: **5² = 25 casos**
- Robust worst-case: **7² = 49 casos**

## Estrutura

- `src/main/java/.../domain/CreditPolicy.java`: regra de negócio
- `src/test/resources/features/boundary-value-analysis.feature`: cenários BDD
- `src/test/java/.../steps/BoundaryValueSteps.java`: geração e validação dos casos

## Como executar

```bash
cd lectures/examples/05-boundary-value-analysis-cucumber
mvn test
```

## Objetivo

Mostrar que a técnica BVA não é só "testar min e max":

1. O **clássico** cobre fronteiras internas e externas imediatas.
2. O **robusto** força valores inválidos logo fora da faixa.
3. O **worst-case** evidencia o crescimento combinatório com múltiplas entradas.
4. O **robust worst-case** amplia ainda mais a cobertura para combinações inválidas.
