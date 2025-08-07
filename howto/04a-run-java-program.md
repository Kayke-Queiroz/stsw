### Como compilar, empacotar, decompilar e executar programa java na linha de comando

Execute os comandos abaixo para compilar, empacotar, decompilar e executar programa java na linha de comando 

* Compilar o código do programa e dos testes unitários. O código do teste unitário já é fornecido.
```bash
javac -cp "lib/*" -d bin src/*.java test/*.java
```
* Executar programa java
```bash
java -cp bin/ HelloWorld
```
* Executar testes unitários e verificar o resultado no console
```bash
java -jar lib/junit-platform-console-standalone-1.11.4.jar execute --class-path target --scan-class-path
```
* Criar um jar com o programa
```bash
jar --create --file bin/HelloWorld.jar --main-class HelloWorld -C bin/ HelloWorld.class
```
* Executar programa java por meio do jar
```bash
java -jar bin/HelloWorld.jar
```
* Decompilar classe Java
```bash
javap -cp bin/ -c HelloWorld
```