# P2-15 - OWASP Top Ten: Security Misconfiguration

## 1. Introducao

Security Misconfiguration, ou configuracao incorreta de seguranca, e a categoria A05:2021 do OWASP Top 10. Ela ocorre quando uma aplicacao, servidor, framework, container, servico em nuvem ou componente de infraestrutura e implantado com configuracoes inseguras, incompletas, padrao ou excessivamente permissivas. A falha nem sempre esta no codigo da regra de negocio; muitas vezes esta no modo como o sistema foi instalado, publicado, exposto ou mantido.

Exemplos comuns em aplicacoes web incluem:

- paineis administrativos publicados na internet sem autenticacao forte;
- credenciais padrao mantidas em bancos, consoles, roteadores, CMS ou ferramentas internas;
- listagem de diretorios habilitada em servidores web;
- arquivos sensiveis publicados por engano, como `.env`, backups, dumps SQL, logs, chaves privadas e artefatos de build;
- mensagens de erro detalhadas em producao, revelando stack traces, queries, versoes e caminhos internos;
- headers de seguranca ausentes, como `Content-Security-Policy`, `Strict-Transport-Security`, `X-Content-Type-Options` e politicas de cookies;
- CORS configurado com `Access-Control-Allow-Origin: *` para endpoints autenticados;
- endpoints de diagnostico, metricas, Swagger, Actuator ou debug expostos publicamente;
- permissoes excessivas em buckets, filas, bancos, volumes ou roles de cloud;
- servicos rodando com usuarios privilegiados, como `root`, sem necessidade.

Configuracoes padrao representam risco porque fornecedores normalmente priorizam facilidade de instalacao e compatibilidade. Um produto pode vir com usuario `admin/admin`, modo debug habilitado, painel de exemplo, paginas de status e configuracoes amplas para facilitar testes. Se isso chega a producao, um atacante nao precisa descobrir uma vulnerabilidade nova: ele apenas explora uma superficie conhecida e previsivel.

Os impactos possiveis incluem acesso nao autorizado, vazamento de dados pessoais, alteracao de configuracoes, execucao remota de comandos, escalada de privilegios, tomada de contas administrativas, interrupcao do servico e perda de conformidade com normas como LGPD, PCI DSS e requisitos internos de auditoria.

## 2. Software, servidor e framework

Uma falha de configuracao de software ocorre no proprio produto ou aplicacao. Exemplo: uma aplicacao Spring Boot expondo `/actuator/env` com variaveis sensiveis, ou um CMS mantendo plugins de exemplo ativos.

Uma falha de configuracao de servidor ocorre na camada que publica ou executa a aplicacao. Exemplo: Nginx com `autoindex on`, Apache permitindo acesso a `.git/`, banco de dados aceitando conexoes externas ou container executando como root.

Uma falha de configuracao de framework ocorre quando recursos de seguranca do framework sao usados de forma incorreta. Exemplo: CSRF desabilitado sem justificativa, CORS muito permissivo, cookies de sessao sem `HttpOnly` e `Secure`, ou rotas administrativas fora do filtro de autenticacao.

A OWASP classifica essa vulnerabilidade como grave porque ela e frequente, facil de introduzir e normalmente tem baixo custo de exploracao. Alem disso, configuracoes inseguras costumam afetar a aplicacao inteira, nao apenas uma tela ou endpoint isolado. Por isso a categoria aparece no Top 10: ela representa uma combinacao de alta ocorrencia, impacto relevante e detectabilidade razoavelmente simples por atacantes e ferramentas automatizadas.

## 3. Exemplo pratico de configuracao insegura

Exemplo inseguro em Nginx:

```nginx
server {
    listen 80;
    server_name loja.exemplo.com;

    root /var/www/app;
    autoindex on;

    location /admin {
        proxy_pass http://admin-interno:8080;
    }

    location /backup {
        root /var/www;
    }
}
```

O problema dessa configuracao e que `autoindex on` permite listagem de diretorios, `/admin` e publicado sem restricao explicita e `/backup` pode expor arquivos sensiveis. Um atacante poderia acessar URLs como `/backup/`, listar arquivos `.zip`, `.sql` ou `.env`, encontrar credenciais e depois acessar o painel administrativo.

Configuracao corrigida:

```nginx
server {
    listen 443 ssl http2;
    server_name loja.exemplo.com;

    root /var/www/app/public;
    autoindex off;

    add_header Strict-Transport-Security "max-age=31536000; includeSubDomains" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header Referrer-Policy "no-referrer" always;

    location /admin {
        allow 10.0.0.0/8;
        deny all;
        auth_request /auth/admin;
        proxy_pass http://admin-interno:8080;
    }

    location ~* /(backup|\.git|\.env|logs|dumps) {
        deny all;
        return 404;
    }
}
```

Nesse ajuste, a aplicacao publica somente a pasta `public`, a listagem de diretorios foi desativada, rotas administrativas exigem rede autorizada e autenticacao, e caminhos tipicamente sensiveis sao bloqueados.

## 4. Diretorios sensiveis expostos

Cenario simulado:

```text
https://loja.exemplo.com/backups/
https://loja.exemplo.com/backups/prod-2026-06-01.sql
https://loja.exemplo.com/logs/application.log
https://loja.exemplo.com/.env
```

Se esses recursos estiverem acessiveis sem autenticacao, um atacante pode baixar dados de clientes, hashes de senha, tokens de API, detalhes de infraestrutura e erros internos. A verificacao preventiva deve incluir:

- procurar por `.env`, `.git/`, `backup`, `dump`, `logs`, `tmp`, `uploads`, `admin`, `actuator`, `metrics`, `swagger` e `debug`;
- confirmar se a aplicacao publica apenas artefatos necessarios;
- validar autenticacao e autorizacao em todos os endpoints administrativos;
- bloquear listagem de diretorio;
- garantir que uploads nao sejam servidos com permissao de execucao;
- revisar regras do proxy reverso, firewall, security groups e ingress;
- executar testes automatizados de configuracao antes de cada deploy.

Para endpoints administrativos, deve-se verificar se a rota esta autenticada, se exige perfil administrativo, se ha MFA para operacoes criticas, se nao ha credenciais padrao, se a rota nao aparece em mapas publicos sem necessidade e se o acesso externo esta limitado por rede, VPN, Zero Trust ou outro controle equivalente.

## 5. Uso do Burp Suite no OWASP Juice Shop

O Burp Suite pode identificar falhas de configuracao observando trafego HTTP, rotas escondidas, respostas de erro, headers ausentes, cookies inseguros e arquivos publicados sem necessidade.

Procedimento de teste:

1. Configurar o navegador para usar o proxy do Burp em `127.0.0.1:8080`.
2. Acessar a aplicacao Juice Shop local, por exemplo `http://127.0.0.1:3000`.
3. Navegar pelas telas principais para popular o `HTTP history` e o `Site map`.
4. Usar o `Target > Site map` para observar rotas, arquivos JavaScript e endpoints REST.
5. Enviar requisicoes suspeitas para o `Repeater`, como `/administration`, `/ftp`, `/metrics`, `/api/Challenges`, `/rest/admin/application-version` ou caminhos descobertos nos arquivos JavaScript.
6. Analisar respostas, status codes, headers, cookies, mensagens de erro e dados retornados.

Falhas que podem ser detectadas:

- pagina interna ou administrativa descoberta no frontend;
- endpoint de versao revelando informacao de tecnologia;
- arquivos estaticos ou diretorios acessiveis sem necessidade;
- mensagens de erro detalhadas;
- cookies sem flags de seguranca;
- ausencia de headers defensivos;
- endpoints que retornam dados demais para usuarios comuns.

No Juice Shop, um exemplo didatico e a exposicao do Score Board e de rotas internas pela propria aplicacao. O atacante pode inspecionar arquivos JavaScript no Burp, localizar rotas nao exibidas no menu e acessar funcionalidades que deveriam ser menos evidentes. Embora o Score Board seja parte intencional do laboratorio, ele simula um problema real: publicar recursos administrativos, diagnosticos ou internos por confiar apenas em "seguranca por obscuridade".

Correcao proposta:

- remover rotas sensiveis do bundle publico quando nao forem necessarias;
- exigir autenticacao e autorizacao no backend, nao apenas esconder links no frontend;
- bloquear endpoints administrativos no proxy reverso quando nao forem publicos;
- desabilitar debug e stack trace em producao;
- aplicar headers de seguranca e politicas de cookies;
- manter um baseline de hardening revisado em CI/CD.

## 6. Cenario real: paineis administrativos expostos

Imagine uma empresa que publica `https://admin.empresa.com` na internet com usuario `admin` e senha padrao. As consequencias podem ser graves:

- acesso ao cadastro de clientes e dados pessoais;
- alteracao de pedidos, precos, permissao de usuarios e configuracoes;
- criacao de contas administrativas persistentes;
- instalacao de web shells, plugins maliciosos ou integracoes falsas;
- vazamento de credenciais de banco e APIs internas;
- indisponibilidade do negocio por sabotagem ou ransomware;
- multas, notificacoes reguladoras e perda de confianca.

Politicas de hardening evitariam esse cenario ao exigir:

- inventario de ativos expostos;
- remocao de credenciais padrao antes da publicacao;
- MFA para usuarios privilegiados;
- controle de acesso por perfil e menor privilegio;
- segmentacao de rede;
- revisao de configuracao antes de deploy;
- varreduras periodicas;
- logs e alertas para acesso administrativo;
- processo formal de excecao quando uma configuracao insegura for temporariamente aceita.

## 7. Checklist de configuracao segura para servidores web

- Publicar somente o diretorio necessario da aplicacao.
- Desativar listagem de diretorios.
- Bloquear `.env`, `.git`, backups, dumps, logs e arquivos temporarios.
- Usar HTTPS com TLS moderno e redirecionar HTTP para HTTPS.
- Habilitar HSTS quando aplicavel.
- Configurar `Content-Security-Policy`, `X-Content-Type-Options`, `Referrer-Policy` e demais headers defensivos.
- Garantir `Secure`, `HttpOnly` e `SameSite` em cookies de sessao.
- Remover paginas de exemplo, documentacao interna e consoles de instalacao.
- Desativar modo debug em producao.
- Restringir paineis administrativos por autenticacao forte, rede e autorizacao.
- Evitar CORS permissivo em endpoints autenticados.
- Rodar processos com usuario nao privilegiado.
- Aplicar limites de tamanho, timeout e rate limit.
- Registrar acessos administrativos e falhas de autenticacao.
- Validar configuracao em CI/CD com testes automatizados e scanners.
- Revisar periodicamente versoes, permissoes e exposicoes externas.

## 8. Caso publico e licoes aprendidas

Um caso frequentemente citado e o incidente da Capital One em 2019. A exploracao envolveu uma aplicacao exposta em nuvem, abuso de SSRF e permissoes excessivas associadas ao ambiente, permitindo acesso indevido a dados armazenados. Embora o vetor tenha elementos de SSRF, o incidente tambem ilustra uma licao de Security Misconfiguration: configuracoes de rede, metadados, IAM e permissoes de cloud precisam ser restritas e revisadas.

Licoes aprendidas:

- nao basta proteger a aplicacao; a identidade e a configuracao da infraestrutura tambem precisam de menor privilegio;
- metadados de cloud e credenciais temporarias devem ter protecoes adicionais;
- roles com permissoes amplas aumentam o impacto de uma falha inicial;
- monitoramento de comportamento anomalo e acesso a dados em massa deve gerar alerta;
- revisoes de arquitetura cloud devem fazer parte do processo de seguranca.

## 9. Conclusao

Security Misconfiguration e uma das vulnerabilidades mais praticas e perigosas porque nasce de decisoes operacionais comuns: deixar padroes ativos, publicar recursos internos, confiar em configuracoes de desenvolvimento ou nao revisar infraestrutura. A mitigacao exige hardening, automacao e disciplina: baseline seguro, revisao de configuracoes, menor privilegio, segregacao de ambientes, remocao de debug, protecao de rotas administrativas, headers corretos, logs e verificacoes no pipeline.

Ao revisar um sistema, eu verificaria primeiro a superficie exposta, os endpoints administrativos, os arquivos sensiveis publicados, os headers, os cookies, as configuracoes de CORS, o modo debug, as permissoes de cloud, os usuarios de servico, as mensagens de erro e a presenca de credenciais padrao. O objetivo e garantir que a aplicacao esteja segura por configuracao, e nao apenas por expectativa de que ninguem descubra seus caminhos internos.
