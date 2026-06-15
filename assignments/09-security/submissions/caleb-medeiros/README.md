# I2-09 — Security Misconfiguration e Vulnerable/Outdated Components

**Aluno:** Caleb Medeiros
**Laboratório:** OWASP Juice Shop v19.2.1 (Docker, `http://localhost:3001`)

Evidências em [`evidencias/`](./evidencias/). Print geral do progresso em `00-score-board-geral.png`.

## Desafios resolvidos

| Desafio | Categoria | Dificuldade | Status |
|---|---|---|---|
| Error Handling | Security Misconfiguration | ⭐ | ✅ Resolvido |
| Deprecated Interface | Security Misconfiguration | ⭐⭐ | ✅ Resolvido |
| Frontend Typosquatting | Vulnerable Components | ⭐⭐⭐⭐⭐ | ✅ Resolvido |
| Vulnerable Library | Vulnerable Components | ⭐⭐⭐⭐ | ✅ Resolvido |

### 1. Error Handling (`errorHandlingChallenge`)
Provocar um erro não tratado de forma adequada. Inserindo uma aspa simples na busca quebra a query SQL e o servidor responde com a página de erro contendo o **stack trace** (`SQLITE_ERROR: near "'%'": syntax error`):
```
http://localhost:3001/rest/products/search?q=test'
```
Evidência: `01-error-handling.png`.

### 2. Deprecated Interface (`deprecatedInterfaceChallenge`)
Usar a interface B2B obsoleta que não foi desativada corretamente: no formulário de **Reclamação** (`#/complain`), o campo de upload aceita apenas `.pdf,.zip`, mas o backend ainda processa o tipo **`.xml`** (depreciado). Enviei um arquivo `.xml` pelo formulário.

Evidência: `02-deprecated-interface.png`.

### 3 e 4. Frontend Typosquatting + Vulnerable Library (via Customer Feedback)
Ambos são reportados informando a loja pelo formulário de **Feedback** (`#/contact`). Enviei um relatório de segurança citando os culpados exatos:
- **Frontend Typosquatting** (`typosquattingAngularChallenge`): pacote impostor no frontend → `anuglar2-qrcode` (typo de *angular2-qrcode*).
- **Vulnerable Library** (`knownVulnerableComponentChallenge`): biblioteca vulnerável usada → `sanitize-html 1.4.2`.

Texto do feedback enviado:
> "Relatório de segurança: o frontend inclui o pacote typosquatting 'anuglar2-qrcode'; a aplicação usa a biblioteca vulnerável sanitize-html 1.4.2; e utiliza criptografia insegura (z85 / md5)."

Captcha resolvido (`7*8*9 = 504`). Evidência: `03-feedback-security-report.png`.

> Observação: os labs do BurpSuite (PortSwigger) e os desafios de Security Logging do enunciado são externos e estão sendo resolvidos separadamente.
