# P2-11 - OWASP Top Ten: Injection

## 1. Introducao a Injection

Injection ocorre quando dados nao confiaveis, recebidos de usuarios, APIs, cookies, headers, arquivos ou sistemas externos, sao interpretados como comandos por um interpretador. Esse interpretador pode ser um banco SQL, banco NoSQL, shell do sistema operacional, LDAP, mecanismo XPath, template engine ou motor de scripts.

A OWASP mantem Injection no Top 10 porque esse tipo de falha continua comum, facil de introduzir em codigo de aplicacao e potencialmente grave. Um unico ponto vulneravel pode permitir leitura de dados sensiveis, alteracao ou exclusao de registros, bypass de autenticacao, execucao de comandos e comprometimento do servidor.

O ponto central e a mistura entre dado e comando. Por exemplo, o usuario deveria enviar apenas o valor de um campo de busca, mas a aplicacao concatena esse valor diretamente em uma query SQL. Se o valor contiver sintaxe SQL, o banco pode interpretar parte da entrada como comando.

Impactos comuns:

- Exfiltracao de dados, como usuarios, senhas, pedidos e documentos.
- Corrupcao ou exclusao de dados.
- Bypass de login e autorizacao.
- Escalada de privilegios no banco.
- Execucao remota de comandos, em casos de command injection.
- Movimento lateral quando a aplicacao acessa sistemas internos.

Tipos frequentes de injecao:

- SQL Injection.
- NoSQL Injection.
- LDAP Injection.
- OS Command Injection.
- XPath Injection.
- Template Injection.
- Header Injection.
- Expression Language Injection.
- Log Injection.

### Ciclo simples de um ataque de SQL Injection

```text
Atacante envia entrada maliciosa
          |
          v
Aplicacao concatena entrada em uma query
          |
          v
Banco interpreta dado como parte do comando SQL
          |
          v
Consulta retorna dados indevidos ou altera estado
          |
          v
Atacante confirma falha e amplia exploracao
```

## 2. Entendendo SQL Injection

SQL Injection acontece quando a entrada do usuario modifica a semantica da consulta SQL. Uma aplicacao vulneravel monta a query por concatenacao:

```java
String sql = "SELECT id, nome FROM usuarios WHERE email = '" + email + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
```

Se o usuario informar:

```text
' OR 1=1 --
```

a consulta pode virar:

```sql
SELECT id, nome FROM usuarios WHERE email = '' OR 1=1 --'
```

`OR 1=1` torna a condicao sempre verdadeira, e `--` comenta o restante da query. O resultado pode ser a listagem de todos os usuarios ou um bypass de autenticacao.

### Vetores de entrada testaveis

Um testador deve avaliar qualquer dado controlado pelo cliente:

- Parametros GET: `/produto?id=1`.
- Corpo POST: `usuario=admin&senha=123`.
- JSON: `{"id":"1"}`.
- Headers: `User-Agent`, `Referer`, `X-Forwarded-For`.
- Cookies: `session=...`, `perfil=...`.
- Campos ocultos em formularios.
- Parametros de rota: `/usuarios/10`.

### Tipos de SQL Injection

| Tipo | Descricao | Exemplo |
|---|---|---|
| Tautologica | Usa condicao sempre verdadeira. | `' OR '1'='1` |
| Piggy-backed query | Tenta executar uma segunda instrucao. | `1; DROP TABLE usuarios; --` |
| UNION-based | Usa `UNION SELECT` para juntar dados de outra tabela. | `' UNION SELECT null, senha FROM usuarios --` |
| Error-based | Forca erro para revelar informacoes do banco. | Entrada que quebra conversao de tipo. |
| Blind boolean-based | Infere dados por diferenca entre respostas verdadeiras e falsas. | `AND SUBSTR(senha,1,1)='a'` |
| Blind time-based | Infere dados pelo tempo de resposta. | `AND SLEEP(5)` |

## 3. Prevencao com PreparedStatement em Java

`PreparedStatement` separa codigo SQL de valores. A estrutura da consulta e enviada ao banco com marcadores `?`, e os valores sao ligados por bind parameters. Assim, mesmo que o usuario envie aspas, operadores ou comentarios SQL, o banco trata a entrada como valor literal, nao como comando.

Validacao de entrada e parametrizacao nao sao a mesma coisa. Validacao verifica se o dado tem formato aceitavel, como email valido ou ID numerico. Parametrizacao garante que o dado nao sera interpretado como SQL. As duas tecnicas devem ser usadas juntas, mas validacao sozinha nao substitui bind parameters.

ORMs ajudam, mas nao eliminam totalmente o risco. O risco volta quando o desenvolvedor usa query nativa, concatena JPQL/HQL, monta filtros dinamicamente sem parametros ou chama stored procedures inseguras.

### Login vulneravel com Statement

```java
public boolean loginVulneravel(Connection conn, String email, String senha) throws SQLException {
    String sql = "SELECT id FROM usuarios WHERE email = '" + email
        + "' AND senha = '" + senha + "'";

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    return rs.next();
}
```

Ataque:

```text
email: admin@site.com' --
senha: qualquer
```

Query resultante:

```sql
SELECT id FROM usuarios WHERE email = 'admin@site.com' --' AND senha = 'qualquer'
```

A parte da senha e ignorada pelo comentario.

### Login seguro com PreparedStatement

```java
public boolean loginSeguro(Connection conn, String email, String senhaHash) throws SQLException {
    String sql = "SELECT id FROM usuarios WHERE email = ? AND senha_hash = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.setString(2, senhaHash);

        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    }
}
```

Com a mesma entrada maliciosa, o banco procura literalmente o email `admin@site.com' --`. A sintaxe injetada nao altera a query.

## 4. Tecnicas de teste para SQL Injection

Sinais de possivel SQL Injection:

- Mensagens como `SQL syntax error`, `unterminated string`, `ORA-`, `MySQL`, `PostgreSQL`.
- Respostas diferentes quando se adiciona aspas simples.
- Login aceitando payloads como `' OR 1=1 --`.
- Mudanca de quantidade de registros retornados.
- Atraso artificial quando se usa payload de tempo.
- Erro 500 em campos que deveriam retornar erro de validacao.

Payloads de teste:

```text
'
' OR 'a'='a
' OR 1=1 --
' UNION SELECT null, version() --
' AND SLEEP(5) --
```

Exemplo de rota Java vulneravel:

```java
@GetMapping("/usuarios")
public List<Usuario> buscar(@RequestParam String id) throws SQLException {
    String sql = "SELECT id, nome, email FROM usuarios WHERE id = " + id;
    Statement st = dataSource.getConnection().createStatement();
    ResultSet rs = st.executeQuery(sql);
    return mapper(rs);
}
```

Teste manual:

```bash
curl "http://localhost:8080/usuarios?id=1"
curl "http://localhost:8080/usuarios?id=1%20OR%201=1"
curl "http://localhost:8080/usuarios?id=1%20UNION%20SELECT%20null,version(),null"
```

Correcao:

```java
@GetMapping("/usuarios")
public List<Usuario> buscar(@RequestParam long id) throws SQLException {
    String sql = "SELECT id, nome, email FROM usuarios WHERE id = ?";

    try (Connection conn = dataSource.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setLong(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            return mapper(rs);
        }
    }
}
```

## 5. Testes automatizados com SQLMap

SQLMap e uma ferramenta de teste de seguranca para detectar e explorar SQL Injection em endpoints HTTP. Ela automatiza payloads boolean-based, error-based, UNION-based, stacked queries e time-based, comparando respostas, tempos, codigos HTTP e conteudos retornados.

Comando basico:

```bash
sqlmap -u "http://exemplo.com/item?id=1"
```

Explicacao:

- `sqlmap`: executa a ferramenta.
- `-u`: informa a URL alvo.
- `id=1`: parametro que sera testado.

Parametros importantes:

| Parametro | Uso |
|---|---|
| `--batch` | Responde automaticamente perguntas com opcoes padrao. |
| `--risk` | Aumenta agressividade dos testes. |
| `--level` | Aumenta quantidade de parametros e headers testados. |
| `--technique=B` | Testa boolean-based blind. |
| `--technique=T` | Testa time-based blind. |
| `--data` | Envia corpo POST. |
| `--cookie` | Envia cookies autenticados. |
| `--dbs` | Lista bancos encontrados. |
| `--tables` | Lista tabelas de um banco. |
| `--columns` | Lista colunas. |
| `--dump` | Extrai dados. |

Endpoint vulneravel em Spring Boot:

```java
@GetMapping("/produto")
public Produto produto(@RequestParam String id) throws SQLException {
    String sql = "SELECT id, nome, preco FROM produtos WHERE id = " + id;
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    return converter(rs);
}
```

Execucao:

```bash
sqlmap -u "http://localhost:8080/produto?id=2" --batch --risk=3 --level=5
sqlmap -u "http://localhost:8080/produto?id=2" --batch --technique=B
sqlmap -u "http://localhost:8080/produto?id=2" --batch --technique=T
```

Se vulneravel, o relatorio indicaria que o parametro `id` e injetavel, mostrando tipo de tecnica, payload usado e banco identificado. Depois da correcao com `PreparedStatement`, a expectativa e que a ferramenta nao consiga confirmar injecao.

Extracao automatizada em laboratorio controlado:

```bash
sqlmap -u "http://localhost:8080/produto?id=2" --dbs --batch
sqlmap -u "http://localhost:8080/produto?id=2" -D loja --tables --batch
sqlmap -u "http://localhost:8080/produto?id=2" -D loja -T usuarios --columns --batch
sqlmap -u "http://localhost:8080/produto?id=2" -D loja -T usuarios --dump --batch
```

O risco organizacional aumenta muito se a aplicacao usa usuario de banco com privilegios excessivos. Uma falha de leitura pode virar exclusao de tabelas, leitura de dados de outros sistemas ou alteracao de permissoes.

### Protecoes, bypasses e evasao

`--tamper` aplica transformacoes nos payloads para tentar escapar de filtros simples ou WAFs. Exemplo:

```bash
sqlmap -u "http://localhost:8080/produto?id=2" \
  --tamper=between,randomcase \
  --batch
```

Um ataque direto envia payloads tradicionais. Um ataque com evasao altera representacao, capitalizacao, espacos, comentarios ou operadores para tentar passar por filtros mal implementados. Isso mostra por que filtros de string nao sao defesa suficiente: a correcao correta e parametrizacao.

### Testes autenticados

Com cookie:

```bash
sqlmap -u "http://localhost:8080/admin/relatorio?id=10" \
  --cookie="token=eyJhbGciOiJIUzI1NiJ9..." \
  --batch
```

Com POST:

```bash
sqlmap -u "http://localhost:8080/login" \
  --data="usuario=admin&senha=123" \
  --batch
```

O uso deve ser autorizado e restrito ao ambiente de teste, pois a ferramenta pode gerar carga, modificar dados e acessar informacoes sensiveis.

### Integracao segura com CI/CD

Exemplo de rotina controlada:

```bash
#!/usr/bin/env bash
set -euo pipefail

BASE_URL="${BASE_URL:-http://localhost:8080}"
COOKIE="${TEST_COOKIE:-}"

sqlmap -u "$BASE_URL/produto?id=2" \
  --cookie="$COOKIE" \
  --batch \
  --risk=1 \
  --level=2 \
  --flush-session \
  --output-dir=./reports/sqlmap
```

Em pipeline, o ideal e executar contra ambiente efemero com dados ficticios, limitar `risk` e `level`, registrar evidencias, falhar o build se houver injecao confirmada e nunca testar sistemas de terceiros sem autorizacao formal.

## 6. XSStrike e XSS

XSStrike e uma ferramenta focada em detectar Cross-Site Scripting. Ela tenta identificar reflexao de entrada, contexto de saida e payloads adequados para aquele contexto, em vez de apenas enviar uma lista fixa de payloads.

Tipos de XSS:

- Reflected XSS: payload vem na requisicao e volta imediatamente na resposta.
- Stored XSS: payload e persistido no servidor e executado quando outro usuario acessa.
- DOM-Based XSS: JavaScript no cliente processa dados nao confiaveis e escreve no DOM de forma insegura.

Exemplo de execucao:

```bash
python3 xsstrike.py -u "http://localhost:8080/pesquisa?q=teste"
```

Recursos relevantes:

- Fuzzing inteligente de parametros.
- Analise de contexto HTML, atributo e JavaScript.
- Crawling para descobrir URLs.
- Testes de parametros com `--params`.
- Analise de scripts para possivel DOM XSS.
- Fuzzer para payloads mutantes.

### XSS refletido em aplicacao Java simulada

Codigo vulneravel:

```java
@GetMapping("/pesquisa")
@ResponseBody
public String pesquisa(@RequestParam String q) {
    return "<html>Resultado para: " + q + "</html>";
}
```

Teste:

```bash
python3 xsstrike.py -u "http://localhost:8080/pesquisa?q=teste"
```

Se a aplicacao refletir sem encoding, um payload como `<script>alert(1)</script>` pode executar no navegador da vitima. A correcao e fazer output encoding adequado ao contexto, usar templates seguros, Content Security Policy como defesa adicional e evitar inserir HTML bruto com entrada do usuario.

### DOM-Based XSS

Pagina vulneravel:

```html
<script>
  document.write(location.hash);
</script>
```

Teste:

```bash
python3 xsstrike.py -u "http://localhost/teste.html#xss"
```

A falha ocorre porque `location.hash`, controlado pelo usuario, e escrito diretamente no DOM. O correto e usar APIs seguras, como `textContent`, e sanitizacao robusta quando HTML for realmente necessario.

### Crawling e parametros

```bash
python3 xsstrike.py -u "http://localhost:8080" --crawl
python3 xsstrike.py -u "http://localhost:8080" --params
```

`--crawl` navega por links para encontrar novas superficies. `--params` tenta identificar parametros testaveis. Em aplicacoes reais, isso ajuda a descobrir vetores que nao estavam documentados.

### Bypass e uso etico

Payloads polimorficos sao variacoes do payload original para escapar de filtros simples, como alterar capitalizacao, codificacao ou estrutura HTML. Ferramentas como XSStrike podem encontrar bypasses contra validacoes fracas, mas o uso correto e apenas em ambientes autorizados: laboratorios como DVWA, WebGoat, bWAPP, Juice Shop ou sistemas internos com permissao.

## 7. Caso real: TalkTalk

Um caso frequentemente citado de SQL Injection e o incidente da TalkTalk, em que falhas de seguranca em aplicacoes expostas permitiram acesso indevido a dados de clientes. O vetor esteve relacionado a injecao em aplicacao web e ausencia de controles adequados de desenvolvimento seguro.

Falhas organizacionais associadas:

- Falta de revisao de codigo focada em seguranca.
- Ausencia ou insuficiencia de testes automatizados de injecao.
- Falta de SAST/DAST efetivo no ciclo de desenvolvimento.
- Controle inadequado de superficie exposta.
- Possivel excesso de informacoes e privilegios acessiveis a partir da aplicacao.

Medidas recomendadas pela OWASP:

- Usar queries parametrizadas.
- Evitar concatenacao de comandos.
- Validar entrada por allow list.
- Escapar dados apenas como defesa complementar e no contexto correto.
- Limitar privilegios do usuario de banco.
- Tratar erros sem revelar detalhes internos.
- Incluir testes de seguranca em CI/CD.

## 8. Checklist Java contra Injection

| Item | Exemplo ou pratica |
|---|---|
| Usar PreparedStatement | `WHERE email = ?` com `ps.setString(1, email)`. |
| Evitar Statement com concatenacao | Nunca montar SQL com `"... " + input`. |
| Validar entradas | IDs como `long`, emails com formato esperado, enums para filtros. |
| Usar allow list | Ordenacao apenas por colunas permitidas: `nome`, `created_at`. |
| Tratar erros genericamente | Retornar `400` ou `500` sem stack trace SQL. |
| Menor privilegio | Usuario da aplicacao nao deve ter `DROP` se so precisa ler/escrever. |
| Testar headers e cookies | Nao testar apenas parametros GET. |
| Cobrir CI/CD | DAST em ambiente controlado com dados ficticios. |

Validacao simples com regex:

```java
if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
    throw new IllegalArgumentException("Email invalido");
}
```

Tratamento de erro sem expor banco:

```java
try {
    return usuarioRepository.buscarPorEmail(email);
} catch (SQLException ex) {
    logger.error("Erro ao consultar usuario", ex);
    throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Nao foi possivel processar a solicitacao"
    );
}
```

Conclusao: a defesa principal contra Injection e separar dados de comandos. Em Java, isso significa usar `PreparedStatement`, APIs seguras de ORM, validacao por allow list, menor privilegio e testes automatizados. Ferramentas como SQLMap e XSStrike sao uteis para validacao em laboratorio, mas nao substituem codigo seguro desde o projeto.
