# Estudo de Caso: Boundary Value Analysis em Missões de Drones de Resgate

## Objetivo

Neste estudo de caso, a turma deve aplicar **Boundary Value Analysis (BVA)** para projetar casos de teste a partir de regras de negócio com intervalos numéricos.

Você deve justificar a escolha dos casos de teste para cada um dos métodos:

- BVA normal
- BVA robusto
- Worst-case
- Robust worst-case

Ao final, cada grupo deve ser capaz de explicar por que escolheu cada valor de teste e qual risco aquele valor ajuda a revelar.

## Cenário

Uma Defesa Civil municipal está implantando um sistema para autorizar missões de drones em áreas de risco.

Os drones são usados para entregar kits de primeiros socorros, água, rádio comunicador e sensores temporários em regiões isoladas após enchentes ou deslizamentos.

Antes de liberar uma missão, o sistema precisa avaliar se a operação é segura. A autorização depende de três variáveis numéricas:

- nível de bateria do drone
- velocidade do vento na região
- peso da carga transportada

Como a decisão pode afetar uma operação real de resgate, erros próximos aos limites são críticos.

## Regra de negócio

Uma missão é **AUTORIZADA** somente se todas as condições abaixo forem verdadeiras:

1. `bateria` deve estar entre `30` e `100` por cento, inclusive.
2. `vento` deve estar entre `0` e `40` km/h, inclusive.
3. `pesoCarga` deve estar entre `1` e `8` kg, inclusive.

Caso qualquer entrada esteja fora desses limites, a missão deve ser **NEGADA**.

## Valores nominais sugeridos

Use os seguintes valores nominais quando uma variável não estiver sendo analisada:

- `bateria = 70`
- `vento = 20`
- `pesoCarga = 4`

## O que desenvolver

Crie um projeto Maven no diretório `classroom/bva` com esta estrutura mínima:

```text
classroom/bva
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── ...
    └── test
        └── java
            └── ...
```

## Parte 1: Programa Java

Desenvolva uma classe de domínio, por exemplo:

- `DroneMissionPolicy`

Essa classe deve conter um método público semelhante a:

```java
String evaluate(int bateria, int vento, int pesoCarga)
```

O método deve retornar:

- `AUTORIZADA`
- `NEGADA`

Você pode usar `enum` em vez de `String`, se preferir.

## Parte 2: Casos de teste com BVA

Monte os testes em JUnit 5, separando as técnicas em classes ou métodos diferentes.

Sugestão de organização:

- `NormalBvaTest`
- `RobustBvaTest`
- `WorstCaseBvaTest`
- `RobustWorstCaseBvaTest`

## Como derivar os casos

### 1. BVA normal

Para cada variável, use os valores:

```text
min, min + 1, nominal, max - 1, max
```

Como há `n = 3` variáveis, o BVA normal com suposição de falha única deve produzir:

```text
4n + 1 = 13 casos
```

Perguntas para orientar:

- Quais são os quatro valores de fronteira da bateria?
- Quais são os quatro valores de fronteira do vento?
- Quais são os quatro valores de fronteira do peso da carga?
- Qual é o caso nominal único?

### 2. BVA robusto

Para cada variável, use os valores:

```text
min - 1, min, min + 1, nominal, max - 1, max, max + 1
```

Como há `n = 3` variáveis, o BVA robusto com suposição de falha única deve produzir:

```text
6n + 1 = 19 casos
```

Perguntas para orientar:

- Quais casos devem ser negados por estarem logo abaixo do limite?
- Quais casos devem ser negados por estarem logo acima do limite?
- O sistema trata limites inclusivos corretamente?

### 3. Worst-case

Combine todos os valores válidos de fronteira das três variáveis:

```text
5^n = 5^3 = 125 casos
```

Use, para cada variável:

```text
min, min + 1, nominal, max - 1, max
```

Perguntas para orientar:

- Todas as combinações continuam autorizadas?
- Alguma combinação válida foi negada por engano?
- O número de testes ainda é manejável?

### 4. Robust worst-case

Combine todos os valores válidos e inválidos de fronteira das três variáveis:

```text
7^n = 7^3 = 343 casos
```

Use, para cada variável:

```text
min - 1, min, min + 1, nominal, max - 1, max, max + 1
```

Perguntas para orientar:

- Quais combinações devem ser autorizadas?
- Quais combinações devem ser negadas?
- É viável escrever todos os casos manualmente?
- Quando faz sentido gerar dados de teste programaticamente?

## Tabela de apoio

| Variável | Mínimo | Nominal | Máximo | Valores robustos |
|---|---:|---:|---:|---|
| `bateria` | `30` | `70` | `100` | `29, 30, 31, 70, 99, 100, 101` |
| `vento` | `0` | `20` | `40` | `-1, 0, 1, 20, 39, 40, 41` |
| `pesoCarga` | `1` | `4` | `8` | `0, 1, 2, 4, 7, 8, 9` |

## Requisitos técnicos

O projeto deve usar:

- Java 21
- Maven
- JUnit 5
- JaCoCo

No `pom.xml`, configure:

- `maven-compiler-plugin`
- `maven-surefire-plugin`
- `jacoco-maven-plugin`

## Como executar

### Rodar todos os testes

```bash
cd classroom/bva
mvn test
```

### Gerar relatório de cobertura

```bash
cd classroom/bva
mvn verify
```

O relatório HTML do JaCoCo deve ficar em:

```text
target/site/jacoco/index.html
```

Para visualizar com `jwebserver`:

```bash
cd target/site/jacoco
jwebserver -p 8000
```

Depois acesse:

```text
http://localhost:8000
```

## Entrega esperada em sala

Ao final da atividade, cada grupo deve entregar:

- implementação da regra de negócio
- testes para BVA normal
- testes para BVA robusto
- testes para worst-case
- testes para robust worst-case
- relatório JaCoCo gerado
- uma breve explicação da estratégia usada

## Perguntas para discussão

1. Por que os valores `min`, `min + 1`, `max - 1` e `max` são mais importantes que valores aleatórios do meio do intervalo?
2. Qual é a diferença prática entre BVA normal e BVA robusto?
3. O que muda quando saímos de suposição de falha única para worst-case?
4. O conjunto robust worst-case com `343` casos deve ser escrito manualmente?
5. Como automatizar a geração dos casos sem perder clareza didática?
6. Se a regra tivesse mais duas variáveis, o que aconteceria com a quantidade de testes?
