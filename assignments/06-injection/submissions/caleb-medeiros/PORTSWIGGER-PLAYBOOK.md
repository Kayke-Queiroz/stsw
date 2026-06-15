# Playbook operacional dos labs PortSwigger/BurpSuite

Este documento cobre os 29 labs externos PortSwigger Web Security Academy listados nos READMEs das atividades `06-injection`, `07-ssrf`, `08-identification` e `09-security`.

Escopo e regras:

- Isto e um playbook de execucao. Nao e evidencia e nao marca nenhum lab como executado.
- Executar somente em instancias oficiais iniciadas pelo PortSwigger Web Security Academy.
- Nao reutilizar payloads ou automacoes contra alvos fora da PortSwigger Academy.
- Para cada lab, capturar evidencia apenas quando a pagina mostrar `Lab solved`, `Congratulations, you solved the lab`, ou status equivalente no dashboard da Academy.
- Sempre manter Burp Suite capturando trafego para registrar requests/responses relevantes no HTTP history ou Repeater.

## Resumo de cobertura

| Atividade | Categoria | Labs |
| --- | --- | ---: |
| 06-injection | SQL injection e XSS | 11 |
| 07-ssrf | SSRF | 2 |
| 08-identification | Authentication e Broken Access Control | 12 |
| 09-security | Information disclosure / Security misconfiguration | 4 |
| Total |  | 29 |

## 06-injection - SQL injection e XSS

### 01. SQL injection vulnerability in WHERE clause allowing retrieval of hidden data

- URL oficial: https://portswigger.net/web-security/sql-injection/lab-retrieve-hidden-data
- Objetivo de solucao: exibir produtos nao lancados alterando o filtro de categoria vulneravel a SQL injection.
- Passos/payloads:
  1. Abrir a instancia do lab e selecionar uma categoria de produto.
  2. No Burp Proxy HTTP history, enviar a requisicao do filtro de categoria para Repeater.
  3. Alterar o parametro `category` para fechar a string SQL e forcar uma condicao verdadeira:

     ```text
     '+OR+1=1--
     ```

  4. Enviar a requisicao modificada e conferir se a resposta passa a listar produtos ocultos/nao lancados.
- Evidencia esperada: banner `Lab solved` no lab e request no Burp mostrando o parametro `category` modificado.

### 02. SQL injection vulnerability allowing login bypass

- URL oficial: https://portswigger.net/web-security/sql-injection/lab-login-bypass
- Objetivo de solucao: autenticar como `administrator` explorando SQL injection no login.
- Passos/payloads:
  1. Abrir a pagina de login com Burp Proxy ativo.
  2. Interceptar ou reenviar para Repeater o `POST /login`.
  3. Substituir o valor do campo `username` por:

     ```text
     administrator'--
     ```

  4. Usar qualquer valor no campo `password`, pois o comentario SQL remove a validacao restante da senha.
  5. Enviar a requisicao e confirmar que a sessao abre como administrador.
- Evidencia esperada: `Lab solved` apos login como `administrator`, com a requisicao de login modificada visivel no Burp.

### 03. Reflected XSS into HTML context with nothing encoded

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/reflected/lab-html-context-nothing-encoded
- Objetivo de solucao: executar `alert` via XSS refletido no campo de busca.
- Passos/payloads:
  1. Abrir a pagina do lab.
  2. Inserir no campo de busca:

     ```html
     <script>alert(1)</script>
     ```

  3. Enviar a busca e confirmar que o script e refletido e executado.
- Evidencia esperada: alerta no navegador e status `Lab solved`.

### 04. Stored XSS into HTML context with nothing encoded

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/stored/lab-html-context-nothing-encoded
- Objetivo de solucao: persistir um payload XSS em comentario de blog.
- Passos/payloads:
  1. Abrir qualquer post do blog no lab.
  2. Enviar um comentario contendo:

     ```html
     <script>alert(1)</script>
     ```

  3. Preencher os demais campos exigidos com dados validos.
  4. Publicar o comentario e recarregar/abrir o post para executar o payload armazenado.
- Evidencia esperada: alerta ao visualizar o post e status `Lab solved`.

### 05. DOM XSS in document.write sink using source location.search

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/dom-based/lab-document-write-sink
- Objetivo de solucao: explorar uso inseguro de `document.write` com dados vindos de `location.search`.
- Passos/payloads:
  1. Inserir uma string aleatoria na busca e inspecionar o DOM para confirmar o contexto de reflexao.
  2. Usar um payload que escape do atributo gerado e injete um elemento executavel:

     ```html
     "><svg onload=alert(1)>
     ```

  3. Enviar a busca e confirmar a execucao do `alert`.
- Evidencia esperada: alerta executado no navegador e status `Lab solved`.

### 06. DOM XSS in innerHTML sink using source location.search

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/dom-based/lab-innerhtml-sink
- Objetivo de solucao: explorar atribuicao insegura em `innerHTML` usando a busca controlada pela URL.
- Passos/payloads:
  1. Enviar uma busca normal e confirmar no DOM onde o texto aparece.
  2. Inserir payload baseado em elemento com erro de carregamento:

     ```html
     <img src=1 onerror=alert(1)>
     ```

  3. Submeter a busca.
- Evidencia esperada: alerta disparado pelo evento `onerror` e status `Lab solved`.

### 07. DOM XSS in jQuery anchor href attribute sink using location.search source

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/dom-based/lab-jquery-href-attribute-sink
- Objetivo de solucao: fazer o link `back` da pagina de feedback executar JavaScript.
- Passos/payloads:
  1. Abrir a pagina `Submit feedback`.
  2. Alterar o parametro `returnPath` na URL para uma string de teste e inspecionar o link `back`.
  3. Trocar `returnPath` por:

     ```text
     javascript:alert(document.cookie)
     ```

  4. Recarregar a pagina e clicar no link `back`.
- Evidencia esperada: alerta com `document.cookie` e status `Lab solved`.

### 08. DOM XSS in jQuery selector sink using a hashchange event

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/dom-based/lab-jquery-selector-hash-change-event
- Objetivo de solucao: entregar exploit que acione `print()` no navegador da vitima via evento `hashchange`.
- Passos/payloads:
  1. Confirmar que a home usa `location.hash` em um seletor jQuery para rolagem automatica.
  2. Abrir o exploit server do proprio lab.
  3. No corpo do exploit, inserir um iframe apontando para a instancia do lab e alterando o hash no `onload`:

     ```html
     <iframe src="https://YOUR-LAB-ID.web-security-academy.net/#" onload="this.src+='<img src=x onerror=print()>'"></iframe>
     ```

  4. Substituir `YOUR-LAB-ID` pelo host real da instancia.
  5. Usar `View exploit` para validar o comportamento e depois `Deliver to victim`.
- Evidencia esperada: `Lab solved` apos entrega ao usuario vitima; opcionalmente screenshot do exploit server armazenado.

### 09. Reflected XSS into attribute with angle brackets HTML-encoded

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/contexts/lab-attribute-angle-brackets-html-encoded
- Objetivo de solucao: escapar de atributo HTML refletido sem depender de `<` ou `>`.
- Passos/payloads:
  1. Fazer uma busca com string aleatoria.
  2. Enviar a requisicao para Repeater e confirmar que o valor e refletido dentro de atributo entre aspas.
  3. Substituir a entrada por:

     ```text
     "onmouseover="alert(1)
     ```

  4. Abrir a URL resultante no navegador e passar o mouse sobre o elemento vulneravel.
- Evidencia esperada: alerta ao passar o mouse e status `Lab solved`.

### 10. Stored XSS into anchor href attribute with double quotes HTML-encoded

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/contexts/lab-href-attribute-double-quotes-html-encoded
- Objetivo de solucao: gravar uma URL JavaScript no campo de website do comentario.
- Passos/payloads:
  1. Abrir um post do blog.
  2. Enviar um comentario preenchendo o campo `Website` com:

     ```text
     javascript:alert(1)
     ```

  3. Publicar o comentario.
  4. Clicar no nome do autor do comentario, que sera renderizado como link.
- Evidencia esperada: alerta ao clicar no autor e status `Lab solved`.

### 11. Reflected XSS into a JavaScript string with angle brackets HTML encoded

- URL oficial: https://portswigger.net/web-security/cross-site-scripting/contexts/lab-javascript-string-angle-brackets-html-encoded
- Objetivo de solucao: quebrar uma string JavaScript refletida e executar `alert`.
- Passos/payloads:
  1. Enviar uma busca com texto aleatorio.
  2. No Burp Repeater, confirmar que o valor aparece dentro de uma string JavaScript.
  3. Substituir o valor buscado por:

     ```text
     '-alert(1)-'
     ```

  4. Abrir a URL no navegador.
- Evidencia esperada: alerta durante o carregamento e status `Lab solved`.

## 07-ssrf - Server-side request forgery

### 12. Basic SSRF against the local server

- URL oficial: https://portswigger.net/web-security/ssrf/lab-basic-ssrf-against-localhost
- Objetivo de solucao: usar o stock checker para acessar `localhost` do servidor e apagar `carlos` no painel admin interno.
- Passos/payloads:
  1. Abrir um produto e clicar em `Check stock`.
  2. Enviar o request do stock checker para Burp Repeater.
  3. Alterar o parametro `stockApi` para:

     ```text
     http://localhost/admin
     ```

  4. Ler o HTML retornado para identificar o endpoint de exclusao.
  5. Enviar nova requisicao com `stockApi` apontando para:

     ```text
     http://localhost/admin/delete?username=carlos
     ```

- Evidencia esperada: `Lab solved` apos exclusao de `carlos`; request SSRF no Repeater/HTTP history.

### 13. Basic SSRF against another back-end system

- URL oficial: https://portswigger.net/web-security/ssrf/lab-basic-ssrf-against-backend-system
- Objetivo de solucao: localizar painel admin em `192.168.0.X:8080` via stock checker e apagar `carlos`.
- Passos/payloads:
  1. Capturar o request `Check stock` e enviar para Burp Intruder.
  2. Ajustar `stockApi` para testar o range interno:

     ```text
     http://192.168.0.[PAYLOAD]:8080/admin
     ```

  3. Configurar payload numerico de `1` a `255`, passo `1`.
  4. Executar o ataque apenas contra a instancia PortSwigger do lab.
  5. Ordenar por status/length e identificar o unico host que retorna painel admin, normalmente HTTP `200`.
  6. Enviar esse request para Repeater e trocar o caminho para:

     ```text
     /admin/delete?username=carlos
     ```

- Evidencia esperada: `Lab solved` apos requisicao de delete no host interno encontrado.

## 08-identification - Authentication e Broken Access Control

### 14. Username enumeration via different responses

- URL oficial: https://portswigger.net/web-security/authentication/password-based/lab-username-enumeration-via-different-responses
- Objetivo de solucao: enumerar um usuario valido por diferenca de resposta, quebrar a senha com a wordlist do lab e acessar a conta.
- Passos/payloads:
  1. Enviar um login invalido e capturar `POST /login`.
  2. Enviar o request para Burp Intruder.
  3. Primeiro ataque: colocar payload position apenas em `username`, manter senha fixa invalida, e usar a lista oficial `Candidate usernames` do lab.
  4. Identificar o usuario cuja resposta muda de `Invalid username` para `Incorrect password`, ou cujo length/status difere.
  5. Segundo ataque: fixar o username encontrado, colocar payload position em `password` e usar a lista oficial `Candidate passwords`.
  6. Identificar a senha pelo status `302` ou resposta de login bem-sucedido.
  7. Logar no navegador com o par encontrado.
- Evidencia esperada: `Lab solved` ao acessar `My account`; screenshots dos resultados do Intruder podem complementar.

### 15. 2FA simple by-pass

- URL oficial: https://portswigger.net/web-security/authentication/multi-factor/lab-2fa-simple-bypass
- Objetivo de solucao: acessar a conta de `carlos` mesmo sem possuir o codigo 2FA.
- Passos/payloads:
  1. Logar com `wiener:peter` para observar o fluxo normal e confirmar a URL da conta (`/my-account`).
  2. Sair da conta.
  3. Logar com as credenciais fornecidas da vitima no lab:

     ```text
     carlos:montoya
     ```

  4. Quando a aplicacao pedir o codigo 2FA, navegar manualmente para:

     ```text
     /my-account
     ```

  5. Confirmar que a pagina de Carlos abre apesar da etapa 2FA pendente.
- Evidencia esperada: `Lab solved` ao carregar a pagina da conta de `carlos`.

### 16. Password reset broken logic

- URL oficial: https://portswigger.net/web-security/authentication/other-mechanisms/lab-password-reset-broken-logic
- Objetivo de solucao: redefinir a senha de `carlos` abusando de validacao incorreta do token de reset.
- Passos/payloads:
  1. Solicitar reset de senha para `wiener` e abrir o link recebido no email client do lab.
  2. Redefinir a propria senha e localizar no Burp o `POST /forgot-password` usado para concluir a troca.
  3. Enviar esse request para Repeater.
  4. Testar remover/limpar o valor do token de reset na query string e no corpo, confirmando que a aplicacao ainda aceita a troca.
  5. Repetir o request alterando o campo `username` para:

     ```text
     carlos
     ```

  6. Definir uma nova senha controlada, por exemplo `NewPass123!`.
  7. Logar como `carlos` com a senha definida.
- Evidencia esperada: `Lab solved` ao acessar a conta de `carlos` com a nova senha.

### 17. Unprotected admin functionality

- URL oficial: https://portswigger.net/web-security/access-control/lab-unprotected-admin-functionality
- Objetivo de solucao: descobrir painel admin nao protegido e apagar `carlos`.
- Passos/payloads:
  1. Acessar:

     ```text
     /robots.txt
     ```

  2. Ler a diretiva `Disallow` para obter o caminho do painel.
  3. Navegar para o caminho revelado, tipicamente:

     ```text
     /administrator-panel
     ```

  4. Usar a funcao administrativa para excluir `carlos`.
- Evidencia esperada: `Lab solved` apos exclusao do usuario.

### 18. Unprotected admin functionality with unpredictable URL

- URL oficial: https://portswigger.net/web-security/access-control/lab-unprotected-admin-functionality-with-unpredictable-url
- Objetivo de solucao: descobrir a URL imprevisivel do painel admin vazada no front-end e apagar `carlos`.
- Passos/payloads:
  1. Abrir a home da instancia.
  2. Ver o source da pagina ou usar Burp para inspecionar a resposta HTML.
  3. Procurar JavaScript que contenha o caminho do painel administrativo, por exemplo padrao semelhante a `/admin-...`.
  4. Acessar o caminho descoberto.
  5. Excluir `carlos`.
- Evidencia esperada: `Lab solved` apos usar o painel vazado.

### 19. User role controlled by request parameter

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-role-controlled-by-request-parameter
- Objetivo de solucao: forjar papel de administrador em cookie/parametro controlavel e apagar `carlos`.
- Passos/payloads:
  1. Acessar `/admin` e confirmar bloqueio.
  2. Logar com:

     ```text
     wiener:peter
     ```

  3. Com interceptacao de resposta habilitada no Burp, observar o cookie definido no login.
  4. Alterar o cookie:

     ```text
     Admin=false
     ```

     para:

     ```text
     Admin=true
     ```

  5. Navegar para `/admin`.
  6. Excluir `carlos`.
- Evidencia esperada: `Lab solved` com cookie `Admin=true` em uso.

### 20. User role can be modified in user profile

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-role-can-be-modified-in-user-profile
- Objetivo de solucao: elevar o proprio `roleid` para `2` via update de perfil e apagar `carlos`.
- Passos/payloads:
  1. Logar com `wiener:peter`.
  2. Atualizar o email no perfil e capturar a requisicao JSON.
  3. Enviar para Repeater e adicionar o campo:

     ```json
     "roleid": 2
     ```

  4. Enviar o update e confirmar na resposta que o `roleid` virou `2`.
  5. Acessar `/admin`.
  6. Excluir `carlos`.
- Evidencia esperada: `Lab solved`, com request de update contendo `roleid: 2`.

### 21. User ID controlled by request parameter

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-id-controlled-by-request-parameter
- Objetivo de solucao: obter a API key de `carlos` por IDOR e submeter como solucao.
- Passos/payloads:
  1. Logar com `wiener:peter`.
  2. Abrir `My account` e observar o parametro:

     ```text
     /my-account?id=wiener
     ```

  3. Enviar a requisicao para Repeater.
  4. Alterar o parametro para:

     ```text
     id=carlos
     ```

  5. Copiar a API key exibida na resposta.
  6. Submeter a API key no formulario `Submit solution`.
- Evidencia esperada: `Lab solved` apos submissao da API key de `carlos`.

### 22. User ID controlled by request parameter, with unpredictable user IDs

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-id-controlled-by-request-parameter-with-unpredictable-user-ids
- Objetivo de solucao: encontrar o GUID de `carlos`, acessar a conta dele por IDOR e submeter a API key.
- Passos/payloads:
  1. Procurar um post ou pagina publica criada por `carlos`.
  2. Clicar no autor `carlos` e copiar o GUID/ID presente na URL do perfil.
  3. Logar com `wiener:peter`.
  4. Abrir `My account` e trocar o parametro `id` pelo GUID de `carlos`.
  5. Capturar a API key exibida.
  6. Submeter a API key no lab.
- Evidencia esperada: `Lab solved` apos submissao da API key correta.

### 23. User ID controlled by request parameter with data leakage in redirect

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-id-controlled-by-request-parameter-with-data-leakage-in-redirect
- Objetivo de solucao: obter a API key de `carlos` no corpo de uma resposta de redirect.
- Passos/payloads:
  1. Logar com `wiener:peter`.
  2. Capturar request de `My account` e enviar para Repeater.
  3. Alterar:

     ```text
     id=wiener
     ```

     para:

     ```text
     id=carlos
     ```

  4. Enviar e nao seguir automaticamente o redirect.
  5. Ler o corpo da resposta `302`, onde a API key de `carlos` e vazada.
  6. Submeter a API key no lab.
- Evidencia esperada: `Lab solved` apos submissao da API key vazada na resposta de redirect.

### 24. User ID controlled by request parameter with password disclosure

- URL oficial: https://portswigger.net/web-security/access-control/lab-user-id-controlled-by-request-parameter-with-password-disclosure
- Objetivo de solucao: obter a senha do `administrator`, logar como admin e apagar `carlos`.
- Passos/payloads:
  1. Logar com `wiener:peter`.
  2. Abrir `My account` e alterar o parametro `id` para:

     ```text
     administrator
     ```

  3. Ver a resposta no Burp e localizar o valor do campo de senha pre-preenchido do administrador.
  4. Logar como `administrator` usando a senha descoberta.
  5. Acessar `/admin`.
  6. Excluir `carlos`.
- Evidencia esperada: `Lab solved` apos exclusao de `carlos` como administrador.

### 25. Insecure direct object references

- URL oficial: https://portswigger.net/web-security/access-control/lab-insecure-direct-object-references
- Objetivo de solucao: baixar transcript de chat previsivel, encontrar a senha de `carlos` e logar na conta dele.
- Passos/payloads:
  1. Abrir `Live chat`.
  2. Enviar uma mensagem qualquer e clicar em `View transcript`.
  3. Observar que o transcript e servido por arquivo numerico, por exemplo `2.txt` ou similar.
  4. Alterar o nome do arquivo para:

     ```text
     1.txt
     ```

  5. Ler o transcript e extrair a senha de `carlos`.
  6. Logar como `carlos` com a senha encontrada.
- Evidencia esperada: `Lab solved` ao acessar a conta de `carlos`.

## 09-security - Information disclosure / Security misconfiguration

### 26. Information disclosure in error messages

- URL oficial: https://portswigger.net/web-security/information-disclosure/exploiting/lab-infoleak-in-error-messages
- Objetivo de solucao: obter e submeter a versao do framework vazada em stack trace.
- Passos/payloads:
  1. Abrir uma pagina de produto e capturar o request `GET /product?productId=...`.
  2. Enviar para Repeater.
  3. Trocar `productId` por um valor nao numerico, por exemplo:

     ```text
     productId="example"
     ```

  4. Enviar e revisar o stack trace retornado.
  5. Extrair a versao do framework revelada no erro.
  6. Submeter a versao no formulario `Submit solution`.
- Evidencia esperada: `Lab solved` apos submeter a versao correta.

### 27. Information disclosure on debug page

- URL oficial: https://portswigger.net/web-security/information-disclosure/exploiting/lab-infoleak-on-debug-page
- Objetivo de solucao: localizar pagina de debug e submeter o valor de `SECRET_KEY`.
- Passos/payloads:
  1. Com Burp capturando, abrir a home do lab.
  2. Usar `Target > Site map` e `Engagement tools > Find comments`, ou revisar manualmente o HTML.
  3. Procurar comentario que aponte para:

     ```text
     /cgi-bin/phpinfo.php
     ```

  4. Acessar a pagina de debug.
  5. Localizar o valor da variavel de ambiente `SECRET_KEY`.
  6. Submeter o valor no lab.
- Evidencia esperada: `Lab solved` apos submeter o `SECRET_KEY`.

### 28. Source code disclosure via backup files

- URL oficial: https://portswigger.net/web-security/information-disclosure/exploiting/lab-infoleak-via-backup-files
- Objetivo de solucao: encontrar arquivo de backup de codigo-fonte e submeter a senha do banco vazada.
- Passos/payloads:
  1. Acessar:

     ```text
     /robots.txt
     ```

  2. Identificar a existencia do diretorio:

     ```text
     /backup
     ```

  3. Abrir o diretorio e localizar:

     ```text
     /backup/ProductTemplate.java.bak
     ```

  4. Ler o arquivo e procurar credenciais hardcoded do Postgres ou builder de conexao.
  5. Copiar a senha do banco.
  6. Submeter a senha no formulario do lab.
- Evidencia esperada: `Lab solved` apos submeter a senha correta.

### 29. Authentication bypass via information disclosure

- URL oficial: https://portswigger.net/web-security/information-disclosure/exploiting/lab-infoleak-authentication-bypass
- Objetivo de solucao: descobrir header customizado por `TRACE`, simular localhost e apagar `carlos`.
- Passos/payloads:
  1. Enviar `GET /admin` para Repeater e confirmar que o acesso exige administrador ou origem local.
  2. Trocar o metodo para:

     ```http
     TRACE /admin HTTP/1.1
     ```

  3. Inspecionar a resposta e identificar o header customizado refletido pela infraestrutura:

     ```http
     X-Custom-IP-Authorization
     ```

  4. Reenviar a requisicao para `/admin` adicionando:

     ```http
     X-Custom-IP-Authorization: 127.0.0.1
     ```

  5. Confirmar acesso ao painel admin.
  6. Usar o mesmo header para executar a exclusao de `carlos` no endpoint administrativo, ou configurar Match and Replace no Burp para adicionar o header a todas as requests da instancia.
- Evidencia esperada: `Lab solved` apos exclusao de `carlos`, com request contendo `X-Custom-IP-Authorization: 127.0.0.1`.

## Checklist de evidencia para execucao futura

Para cada lab executado posteriormente, anexar no PR:

- Screenshot da pagina oficial do lab mostrando `Lab solved` ou equivalente.
- Screenshot do Burp HTTP history/Repeater com o request principal do exploit.
- Nome do lab e categoria no nome do arquivo de evidencia.
- Observacao se a solucao usou exploit server, Intruder, Repeater, Match and Replace ou apenas navegador.
