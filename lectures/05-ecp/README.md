# Equivalence Class Partitioning com Java + Cucumber

Este exemplo demonstra a técnica **Equivalence Class Partitioning (ECP)**, ou **particionamento em classes de equivalência**.

A ideia central é dividir o domínio de entrada em grupos que tendem a produzir o mesmo comportamento. Em vez de testar todos os valores possíveis, escolhemos representantes de cada classe válida e inválida.

## Regra de negócio usada no exemplo

O exemplo modela uma política de matrícula em uma disciplina avançada.

Uma solicitação pode resultar em:

- `MATRICULA_CONFIRMADA`: estudante elegível, pagamento aceito e vaga disponível
- `LISTA_DE_ESPERA`: estudante elegível e pagamento aceito, mas turma sem vagas
- `RECUSADA`: dados válidos, mas a solicitação não atende a uma regra de negócio
- `DADOS_INVALIDOS`: alguma entrada está fora do domínio aceito pelo sistema

## Entradas avaliadas

- `idade`
- `nota` obtida no pré-requisito
- `vagas` disponíveis na turma
- `pagamento`

## Classes de equivalência

### Idade

- Inválida: menor de idade (`idade < 18`)
- Válida: adulto (`18 <= idade <= 59`)
- Válida: pessoa sênior (`60 <= idade <= 120`)
- Inválida: idade fora do limite real (`idade > 120`)

### Nota do pré-requisito

- Inválida: abaixo da escala (`nota < 0`)
- Válida: insuficiente (`0 <= nota < 70`)
- Válida: suficiente (`70 <= nota <= 100`)
- Inválida: acima da escala (`nota > 100`)

### Vagas

- Inválida: quantidade negativa (`vagas < 0`)
- Válida: turma cheia (`vagas == 0`)
- Válida: turma com vagas (`vagas > 0`)

### Pagamento

- Válida: pagamento confirmado (`PAGO`)
- Válida: bolsa aprovada (`BOLSA`)
- Válida: pagamento pendente (`PENDENTE`)
- Válida: pagamento cancelado (`CANCELADO`)
- Inválida: código desconhecido

## Estratégia de teste adotada

A feature escolhe representantes das classes equivalentes de cada entrada, mantendo as demais entradas em valores nominais válidos. Ao final, inclui combinações representativas entre classes válidas para mostrar decisões de negócio diferentes.

Esse desenho é proposital: em ECP, o objetivo não é testar todos os valores de cada intervalo, mas selecionar valores que representem grupos equivalentes.

## Estrutura

- `src/main/java/.../domain/EnrollmentPolicy.java`: regra de negócio
- `src/main/java/.../domain/EnrollmentDecision.java`: decisões possíveis
- `src/main/java/.../domain/PaymentStatus.java`: situações de pagamento
- `src/main/java/.../EnrollmentPolicyApp.java`: aplicação console
- `src/test/resources/features/EnrollmentPolicy-ECP.feature`: cenários BDD
- `src/test/java/.../steps/EnrollmentPolicySteps.java`: execução da regra e validação do resultado esperado

## Como executar

### Testes automatizados

```bash
cd lectures/05-ecp
mvn test
```

### Relatório HTML local do Cucumber

O runner do Cucumber gera um relatório HTML local em:

```text
target/site/cucumber-reports/Cucumber.html
```

Para gerar o relatório:

```bash
cd lectures/05-ecp
mvn test
```

Sirva o diretório do relatório com o `jwebserver`:

```bash
cd target/site/cucumber-reports
jwebserver -p 8000
```

Depois acesse no navegador:

```text
http://localhost:8000/Cucumber.html
```

### Aplicação console

Execute informando os valores como argumentos:

```bash
cd lectures/05-ecp
mvn compile exec:java -Dexec.args="35 85 3 PAGO"
```

Ou execute sem argumentos para digitar os valores no console:

```bash
cd lectures/05-ecp
mvn compile exec:java
```

## Objetivo

Mostrar que ECP ajuda a selecionar testes de forma econômica e defensável:

1. Identificamos classes válidas e inválidas para cada entrada.
2. Escolhemos representantes de cada classe.
3. Mantemos as demais entradas em valores nominais quando queremos isolar uma partição.
4. Adicionamos combinações relevantes para cobrir decisões de negócio.
