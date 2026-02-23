### Instalação do OpenJDK 21 no Linux via apt

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