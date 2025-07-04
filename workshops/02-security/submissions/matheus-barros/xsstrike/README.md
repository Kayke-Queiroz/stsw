# XSStrike
Matheus Antônio de Castro de Barros, 2025.1

## Introdução

### O que é o XSStrike

O XSStrike é uma suíte de detecção de Cross-Site Scripting (XSS) que automatiza a descoberta de vulnerabilidades no lado do cliente. Ele analisa o contexto da reflexão do payload, gera vetores de ataque específicos, identifica e tenta contornar WAFs e pode até mesmo encontrar falhas complexas de DOM XSS.

### Onde ele se encaixa na Pirâmide de Automação

Na pirâmide clássica (Unidade → Integração/Serviço → UI/E2E), o XSStrike, assim como o SQLmap, opera no **terceiro nível**. Ele testa a aplicação já implantada, exercitando a camada HTTP (black-box), portanto fica no mesmo degrau dos testes End-to-End (E2E) ou DAST.

## Principais Funcionalidades
- **Recursos suportados**  
  - Crawling automático para descoberta de parâmetros e pontos de entrada  
  - Fuzzing inteligente de payloads XSS (tamanho, contexto e bypass de filtros)  
  - Detecção de XSS refletido, armazenado e baseado em DOM  
  - Scripts de evasão (*tamper*) para WAF/IPS, suporte a proxy HTTP  
- **Tipos de testes possíveis**  
  - **Caixa-preta**: explora a aplicação apenas via HTTP sem acesso ao código-fonte  
  - **Caixa-cinza**: usando requisições pré-capturadas (Burp/ZAP) ou perfis de autenticação  
  - (*Não há instrumentação para caixa-branca direta*)  
- **Integrações disponíveis**  
  - Importação de requisições via arquivo `-r` (Burp Suite, OWASP ZAP)  
  - Saída em JSON / CSV para parsers externos (JUnit, scripts CI)  
  - Uso em pipelines de CI/CD (GitHub Actions, Jenkins, GitLab CI)  
  - Proxy forward para navegadores/headless (Chrome/Firefox)

## Demonstração

Este guia apresenta um passo a passo direto para instalar o XSStrike e executar um scan de descoberta e exploração em um alvo de teste.

### Pré-requisitos
* Ambiente Linux (Ubuntu, WSL, Kali, etc.).
* Acesso `sudo`.
* `git` e `python3-pip` instalados.

---

### Instalação e Configuração Rápida

**Baixar via Git**

Crie um diretório `tools` e clone o repositório oficial do XSStrike.

```bash
mkdir -p ~/tools
cd ~/tools
git clone --depth 1 [https://github.com/s0md3v/XSStrike.git](https://github.com/s0md3v/XSStrike.git)
```
### Tornar Executável e Criar Link Simbólico
Permita a execução do script e crie um atalho global para o comando **xsstrike**.

```bash
# Navegue até o diretório
cd XSStrike
# Adicione permissão de execução
chmod +x xsstrike.py
# Crie um link simbólico
sudo ln -s $(pwd)/xsstrike.py /usr/local/bin/xsstrike
```
### Instalar Dependências
Instale as dependências necessárias usando `pip`.

```bash
pip3 install -r requirements.txt
``` 
### Testar a Instalação
Verifique se o XSStrike está funcionando corretamente.
```bash
xsstrike --help
```

### Exploração de Laboratório: PortSwigger Reflected XSS

Este guia apresenta o fluxo de trabalho profissional para resolver um laboratório de XSS, combinando análise manual com a automação do XSStrike.

**Alvo:** Um laboratório de "Reflected XSS" da PortSwigger Web Security Academy.

#### Passo 1: Análise Manual e Descoberta do Vetor

O primeiro passo é sempre entender a aplicação manualmente.

1. Abra a URL do laboratório no seu navegador.
2. Use a funcionalidade de busca e digite um valor de teste, como `testando`.
3. Observe a URL resultante. Ela deve se parecer com: `https://<ID_DO_LAB>.web-security-academy.net/?search=testando`
4. Confirme que a palavra `testando` aparece no corpo da página.

Com isso, identificamos o parâmetro `search` como nosso ponto de injeção.

#### Passo 2: Scan Focado com XSStrike

Agora, aponte o XSStrike diretamente para o alvo que descobrimos.

Use aspas simples `' '` para garantir que o terminal não modifique caracteres especiais na URL.

```bash
xsstrike -u 'https://<ID_DO_LAB>.web-security-academy.net/?search=test'
```

#### Passo 3: Análise do Resultado

O XSStrike irá testar diversos vetores e deve retornar um payload bem-sucedido.

**Saída Esperada:**

```
[!] Reflections found: 1
[~] Analysing reflections
[~] Generating payloads
[+] Payload: <hTmL%0aonmouSeoVER%0d=%0dconfirm()%0dx>
...
```

## Lista de Frameworks Similares
- **XSSer** – foco em XSS automático com GUI e opções de proxy  
- **DalFox** – scanner de XSS em Go, geração de payloads e GitHub Action oficial  
- **XSpear** – escrito em Python+JavaScript, ideal para SPAs e APIs modernas  
- **XSS Hunter** – plataforma colaborativa para validação de XSS em ambientes de produção  
- **BeEF** / **Burp Suite Scanner** / **OWASP ZAP** – scanners de segurança web mais amplos que incluem detecção de XSS  


## Vantagens e Desvantagens
| Aspecto                  | Vantagens                                                                 | Desvantagens                                                           |
| ------------------------ | ------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| **Técnico**              | Payloads inteligentes, bypass de WAF, testes DOM, scriptable, proxy-ready | CLI-only, pode ser lento em apps grandes, não há GUI oficial           |
| **Maturidade**           | Ativo desde 2015, comunidade engajada, updates regulares                  | Documentação pontual em alguns módulos, evolução de features irregular |
| **Curva de aprendizado** | Uso básico imediato (`-u URL`)                                            | Flags avançadas (>50 opções) requerem leitura de manual                |
| **Documentação**         | README detalhado, exemplos de uso e demos no GitHub                       | Falta tutorial passo-a-passo para pipelines CI/CD                      |
| **Performance**          | Fuzzing adaptativo, multithreading parcial                                | Scans profundos podem demorar; overhead de crawling em sites grandes   |


##### Conclusão
- **Avaliação crítica:**  
  XSStrike destaca-se por sua capacidade de combinar crawling avançado e fuzzing contextual para XSS, sendo uma ótima opção para pentesters e automação em pipelines de entrega.  
- **Recomendações de uso:**  
  - Adotar quando precisar de DAST focado em XSS ou em bug bounty para acelerar descoberta de payloads em múltiplos contextos.  
  - Não usar como única defesa: complemente com SAST (Semgrep/SonarQube), WAF e validação de entrada no código-fonte.  
  - Evitar em produção sem staging, pois payloads de teste podem gerar logs de erro ou efeitos colaterais em aplicações sensíveis.  
