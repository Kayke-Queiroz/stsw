# P2-14 - OWASP Top Ten: Broken Access Control

## 1. Introducao

Broken Access Control ocorre quando uma aplicacao nao aplica corretamente as regras que definem o que cada usuario pode acessar ou executar. A autenticacao responde "quem e o usuario"; a autorizacao responde "o que esse usuario pode fazer". Broken Access Control e uma falha de autorizacao.

Essa categoria aparece em primeiro lugar no OWASP Top 10 de 2021 porque e muito frequente e tem impacto alto. Sistemas modernos possuem muitas rotas, perfis, APIs, microservicos, objetos de negocio e regras condicionais. Se a verificacao de permissao fica espalhada ou depende do frontend, algum caminho tende a ficar exposto.

Principios de controle de acesso:

- Negar por padrao.
- Aplicar autorizacao no servidor, nao apenas na interface.
- Usar menor privilegio.
- Validar permissao em cada requisicao.
- Verificar propriedade do objeto acessado.
- Centralizar politicas quando possivel.
- Registrar acessos negados e acoes sensiveis.
- Nao confiar em IDs, parametros ou tokens modificaveis pelo cliente.

## 2. Autenticacao x autorizacao

Autenticacao confirma identidade. Exemplo: o usuario fez login com senha e MFA.

Autorizacao verifica permissao. Exemplo: mesmo autenticado, um usuario comum nao pode acessar `/admin` nem consultar pedidos de outro usuario.

Uma aplicacao pode autenticar corretamente e ainda estar vulneravel se permitir que qualquer usuario autenticado acesse objetos ou funcoes que nao pertencem a ele.

## 3. Exemplos comuns de Broken Access Control

| Falha | Descricao | Exemplo |
|---|---|---|
| IDOR | Usuario altera ID direto de objeto e acessa dado de outro usuario. | `/api/pedidos/1002` em vez de `/api/pedidos/1001`. |
| Bypass por URL | Rota administrativa existe e nao checa perfil no backend. | `/admin/usuarios` acessivel por usuario comum. |
| Escalada vertical | Usuario comum executa acao de administrador. | Alterar papel de outro usuario. |
| Escalada horizontal | Usuario acessa dados de outro usuario do mesmo nivel. | Cliente A ve fatura do cliente B. |
| Forja de token | Aplicacao confia em claims alteraveis ou token sem assinatura valida. | Mudar `"role":"user"` para `"role":"admin"`. |
| Falha em metodo HTTP | GET protegido, mas DELETE/PATCH sem verificacao. | `DELETE /api/users/2` permitido. |
| CORS permissivo | API permite origem indevida com credenciais. | `Access-Control-Allow-Origin: *` com cookies. |
| Force browsing | Usuario acessa recurso oculto digitando URL. | `/relatorios/financeiro.xlsx`. |
| Falta de verificacao no objeto | Verifica login, mas nao propriedade do recurso. | Baixar arquivo pelo ID sequencial. |

Impactos:

- Exfiltracao de dados pessoais e financeiros.
- Acesso a informacoes administrativas.
- Alteracao ou exclusao de dados de terceiros.
- Escalada de privilegios.
- Fraude em compras, pedidos, reembolsos ou beneficios.
- Violacao de privacidade e requisitos legais.

## 4. Exemplo de API insegura com IDOR

Codigo vulneravel:

```java
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
```

Falha: o endpoint exige apenas o ID do pedido. Se o usuario autenticado for dono do pedido `10`, ele pode tentar acessar `11`, `12` ou qualquer outro ID. Isso e IDOR porque o objeto direto, `pedido`, e referenciado por um identificador previsivel sem verificacao de propriedade.

Ataque:

```bash
curl -i "https://loja.exemplo.com/api/pedidos/10" \
  -H "Authorization: Bearer token-do-usuario-a"

curl -i "https://loja.exemplo.com/api/pedidos/11" \
  -H "Authorization: Bearer token-do-usuario-a"
```

Se a segunda resposta retorna pedido do usuario B, ha Broken Access Control.

## 5. API segura com verificacao de permissao

Codigo corrigido:

```java
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id, Authentication auth) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!authorizationService.podeVerPedido(auth.getName(), pedido)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return pedido;
    }
}
```

Servico de autorizacao:

```java
public boolean podeVerPedido(String emailUsuario, Pedido pedido) {
    return pedido.getCliente().getEmail().equals(emailUsuario)
        || usuarioRepository.temPapel(emailUsuario, "ADMIN");
}
```

Comportamento esperado:

- Usuario dono do pedido recebe `200 OK`.
- Usuario autenticado, mas sem permissao, recebe `403 Forbidden`.
- Usuario nao autenticado recebe `401 Unauthorized`.
- Recurso inexistente pode retornar `404 Not Found`.

Em sistemas sensiveis, pode-se retornar `404` para recurso existente sem permissao, evitando revelar existencia. A politica deve ser consistente.

## 6. Bypass por parametro na URL

Exemplo vulneravel:

```http
GET /api/conta?userId=2 HTTP/1.1
Cookie: session=usuario-1
```

Se a aplicacao usa `userId=2` para decidir qual conta retornar, o usuario 1 pode manipular o parametro. O correto e obter o usuario autenticado da sessao/token e ignorar IDs de usuario fornecidos pelo cliente, salvo quando houver verificacao explicita de permissao.

Resposta segura:

```http
HTTP/1.1 403 Forbidden
Content-Type: application/json

{"erro":"acesso negado"}
```

Ou, se a API for para "minha conta", nem deve aceitar `userId`:

```http
GET /api/minha-conta HTTP/1.1
Cookie: session=usuario-1
```

O backend identifica o usuario pela sessao.

## 7. Testes com Burp Suite

Burp Suite ajuda a identificar Broken Access Control porque permite capturar, modificar e repetir requisicoes.

Metodos de teste:

- Criar dois usuarios: um administrador e um usuario comum.
- Capturar requisicoes administrativas com o usuario admin.
- Repetir a mesma requisicao usando cookie/token do usuario comum.
- Alterar IDs em URLs, parametros, JSON e headers.
- Trocar metodo HTTP, como GET para DELETE.
- Remover token para verificar se endpoint exige autenticacao.
- Usar Burp Comparer para comparar respostas autorizadas e negadas.

Exemplo de procedimento:

1. Login como admin.
2. Acessar `/admin/users`.
3. Enviar requisicao para Repeater.
4. Login como usuario comum em outra sessao.
5. Substituir o cookie/token da requisicao admin pelo cookie/token comum.
6. Reenviar.
7. Resultado seguro: `403 Forbidden`.
8. Resultado vulneravel: `200 OK` com dados administrativos.

Burp Comparer pode mostrar se a resposta do usuario comum tem o mesmo tamanho, conteudo e estrutura da resposta admin. Se forem quase identicas, ha forte indicio de falta de autorizacao.

## 8. Teste automatizado em Gherkin

```gherkin
Feature: Controle de acesso a pedidos

  Scenario: Usuario nao pode acessar pedido de outro usuario
    Given existe um usuario "alice@example.com"
    And existe um usuario "bob@example.com"
    And existe um pedido "PED-2" pertencente a "bob@example.com"
    And estou autenticado como "alice@example.com"
    When eu envio GET "/api/pedidos/PED-2"
    Then a resposta deve ter status 403
    And o corpo da resposta nao deve conter dados do pedido "PED-2"

  Scenario: Administrador pode acessar pedido de qualquer usuario
    Given existe um usuario administrador "admin@example.com"
    And existe um pedido "PED-2" pertencente a "bob@example.com"
    And estou autenticado como "admin@example.com"
    When eu envio GET "/api/pedidos/PED-2"
    Then a resposta deve ter status 200
    And o corpo da resposta deve conter o identificador "PED-2"
```

Esse tipo de teste deve ser repetido para leitura, edicao, exclusao e acoes sensiveis.

## 9. Caso real

Um exemplo conhecido de falha de controle de acesso foi a exposicao de documentos em sistemas que usavam URLs previsiveis ou parametros sequenciais sem checar se o usuario tinha permissao para o documento solicitado. Em incidentes desse tipo, o atacante ou pesquisador altera o identificador na URL e consegue baixar documentos de terceiros.

Natureza da falha:

- Logica de autorizacao ausente ou aplicada apenas na tela.
- IDs sequenciais facilitando enumeracao.
- Servidor retornando documento com base apenas no ID.
- Falta de logs e alertas para acesso massivo a objetos.

Dados potencialmente expostos:

- Documentos pessoais.
- Contratos.
- Dados financeiros.
- Informacoes cadastrais.

Defesa em camadas:

- IDs nao previsiveis ajudam, mas nao substituem autorizacao.
- Verificacao de propriedade em todo endpoint.
- Politicas centralizadas por papel, escopo e objeto.
- Logs de acesso por usuario e recurso.
- Alertas para enumeracao de IDs.
- Testes automatizados de autorizacao horizontal e vertical.
- Revisao de endpoints administrativos.
- Separacao de permissoes entre frontend e backend.

## 10. Praticas para backend e frontend

Backend:

- Implementar autorizacao em todos os endpoints.
- Usar `@PreAuthorize` ou servicos de autorizacao consistentes.
- Evitar confiar em parametros como `isAdmin=true`.
- Validar ownership do recurso.
- Aplicar menor privilegio em banco e servicos.
- Escrever testes para usuario dono, usuario nao dono e admin.
- Tratar `401` e `403` corretamente.
- Registrar tentativas negadas.

Frontend:

- Esconder botoes que o usuario nao pode usar melhora UX, mas nao e controle de seguranca.
- Nao armazenar papel ou permissao como fonte de verdade manipulavel.
- Tratar erros `401` e `403` sem vazar dados.
- Nao montar chamadas administrativas apenas com base em flags locais.

## 11. Conclusao

Broken Access Control e a falha em aplicar corretamente regras de autorizacao. Ela pode ser identificada quando um usuario acessa recursos de outro, executa acoes administrativas, manipula IDs, altera parametros de perfil ou acessa URLs ocultas sem permissao.

Sinais de vulnerabilidade:

- Endpoints que recebem `userId` do cliente para decidir acesso.
- IDs sequenciais retornando dados de outros usuarios.
- Rota administrativa protegida apenas pelo menu do frontend.
- Diferenca confusa entre `401` e `403`.
- Falta de testes automatizados de autorizacao.
- Tokens aceitos sem validacao robusta de assinatura e claims.

E dificil garantir controle de acesso em sistemas grandes porque regras mudam, perfis se multiplicam, microservicos duplicam logica e funcionalidades novas criam endpoints rapidamente. Por isso, a autorizacao deve ser projetada como requisito central: negar por padrao, checar permissao no servidor, validar propriedade do objeto e testar continuamente os cenarios de abuso.
