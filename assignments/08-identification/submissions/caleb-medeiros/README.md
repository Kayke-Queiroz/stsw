# I2-08 — Identification and Authentication Failures e Broken Access Control

**Aluno:** Caleb Medeiros
**Laboratório:** OWASP Juice Shop v19.2.1 (Docker, `http://localhost:3001`)

Evidências em [`evidencias/`](./evidencias/). Print geral do progresso em `00-score-board-geral.png`.

## Desafios resolvidos

| Desafio | Categoria | Dificuldade | Status |
|---|---|---|---|
| Password Strength | Broken Authentication | ⭐⭐ | ✅ Resolvido |
| Admin Section | Broken Access Control | ⭐⭐ | ✅ Resolvido |
| Five-Star Feedback | Broken Access Control | ⭐⭐ | ✅ Resolvido |
| View Basket | Broken Access Control | ⭐⭐ | ✅ Resolvido |

### 1. Password Strength (`weakPasswordChallenge`)
Login na conta de administrador usando a **senha padrão fraca** `admin123` (sem SQL Injection):
- E-mail: `admin@juice-sh.op`
- Senha: `admin123`

Evidência: `03-password-strength-login.png` (formulário com as credenciais padrão).

### 2. Admin Section (`adminSectionChallenge`)
Acesso à área administrativa restrita navegando diretamente para a rota `#/administration` (controle de acesso quebrado — a rota não valida o papel do usuário no cliente). A página exibe **Registered Users** e **Customer Feedback**.

Evidência: `01-admin-section.png`.

### 3. Five-Star Feedback (`feedbackChallenge`)
Como admin, na seção **Customer Feedback** do painel de administração, remover todos os comentários com avaliação 5 estrelas (clicando no ícone de lixeira). Após a remoção, nenhum feedback 5 estrelas permanece (verificado via API: `0` restantes).

Evidência: `01-admin-section.png` (painel de feedback do administrador).

### 4. View Basket (`basketAccessChallenge`)
Visualizar o carrinho de **outro usuário** alterando o identificador do cesto (`bid`) armazenado no `sessionStorage` do navegador (de `1` para `2`) e abrindo `#/basket`. O servidor retorna o conteúdo do cesto de outro usuário (IDOR).

Evidência: `02-view-basket.png`.

> **Labs do BurpSuite (PortSwigger):** evidências dos 7 labs de Broken Access Control (Unprotected admin, User role/ID controlled by request parameter, etc.) em [`evidencias/portswigger/`](./evidencias/portswigger/). Playbook completo dos labs Burp está na atividade 06 (`PORTSWIGGER-PLAYBOOK.md`).
