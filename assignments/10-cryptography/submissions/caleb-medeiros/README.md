# I2-10 — Resolver vulnerabilidades do tipo Cryptographic Failures

**Aluno:** Caleb Medeiros
**Laboratório:** OWASP Juice Shop v19.2.1 (Docker, `http://localhost:3001`)

Evidências em [`evidencias/`](./evidencias/). Print geral do progresso em `00-score-board-geral.png`.

## Desafio resolvido

| Desafio | Categoria | Dificuldade | Status |
|---|---|---|---|
| Weird Crypto | Cryptographic Issues | ⭐⭐ | ✅ Resolvido |

### Weird Crypto (`weirdCryptoChallenge`)
Informar a loja, pelo formulário de **Feedback** (`#/contact`), sobre um algoritmo/biblioteca de criptografia que ela não deveria usar do jeito que usa. O comentário menciona termos inseguros aceitos pelo desafio, como `z85` / `md5` (a aplicação usa hashing/encoding fracos como MD5 para senhas e z85/base85 para tokens).

Texto do feedback enviado:
> "Relatório de segurança: ... e utiliza criptografia insegura (z85 / md5)."

Captcha resolvido (`7*8*9 = 504`). Evidência: `01-weird-crypto-feedback.png`.

> Observação: o mesmo feedback de segurança também reportou os desafios de Vulnerable Components (ver atividade 09).
