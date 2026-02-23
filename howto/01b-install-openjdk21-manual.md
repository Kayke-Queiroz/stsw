### Instalação manual do OpenJDK 21 no Linux

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
