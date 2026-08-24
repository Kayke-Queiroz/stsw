# Implementação: Boundary Value Analysis em Missões de Drones de Resgate

Esta submissão implementa o estudo de caso proposto em `classroom/04-bva`.

## Regra implementada

A classe `DroneMissionPolicy` autoriza uma missão somente se:

- `30 <= bateria <= 100`
- `0 <= vento <= 40`
- `1 <= pesoCarga <= 8`

Caso qualquer entrada esteja fora desses intervalos, a missão é negada.

## Estrutura

- `src/main/java/.../DroneMissionPolicy.java`: regra de negócio
- `src/main/java/.../DroneMissionDecision.java`: decisões possíveis
- `src/test/java/.../NormalBvaTest.java`: BVA normal, `13` casos
- `src/test/java/.../RobustBvaTest.java`: BVA robusto, `19` casos
- `src/test/java/.../WorstCaseBvaTest.java`: worst-case, `125` casos
- `src/test/java/.../RobustWorstCaseBvaTest.java`: robust worst-case, `343` casos
- `src/test/java/.../BvaTestSupport.java`: geração programática das combinações
- `src/test/resources/features/DroneMission-BVA.feature`: documentação executável em Gherkin
- `src/test/java/.../cucumber/RunCucumberTest.java`: runner Cucumber
- `src/test/java/.../cucumber/DroneMissionSteps.java`: steps da feature

## Quantidade de casos

Como o problema possui `n = 3` variáveis:

- BVA normal: `4n + 1 = 13`
- BVA robusto: `6n + 1 = 19`
- Worst-case: `5^n = 125`
- Robust worst-case: `7^n = 343`

Total esperado: `500` execuções de teste.

Além dos testes unitários completos, há uma suíte BDD com Cucumber para apresentação em aula:

- BVA normal completo: `13` exemplos
- BVA robusto completo: `19` exemplos
- worst-case: casos representativos
- robust worst-case: casos representativos

Os conjuntos combinatórios completos permanecem nos testes JUnit parametrizados, porque `125` e `343` linhas em Gherkin deixariam a feature pouco legível.

## Como executar

```bash
cd classroom/04-bva/submissions/fabricio-santana
mvn test
```

Esse comando executa os testes JUnit e a feature Cucumber.

## Como executar o Cucumber sem JUnit

O runner `RunCucumberWithJunitTest` é executado pelo Maven Surefire quando você roda:

```bash
mvn test
```

Para executar o Cucumber diretamente pela CLI, sem passar pelo JUnit Platform, use:

```bash
cd classroom/04-bva/submissions/fabricio-santana
mvn test-compile exec:java
```

Esse comando usa a classe:

```text
br.edu.idp.stsw.classroom.bva.runners.RunCucumberWithCli
```

A diferença é:

- `mvn test`: executa JUnit e Cucumber via JUnit Platform.
- `mvn test-compile exec:java`: compila as classes de teste e executa `io.cucumber.core.cli.Main` diretamente.

O relatório HTML desse runner CLI fica em:

```text
target/site/cucumber-reports/CucumberCli.html
```

## Como visualizar o relatório local do Cucumber

O relatório HTML do Cucumber fica em:

```text
target/site/cucumber-reports/Cucumber.html
```

Para visualizar com `jwebserver`:

```bash
cd target/site/cucumber-reports
jwebserver -p 8001
```

Depois acesse:

```text
http://localhost:8001/Cucumber.html
```

## Como gerar cobertura

```bash
cd classroom/04-bva/submissions/fabricio-santana
mvn verify
```

O relatório HTML do JaCoCo fica em:

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
