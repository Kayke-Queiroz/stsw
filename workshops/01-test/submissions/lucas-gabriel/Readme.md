# 🎯 Cobertura - Testes com Cobertura em Java

Framework Utilizado: Cobertura (versão X.Y.Z)
Alunos: Lucas e Gabriel

## 📝 Introdução
⚙️ Descrição do Framework

O Cobertura é uma ferramenta de cobertura de código open-source para Java, que permite realizar a análise de testes de caixa-branca. 

Lançado em 2005 como sucessora do jcoverage, ele instrumenta o bytecode do Java, semelhante ao que a JDK faz, para medir quais linhas/branches de código foram executadas durante a execução de testes, e gera relatórios de cobertura que podem ser usados para identificar áreas não testadas do sistema.

## 🏰 Posicionamento na Pirâmide de Automação de Testes


O Cobertura não é responsável pela execução dos testes, mas sim por medir a cobertura de código durante os testes unitários executados com frameworks como JUnit. 

Seu posicionamento é focado principalmente no nível de unidade (teste de unidade), mas também em testes de integração com monitoramento de API´s. 

Cobertura auxilia na análise da quantidade de código testado, sendo uma ferramenta crucial para melhorar a qualidade do software ao garantir que o maior número possível de cenários de código seja testado, monitorando também regressões em pipelines de CI.

## 🔧 Principais Funcionalidades

Recursos Suportados:

Geração de relatórios de cobertura de código.

Instrumentação do bytecode para monitorar a execução de cada linha.

Análise de cobertura de testes durante a execução de testes automatizados.

Suporte a relatórios em diversos formatos (HTML, XML, CSV).

Permite filtrar e excluir pacotes ou classes

## 🧠 Tipos de Testes Possíveis:

Caixa-preta: Testa a funcionalidade sem conhecer a implementação interna, desde que execute o código.

Caixa-branca: Foca na cobertura do código interno do sistema, analisando se as estruturas e lógicas de implementação foram corretamente testadas.

O Cobertura se destaca na análise de testes de caixa-branca, ou seja, ele permite visualizar quais partes do código são exercidas pelos testes, possibilitando identificar áreas não testadas.

## 🔄 Integrações Disponíveis:

JUnit: Cobertura é compatível com frameworks de testes de unidade, como o JUnit, para medir a cobertura de testes de forma automática, sobre o bytecode instrumentado.

CI/CD: Pode ser integrado em pipelines de integração contínua, permitindo a análise de cobertura em ambientes automatizados, produzindo relatórios usados em Gitlab, GitHub e Jenkins.

Maven/Gradle/Ant:
Pode ser integrado com frameworks Maven/Gradle/Ant, por meio de plugins inseridos no pom.xml

## 🚀 Demonstração
Exemplo Implementado

No exemplo a seguir, criamos uma classe de testes JUnit para uma simples classe Calculadora, com métodos para operações básicas como soma, subtração, multiplicação, divisão e potência. Durante a execução dos testes, o Cobertura pode ser utilizado para gerar um relatório que indica a cobertura de código para cada operação executada.

Foto:


## 💡 Instruções para Execução do Código

Requisitos:

- Java 8 ou inferior.

- JUnit 4.x para os testes.

- Cobertura 1.9 (ou versão superior).

Passos:

Clone o repositório do código.

```
git clone "repositório"
```

Adicione o Cobertura ao seu projeto (via Maven ou Gradle), com plugin.

Exemplo para Maven:

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.codigo</groupId>
    <artifactId>cobertura-codigo</artifactId>
    <version>1.0-SNAPSHOT</version>

    

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <!-- Plugin do Cobertura -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>cobertura-maven-plugin</artifactId>
                <version>2.7</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>cobertura</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <!-- JUnit para testes -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

```

Execute os testes com o JUnit
```
mvn clean cobertura:cobertura
```

Gere o relatório de cobertura utilizando o Cobertura, presente no target/site/cobertura/index.html.


Após rodar os testes, você poderá visualizar o relatório de cobertura no formato desejado, executando o index.html com a extensão GoLive

## 🔄 Lista de Frameworks Similares

JaCoCo: Outra ferramenta de cobertura de código que oferece relatórios de execução detalhados e é frequentemente usada em projetos Java, com um padrão moderno e uso de Agente on-the-fly.

Emma: Predecessor do Cobertura, que hoje foi descontinuado.

Clover/OpenClover:
Ferramenta comercial de modelo híbrido


## ⚖️ Vantagens e Desvantagens
### Vantagens:

Boa compatibilidade com ferramentas CI

Relatórios flexíveis e legíveis: Suporte a diferentes formatos de relatório, como HTML e XML.

Integração fácil: Facilmente integrável a sistemas de CI/CD.

Sem agente em runtime

### Desvantagens:

Desatualizado: O Cobertura não é tão ativamente mantido quanto o JaCoCo, que tem mais recursos modernos.

Instrumentação offline menos flexível: Para iniciantes, a configuração inicial pode exigir alguma familiaridade com ferramentas de cobertura.

## ✅ Conclusão

O Cobertura é uma boa opção para análise de cobertura de código em testes de unidade em projetos Java. Ele é mais útil quando combinado com frameworks de teste como JUnit. No entanto, devido à sua falta de manutenção ativa, pode ser uma boa ideia considerar alternativas mais modernas, como JaCoCo, especialmente se a integração contínua e relatórios detalhados forem necessários.

### Quando Adotar:

Em projetos legados onde o Cobertura já está em uso.

Para análise de cobertura de código simples em testes de unidade.

### Quando Não Adotar:

Em novos projetos, onde ferramentas mais modernas e ativamente mantidas como JaCoCo são preferíveis.
