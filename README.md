## Segurança e Teste de Software

Seja bem-vindo(a)! 🤗

Este repositório reúne materiais, tarefas, projetos, leituras e referências bibliográficas da disciplina **Segurança e Teste de Software**, oferecida no 6º semestre da graduação em Engenharia de Software do **[Instituto Brasileiro de Ensino, Desenvolvimento e Pesquisa](http://idp.edu.br)** e ministrada pelo professor **[Fabricio Santana](https://github.com/fabriciosantana/)** no **2º semestre de 2025**. 👨🏾‍🏫

Vamos começar! 🎉

### 📚 Referências importantes
- [Plano de ensino](https://1drv.ms/w/s!Avnn2LcOmn0Y3l_dUnQKwrevbU8h?e=0YuCb2)
- [Aulas](./lectures)
- [Leituras](./readings)
- [Atividades](./assignments)
- [Projetos](./projects)

### 🏁 Antes de começar

Para executar os códigos de exemplo, realizar as atividades práticas e desenvolver o projeto, é essencial ter um computador e configurá-lo com as ferramentas apropriadas.

A escolha do **[computador](https://en.wikipedia.org/wiki/Computer)** e do **[sistema operacional](https://en.wikipedia.org/wiki/Operating_system)** pode impactar significativamente sua experiência no desenvolvimento de softwares.

Para programar em **[Java](https://en.wikipedia.org/wiki/Java_(programming_language))**, não é necessário um computador de alto desempenho, mas recomenda-se um processador moderno, com pelo menos **16 GB de RAM** e um SSD para garantir melhor desempenho na compilação e execução dos programas. 

Quanto ao sistema operacional, o **[Linux](https://en.wikipedia.org/wiki/Linux)** é amplamente utilizado por desenvolvedores devido à sua compatibilidade com ferramentas open-source e ambientes de desenvolvimento, mas o **[Windows](https://en.wikipedia.org/wiki/Microsoft_Windows)** e **[macOS](https://en.wikipedia.org/wiki/MacOS)** também oferecem suporte completo para Java. 

Independentemente do sistema operacional escolhido, seu código poderá ser executado em qualquer outro sistema operacional que possua um ambiente Java devidamente configurado com a **[Java Virtual Machine](https://en.wikipedia.org/wiki/Java_virtual_machine)** geralmente por meio do **[Java Development Kit (JDK)](https://en.wikipedia.org/wiki/Java_Development_Kit)**. Isso ocorre porque o **[Java é uma linguagem multiplataforma](https://en.wikipedia.org/wiki/Cross-platform_software)**, permitindo que programas escritos em Java sejam executados sem modificações em diferentes sistemas operacionais. 

Caso esteja utilizando o Windows, uma alternativa prática para desenvolver em um ambiente Linux é o **[Windows Subsystem for Linux (WSL)](https://en.wikipedia.org/wiki/Windows_Subsystem_for_Linux)**, que permite rodar distribuições Linux diretamente no Windows com desempenho quase nativo. Outra opção é utilizar um **container de desenvolvimento na nuvem**, como o **[GitHub Codespaces](https://docs.github.com/codespaces/overview)**, garantindo um ambiente isolado, portátil e fácil de configurar, sem a necessidade de instalar todas as dependências no sistema operacional principal.

Além de um computador e um sistema operacional, para desenvolvier software em Java, é necessário instalar uma distribuição do **[Java Development Kit (JDK)](https://en.wikipedia.org/wiki/Java_Development_Kit)**. Há várias distribuições do JDK disponíveis, este respositório utiliza **[OpenJDK](https://en.wikipedia.org/wiki/OpenJDK)**, versão 21.

Embora seja possível desenvolver programas em Java utilizando qualquer **[editor de texto](https://en.wikipedia.org/wiki/Text_editor)** e um **[terminal](https://en.wikipedia.org/wiki/Terminal_emulator)**, recomenda-se o uso de um **[Ambiente de Desenvolvimento Integrado (Integrated Development Environment - IDE)](https://en.wikipedia.org/wiki/Integrated_development_environment)**. As IDEs tornam o desenvolvimento mais eficiente ao oferecer funcionalidades como edição avançada de código, compilação, testes, depuração, geração de pactoes e uma interface gráfica intuitiva.

Existem diversas IDEs para programação em Java no mercado, todas compatíveis com o código deste repositório. No entanto, recomenda-se a utilização do **[Visual Studio Code (VS Code)](https://code.visualstudio.com/)** devido à sua simplicidade, flexibilidade, extensibilidade e possibilidade de execução online, sem necessidade de instalação. Além disso, o VS Code oferece integração nativa com o **[GitHub Codespaces](https://github.com/features/codespaces)**, tornando o ambiente de desenvolvimento ainda mais acessível e eficiente.

Desenvolver software é um processo contínuo que exige colaboração e organização, frequentemente envolvendo vários desenvolvedores ao longo do tempo. Para gerenciar versões do código, facilitar o trabalho em equipe e manter um histórico estruturado das alterações, este repositório adota o **[Git](https://git-scm.com/)**, um sistema de controle de versão distribuído amplamente utilizado. O Git permite acompanhar mudanças no código, trabalhar em diferentes ramificações e sincronizar o projeto com repositórios remotos, como o **[GitHub](https://github.com/)**.

Em síntese, para aproveitar todo o conteúdo e praticar muito você precisa de no mínimo:
* Um computador com o sistema operacional de sua escolha, recomenda-se utilizar Linux (Ubuntu), Windows com WSL ou o Github Codespaces
* Instalar uma das distrições do Java Development Kit (JDK), recomenda-se o OpenJDK 21
* Instalar uma IDE, recomenda-se o VS Code com os plugins para Java e Git

Finalizada as devidas introduções, vamos seguir para a instalação das ferramentas com os passos abaixo:
* [Instalação do OpenJDK 21 no Linux via apt](#-instalação-do-openjdk-21-no-linux-via-apt)
    * (opcional) [Instalação manual do OpenJDK 21 no Linux](#-instalação-manual-do-openjdk-21-no-linux)
    * (opcional) [Instalação do OpenJDK 21 no Linux via SDKMAN!](#-instalação-do-openjdk-21-via-sdkman)
* [Instalação do VS Code no Linux via apt](#️-instalar-o-vs-code-via-apt)
    * (opcional) [Instalação manual do VS Code no Linux](#️-instalação-manual-do-vs-code-no-linux)
* Instalação dos seguintes plugins no VS Code
    * Java Platform Extension for Visual Code
    * Extension Pack for Java
    * GitHub
    * GitHub Copilot
    * GitHub Copilot Chat

Se tudo isso é muito novo para você, invista um tempo em fortalecer sua base de conhecimento com os seguintes recursos:
* [Introduction to Linux](https://training.linuxfoundation.org/training/introduction-to-linux/)
* [Getting started with Visual Studio Code](https://code.visualstudio.com/docs/introvideos/basics)
* [Intro to GitHub](https://education.github.com/experiences/intro_to_github)
* [GitHub Foundations (Microsoft)](https://education.github.com/experiences/intro_to_github)

### 🛠️ Configuração do ambiente

<details>
<summary><h4>🐧 Instalação do OpenJDK 21 no Linux via apt</h4></summary>

Executar os seguintes comandos no terminal para instalar o OpenJDK 21 no Linux:

1. **Atualizar pacotes**

```bash
sudo apt update && sudo apt upgrade -y
```

2. **Instalar o OpenJDK 21**

```bash
sudo apt install -y openjdk-21-jdk
```

3. **Verificar a instalação**

```bash
java -version
```
4. **Confirmar a instalação**

```bash
openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)
```

5. **Configuarar o OpenJDK 21 como padrão**

Esse passo é necessário apenas caso tenha várias versões do Java instaladas

```bash
sudo update-alternatives --config java
```

```bash
sudo update-alternatives --config javac
```

</details>

<details>
<summary><h4>🐧 Instalação manual do OpenJDK 21 no Linux</h4></summary>

Executar os seguintes comandos no terminal para instalar o OpenJDK 21 no Linux:

1. **Baixar o OpenJDK**

Baixar executando o comando abaixo no terminal ou fazer download da versão 21 em https://jdk.java.net/archive/

```bash
wget https://download.java.net/java/GA/jdk21.0.2/f2283984656d49d69e91c558476027ac/13/GPL/openjdk-21.0.2_linux-x64_bin.tar.gz
```

2. **Extrair o arquivo baixado e mova para o diretório /opt/**

```bash
tar -xvzf openjdk-21.0.2_linux-x64_bin.tar.gz
sudo mv jdk-21.0.2 /opt/
```

3. **Configurar variáveis de ambiente**

```bash
echo "export JAVA_HOME=/opt/jdk-21.0.2" >> ~/.bashrc
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
```

4. **Verificar a instalação**

```bash
java -version
```

5. **Confirmar a instalação**

```bash
openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)
```

6. **Configuarar o OpenJDK 21 como padrão**

Esse passo é necessário apenas caso tenha várias versões do Java instaladas

```bash
sudo update-alternatives --config java
```

```bash
sudo update-alternatives --config javac
```

</details>


<details>
<summary><h4>🐧 Instalação do OpenJDK 21 via SDKMAN</h4></summary>

O [SDKMAN!](https://sdkman.io/) é uma ferramenta que facilita a instalação e o gerenciamento de múltiplas versões do JDK no Linux e macOS. Com ele, você pode instalar, atualizar e alternar entre diferentes versões do Java facilmente.

1. **Instalar o SDKMAN!**

```bash
curl -s "https://get.sdkman.io" | bash
```

2. **Ativar o SDKMAN!**

```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

3. **Verificar instalação**

```bash
sdk version
```

4. **Confirmar instalação**

```bash
SDKMAN 5.19.0
```

5. **Instalar o OpenJDK 21**

```bash
sdk install java 21.0.2-open
```

6. **Verificar a instalação do OpenJDK 21**

```bash
java -version
```

7. **Confirmar a instalação do OpenJDK 21**

```bash
openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)
```

Comando úteis do SDKMAN!

```bash
sdk update

sdk list java

sdk current java
```

</details>


<details>

<summary><h4>📦 Instalar o VS Code via apt</h4></summary>

1. **Atualizar pacotes**

```bash
sudo apt update && sudo apt upgrade -y
```

2. **Baixar o VS Code e adicionar a chave do GPG da Microsoft**

```bash
wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor | sudo tee /usr/share/keyrings/packages.microsoft.gpg > /dev/null
```

3. **Adicionar o repositório do VS Code

```bash
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/packages.microsoft.gpg] https://packages.microsoft.com/repos/code stable main" | sudo tee /etc/apt/sources.list.d/vscode.list
```

4. **Instalar o VS Code**

```bash
sudo apt update
sudo apt install -y code
```

5. **Verificar instalação**

```bash
code --version
```

6. **Confirmar instalação**

```bash
1.96.4
cd4ee3b1c348a13bafd8f9ad8060705f6d4b9cba
x64
```

</details>

<details>
<summary><h4>🖥️ Instalação manual do VS Code no Linux</h4></summary>

1. **Baixar o pacote .deb**

O pacote está disponível no link https://code.visualstudio.com/download

2. **Instalar o pacote**

```bash
sudo dpkg -i code_*.deb
```

3. **Verificar instalação**

```bash
code --version
```

4. **Confirmar instalação**

```bash
1.96.4
cd4ee3b1c348a13bafd8f9ad8060705f6d4b9cba
x64
```

</details>


### Instalação do Cucumber

```bash
mkdir -p lib
```

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

```bash
javac -cp "lib/*" -d bin src/test/java/steps/*.java src/main/java/app/*.java
```

```bash
java -cp "lib/*:bin" io.cucumber.core.cli.Main src/test/resources/features --glue steps
```

```bash 
java -cp "lib/*:bin:src/test/resources/features" io.cucumber.core.cli.Main
```

> 🎉 **Parabéns!** Você concluiu a configuração do ambiente com sucesso! Agora está pronto para começar a programar. 🚀


