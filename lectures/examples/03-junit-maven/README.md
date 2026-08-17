# Exemplo: JUnit com Maven

Este exemplo mostra uma calculadora didatica (`Calculator`) e testes unitarios com JUnit 5.

## Requisitos

- Java 21
- Maven

## Estrutura

- `src/main/java/br/edu/idp/stsw/unittest/Calculator.java`: aplicacao principal e operacoes da calculadora
- `src/test/java/br/edu/idp/stsw/unittest/CalculatorTest.java`: testes unitarios com recursos do JUnit Jupiter
- `pom.xml`: configuracao do Maven, JUnit, Surefire e JaCoCo

## Recursos de JUnit demonstrados

- Ciclo de vida com `@BeforeAll`, `@BeforeEach` e `@AfterEach`
- Nomes legiveis com `@DisplayName`
- Organizacao por contexto com `@Nested`
- Assercoes simples e agrupadas com `assertEquals`, `assertTrue`, `assertFalse` e `assertAll`
- Validacao de excecoes com `assertThrows` e `assertDoesNotThrow`
- Testes parametrizados com `@ValueSource`, `@CsvSource` e `@MethodSource`
- Testes repetidos com `@RepeatedTest`
- Testes dinamicos com `@TestFactory`
- Limite de tempo com `assertTimeout`
- Marcacao de testes com `@Tag`

## Como rodar a aplicacao

Entre no diretorio do exemplo:

```bash
cd lectures/examples/03-junit-maven
```

Execute a aplicacao com Maven:

```bash
mvn compile exec:java
```

Tambem e possivel gerar o jar e executar com Java:

```bash
mvn package
java -jar target/hello-junit-maven-1.0-SNAPSHOT.jar
```

## Como rodar os testes unitarios

```bash
cd lectures/examples/03-junit-maven
mvn test
```

O Maven executa os testes da classe `CalculatorTest` usando o `maven-surefire-plugin`.

## Relatorio de cobertura

O projeto usa o plugin JaCoCo para preparar a coleta de cobertura durante os testes.
Depois de executar `mvn test`, o arquivo de execucao da cobertura fica em:

```text
target/jacoco.exec
```
