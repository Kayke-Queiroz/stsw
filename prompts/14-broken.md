## Broken Access Control (A01:2021)

Referências:

* [OWASP Top Ten - Broken Access Control](https://owasp.org/Top10/A01_2021-Broken_Access_Control/)
* [Broken Access Control](https://owasp.org/www-community/Broken_Access_Control)
* [Access Control](https://portswigger.net/web-security/access-control)

### Introdução à Vulnerabilidade Broken Access Control

```
Explique o que é uma vulnerabilidade do tipo Broken Access Control, segundo a OWASP.
- Por que essa vulnerabilidade está em primeiro lugar no Top 10 da OWASP 2021?
- Quais são os princípios de controle de acesso que as aplicações deveriam seguir?
- Qual a diferença entre *autenticação* e *autorização* no contexto de segurança?

- Liste e explique ao menos 5 exemplos comuns de falhas de controle de acesso.
  - Inclua IDOR, bypass de ACL via manipulação de URL, forja de tokens, entre outros.

- Qual o impacto potencial de uma aplicação com Broken Access Control? 
  - Exfiltração de dados? Escalada de privilégios? Acesso a dados administrativos?
```

### Exemplos Práticos de Falhas de Controle de Acesso

```
Elabore um exemplo de código de API mal configurada e aponte onde está a falha de controle de acesso.
- O código permite que qualquer usuário acesse dados sensíveis alterando o ID no endpoint?
- Essa falha é um caso de Insecure Direct Object Reference (IDOR)? Por quê?

- Escreva um trecho de código (em Java) que demonstra uma API insegura com IDOR.
- Em seguida, reescreva o mesmo código de forma segura, com verificação de permissões adequadas.

- Simule um ataque básico de bypass de controle de acesso utilizando parâmetros na URL (ex: `userId=2`).
  - Qual seria o comportamento esperado da aplicação?
  - Como ela deveria reagir se o usuário tentar acessar dados de outro usuário?
```

### Atividades Guiadas com Ferramentas e Testes

```
Indique como Utilizar o Burp Suite para identificar falhas de controle de acesso em uma aplicação de testes.
- Quais métodos é possível usar para testar permissões de diferentes usuários?
- Como o Burp Comparer pode ajudar a detectar diferenças entre respostas autenticadas e não autorizadas?

- Configure dois usuários: um com privilégios administrativos e outro com perfil comum.
  - Simule ações com o perfil comum tentando acessar endpoints administrativos.
  - Registre as requisições e respostas. O que foi possível acessar sem autorização?

- Crie um teste automatizado (em Gherkin) para validar que um usuário não pode acessar dados de outro.
```

### Aplicação em Cenários Reais

```
Pesquise um caso real onde falhas de controle de acesso causaram grandes impactos.
- Qual foi o incidente? Que tipo de dado foi exposto ou comprometido?
- A falha foi decorrente de lógica mal implementada, permissões mal configuradas, ou ausência de validação?

- Proponha uma estratégia de defesa em camadas para evitar Broken Access Control em um sistema real.
- Que práticas de desenvolvimento seguro podem ser adotadas por equipes de backend e frontend?
```

### Conclusão

```
Explique o que é Broken Access Control e como identificá-lo.
- Quais são os sinais de que uma aplicação pode estar vulnerável?
- Por que é tão difícil garantir controle de acesso corretamente em sistemas grandes?
```