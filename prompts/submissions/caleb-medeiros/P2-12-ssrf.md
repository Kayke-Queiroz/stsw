# P2-12 - OWASP Top Ten: SSRF

## 1. Introducao a SSRF

SSRF, Server-Side Request Forgery, ocorre quando uma aplicacao aceita uma URL ou destino controlado pelo usuario e faz uma requisicao a partir do servidor sem validacao adequada. O atacante nao acessa diretamente o alvo interno; ele induz o servidor vulneravel a fazer essa requisicao em seu lugar.

Essa falha e critica porque o servidor normalmente tem acesso a redes, metadados e sistemas que nao estao expostos ao publico. Assim, uma funcionalidade aparentemente simples, como "buscar imagem por URL", pode virar ponte para acessar `localhost`, servicos internos, interfaces administrativas, bancos, Redis, Elasticsearch ou endpoints de metadata em cloud.

Em ataques client-side, o codigo malicioso roda no navegador da vitima ou usa o navegador como agente. Em SSRF, a requisicao parte do backend. Isso muda o impacto: firewall, ACLs e confianca baseada em rede podem considerar a requisicao legitima porque ela vem de dentro da infraestrutura.

Alvos comuns:

- `127.0.0.1` e `localhost`.
- Faixas privadas como `10.0.0.0/8`, `172.16.0.0/12` e `192.168.0.0/16`.
- Metadata services de cloud, como `http://169.254.169.254/latest/meta-data/`.
- Painel administrativo interno.
- Servicos sem autenticacao expostos apenas na rede interna.
- APIs de microservicos confiando apenas na origem de rede.

A OWASP incluiu SSRF no Top 10 de 2021 porque arquiteturas modernas usam microservicos, cloud, metadata endpoints, webhooks e integracoes HTTP entre sistemas. Isso ampliou a superficie de ataque e aumentou o impacto de uma URL validada incorretamente.

Impactos:

- Leitura de dados internos.
- Descoberta de portas e servicos.
- Roubo de credenciais temporarias de cloud.
- Bypass de firewall por pivot interno.
- Acesso a endpoints administrativos.
- Execucao indireta de acoes em servicos internos.

## 2. Exemplos praticos de ataque

Considere uma aplicacao que permite ao usuario enviar uma URL para buscar imagem de perfil:

```text
POST /perfil/imagem
imageUrl=https://cdn.exemplo.com/avatar.png
```

Se o backend apenas faz download da URL recebida, um atacante pode enviar:

```text
http://127.0.0.1:8080/admin
http://10.0.0.5:9200/_cluster/health
http://169.254.169.254/latest/meta-data/
```

O servidor vulneravel acessaria esses destinos a partir da rede interna.

### Ataque contra metadata da AWS

Em ambientes cloud, o endereco `http://169.254.169.254/latest/meta-data/` fornece metadados da instancia. Se a instancia estiver mal configurada e a aplicacao permitir SSRF, o atacante pode tentar:

```bash
curl -X POST "https://app.exemplo.com/fetch" \
  -H "Content-Type: application/json" \
  -d '{"url":"http://169.254.169.254/latest/meta-data/iam/security-credentials/"}'
```

O objetivo seria obter informacoes sobre o perfil IAM e, em cenarios mal protegidos, credenciais temporarias associadas a esse perfil.

### Codigo Java vulneravel

```java
@PostMapping("/fetch")
public String fetch(@RequestParam String url) throws IOException {
    URL target = new URL(url);
    try (InputStream in = target.openStream()) {
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
}
```

Problema: o parametro `url` e usado diretamente. O codigo nao valida esquema, dominio, porta, IP resolvido, redirecionamentos nem destino final.

Exploracao:

```bash
curl -X POST "http://localhost:8080/fetch" \
  -d "url=http://127.0.0.1:8080/admin"
```

Se houver um servico interno em `/admin`, a resposta pode vazar conteudo restrito.

### SSRF simples x SSRF cego

No SSRF simples, a resposta do destino interno volta para o atacante na resposta da aplicacao. Exemplo: o conteudo de `http://localhost/admin` aparece na tela.

No SSRF cego, o atacante nao ve a resposta diretamente. Ele infere o comportamento por tempo, tamanho, codigo HTTP, mensagens indiretas ou por interacao com um servidor controlado por ele. Exemplo: enviar uma URL para `https://meu-servidor.teste/ping` e verificar se recebeu uma chamada.

### Port scanning interno

O atacante pode testar portas internas e comparar respostas:

```text
http://10.0.0.10:22
http://10.0.0.10:80
http://10.0.0.10:8080
http://10.0.0.10:9200
```

Mesmo sem ver o corpo da resposta, diferencas de timeout, erro de conexao e tempo de resposta podem revelar quais portas estao abertas.

## 3. Mitigacoes e boas praticas

A defesa mais forte e evitar que usuarios controlem destinos de rede. Quando isso nao for possivel, a aplicacao deve aplicar allow list, validacao robusta, restricoes de rede e monitoramento.

### Deny list x allow list

Deny list tenta bloquear destinos perigosos, como `localhost` e `169.254.169.254`. Ela e fragil porque atacantes podem usar representacoes alternativas de IP, DNS, redirects, IPv6, encoding e DNS rebinding.

Allow list permite apenas destinos explicitamente aprovados, como `cdn.exemplo.com` e `imagens.parceiro.com`. E mais eficaz porque reduz o universo de destinos permitidos.

### Por que validar apenas hostname nao basta

Validar apenas texto do hostname e insuficiente. Exemplos de bypass:

- `localhost.evil.com` passa em filtro ingenuo que procura `localhost`.
- IPs em decimal, octal, hexadecimal ou IPv6 podem representar loopback.
- DNS pode resolver inicialmente para IP publico e depois para IP privado.
- Redirecionamentos HTTP podem levar para rede interna.
- O hostname permitido pode ser controlado por atacante em subdominio.

### DNS rebinding

DNS rebinding ocorre quando um dominio controlado pelo atacante resolve para um IP permitido no momento da validacao e depois passa a resolver para IP privado no momento da conexao. Se a aplicacao valida apenas uma vez e nao fixa o IP final, o atacante pode burlar controles.

### Defesa em profundidade

- Usar allow list de dominios e, quando possivel, de IPs.
- Permitir apenas esquemas `https` e `http` quando estritamente necessario.
- Bloquear IPs privados, loopback, link-local, multicast e metadata cloud.
- Desabilitar ou validar redirecionamentos.
- Resolver DNS e validar todos os IPs retornados.
- Aplicar egress firewall: aplicacao so pode sair para destinos necessarios.
- Segmentar rede entre aplicacao, banco e servicos administrativos.
- Usar IMDSv2 ou equivalente em cloud.
- Monitorar requisicoes de saida anormais.
- Definir timeout e limite de tamanho de resposta.

## 4. Funcao segura de validacao de URL

Exemplo simplificado em Java:

```java
private static final Set<String> ALLOWED_HOSTS = Set.of(
    "cdn.exemplo.com",
    "imagens.parceiro.com"
);

public URI validarUrlExterna(String entrada) throws Exception {
    URI uri = new URI(entrada).normalize();

    if (!List.of("https").contains(uri.getScheme())) {
        throw new IllegalArgumentException("Esquema nao permitido");
    }

    String host = uri.getHost();
    if (host == null || !ALLOWED_HOSTS.contains(host.toLowerCase(Locale.ROOT))) {
        throw new IllegalArgumentException("Host nao permitido");
    }

    InetAddress[] enderecos = InetAddress.getAllByName(host);
    for (InetAddress addr : enderecos) {
        if (addr.isAnyLocalAddress()
            || addr.isLoopbackAddress()
            || addr.isLinkLocalAddress()
            || addr.isSiteLocalAddress()
            || addr.isMulticastAddress()) {
            throw new IllegalArgumentException("IP interno bloqueado");
        }
    }

    if (uri.getPort() != -1 && uri.getPort() != 443) {
        throw new IllegalArgumentException("Porta nao permitida");
    }

    return uri;
}
```

Ao fazer a requisicao, tambem e necessario:

- Nao seguir redirecionamentos automaticamente ou validar o destino de cada redirect.
- Usar timeout curto.
- Limitar tamanho do download.
- Registrar host, IP resolvido e status.

Restricao por rede:

```text
Aplicacao web -> permitido apenas HTTPS para cdn.exemplo.com
Aplicacao web -> bloqueado para 127.0.0.0/8
Aplicacao web -> bloqueado para 10.0.0.0/8
Aplicacao web -> bloqueado para 169.254.169.254/32
Aplicacao web -> bloqueado para sub-redes administrativas
```

## 5. Deteccao e exploracao com SSRFmap

SSRFmap e uma ferramenta usada em testes autorizados para automatizar exploracao de SSRF. Ela envia payloads para um parametro vulneravel e tenta mapear servicos ou explorar destinos internos por meio da aplicacao.

Ela pode ajudar a:

- Testar acesso a localhost.
- Testar metadata services.
- Descobrir portas internas.
- Verificar interacao com servidores externos controlados pelo testador.
- Automatizar modulos especificos de exploracao.

Modulos ativos fazem requisicoes e tentam explorar comportamentos. Modulos passivos observam respostas e interacoes sem necessariamente executar acoes mais agressivas.

Exemplo em laboratorio controlado:

```bash
python3 ssrfmap.py \
  -r request.txt \
  -p url \
  -m portscan
```

Onde `request.txt` contem uma requisicao capturada, por exemplo:

```http
POST /fetch HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

url=http://example.com
```

O parametro `-p url` indica que `url` sera substituido pelos payloads. Os resultados devem ser interpretados como indicios: status HTTP, tempo de resposta, erros e conteudo retornado. Confirmacao manual e necessaria para reduzir falsos positivos.

Boas praticas de uso:

- Executar apenas em ambiente autorizado.
- Usar dados ficticios.
- Limitar escopo de IPs e portas.
- Registrar payloads e evidencias.
- Evitar varreduras agressivas em rede compartilhada.

## 6. Estudo de caso: Capital One

Um caso real associado a SSRF foi o incidente da Capital One em 2019. Em alto nivel, uma falha em componente exposto permitiu que requisicoes fossem feitas para o metadata service em ambiente cloud, obtendo credenciais temporarias com permissoes excessivas. Com essas credenciais, dados armazenados em cloud foram acessados indevidamente.

Falhas relevantes:

- Componente exposto permitia comportamento semelhante a SSRF.
- Credenciais temporarias acessiveis pelo metadata service tinham permissoes amplas.
- Controles de rede e monitoramento nao impediram a exploracao em tempo habil.
- A arquitetura confiava demais na posicao interna da requisicao.

Dados comprometidos incluiriam informacoes de clientes e solicitantes de produtos financeiros. A resposta envolveu investigacao, comunicacao publica, cooperacao com autoridades, revisao de controles e endurecimento de configuracoes.

Requisitos que poderiam reduzir o risco:

- Bloqueio de acesso ao metadata service a partir da aplicacao, salvo necessidade formal.
- Uso de versoes de metadata com protecao contra SSRF, como requisicao com token de sessao.
- IAM com menor privilegio.
- Regras de egress restritivas.
- Validacao robusta de destinos externos.
- Alertas para chamadas incomuns a `169.254.169.254`.
- Revisoes de arquitetura para componentes expostos.

Medidas pos-incidente:

- Rotacionar credenciais.
- Auditar permissoes cloud.
- Revisar logs de acesso a dados.
- Adotar detecao de comportamento anomalo.
- Criar testes de SSRF em pipeline.
- Treinar equipes sobre riscos de requisicoes server-side.

## 7. Atividade guiada: simulacao e mitigacao

### Aplicacao vulneravel

```java
@PostMapping("/avatar")
public ResponseEntity<String> baixarAvatar(@RequestParam String imageUrl) throws IOException {
    String body = new String(new URL(imageUrl).openStream().readAllBytes(), StandardCharsets.UTF_8);
    return ResponseEntity.ok(body);
}
```

Ataque:

```bash
curl -X POST "http://localhost:8080/avatar" \
  -d "imageUrl=http://127.0.0.1:8080/admin/health"
```

Resultado vulneravel esperado:

```json
{"status":"ok","database":"up","internal":true}
```

### Automatizacao com SSRFmap

```bash
python3 ssrfmap.py -r request.txt -p imageUrl -m portscan
```

Evidencia esperada: ferramenta indicando diferencas entre portas fechadas, timeouts e endpoints internos acessiveis.

### Correcao com allow list

```java
@PostMapping("/avatar")
public ResponseEntity<String> baixarAvatarSeguro(@RequestParam String imageUrl) throws Exception {
    URI uri = validarUrlExterna(imageUrl);

    HttpClient client = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NEVER)
        .connectTimeout(Duration.ofSeconds(3))
        .build();

    HttpRequest request = HttpRequest.newBuilder(uri)
        .timeout(Duration.ofSeconds(5))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return ResponseEntity.ok(response.body());
}
```

Novo teste:

```bash
curl -i -X POST "http://localhost:8080/avatar" \
  -d "imageUrl=http://127.0.0.1:8080/admin/health"
```

Resultado seguro esperado:

```http
HTTP/1.1 400 Bad Request

Host nao permitido
```

Conclusao da atividade: a exploracao funciona quando a aplicacao trata URL do usuario como destino confiavel. A mitigacao efetiva combina allow list, validacao do IP resolvido, bloqueio de redes internas, controle de redirects e egress firewall.

## 8. Conclusao

SSRF e perigosa porque transforma o servidor da aplicacao em um proxy involuntario para redes internas. Suas principais causas sao funcionalidades que fazem requisicoes para URLs fornecidas pelo usuario, validacao baseada em deny list, confianca em rede interna e permissoes excessivas em cloud.

Mitigacoes principais:

- Evitar URLs arbitrarias.
- Usar allow list.
- Validar esquema, host, porta e IP resolvido.
- Bloquear redes privadas e metadata endpoints.
- Nao seguir redirects sem revalidacao.
- Restringir trafego de saida por firewall.
- Aplicar menor privilegio em cloud.
- Monitorar requisicoes de saida.

O aspecto mais dificil na pratica e garantir que a validacao corresponda ao destino real final da conexao. DNS, redirects, formatos alternativos de IP, IPv6, proxies e mudancas de infraestrutura tornam SSRF uma vulnerabilidade que exige defesa em profundidade, nao apenas uma validacao textual simples.
