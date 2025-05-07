## Vulnerabilidade Injection (A03:2021)

Referências

* [OWASP Top 10 – A03:2021 Injection](https://owasp.org/Top10/A03_2021-Injection/)
* [OWASP Web Security Testing Guide – Testing for SQL Injection](https://owasp.org/www-project-web-security-testing-guide/latest/4-Web_Application_Security_Testing/07-Input_Validation_Testing/05-Testing_for_SQL_Injection)
* [sqlmap](https://sqlmap.org)
* [XSStrike](https://github.com/s0md3v/XSStrike)

### Introdução à Vulnerabilidade Injection (A03:2021)
```
Explique o que é uma vulnerabilidade do tipo Injection, segundo a OWASP.
- Por que ela ainda ocupa um lugar no Top 10 de riscos de segurança em 2021?
- O que significa dizer que dados não confiáveis são interpretados como comandos?
- Quais os impactos mais comuns: exfiltração de dados, corrupção, controle remoto?

- Liste os tipos de injeção descritos pela OWASP: SQL, NoSQL, LDAP, OS command, etc.
- Ilustre o ciclo de um ataque de SQL Injection com um diagrama simples.
```

### Entendendo a Injeção SQL
```
Descreva o funcionamento de uma injeção SQL com base nas diretrizes do Web Security Testing Guide.
- Como a manipulação da entrada do usuário afeta a consulta SQL?
- Quais os principais vetores de entrada testáveis (GET, POST, headers, cookies)?
- O que são ataques tautológicos, piggy-backed queries, blind e time-based?

- Dê um exemplo de consulta SQL construída por concatenação com `Statement` em Java.
- Explique o que aconteceria com a entrada: `' OR 1=1 --`
```

### Prevenção com PreparedStatement em Java
```
Explique o uso de PreparedStatement como defesa primária contra SQL Injection.
- Por que o uso de PreparedStatement com bind parameters evita a injeção?
- Qual a diferença entre validação de entrada e parametrização da consulta?
- O uso de ORMs elimina totalmente o risco de injeção?

- Reescreva uma função Java de login usando PreparedStatement.
- Simule um ataque de injeção em uma versão com `Statement` e outra com `PreparedStatement`.
```

### Técnicas de Teste para SQL Injection
```
Aplique as técnicas do Web Security Testing Guide para identificar SQL Injection.
- Quais sinais revelam uma falha de injeção (ex: mensagens de erro, comportamento anômalo)?
- Como usar comandos tautológicos, UNION SELECT e condições de tempo (SLEEP)?
- Quais ferramentas podem ser usadas para automatizar esses testes?

- Implemente uma rota Java simulando uma aplicação vulnerável (ex: buscar usuário por ID).
- Teste entradas como `' OR 'a'='a`, `' UNION SELECT null, version() --` e observe o resultado.
- Corrija a aplicação usando `PreparedStatement` e valide novamente.
```

### Testando com SQLMap
```
Utilize a ferramenta SQLMap para identificar e explorar uma injeção SQL.
- Como o SQLMap realiza detecção automática?
- O que são técnicas boolean-based, error-based e time-based?
- Quais parâmetros você pode passar para especificar URL, dados POST e cookies?

- Crie uma aplicação Java com endpoint vulnerável a SQLi.
- Execute SQLMap com diferentes técnicas (-–technique=B, -–technique=T) e analise os resultados.
- Corrija a vulnerabilidade e demonstre que o SQLMap não consegue mais explorá-la.
```

### Casos Reais e Impactos
```
Apresente um caso real de ataque por SQL Injection e as consequências.
- Como o atacante explorou a falha?
- Quais medidas organizacionais estavam ausentes (ex: revisão de código, testes, SAST)?
- Como a OWASP recomenda prevenir este tipo de ataque?

- Escolha um caso como o da TalkTalk ou Heartland.
- Faça um mapeamento do vetor de ataque, resposta da empresa e impacto reputacional.
```

### Boas Práticas e Checklist OWASP
```
Com base nas recomendações da OWASP, elabore um checklist para prevenir injeções.
- Use sempre Prepared Statements ou ORMs seguros?
- Valide e sanitize entradas?
- Limite privilégios no banco de dados e o retorno de mensagens de erro?

- Monte um checklist Java com exemplos de:
  - uso de PreparedStatement
  - validação com Regex ou `commons-validator`
  - tratamento de erros sem expor a estrutura do banco
```
### SQLMap – Testes Automatizados de SQL Injection
```
Explore a ferramenta SQLMap, conforme a documentação oficial disponível em https://sqlmap.org.
- O que é o SQLMap e para que ele serve?
- Quais são os principais tipos de testes de injeção suportados pela ferramenta?
- O que diferencia o SQLMap de outras ferramentas de análise de segurança?

- Execute o comando `sqlmap -u "http://exemplo.com/item?id=1"` e explique cada parte.
- Liste os parâmetros mais importantes para ataques automatizados (--risk, --level, --batch).
```

### Detecção de Vulnerabilidades com SQLMap
```
Utilize o SQLMap para detectar vulnerabilidades em um endpoint Java vulnerável.
- Como o SQLMap realiza testes de boolean-based, error-based e time-based injection?
- Quais sinais ele analisa nas respostas HTTP para confirmar a falha?
- O que significa ajustar os níveis de risco (--risk) e profundidade (--level)?

- Implemente um endpoint simples em Java com Spring Boot e JDBC vulnerável.
- Execute: `sqlmap -u "http://localhost:8080/produto?id=2" --batch --risk=3 --level=5`
- Analise o relatório gerado e corrija o código.
```

### Extração de Dados com SQLMap
```
Aprenda a extrair informações do banco de dados de forma automatizada com SQLMap.
- Como listar bancos (`--dbs`), tabelas (`--tables`) e colunas (`--columns`)?
- Como extrair dados reais com `--dump`?
- Quais os riscos organizacionais se a aplicação exposta tiver privilégios excessivos?

- Aponte um endpoint vulnerável em sua aplicação de teste.
- Execute: 
  - `sqlmap -u "<URL>" --dbs`
  - `sqlmap -u "<URL>" -D banco --tables`
  - `sqlmap -u "<URL>" -D banco -T tabela --dump`
```

### Proteções, Bypasses e Evasão
```
Analise as opções de evasão e bypass de proteções implementadas em firewalls e WAFs.
- O que faz a opção `--tamper`?
- Quais técnicas podem ser usadas para contornar filtros simples de input validation?
- Qual a diferença entre um ataque direto e um ataque com evasão de filtros?

- Aplique o parâmetro `--tamper=between,randomcase` em um endpoint com input filtrado.
- Compare a resposta do servidor com e sem o uso de evasão.
```
### Autenticação, Cookies e Sessões

```
Use o SQLMap em aplicações que exigem autenticação.
- Como enviar cookies e cabeçalhos personalizados com SQLMap?
- O que o parâmetro `--cookie` permite?
- Como realizar testes autenticados com formulários (`--data` e `--cookie`)?

- Simule um login com cookie JWT e execute:
  - `sqlmap -u "<URL>" --cookie="token=..." --batch`
- Ou um POST com dados:
  - `sqlmap -u "<URL>" --data="usuario=admin&senha=123" --batch`
```

### Integração com Desenvolvimento Seguro
```
Como o SQLMap pode ser incorporado ao ciclo de desenvolvimento seguro.
- Como a ferramenta pode ser usada em testes de segurança automatizados?
- É possível integrá-la a pipelines de CI/CD com alertas?
- Quais os cuidados éticos e legais ao usar SQLMap?

- Proponha uma rotina segura de varredura automatizada com SQLMap para endpoints internos de uma aplicação Java.
- Esboce um shell script de exemplo para execução periódica da ferramenta.
```

### Introdução ao XSStrike
```
Explore o que é a ferramenta XSStrike e seu propósito na segurança de aplicações web.
- O que é o XSStrike e que tipo de vulnerabilidade ele detecta?
- Qual a diferença entre XSStrike e outras ferramentas de detecção de XSS, como o XSSer ou Burp Scanner?
- Quais os tipos de XSS que o XSStrike pode detectar (Reflected, Stored, DOM-Based)?

- Instale o XSStrike localmente e execute o comando `python3 xsstrike.py -u "http://example.com?q=teste"`.
- Explique os principais recursos disponíveis no menu interativo da ferramenta.
```

### Funcionamento Interno e Heurísticas Inteligentes
```
Entenda como o XSStrike analisa e testa parâmetros de entrada.
- Como o XSStrike realiza fuzzing inteligente usando heurísticas?
- O que é o scan de payloads baseados em contexto (context-aware)?
- Como o mecanismo de análise de JavaScript embutido ajuda na detecção de DOM XSS?

- Execute o XSStrike em uma aplicação de teste com campo GET e analise os resultados.
- Use as opções `--params` e `--crawl` para identificar todos os vetores de injeção possíveis.
```

### Testando XSS Refletido com XSStrike
```
Utilize o XSStrike para detectar XSS refletido em uma aplicação Java simulada.
- O que caracteriza um XSS refletido?
- Quais os efeitos práticos de uma injeção bem-sucedida nesse tipo?
- Como o XSStrike automatiza a criação e a validação de payloads?

- Simule uma aplicação Java com um parâmetro GET que exibe conteúdo da URL.
- Execute: `python3 xsstrike.py -u "http://localhost:8080/pesquisa?q=teste"`
- Analise o payload identificado como vulnerável.
```

### Testando DOM-Based XSS
```
Aplique o XSStrike na tentativa de identificar XSS baseado em DOM.
- O que diferencia o DOM XSS dos outros tipos?
- Como o XSStrike executa análise estática e dinâmica de scripts?
- Que tipo de falha em JavaScript pode gerar um DOM XSS?

- Crie uma página HTML com `document.write(location.hash)` e use XSStrike para analisá-la.
- Execute: `python3 xsstrike.py -u "http://localhost/teste.html#xss"`
- Avalie os resultados da análise estática/dinâmica gerada pela ferramenta.
```

### Integração com Web Crawling e Enumeração de Parâmetros
```
Utilize recursos de crawling e fuzzing para automatizar a análise com XSStrike.
- Como o argumento `--crawl` permite encontrar múltiplas URLs exploráveis?
- O que faz a opção `--params`?
- Como a ferramenta tenta descobrir parâmetros ocultos ou adicionais?

- Execute: `python3 xsstrike.py -u "http://localhost:8080" --crawl`
- Em seguida, teste parâmetros descobertos automaticamente com: `--params`
```

### Bypass de Filtros e Evitação de WAFs
```
Investigue como o XSStrike tenta escapar de filtros e regras de WAFs.
- O que são payloads polimórficos?
- Como a ferramenta utiliza variações para bypass?
- Por que o XSStrike é eficaz contra filtros mal configurados?

- Execute o XSStrike com payloads mutantes (`--fuzzer`) e observe os resultados.
- Tente aplicá-lo em um sistema com validações de entrada simples (ex: `<input>` sanitizado).
```

### Uso Ético e Responsável da Ferramenta

```
Explique  sobre os aspectos legais e éticos no uso de ferramentas como XSStrike.
- Em quais contextos é permitido o uso do XSStrike?
- Quais práticas devem ser evitadas?
- Como aplicar a ferramenta em ambientes controlados, como o DVWA ou WebGoat?

- Configure um ambiente local seguro para testes (Docker com bWAPP, DVWA ou WebGoat).
- Execute o XSStrike nesses ambientes e documente as vulnerabilidades encontradas e corrigidas.
```
