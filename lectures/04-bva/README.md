# Boundary Value Analysis com Java + Cucumber

Este exemplo demonstra a técnica **Boundary Value Analysis (BVA)** e suas variações:

- **BVA normal**: usa suposição de falha única e valores válidos (`min`, `min+1`, `nominal`, `max-1`, `max`)
- **BVA robusto**: usa suposição de falha única e valores válidos e inválidos, ou seja, adiciona `min-1` e `max+1`
- **Worst-case**: usa suposição de falha múltipla e valores válidos
- **Robust worst-case**: usa suposição de falha múltipla e valores válidos e inválidos

## Regra de negócio usada no exemplo

Uma solicitação de crédito é **aprovada** somente se:

- `idade` estiver entre **18** e **65** (inclusive)
- `renda` estiver entre **2000** e **10000** (inclusive)

## Estratégia de teste adotada

Este exemplo valida diretamente o **resultado da regra de negócio** em valores de fronteira.

Como existem duas variáveis de entrada (`idade` e `renda`), os conjuntos de teste esperados são:

- **BVA normal**: `4n + 1 = 9` casos
- **BVA robusto**: `6n + 1 = 13` casos
- **Worst-case**: `5^n = 25` casos
- **Robust worst-case**: `7^n = 49` casos

Total: **96 casos** executados pelo Cucumber.

## Estrutura

- `src/main/java/.../domain/CreditPolicy.java`: regra de negócio
- `src/main/java/.../CreditPolicyApp.java`: aplicação console que usa a regra de negócio
- `src/test/resources/features/boundary-value-analysis.feature`: cenários BDD
- `src/test/java/.../steps/BoundaryValueSteps.java`: execução da regra e validação do resultado esperado

## Como executar

### Testes automatizados

```bash
cd lectures/04-bva
mvn test
```

### Aplicação console

Execute informando os valores como argumentos:

```bash
cd lectures/04-bva
mvn compile exec:java -Dexec.mainClass=br.edu.idp.es.stsw.bva.CreditPolicyApp -Dexec.args="40 5000"
```

Ou execute sem argumentos para digitar os valores no console:

```bash
cd lectures/04-bva
mvn compile exec:java -Dexec.mainClass=br.edu.idp.es.stsw.bva.CreditPolicyApp
```

## Objetivo

Mostrar que a técnica BVA não é só "testar min e max", mas sim validar decisões:

1. O **normal** valida fronteiras no espaço de entrada válida.
2. O **robusto** valida fronteiras no espaço de entrada válida e inválida.
3. O **worst-case** exercita combinações de fronteiras em múltiplas variáveis.
4. O **robust worst-case** reforça comportamento com combinações inválidas.
