### Instalação manual do Cucumber

* Criar diretório para armazenar as dependências

```bash
mkdir -p lib
```

* Baixar as dependências do Cucumber

```bash
curl -L -o lib/cucumber-java-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-java/7.21.1/cucumber-java-7.21.1.jar
```

```bash
curl -L -o lib/cucumber-core-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-core/7.21.1/cucumber-core-7.21.1.jar
```

```bash
curl -L -o lib/tag-expressions-6.1.2.jar https://repo1.maven.org/maven2/io/cucumber/tag-expressions/6.1.2/tag-expressions-6.1.2.jar
```

```bash
curl -L -o lib/cucumber-gherkin-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-gherkin/7.21.1/cucumber-gherkin-7.21.1.jar
```

```bash
curl -L -o lib/cucumber-plugin-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-plugin/7.21.1/cucumber-plugin-7.21.1.jar
```

```bash
curl -L  -o lib/messages-27.2.0.jar https://repo1.maven.org/maven2/io/cucumber/messages/27.2.0/messages-27.2.0.jar
```
```bash
curl -L  -o lib/cucumber-gherkin-messages-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-gherkin-messages/7.21.1/cucumber-gherkin-messages-7.21.1.jar
```

```bash
curl -L  -o lib/cucumber-expressions-18.0.1.jar https://repo1.maven.org/maven2/io/cucumber/cucumber-expressions/18.0.1/cucumber-expressions-18.0.1.jar
```

```bash
curl -L  -o lib/ci-environment-10.0.1.jar https://repo1.maven.org/maven2/io/cucumber/ci-environment/10.0.1/ci-environment-10.0.1.jar
```

```bash
curl -L  -o lib/gherkin-32.0.0.jar https://repo1.maven.org/maven2/io/cucumber/gherkin/32.0.0/gherkin-32.0.0.jar
```

```bash
curl -L  -o lib/datatable-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/datatable/7.21.1/datatable-7.21.1.jar
```

```bash
curl -L  -o lib/docstring-7.21.1.jar https://repo1.maven.org/maven2/io/cucumber/docstring/7.21.1/docstring-7.21.1.jar
```

```bash
curl -L  -o lib/apiguardian-api-1.1.2.jar https://repo1.maven.org/maven2/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar
```

* Compilar programa e os testes

```bash
javac -cp "lib/*" -d bin src/test/java/steps/*.java src/main/java/app/*.java
```

* Executar os testes

```bash
java -cp "lib/*:bin" io.cucumber.core.cli.Main src/test/resources/features --glue steps
```

```bash 
java -cp "lib/*:bin:src/test/resources/features" io.cucumber.core.cli.Main
```