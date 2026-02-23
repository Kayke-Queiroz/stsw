### Instalar o VS Code via apt

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
