# P2-10 - Introducao ao Protocolo HTTP

## 1. O que e HTTP

HTTP, Hypertext Transfer Protocol, e o protocolo de aplicacao usado para transferencia de recursos na Web. Ele define como um cliente, normalmente navegador, aplicativo mobile ou cliente de API, envia uma requisicao para um servidor e como o servidor responde com um recurso, um erro ou uma instrucao de redirecionamento.

Sua importancia esta no fato de ser a base da comunicacao web: paginas HTML, APIs REST, imagens, arquivos, autenticacao, cookies, caches, downloads e integracoes entre servicos dependem de mensagens HTTP bem formadas. Mesmo quando a conexao e protegida por TLS, o protocolo de aplicacao continua sendo HTTP, agora transportado como HTTPS.

### HTTP sem estado

Dizer que HTTP e "sem estado" significa que cada requisicao e independente. O protocolo, por si so, nao lembra que uma requisicao anterior veio do mesmo usuario. Se um usuario faz login e depois abre a pagina de pedidos, o servidor so reconhece aquele usuario porque algum mecanismo adicional e enviado junto com a nova requisicao, como cookie de sessao, token JWT ou outro cabecalho de autenticacao.

Isso simplifica a arquitetura, mas exige mecanismos de sessao e autenticacao para manter continuidade entre interacoes.

### Modelo cliente-servidor

No HTTP, o cliente inicia a comunicacao e o servidor responde:

1. O cliente monta uma requisicao com metodo, URL, cabecalhos e, opcionalmente, corpo.
2. O servidor interpreta a requisicao.
3. O servidor executa a acao solicitada, como consultar dados, criar um recurso ou retornar um arquivo.
4. O servidor envia uma resposta com codigo de status, cabecalhos e corpo.
5. O cliente interpreta a resposta e atualiza a interface ou processa os dados.

### Principais versoes

| Versao | Caracteristicas principais |
|---|---|
| HTTP/0.9 | Versao inicial, muito simples, usada basicamente para obter documentos HTML. |
| HTTP/1.0 | Introduziu cabecalhos, codigos de status e metadados mais completos. |
| HTTP/1.1 | Tornou conexoes persistentes comuns, adicionou melhorias de cache, host virtual e suporte mais robusto a proxies. |
| HTTP/2 | Usa comunicacao binaria, multiplexacao, compressao de cabecalhos e melhor aproveitamento de uma unica conexao TCP. |
| HTTP/3 | Usa QUIC sobre UDP em vez de TCP, reduzindo latencia e melhorando comportamento em redes instaveis. |

### Fluxograma do ciclo HTTP

```text
Usuario/Navegador
       |
       v
Monta requisicao HTTP
       |
       v
Resolve DNS e abre conexao
       |
       v
Envia metodo + URL + headers + corpo
       |
       v
Servidor processa a requisicao
       |
       v
Servidor retorna status + headers + corpo
       |
       v
Cliente interpreta a resposta
       |
       v
Pagina/API e exibida ou processada
```

### Exemplo de comunicacao navegador-servidor

Um usuario acessa `https://loja.exemplo.com/produtos/10`.

O navegador envia:

```http
GET /produtos/10 HTTP/1.1
Host: loja.exemplo.com
Accept: text/html
User-Agent: Mozilla/5.0
```

O servidor responde:

```http
HTTP/1.1 200 OK
Content-Type: text/html; charset=utf-8
Cache-Control: private, max-age=60

<html>
  <body>Produto 10</body>
</html>
```

## 2. Estrutura de uma requisicao HTTP

Uma requisicao HTTP tem tres partes principais:

| Parte | Funcao |
|---|---|
| Linha de requisicao | Informa metodo, caminho/URL e versao HTTP. |
| Cabecalhos | Enviam metadados, como host, tipo de conteudo, cookies e autenticacao. |
| Corpo | Opcional. Contem dados enviados ao servidor, comum em POST, PUT e PATCH. |

Exemplo:

```http
POST /api/tarefas HTTP/1.1
Host: api.exemplo.com
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9

{
  "titulo": "Estudar HTTP",
  "concluida": false
}
```

### URL e relacao com HTTP

URL, Uniform Resource Locator, identifica um recurso. Uma URL completa pode conter:

```text
https://api.exemplo.com:443/tarefas/10?expand=usuario#comentarios
```

| Componente | Exemplo | Significado |
|---|---|---|
| Esquema | `https` | Protocolo usado. |
| Host | `api.exemplo.com` | Servidor de destino. |
| Porta | `443` | Porta da conexao. |
| Caminho | `/tarefas/10` | Recurso solicitado. |
| Query string | `expand=usuario` | Parametros adicionais. |
| Fragmento | `comentarios` | Referencia no cliente; normalmente nao e enviada ao servidor. |

Exemplo manual de requisicao:

```http
GET /api/tarefas?status=aberta HTTP/1.1
Host: api.exemplo.com
Accept: application/json
Cookie: sessionId=abc123
```

## 3. Estrutura de uma resposta HTTP

Uma resposta HTTP normalmente contem:

| Parte | Funcao |
|---|---|
| Linha de status | Versao HTTP, codigo numerico e frase descritiva. |
| Cabecalhos | Metadados sobre resposta, servidor, cache, cookies e tipo de conteudo. |
| Corpo | Dados retornados, como HTML, JSON, imagem ou mensagem de erro. |

O status code indica o resultado da requisicao. Por exemplo, `200` significa sucesso, `404` significa recurso nao encontrado e `500` indica erro interno no servidor.

Exemplo completo simulado:

```http
HTTP/1.1 201 Created
Content-Type: application/json
Location: /api/tarefas/42
Cache-Control: no-store

{
  "id": 42,
  "titulo": "Estudar HTTP",
  "concluida": false
}
```

## 4. Metodos HTTP

Os metodos indicam a intencao da requisicao.

| Metodo | Uso comum | Observacao |
|---|---|---|
| GET | Consultar recurso | Nao deve alterar estado no servidor. |
| POST | Criar recurso ou executar acao | Pode ter corpo e gerar novo estado. |
| PUT | Substituir completamente um recurso | Deve enviar a representacao completa. |
| PATCH | Alterar parcialmente um recurso | Envia apenas campos modificados. |
| DELETE | Remover recurso | Pode retornar `204 No Content`. |
| OPTIONS | Descobrir metodos/capacidades permitidos | Usado tambem em CORS preflight. |
| HEAD | Consultar headers sem corpo | Util para verificar metadados. |

### GET x POST

GET e usado para leitura. Seus parametros normalmente ficam na URL, como `/produtos?categoria=livros`. Deve ser seguro e idempotente, isto e, repetir a requisicao nao deveria alterar o estado do servidor.

POST e usado para criar recursos ou enviar dados que provocam processamento. O corpo da requisicao carrega os dados, por exemplo JSON de cadastro. POST nao e necessariamente idempotente: repetir um POST de compra pode criar duas compras se a API nao tiver protecao.

### PUT x PATCH

Use PUT quando o cliente estiver substituindo a representacao completa do recurso:

```json
{
  "titulo": "Nova tarefa",
  "concluida": true,
  "prioridade": "alta"
}
```

Use PATCH quando apenas parte do recurso deve mudar:

```json
{
  "concluida": true
}
```

### OPTIONS

OPTIONS informa quais metodos e configuracoes sao aceitos por um recurso. Em navegadores, aparece em requisicoes CORS como preflight, antes de um metodo ou cabecalho considerado sensivel.

Exemplos com `curl`:

```bash
curl -i "https://api.exemplo.com/tarefas?status=aberta"
```

```bash
curl -i -X POST "https://api.exemplo.com/tarefas" \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Estudar HTTP","concluida":false}'
```

## 5. Codigos de status HTTP

| Categoria | Significado |
|---|---|
| 1xx | Informacional. A requisicao continua em processamento. |
| 2xx | Sucesso. A requisicao foi recebida e processada. |
| 3xx | Redirecionamento. O cliente deve buscar outro endereco ou usar cache. |
| 4xx | Erro do cliente. A requisicao esta invalida, nao autorizada ou nao encontrou recurso. |
| 5xx | Erro do servidor. O servidor falhou ao processar requisicao aparentemente valida. |

| Codigo | Significado | Exemplo pratico |
|---|---|---|
| 200 OK | Requisicao concluida com sucesso. | Consulta de lista de tarefas. |
| 201 Created | Recurso criado. | POST cria uma nova tarefa. |
| 301 Moved Permanently | Recurso movido definitivamente. | Site migrou de HTTP para HTTPS. |
| 302 Found | Redirecionamento temporario. | Usuario nao logado e enviado para login. |
| 400 Bad Request | Requisicao mal formada. | JSON invalido no corpo. |
| 401 Unauthorized | Falta autenticacao valida. | Token ausente ou expirado. |
| 403 Forbidden | Usuario autenticado nao tem permissao. | Usuario comum tenta acessar painel admin. |
| 404 Not Found | Recurso nao encontrado. | Tarefa inexistente. |
| 500 Internal Server Error | Falha inesperada no servidor. | Excecao nao tratada. |
| 503 Service Unavailable | Servico indisponivel temporariamente. | Manutencao ou sobrecarga. |

Redirecionamento permanente usa codigos como `301` e `308`, indicando que o cliente pode atualizar referencias para o novo endereco. Redirecionamento temporario usa codigos como `302`, `303` e `307`, indicando que a mudanca nao deve ser considerada definitiva.

## 6. Cabecalhos HTTP

Headers sao metadados no formato `Nome: valor`. Eles nao sao o conteudo principal da mensagem, mas controlam como a mensagem deve ser interpretada.

### Headers de requisicao

Exemplos:

| Header | Funcao |
|---|---|
| Host | Informa o dominio de destino. |
| Accept | Tipos de resposta aceitos pelo cliente. |
| Content-Type | Tipo do corpo enviado. |
| Authorization | Credencial de autenticacao. |
| Cookie | Cookies enviados ao servidor. |
| User-Agent | Identificacao do cliente. |

### Headers de resposta

Exemplos:

| Header | Funcao |
|---|---|
| Content-Type | Tipo de conteudo retornado. |
| Set-Cookie | Define cookie no cliente. |
| Cache-Control | Controla cache. |
| Location | Indica recurso criado ou destino de redirecionamento. |
| ETag | Identificador de versao do recurso. |
| Strict-Transport-Security | Forca uso futuro de HTTPS. |

Exemplo de requisicao autenticada:

```http
GET /api/minha-conta HTTP/1.1
Host: api.exemplo.com
Accept: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9
Cookie: sessionId=abc123
```

## 7. Cookies, sessoes e tokens

Cookies sao pequenos valores armazenados pelo navegador e enviados automaticamente para o dominio correspondente. O servidor define cookies com `Set-Cookie`; o cliente os envia depois no header `Cookie`.

Exemplo de configuracao de cookie seguro:

```http
HTTP/1.1 200 OK
Content-Type: application/json
Set-Cookie: sessionId=abc123; HttpOnly; Secure; SameSite=Lax; Path=/; Max-Age=1800

{"status":"autenticado"}
```

`HttpOnly` impede que JavaScript leia o cookie, reduzindo impacto de XSS sobre cookies de sessao. `Secure` faz o navegador enviar o cookie apenas sobre HTTPS. `SameSite` reduz risco de CSRF ao controlar envio do cookie em navegacao cross-site.

Sessao baseada em cookie normalmente guarda um identificador opaco no cliente e o estado real no servidor. Token, como JWT, costuma carregar informacoes assinadas no proprio token e pode ser validado sem consultar a sessao central, embora revogacao e expiracao precisem ser bem desenhadas.

## 8. Cache HTTP

Cache reduz latencia, economiza banda e diminui carga no servidor. Ele deve ser configurado cuidadosamente para nao armazenar dados sensiveis.

`Cache-Control` e o principal header moderno. Diretivas comuns:

| Diretiva | Significado |
|---|---|
| `no-store` | Nao armazenar a resposta. Recomendado para dados sensiveis. |
| `no-cache` | Pode armazenar, mas deve revalidar antes de reutilizar. |
| `max-age=60` | Resposta fresca por 60 segundos. |
| `public` | Pode ser armazenada por caches compartilhados. |
| `private` | Apenas cache privado do usuario deve armazenar. |
| `must-revalidate` | Cache vencido precisa ser validado antes do uso. |

Cache privado pertence ao usuario, como cache do navegador. Cache publico pode ser compartilhado, como CDN ou proxy corporativo. Dados autenticados normalmente devem usar `private` ou `no-store`.

ETag funciona como uma versao do recurso:

```http
HTTP/1.1 200 OK
ETag: "tarefas-v5"
Cache-Control: private, max-age=0, must-revalidate
```

Na proxima requisicao:

```http
GET /api/tarefas HTTP/1.1
Host: api.exemplo.com
If-None-Match: "tarefas-v5"
```

Se nada mudou, o servidor responde:

```http
HTTP/1.1 304 Not Modified
ETag: "tarefas-v5"
```

Exemplos eficientes:

```http
Cache-Control: public, max-age=31536000, immutable
```

para arquivos versionados, como `app.9f3a.js`.

```http
Cache-Control: no-store
```

para extratos bancarios, tokens ou dados pessoais sensiveis.

## 9. HTTPS e seguranca no transporte

HTTPS e HTTP transportado dentro de uma conexao TLS. Ele protege confidencialidade, integridade e autenticidade do servidor.

HTTP puro trafega em texto claro: um intermediario na rede pode ler ou alterar requisicoes e respostas. HTTPS criptografa os dados e permite verificar se o servidor possui certificado valido para aquele dominio.

Certificados digitais vinculam uma chave publica a uma identidade, normalmente um dominio, e sao emitidos por autoridades certificadoras confiaveis. O navegador valida se o certificado nao expirou, se corresponde ao dominio e se a cadeia de confianca e aceita.

Resumo do handshake TLS:

1. Cliente informa versoes e algoritmos suportados.
2. Servidor envia certificado e parametros criptograficos.
3. Cliente valida o certificado.
4. Cliente e servidor negociam chaves de sessao.
5. A partir dai, trafego HTTP segue criptografado e autenticado.

## 10. APIs e HTTP REST

Uma API RESTful usa recursos identificados por URLs, metodos HTTP para operacoes e codigos de status para representar resultados. Ela deve ser stateless, ter representacoes padronizadas, normalmente JSON, e separar claramente recursos de acoes.

Boas praticas:

- Usar substantivos no endpoint: `/tarefas`, nao `/getTarefas`.
- Usar metodos corretamente: GET para leitura, POST para criacao, PATCH para alteracao parcial.
- Retornar status coerentes: `201` ao criar, `404` para recurso ausente, `403` para sem permissao.
- Versionar quando necessario: `/api/v1/tarefas`.
- Validar entrada e retornar erros padronizados.

Modelo simples de API de tarefas:

| Operacao | Requisicao | Resposta esperada |
|---|---|---|
| Listar tarefas | `GET /api/tarefas` | `200 OK` com lista. |
| Criar tarefa | `POST /api/tarefas` | `201 Created` com `Location`. |
| Obter tarefa | `GET /api/tarefas/42` | `200 OK` ou `404`. |
| Atualizar parte | `PATCH /api/tarefas/42` | `200 OK`. |
| Substituir | `PUT /api/tarefas/42` | `200 OK` ou `204`. |
| Remover | `DELETE /api/tarefas/42` | `204 No Content`. |

## 11. HTTP/2 e HTTP/3

HTTP/1.1 funciona bem, mas sofre com limitacoes de multiplas requisicoes concorrentes em uma conexao. HTTP/2 melhora isso com multiplexacao: varias requisicoes e respostas trafegam simultaneamente na mesma conexao sem exigir uma conexao TCP por recurso.

HTTP/3 usa QUIC, baseado em UDP. Isso reduz o custo de estabelecimento de conexao, melhora recuperacao em perda de pacotes e evita que a perda de um pacote bloqueie todos os fluxos como pode acontecer no TCP.

| Caracteristica | HTTP/1.1 | HTTP/2 | HTTP/3 |
|---|---|---|---|
| Formato | Texto | Binario | Binario sobre QUIC |
| Transporte | TCP | TCP | QUIC/UDP |
| Multiplexacao | Limitada | Sim | Sim, com melhor isolamento |
| Compressao de headers | Nao nativa | HPACK | QPACK |
| Latencia inicial | Maior | Menor que HTTP/1.1 | Menor, especialmente com retomada |
| Problema de perda de pacote | Afeta conexao TCP | Pode afetar varios fluxos | Melhor tratamento por fluxo QUIC |

Migrar para HTTP/2 ou HTTP/3 pode trazer menor latencia, melhor uso de conexoes, carregamento mais eficiente de paginas com muitos recursos e melhor experiencia em redes moveis. A migracao deve preservar configuracoes de seguranca, cache e observabilidade.
