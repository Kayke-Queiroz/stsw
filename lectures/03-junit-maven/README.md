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
cd lectures/03-junit-maven
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
cd lectures/03-junit-maven
mvn test
```

O Maven executa os testes da classe `CalculatorTest` usando o `maven-surefire-plugin`.

## Como visualizar a cobertura com JaCoCo

O projeto usa o plugin JaCoCo para coletar a cobertura durante os testes.

Para gerar o relatorio HTML:

```bash
cd lectures/03-junit-maven
mvn verify
```

Abra o arquivo abaixo no navegador:

```text
target/site/jacoco/index.html
```

Se estiver em um ambiente remoto, como Codespaces ou container, suba um servidor HTTP simples com o `jwebserver`, disponivel no JDK:

```bash
cd target/site/jacoco
jwebserver -p 8000
```

Depois acesse no navegador:

```text
http://localhost:8000
```

O arquivo `target/jacoco.exec` tambem e gerado, mas ele e um arquivo binario de execucao do JaCoCo. Para visualizar a cobertura, use o relatorio HTML em `target/site/jacoco/index.html`.
