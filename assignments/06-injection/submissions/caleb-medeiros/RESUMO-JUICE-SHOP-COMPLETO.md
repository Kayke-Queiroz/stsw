# OWASP Juice Shop — Resumo de Conclusao (Caleb Medeiros)

**Instancia:** OWASP Juice Shop v19.2.1 em Docker (http://localhost:3001)

## Resultado

- **Desafios resolvidos: 93/111** (~84% dos hacking challenges no score board)
- **Maximo alcancavel nesta imagem Docker: 94** (17 desafios tem disabledEnv: Docker, impossiveis aqui)
- **Cobertura sobre o alcancavel: 93/94 = 99%**

Print do score board: evidencias/00-score-board-geral.png. Os 16 desafios das atividades I2-06..I2-10 tem print passo a passo nas respectivas pastas.

## Resolvidos por categoria (93)

### Broken Access Control (11)
- [1star] Web3 Sandbox
- [2star] Admin Section
- [2star] Five-Star Feedback
- [2star] View Basket
- [3star] Forged Feedback
- [3star] Forged Review
- [3star] Manipulate Basket
- [3star] Product Tampering
- [3star] CSRF
- [4star] Easter Egg
- [6star] SSRF

### Broken Anti Automation (4)
- [3star] CAPTCHA Bypass
- [5star] Extra Language
- [5star] Reset Morty's Password
- [6star] Multiple Likes

### Broken Authentication (9)
- [2star] Password Strength
- [3star] Bjoern's Favorite Pet
- [3star] GDPR Data Erasure
- [3star] Reset Jim's Password
- [4star] Login Bjoern
- [4star] Reset Bender's Password
- [5star] Change Bender's Password
- [5star] Reset Bjoern's Password
- [5star] Two Factor Authentication

### Cryptographic Issues (5)
- [2star] Weird Crypto
- [4star] Nested Easter Egg
- [6star] Forged Coupon
- [6star] Imaginary Challenge
- [6star] Premium Paywall

### Improper Input Validation (11)
- [1star] Repetitive Registration
- [1star] Zero Stars
- [1star] Missing Encoding
- [2star] Empty User Registration
- [3star] Admin Registration
- [3star] Payback Time
- [3star] Upload Size
- [3star] Upload Type
- [3star] Deluxe Fraud
- [4star] Expired Coupon
- [4star] Poison Null Byte

### Injection (8)
- [2star] Login Admin
- [3star] Database Schema
- [3star] Login Bender
- [3star] Login Jim
- [4star] Christmas Special
- [4star] Ephemeral Accountant
- [4star] NoSQL Manipulation
- [4star] User Credentials

### Miscellaneous (6)
- [1star] Privacy Policy
- [1star] Score Board
- [1star] Bully Chatbot
- [1star] Mass Dispel
- [2star] Security Policy
- [3star] Security Advisory

### Observability Failures (4)
- [1star] Exposed Metrics
- [4star] Access Log
- [4star] Misplaced Signature File
- [5star] Leaked Access Logs

### Security Misconfiguration (4)
- [1star] Error Handling
- [2star] Deprecated Interface
- [5star] Cross-Site Imaging
- [6star] Login Support Team

### Security through Obscurity (3)
- [3star] Privacy Policy Inspection
- [4star] Steganography
- [5star] Blockchain Hype

### Sensitive Data Exposure (16)
- [1star] Confidential Document
- [2star] Password Hash Leak
- [2star] NFT Takeover
- [2star] Login MC SafeSearch
- [2star] Meta Geo Stalking
- [2star] Visual Geo Stalking
- [2star] Exposed credentials
- [3star] Login Amy
- [4star] Forgotten Developer Backup
- [4star] Forgotten Sales Backup
- [4star] GDPR Data Theft
- [4star] Leaked Unsafe Product
- [4star] Reset Uvogin's Password
- [5star] Email Leak
- [5star] Retrieve Blueprint
- [5star] Leaked API Key

### Unvalidated Redirects (2)
- [1star] Outdated Allowlist
- [4star] Allowlist Bypass

### Vulnerable Components (7)
- [4star] Legacy Typosquatting
- [4star] Vulnerable Library
- [5star] Frontend Typosquatting
- [5star] Supply Chain Attack
- [5star] Unsigned JWT
- [5star] Kill Chatbot
- [6star] Forged Signed JWT

### XSS (2)
- [1star] DOM XSS
- [1star] Bonus Payload

### XXE (1)
- [3star] XXE Data Access

## Nao resolvidos — exigem carteira Web3/MetaMask (2)

- [3star] Mint the Honey Pot (Improper Input Validation) — Mint the Honey Pot NFT by gathering BEEs from the bee haven.
- [6star] Wallet Depletion (Miscellaneous) — Withdraw more ETH from the new wallet than you deposited.

> Dependem de uma carteira MetaMask conectada a uma blockchain de testes com ETH (ver evidencias/99-web3-precisa-metamask.png) — infra indisponivel no navegador automatizado. Mesma categoria dos labs do BurpSuite (ferramenta externa).

## Indisponiveis no Docker (17) — disabledEnv: Docker

- API-only XSS (XSS)
- Arbitrary File Write (Vulnerable Components)
- Blocked RCE DoS (Insecure Deserialization)
- CSP Bypass (XSS)
- Client-side XSS Protection (XSS)
- HTTP-Header XSS (XSS)
- Local File Read (Vulnerable Components)
- Memory Bomb (Insecure Deserialization)
- NoSQL DoS (Injection)
- NoSQL Exfiltration (Injection)
- Reflected XSS (XSS)
- SSTi (Injection)
- Server-side XSS Protection (XSS)
- Successful RCE DoS (Insecure Deserialization)
- Video XSS (XSS)
- XXE Data Access (XXE)
- XXE DoS (XXE)
