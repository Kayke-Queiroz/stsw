# Auditoria dos labs PortSwigger/BurpSuite

Esta checagem separa o que foi executado no Juice Shop local do que ainda falta fazer nos labs externos do PortSwigger Web Security Academy.

## Status geral

- Juice Shop local em Docker: executado e documentado com Burp Suite.
- PortSwigger/BurpSuite Web Security Academy: nao executado/documentado nesta rodada.
- Motivo: estes labs sao instancias externas do PortSwigger, separadas do container local do Juice Shop. A entrega atual contem evidencias locais do Juice Shop em `127.0.0.1:3001` e esta aguardando sessao autenticada no PortSwigger para executar as instancias externas.

## Labs externos pendentes

### 06 - Injection e XSS

- [ ] SQL injection vulnerability in WHERE clause allowing retrieval of hidden data
- [ ] SQL injection vulnerability allowing login bypass
- [ ] Reflected XSS into HTML context with nothing encoded
- [ ] Stored XSS into HTML context with nothing encoded
- [ ] DOM XSS in document.write sink using source location.search
- [ ] DOM XSS in innerHTML sink using source location.search
- [ ] DOM XSS in jQuery anchor href attribute sink using location.search source
- [ ] DOM XSS in jQuery selector sink using a hashchange event
- [ ] Reflected XSS into attribute with angle brackets HTML-encoded
- [ ] Stored XSS into anchor href attribute with double quotes HTML-encoded
- [ ] Reflected XSS into a JavaScript string with angle brackets HTML encoded

### 07 - SSRF

- [ ] Basic SSRF against the local server
- [ ] Basic SSRF against another back-end system

### 08 - Identification, Authentication e Broken Access Control

- [ ] Username enumeration via different responses
- [ ] 2FA simple by-pass
- [ ] Password reset broken logic
- [ ] Unprotected admin functionality
- [ ] Unprotected admin functionality with unpredictable URL
- [ ] User role controlled by request parameter
- [ ] User role can be modified in user profile
- [ ] User ID controlled by request parameter
- [ ] User ID controlled by request parameter, with unpredictable user Ids
- [ ] User ID controlled by request parameter with data leakage in redirect
- [ ] User ID controlled by request parameter with password disclosure
- [ ] Insecure direct object references

### 09 - Security Logging / Information Disclosure

- [ ] Information disclosure in error messages
- [ ] Information disclosure on debug page
- [ ] Source code disclosure via backup files
- [ ] Authentication bypass via information disclosure

### 10 - Cryptography

- Nenhum lab externo do BurpSuite/PortSwigger esta listado no README da atividade 10; ha somente o desafio do Juice Shop `Weird Crypto`, que foi resolvido e documentado.

## Estado local verificado

Em 15/06/2026, a API local `/api/Challenges/` confirmou os desafios do Juice Shop documentados como resolvidos. A unica excecao esperada e `Reflected XSS`, que o proprio Juice Shop marca como indisponivel em Docker (`disabledEnv: "Docker"`).
