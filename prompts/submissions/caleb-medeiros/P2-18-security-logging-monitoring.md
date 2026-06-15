# P2-18 - OWASP Top Ten: Security Logging and Monitoring Failures

## 1. Introducao

Security Logging and Monitoring Failures e a categoria A09:2021 do OWASP Top 10. Ela ocorre quando uma aplicacao nao registra eventos relevantes de seguranca, registra dados insuficientes, nao protege os logs, nao correlaciona eventos ou nao gera alertas em tempo habil para resposta a incidentes. O problema nao e apenas "nao ter log"; e nao conseguir detectar, investigar e responder a ataques.

Esse tipo de falha continua relevante porque sistemas modernos geram muitos eventos, mas nem todos sao uteis para seguranca. Sem estrategia clara, a equipe pode ter logs demais sem alerta, logs de menos para investigacao ou logs com dados sensiveis indevidos. Em producao, a ausencia de monitoramento e critica porque um ataque pode permanecer ativo por dias ou meses, aumentando vazamento, fraude e indisponibilidade.

Incidentes de grande impacto, como o caso Equifax, mostram que falhas de gestao de vulnerabilidades, visibilidade e resposta podem ampliar o dano. Quando uma organizacao nao detecta exploracoes rapidamente, perde a oportunidade de conter o ataque no inicio. Os impactos sao tecnicos, financeiros e organizacionais: vazamento de dados, investigacoes caras, perda de reputacao, multas, troca de liderancas e obrigacao de melhorar controles.

## 2. Relacao com deteccao de ataques

Logs e monitoramento sao os olhos do time de seguranca. Eles permitem identificar sinais como:

- muitas falhas de login;
- login bem-sucedido apos varias tentativas;
- acesso administrativo fora do horario;
- mudanca de permissao;
- criacao de usuario privilegiado;
- erros 500 em massa;
- consultas suspeitas;
- acessos a endpoints internos;
- alto volume de exportacao de dados;
- alteracoes em configuracoes criticas;
- chamadas bloqueadas por WAF;
- uso anormal de tokens ou chaves de API.

Sem esses registros, a empresa pode ate sofrer um ataque, mas nao tera evidencias suficientes para entender o vetor, o escopo, os usuarios afetados e as acoes do invasor.

## 3. Causas e sintomas

Log insuficiente ou desativado significa que eventos relevantes nao sao registrados, sao registrados apenas no console local, sao descartados apos reinicio, nao possuem campos suficientes ou nao sao centralizados. Exemplos:

- registrar apenas "erro ao logar" sem usuario, IP, motivo e contexto;
- nao registrar falhas de autenticacao;
- nao registrar alteracao de senha ou permissao;
- nao registrar acesso negado;
- nao registrar operacoes administrativas;
- nao registrar exportacao ou exclusao de dados;
- logs dispersos em servidores sem coleta central;
- ausencia de retencao;
- relogios sem sincronizacao;
- logs contendo senha, token ou dados pessoais em excesso.

A correlacao entre eventos e alertas em tempo real e importante porque um evento isolado pode parecer normal, mas uma sequencia pode indicar ataque. Cinco falhas de login podem ser erro do usuario; mil falhas em varios usuarios indicam brute force ou credential stuffing. Um login administrativo seguido de exportacao de base e criacao de token deve gerar alerta de alta prioridade.

Notificar acessos nao autorizados e critico porque esse tipo de evento indica que um controle de seguranca foi acionado. Mesmo quando o acesso e bloqueado, a tentativa revela reconhecimento, abuso de credencial, automacao ou tentativa de escalada.

Logs inadequados afetam investigacoes porque faltam evidencias como timestamp, usuario, IP, user-agent, acao, objeto afetado, resultado, request id e origem do evento. Sem isso, a equipe nao consegue reconstruir a linha do tempo nem provar o impacto.

## 4. Boas praticas de logging

Eventos que devem ser registrados:

- login com sucesso e falha;
- logout e expiracao de sessao;
- reset e alteracao de senha;
- MFA habilitado, removido ou falho;
- criacao, remocao e alteracao de usuario;
- mudanca de perfil, papel ou permissao;
- acesso negado;
- operacoes administrativas;
- alteracao de configuracao;
- exportacao, exclusao e acesso em massa a dados;
- chamadas a APIs sensiveis;
- erros de validacao de seguranca;
- falhas de autorizacao;
- uso de token de API;
- upload de arquivos;
- eventos do WAF, rate limit e deteccoes de abuso.

Campos uteis:

- timestamp com timezone;
- request id ou correlation id;
- usuario ou identificador anonimo;
- origem, como IP e user-agent;
- acao executada;
- recurso afetado;
- resultado;
- motivo da falha;
- severidade;
- servico e ambiente;
- hash ou identificador de sessao, sem registrar o segredo real.

Integridade dos logs:

- enviar logs para coletor central imutavel ou com controle de alteracao;
- restringir permissao de leitura e escrita;
- separar logs de aplicacao e auditoria;
- assinar ou armazenar em trilha append-only quando necessario;
- sincronizar relogios com NTP;
- registrar alteracoes na configuracao de logging.

Confidencialidade dos logs:

- nunca registrar senha, token, segredo, cookie de sessao, chave privada ou numero completo de cartao;
- mascarar dados pessoais;
- aplicar controle de acesso;
- criptografar em transito e em repouso;
- definir politica de retencao;
- limitar acesso por necessidade operacional.

## 5. SIEM

SIEM significa Security Information and Event Management. Uma ferramenta SIEM centraliza logs e eventos de varias fontes, como aplicacoes, servidores, cloud, firewalls, EDR, banco de dados e provedores de identidade. O SIEM normaliza eventos, correlaciona padroes, gera alertas e ajuda analistas a investigar incidentes.

O papel do SIEM na deteccao de ataques e transformar eventos dispersos em sinais acionaveis. Exemplos:

- correlacionar falhas de login em muitos usuarios;
- detectar login impossivel por geolocalizacao;
- alertar uso de conta administrativa fora do padrao;
- identificar picos de erro 403 e 500;
- detectar exportacao anormal de dados;
- cruzar evento de WAF com log da aplicacao.

Em tempo real, o SIEM reduz o tempo medio de deteccao e resposta, pois permite priorizar alertas por severidade, contexto e risco.

## 6. Analise do codigo fornecido

Codigo original:

```java
try {
    login(user, password);
} catch (Exception e) {
    System.out.println("Erro ao logar");
}
```

Esse log nao e seguro nem completo. Problemas:

- usa `System.out.println` em vez de framework de logging;
- nao registra usuario ou identificador;
- nao registra IP de origem;
- nao registra timestamp de forma estruturada;
- nao diferencia senha incorreta, usuario inexistente, conta bloqueada ou erro interno;
- nao registra correlation id;
- nao informa severidade;
- pode esconder erro real ao capturar `Exception` generico;
- nao gera alerta para tentativas repetidas.

Versao melhorada com SLF4J:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public void authenticate(LoginRequest request, HttpServletRequest http) {
        String username = request.username();
        String ip = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");
        String requestId = http.getHeader("X-Request-Id");

        MDC.put("requestId", requestId);
        MDC.put("clientIp", ip);

        try {
            login(username, request.password());
            log.info("auth.login.success user={} ip={} userAgent={}",
                    username, ip, userAgent);
        } catch (InvalidCredentialsException e) {
            log.warn("auth.login.failure user={} ip={} reason=invalid_credentials userAgent={}",
                    username, ip, userAgent);
            throw e;
        } catch (AccountLockedException e) {
            log.warn("auth.login.failure user={} ip={} reason=account_locked userAgent={}",
                    username, ip, userAgent);
            throw e;
        } catch (Exception e) {
            log.error("auth.login.error user={} ip={} reason=unexpected_error",
                    username, ip, e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}
```

Esse codigo nao registra a senha, usa niveis adequados (`info`, `warn`, `error`), inclui IP, usuario, tipo de falha e request id. O timestamp normalmente e adicionado pelo layout do framework de logging.

Exemplo de formato JSON de log:

```json
{
  "timestamp": "2026-06-15T10:15:00-03:00",
  "level": "WARN",
  "event": "auth.login.failure",
  "user": "cliente@example.com",
  "clientIp": "203.0.113.10",
  "reason": "invalid_credentials",
  "requestId": "req-abc123"
}
```

## 7. Pratica com OWASP Juice Shop

No OWASP Juice Shop, a analise pode ser feita observando o console do container, o trafego no Burp Suite e os efeitos de ataques simulados.

Procedimento:

```bash
docker logs -f juice-shop
```

Em paralelo:

1. Configurar o navegador no proxy do Burp.
2. Simular falhas de login com usuarios inexistentes.
3. Testar SQL Injection no formulario de login.
4. Acessar uma rota administrativa ou rota sensivel.
5. Tentar acessar carrinho de outro usuario.
6. Enviar payload XSS em campo de busca ou feedback.
7. Verificar se o console ou arquivos registram evento de seguranca claro.

Eventos que muitas aplicacoes vulneraveis deixam de registrar adequadamente:

- tentativa de SQL Injection;
- varias falhas de login no mesmo usuario;
- acesso negado a recurso de outro usuario;
- uso de payload XSS;
- exclusao de feedback administrativo;
- requisicao SSRF;
- acesso a endpoint interno descoberto.

Se esses eventos aparecem apenas como erro generico, ou nao aparecem, ha falha de monitoramento. A correcao seria criar eventos de auditoria especificos para autenticacao, autorizacao, abuso de input, alteracoes administrativas e acesso a dados sensiveis.

## 8. Cenario real: e-commerce

Como responsavel de seguranca de um e-commerce, eu monitoraria:

- login e falha de login;
- brute force e credential stuffing;
- criacao e alteracao de conta;
- alteracao de email, senha, telefone e endereco;
- aplicacao de cupons;
- alteracao de preco ou estoque;
- criacao, cancelamento e reembolso de pedido;
- acesso a painel administrativo;
- exportacao de relatorios;
- tentativas de SQLi, XSS, SSRF e path traversal;
- alteracao de permissoes;
- criacao de token de API;
- pagamento recusado em massa;
- anomalias de volume por IP, usuario ou conta.

Alertas:

- muitas falhas de login por minuto;
- login de administrador fora do pais ou horario esperado;
- usuario comum acessando endpoint administrativo;
- aumento de erros 403 ou 500;
- exportacao incomum de dados;
- pedido com desconto fora do limite;
- criacao de varios usuarios a partir do mesmo IP;
- token usado de localizacao incomum.

## 9. Plano de resposta a incidentes sem logs

Cenario: a empresa descobre vazamento, mas nao coletou logs adequados.

Evidencias que podem estar faltando:

- quando o ataque comecou;
- quais contas foram acessadas;
- quais dados foram exportados;
- qual IP ou token foi usado;
- qual vulnerabilidade foi explorada;
- quais acoes administrativas ocorreram;
- se houve persistencia;
- se logs foram apagados;
- qual foi o escopo real do incidente.

Plano de resposta:

1. Conter o incidente isolando sistemas afetados.
2. Preservar evidencias existentes, como snapshots, logs de proxy, WAF, cloud, banco e backups.
3. Rotacionar credenciais e tokens.
4. Revisar contas administrativas e sessoes ativas.
5. Reconstruir linha do tempo com fontes externas, como CDN, provedor cloud e banco.
6. Corrigir a vulnerabilidade explorada.
7. Implantar logging minimo antes de reabrir o sistema.
8. Comunicar partes afetadas conforme obrigacao legal.
9. Realizar pos-incidente e atualizar politica.

Mudancas na politica de logging:

- definir eventos obrigatorios;
- usar logs estruturados;
- centralizar logs;
- proteger integridade;
- criar alertas;
- definir retencao;
- mascarar dados sensiveis;
- testar alertas periodicamente;
- revisar dashboards com o time de seguranca;
- incluir logging como criterio de aceite de funcionalidades sensiveis.

## 10. Conclusao

Falhas de logging e monitoramento nao impedem diretamente o primeiro ataque, mas aumentam muito o dano porque atrasam deteccao, resposta e investigacao. Uma aplicacao segura precisa registrar eventos relevantes, proteger logs contra alteracao e vazamento, centralizar informacoes, gerar alertas acionaveis e manter retencao suficiente. O objetivo e reduzir o tempo entre ataque, deteccao, contencao e recuperacao.
