## Protocolo HTTP

Referências:

* [HTTP - Mozilla](https://developer.mozilla.org/pt-BR/docs/Web/HTTP)

### Introdução
```
Explique o que é o protocolo HTTP, sua importância para a comunicação na web e como ele funciona em alto nível.
- O que significa dizer que o HTTP é um protocolo "sem estado"?
- Como o modelo cliente-servidor é aplicado no HTTP?
- Quais são as principais versões do protocolo HTTP e suas diferenças?

- Crie um fluxograma simples representando o ciclo de uma requisição HTTP.
- Exemplifique uma comunicação entre navegador e servidor usando HTTP.
``` 

### Estrutura de uma Requisição HTTP
```
Descreva como é estruturada uma requisição HTTP.
- Quais são as partes de uma requisição HTTP (linha de requisição, cabeçalhos, corpo)?
- Explique o que é uma URL e como ela se relaciona com a requisição HTTP.
- Mostre um exemplo textual de uma requisição HTTP feita manualmente.
```

### Estrutura de uma Resposta HTTP
```
Detalhe a estrutura de uma resposta HTTP.
- O que compõe uma resposta HTTP (linha de status, cabeçalhos, corpo)?
- O que significa o "status code" na resposta?
- Apresente um exemplo de resposta HTTP completa simulada.
```

### Métodos HTTP (GET, POST, PUT, DELETE, etc.)
```
Explique o papel dos métodos HTTP e quando utilizar cada um.
- Descreva as diferenças entre GET e POST.
- Quando usar PUT em vez de PATCH?
- O que é o método OPTIONS e qual sua utilidade?
- Escreva exemplos práticos (pseudocódigo ou curl) para uma requisição GET e uma POST.
```

### Códigos de Status HTTP
```
Explore a classificação dos códigos de status HTTP.
- Quais são as categorias dos códigos (1xx, 2xx, 3xx, 4xx, 5xx)?
- Explique os códigos 200, 201, 301, 400, 401, 403, 404, 500 e 503.
- O que é redirecionamento permanente e temporário?
- Monte um quadro resumindo os códigos de status mais importantes com exemplos práticos de resposta.
```

### Cabeçalhos HTTP (Headers)

```
Analise o que são cabeçalhos HTTP e sua importância.
- O que são headers de requisição? Cite exemplos.
- O que são headers de resposta? Cite exemplos.
- Quais são os cabeçalhos mais comuns (Content-Type, Authorization, Cookie, etc.)?
- Mostre exemplos de cabeçalhos usados em uma requisição de autenticação.
```

### Cookies, Sessões e Tokens
```
Explique o papel dos cookies em HTTP e sua relação com sessões.
- Como os cookies são enviados e recebidos via HTTP?
- O que são cookies HttpOnly e Secure?
- Diferencie sessão baseada em cookie e autenticação baseada em token (ex: JWT).
- Crie um exemplo de resposta HTTP que configura um cookie seguro.
```

### Cache no HTTP

```
Discuta o funcionamento do cache HTTP e sua importância.
- O que é cache-control e quais são seus principais directives?
- Qual a diferença entre cache privado e cache público?
- Como funciona a estratégia de validação usando ETag?
- Escreva exemplos de cabeçalhos para controlar cache de forma eficiente.
```

### 9. HTTPS e Segurança no Transporte de Dados
```
Apresente o conceito de HTTPS e como ele garante segurança na comunicação.
- Como o SSL/TLS atua na segurança de dados no HTTP?
- O que são certificados digitais?
- Qual a diferença entre HTTP e HTTPS na prática?
- Descreva o que acontece durante o processo de handshake SSL/TLS.
```

### APIs e HTTP (REST)

```
Relacione o uso do HTTP no desenvolvimento de APIs RESTful.
- O que caracteriza uma API RESTful?
- Como métodos HTTP e códigos de status são usados em APIs?
- O que são boas práticas para construir endpoints em APIs?
- Modele um exemplo simples de API REST para gerenciamento de tarefas.
```

### HTTP/2 e HTTP/3
```
Explore as diferenças e vantagens do HTTP/2 e HTTP/3 em relação ao HTTP/1.1.
- O que é multiplexação no HTTP/2?
- Como o QUIC impacta a performance do HTTP/3?
- Quais são os benefícios de migrar para HTTP/2 ou HTTP/3?
- Monte um quadro comparativo entre HTTP/1.1, HTTP/2 e HTTP/3.
```