### Instruções para testes locais das atividades

Cada atividade é acompanhada de testes unitários. Para avaliar seu código antes de submetê-lo, execute os seguintes comandos a partir de seu diretório pessoal de cada atividade

* Criar diretório para armazenar as dependências
 ```bash
 mkdir -p lib
 ```
* Baixar o junit
```bash
 curl -L -o lib/junit-platform-console-standalone-1.11.4.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
```
* Compilar o código do programa e dos testes unitários. O código do teste unitário já é fornecido.
```bash
 javac -cp "lib/*" -d bin src/*.java ../../test/*.java
 ```
 * Executar testes unitários e verificar o resultado no console
 ```bash
 java -jar lib/junit-platform-console-standalone-1.11.4.jar execute --class-path bin --scan-class-path
 ```