
Nesta página você encontra
1. [Lista de atividades](#lista-de-atividades)
1. [Instruções gerais para entrega das atividades](#instruções-gerais-para-entrega-das-atividades)
1. [Instruções para testes locais das atividades](#instruções-para-testes-locais-das-atividades)
1. [Como compilar, empacotar, decompilar e executar programa java na linha de comando</summary>](#como-compilar-empacotar-decompilar-e-executar-programa-java-na-linha-de-comando)

### Lista de atividades

| # | Atividades |
|---|---|
| 0 | [Hello, World!](./00-hello) |
| 1 | [Implementar programa do Triângulo](./01-triangle) |
| 2 | [Implementar casos de testes em Cucumber e Gherkin](./02-triangle-bdd) |
| 3 | [Implementar casos de testes com métodos blackbox](./03-triangle-black/) |
| 4 | [Implementar casos de testes com métodos whitebox](./04-triangle-white/) |
| 5 | [Implementar estudo de caso Test Pyramid](./05-pyramid/) |
| 6 | [Resolver laboratórios de injection](./06-injection/) |
| 7 | [Resolver laboratórios de ssrf](./07-ssrf//) |
| 8 | [Resolver laboratórios de identification and authentication e Broken Access Control](./08-access//) |

### Instruções gerais para entrega das atividades

* Realizar fork do repositório da disciplina
```bash
gh repo fork fabriciosantana/poo
```
* Clonar seu repositório que você acabou de clonar
```bash
git clone https://github.com/<seu-usuario>/stsw.git
```
* Adicionar o repositório original como remoto
```bash
git remote add upstream https://github.com/fabriciosantana/stsw.git
```
* Verificar a configuração dos repositórios remotos
```bash
git remote -v
```
* Atualizar fork para evitar conflitos
```bash
git fetch upstream
git checkout 2025.1
git merge upstream/2025.1
```

```bash
gh repo sync
```
* Criar um diretório com seu nome e sobrenome dentro do diretório da atividade, conforme exemplo abaixo(o nome do último diretório deve ser seu nome e sobrenome):
```bash
mkdir poo/assignments/00-hello/submissions/fabricio-santana/src  
```
* Desenvolver programa Java dentro do seu diretório atendendo os requisitos da especificação e os requisitos de implementação
* Comitar alterações em seu repositório
```bash
git add .   
git commit -m "minha solução da tarefa"
git push
```
* Enviar um pull request
```bash
gh pr create --base 2025.1 --head seu-usuario:2025.1 --title "Minha tarefa XXX" --body "Descrição das alterações realizadas."
```
* Observar se os testes do pull request rodaram com sucesso
* Submeter link do pull request no [ambiente virtual](https://ambientevirtual.idp.edu.br/)
* Cumprir prazo de entrega

### Instruções para testes locais das atividades

Cada atividade é acompanhada de testes unitários. Para avaliar seu código antes de submetê-lo, execute os seguintes comandos a partir de seu diretório pessoal de cada atividade
 ```bash
 mkdir -p lib

 curl -L -o lib/junit-platform-console-standalone-1.11.4.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar

 javac -cp "lib/*" -d bin src/*.java ../../test/*.java
 
 java -jar lib/junit-platform-console-standalone-1.11.4.jar --class-path bin --scan-class-path
 ```

### Como compilar, empacotar, decompilar e executar programa java na linha de comando</summary>

Execute os comandos abaixo para compilar, empacotar, decompilar e executar programa java na linha de comando 

```bash
javac -cp "lib/*" -d bin src/*.java test/*.java

java -cp bin/ HelloWorld

java -jar lib/junit-platform-console-standalone-1.11.4.jar execute --class-path target --scan-class-path

jar --create --file bin/HelloWorld.jar --main-class HelloWorld -C bin/ HelloWorld.class

java -jar bin/HelloWorld.jar

javap -cp bin/ -c HelloWorld
```