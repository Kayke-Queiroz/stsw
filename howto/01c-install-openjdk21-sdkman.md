### Instalação do OpenJDK 21 via SDKMAN

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
