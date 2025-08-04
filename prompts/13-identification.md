## Identification and Authentication Failures (A07:2021)

Referências:

* [OWASP Top Ten - Identification and Authentication Failures](https://owasp.org/Top10/A07_2021-Identification_and_Authentication_Failures/)
* [Authentication (Portswigger)](https://portswigger.net/web-security/authentication)

### Introdução à Vulnerabilidade Identification and Authentication Failures

```
Explique o que é a vulnerabilidade *Identification and Authentication Failures*, segundo a OWASP.
- Por que ela representa um risco crítico para sistemas modernos?
- Quais os principais objetivos dos mecanismos de identificação e autenticação?
- O que pode acontecer se esses mecanismos forem mal implementados?

- Liste os principais problemas relacionados a autenticação falha: credenciais fracas, gerenciamento incorreto de senhas, falta de autenticação multifator, etc.
- Por que o uso de autenticação multifator (MFA) é considerado uma boa prática recomendada pela OWASP?
```

### Tipos de Falhas Comuns em Autenticação

```
Diferencie os conceitos de *identificação* e *autenticação* no contexto da segurança da informação.
- Quais são os tipos mais comuns de autenticação (baseada em conhecimento, posse e inerência)?
- O que caracteriza uma falha de autenticação do tipo *credential stuffing*?
- Como funciona um ataque de *brute force* e como ele pode ser mitigado?

- Explique a importância do controle de sessões e do tempo de expiração de sessões para manter a segurança.
- O que pode ocorrer se os tokens de sessão não forem invalidados corretamente?
```

### Exemplos Práticos de Vulnerabilidades

```
Analise os exemplos práticos de falhas de autenticação descritos nos sites da OWASP e PortSwigger.
- O que acontece quando uma aplicação expõe respostas diferentes para usuários válidos e inválidos?
- Como a falta de limites de tentativas de login pode ser explorada?

- Elabore um exemplo de código vulnerável em Java onde a autenticação é feita de forma insegura (ex: comparação de senhas com `==` ao invés de `equals()`).
- Proponha a correção segura para esse código.
```

### Exemplos Práticos de Vulnerabilidades

```
Analise os exemplos práticos de falhas de autenticação descritos nos sites da OWASP e PortSwigger.
- O que acontece quando uma aplicação expõe respostas diferentes para usuários válidos e inválidos?
- Como a falta de limites de tentativas de login pode ser explorada?

- Elabore um exemplo de código vulnerável em Java onde a autenticação é feita de forma insegura (ex: comparação de senhas com `==` ao invés de `equals()`).
- Proponha a correção segura para esse código.
```

### Testes de Segurança em Mecanismos de Autenticação

```
Descreva como um testador de segurança pode verificar se um sistema está vulnerável a falhas de autenticação.
- Que técnicas podem ser usadas para explorar essas falhas (ex: força bruta, dictionary attack, manipulação de parâmetros)?
- Qual a importância de ferramentas como BurpSuite para testar autenticação?

- Proponha um passo a passo prático para realizar um teste de força bruta com BurpSuite.
- Como detectar falhas relacionadas a tempo de expiração de sessão e logout inefetivo?
```

### Aplicação em Cenários Reais

```
Considere uma aplicação de e-commerce que permite login sem limites de tentativas.
- Quais riscos isso oferece à empresa e aos usuários?
- Quais mecanismos poderiam ser implementados para proteger a autenticação nesse caso?

- Simule um cenário em que um atacante consiga acessar a conta de um usuário usando técnicas como credential stuffing. Como a equipe de segurança poderia detectar e responder a esse incidente?
```

### Conclusão

```
Explique por que as falhas de autenticação ainda são comuns em sistemas modernos.
- Qual a relação entre usabilidade e segurança em mecanismos de autenticação?
- Apresente um exemplo onde uma escolha de design para facilitar o uso comprometeu a segurança?
```