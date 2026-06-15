# I2-06 — Resolver vulnerabilidades do tipo Injection

**Aluno:** Caleb Medeiros
**Laboratório:** OWASP Juice Shop v19.2.1 (Docker, `http://localhost:3001`)

As evidências (prints) de cada passo estão na pasta [`evidencias/`](./evidencias/). O print geral do Score Board com o progresso está em `00-score-board-geral.png`.

## Desafios resolvidos

| Desafio | Categoria | Dificuldade | Status |
|---|---|---|---|
| Score Board | Miscellaneous | ⭐ | ✅ Resolvido |
| Login Admin | Injection (SQLi) | ⭐⭐ | ✅ Resolvido |
| User Credentials | Injection (SQLi) | ⭐⭐⭐⭐ | ✅ Resolvido |
| DOM XSS | XSS | ⭐ | ✅ Resolvido |
| Bonus Payload | XSS | ⭐ | ✅ Resolvido |
| Reflected XSS | XSS | ⭐⭐ | ⚠️ Indisponível no Docker (`disabledEnv: Docker`) |

### 1. Score Board (`scoreBoardChallenge`)
Encontrar a página de placar escondida acessando a rota `#/score-board`.
Evidência: `01-score-board.png`

### 2. Login Admin (`loginAdminChallenge`) — SQL Injection
Bypass de autenticação no campo de e-mail do login com o payload `' OR 1=1--`, que comenta o resto da query e retorna o primeiro usuário (admin).
Evidências: `02-login-admin-payload.png` (payload no formulário), `02-login-admin-success.png` (logado como admin).

### 3. User Credentials (`unionSqlInjectionChallenge`) — UNION SQL Injection
Injeção UNION no parâmetro de busca de produtos para extrair a tabela `Users` (e-mails e hashes MD5 das senhas). A busca monta `... WHERE ((name LIKE '%<q>%' OR description LIKE '%<q>%') ...)`, com 9 colunas.

Payload (na caixa de busca):
```
qwert')) UNION SELECT id, email, password, '4','5','6','7','8','9' FROM Users--
```
Evidências: `03-user-credentials.png` (busca + notificação), `03b-user-credentials-dump.png` (resposta da API com credenciais vazadas, ex.: `admin@juice-sh.op` / `0192023a7bbd73250516f069df18b500`).

### 4. DOM XSS (`localXssChallenge`)
XSS baseado em DOM via caixa de busca (o termo é refletido no DOM via `innerHTML`):
```
<iframe src="javascript:alert(`xss`)">
```
O `alert("xss")` foi disparado. Evidência: `04-dom-xss.png`.

### 5. Bonus Payload (`xssBonusChallenge`)
Mesmo sink de XSS, usando o payload bônus que embute o player do SoundCloud ("OWASP Juice Shop Jingle"):
```
<iframe width="100%" height="166" ... src="https://w.soundcloud.com/player/?url=...771984076..."></iframe>
```
Evidência: `05-bonus-payload.png` (player renderizado na página).

### 6. Reflected XSS (`reflectedXssChallenge`) — indisponível no Docker
Este desafio possui `disabledEnv: Docker` na própria descrição ("This challenge is potentially harmful on Docker!") e faz parte dos 17 desafios desabilitados nesta imagem Docker — não pode ser concluído neste ambiente. A técnica seria refletir o payload `<iframe src="javascript:alert(`xss`)">` no parâmetro `id` do rastreamento de pedido (`#/track-result?id=...`); porém o servidor sanitiza o `orderId` e o desafio está desabilitado.

> **Labs do BurpSuite (PortSwigger):** evidências dos labs de SQL Injection em [`evidencias/portswigger/`](./evidencias/portswigger/) (01–02). Playbook completo de todos os labs Burp em [`PORTSWIGGER-PLAYBOOK.md`](./PORTSWIGGER-PLAYBOOK.md). Os labs de SSRF e Broken Access Control têm evidência nas atividades 07 e 08.
