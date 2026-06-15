# I2-07 — Resolver vulnerabilidades do tipo SSRF

**Aluno:** Caleb Medeiros
**Laboratório:** OWASP Juice Shop v19.2.1 (Docker, `http://localhost:3001`)

Evidências em [`evidencias/`](./evidencias/). Print geral do progresso em `00-score-board-geral.png`.

## Desafio resolvido

| Desafio | Categoria | Dificuldade | Status |
|---|---|---|---|
| SSRF | Broken Access Control | ⭐⭐⭐⭐⭐⭐ | ✅ Resolvido |

### SSRF (`ssrfChallenge`) — Server-Side Request Forgery
Na página de perfil (`/profile`), o campo **"Link image from URL"** faz o **servidor** buscar a URL informada. Apontando essa URL para um recurso **interno** do próprio servidor, forçamos o servidor a requisitar um endpoint que ele não deveria acessar a pedido do cliente.

Como o servidor roda na porta **3000 dentro do container** (mapeada para 3001 no host), a URL interna usa `localhost:3000`:
```
http://localhost:3000/solve/challenges/server-side?key=tRtBdrAhEAEZeqsJ0DcaDkDXLf3hQmd2GH3I33H4DCFiNJ54q
```
Passos: colar a URL no campo de imagem do perfil e clicar em **"Link Image"** → o servidor faz a requisição interna (SSRF).

Evidência: `01-ssrf-payload.png` (URL interna no campo de imagem do perfil).

> **Labs do BurpSuite (PortSwigger):** evidências dos 2 labs de SSRF (Basic SSRF localhost / back-end system) em [`evidencias/portswigger/`](./evidencias/portswigger/). Playbook completo dos labs Burp está na atividade 06 (`PORTSWIGGER-PLAYBOOK.md`).
