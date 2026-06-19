# P2-13 - OWASP Top Ten: Identification and Authentication Failures

## 1. Introducao

Identification and Authentication Failures sao falhas nos mecanismos que identificam quem e o usuario e verificam se ele realmente e quem afirma ser. Segundo a OWASP, esse risco inclui problemas como credenciais fracas, ataques de forca bruta, recuperacao de senha insegura, sessoes mal gerenciadas, tokens previsiveis, ausencia de MFA e armazenamento inadequado de senhas.

Essa categoria e critica porque autenticacao e a porta de entrada para funcionalidades sensiveis. Se um atacante consegue assumir uma conta, ele pode acessar dados pessoais, fazer compras, alterar configuracoes, fraudar transacoes ou escalar privilegios.

Objetivos dos mecanismos de identificacao e autenticacao:

- Identificar o sujeito: descobrir qual conta, usuario, servico ou cliente esta tentando acessar.
- Autenticar o sujeito: verificar uma prova, como senha, token, certificado ou biometria.
- Estabelecer uma sessao segura apos a autenticacao.
- Impedir reutilizacao indevida de credenciais e sessoes.
- Registrar eventos relevantes para deteccao e resposta.

Se esses mecanismos forem mal implementados, podem ocorrer sequestro de conta, vazamento de dados, fraudes, acesso administrativo indevido e perda de confianca.

Problemas comuns:

- Senhas fracas ou sem politica minima.
- Senhas armazenadas sem hash forte e salt.
- Falta de rate limit em login.
- Falta de bloqueio ou atraso progressivo apos tentativas falhas.
- Ausencia de MFA em contas sensiveis.
- Recuperacao de senha baseada em perguntas faceis.
- Tokens de sessao previsiveis.
- Sessao nao invalidada apos logout.
- Token sem expiracao ou com expiracao muito longa.
- Mensagens que permitem enumerar usuarios.

MFA e boa pratica porque adiciona outra prova alem da senha. Mesmo que a senha seja vazada em phishing ou reutilizada de outro site, o atacante ainda precisa do segundo fator, como aplicativo autenticador, chave FIDO2 ou codigo temporario.

## 2. Identificacao x autenticacao

Identificacao e a afirmacao de identidade. Exemplo: informar `maria@exemplo.com` no login.

Autenticacao e a verificacao dessa identidade. Exemplo: provar conhecimento da senha correta ou posse de uma chave de seguranca.

Autorizacao, que e outro conceito, ocorre depois: define o que aquele usuario autenticado pode acessar.

## 3. Tipos comuns de autenticacao

| Tipo | Descricao | Exemplo |
|---|---|---|
| Conhecimento | Algo que o usuario sabe. | Senha, PIN. |
| Posse | Algo que o usuario possui. | Token fisico, celular, chave FIDO2. |
| Inerencia | Algo que o usuario e. | Biometria, digital, face. |

Autenticacao multifator combina fatores de categorias diferentes. Senha + codigo no aplicativo autenticador e mais forte do que senha + outra pergunta de seguranca, pois perguntas continuam sendo conhecimento.

## 4. Credential stuffing e brute force

Credential stuffing e o uso automatizado de pares usuario/senha vazados de outros servicos. Ele explora reutilizacao de senha. O atacante nao precisa adivinhar; ele testa credenciais ja conhecidas em escala.

Brute force tenta descobrir a senha por tentativa e erro. Pode ser totalmente exaustivo, testando combinacoes, ou baseado em dicionario, testando senhas provaveis.

Mitigacoes:

- MFA.
- Rate limit por usuario, IP, dispositivo e faixa de rede.
- Backoff progressivo apos falhas.
- Deteccao de senhas vazadas.
- Protecao contra automacao.
- Alertas de login suspeito.
- Hash de senha com algoritmo adequado, como bcrypt, scrypt ou Argon2.
- Bloqueio temporario com cuidado para nao permitir negacao de servico contra usuarios legitimos.

## 5. Controle de sessoes

Apos login, a aplicacao cria uma sessao ou emite token. Esse elemento passa a representar o usuario autenticado; por isso, precisa ser protegido.

Boas praticas:

- Gerar IDs de sessao aleatorios e com alta entropia.
- Usar cookies `HttpOnly`, `Secure` e `SameSite`.
- Expirar sessoes por inatividade e tempo absoluto.
- Regenerar sessao apos login para evitar session fixation.
- Invalidar sessao no logout.
- Invalidar tokens apos troca de senha.
- Nao colocar dados sensiveis desnecessarios em JWT.
- Usar tempo de expiracao curto para access tokens.

Se tokens de sessao nao forem invalidados corretamente, um atacante que roubou um cookie pode continuar acessando a conta mesmo apos logout ou troca de senha. Em dispositivos compartilhados, isso aumenta risco de acesso indevido.

## 6. Exemplos praticos de vulnerabilidades

### Enumeracao de usuarios

Uma aplicacao vulneravel responde:

```text
usuario inexistente
```

para emails invalidos, mas responde:

```text
senha incorreta
```

para emails existentes. Isso permite enumerar contas validas. A resposta segura deve ser generica:

```text
usuario ou senha invalidos
```

e o tempo de resposta deve ser semelhante.

### Falta de limite de tentativas

Sem rate limit, um atacante pode testar milhares de senhas:

```bash
for senha in $(cat senhas.txt); do
  curl -s -X POST "https://app.exemplo.com/login" \
    -d "email=victim@example.com&senha=$senha"
done
```

Mesmo que cada tentativa tenha baixa chance de sucesso, a escala torna o ataque viavel.

## 7. Codigo Java vulneravel e correcao

### Exemplo inseguro

```java
public boolean autenticar(String usuario, String senha) {
    Usuario u = usuarioRepository.buscarPorEmail(usuario);

    if (u == null) {
        return false;
    }

    return u.getSenha() == senha;
}
```

Problemas:

- `==` compara referencia de objetos, nao conteudo de `String`.
- O exemplo sugere senha em texto claro.
- Nao ha hash forte.
- Nao ha rate limit, MFA ou registro de falhas.

### Exemplo corrigido

```java
public boolean autenticar(String email, String senhaInformada) {
    Usuario u = usuarioRepository.buscarPorEmail(email);

    if (u == null) {
        registrarFalha(email);
        return false;
    }

    boolean senhaCorreta = passwordEncoder.matches(
        senhaInformada,
        u.getSenhaHash()
    );

    if (!senhaCorreta) {
        registrarFalha(email);
        return false;
    }

    limparFalhas(email);
    return true;
}
```

Com Spring Security, `passwordEncoder` poderia ser um `BCryptPasswordEncoder`, e o cadastro salvaria apenas o hash:

```java
String hash = passwordEncoder.encode(senhaEmTexto);
usuario.setSenhaHash(hash);
```

## 8. Testes de seguranca em autenticacao

Um testador deve verificar:

- Enumeracao de usuarios.
- Ausencia de rate limit.
- Politica de senha fraca.
- Recuperacao de senha insegura.
- Tokens previsiveis ou reutilizaveis.
- Logout que nao invalida sessao.
- Sessao que nao expira.
- Falta de MFA em rotas sensiveis.
- Reset de senha que nao invalida sessoes antigas.

### Uso do Burp Suite

Passo a passo para teste de forca bruta controlado:

1. Configurar o navegador para usar o proxy do Burp.
2. Fazer uma tentativa de login valida ou invalida.
3. Enviar a requisicao capturada para o Intruder.
4. Marcar a posicao do campo `senha` como payload.
5. Carregar uma lista pequena e controlada de senhas de teste.
6. Executar contra ambiente autorizado.
7. Comparar status code, tamanho da resposta, tempo e mensagens.
8. Confirmar se ha bloqueio, atraso progressivo ou CAPTCHA apos falhas.

Exemplo de requisicao capturada:

```http
POST /login HTTP/1.1
Host: app.local
Content-Type: application/x-www-form-urlencoded

email=alice@example.com&senha=SenhaTeste123
```

Indicadores de falha:

- Todas as tentativas retornam imediatamente.
- Nao ha bloqueio apos muitas falhas.
- Resposta de sucesso tem tamanho claramente diferente e permite automacao facil.
- O sistema nao alerta usuario sobre tentativas suspeitas.

### Teste de expiracao e logout

1. Fazer login e copiar o cookie de sessao.
2. Fazer logout.
3. Reenviar uma requisicao autenticada antiga no Burp Repeater.
4. Verificar se a resposta e `401 Unauthorized` ou redireciona para login.
5. Repetir apos tempo de inatividade maior que o limite esperado.

Se a requisicao antiga continuar funcionando, o logout ou a expiracao estao inefetivos.

## 9. Aplicacao em e-commerce

Um e-commerce sem limite de tentativas de login expoe riscos diretos:

- Sequestro de contas de clientes.
- Uso indevido de cartoes salvos.
- Alteracao de endereco de entrega.
- Acesso a pedidos e dados pessoais.
- Fraude em cupons, pontos e reembolsos.
- Dano reputacional e obrigacoes legais.

Controles recomendados:

- MFA para acoes sensiveis e contas administrativas.
- Rate limit por IP, conta e dispositivo.
- Deteccao de credential stuffing.
- Bloqueio temporario com notificacao.
- Senhas fortes e verificacao contra listas de senhas vazadas.
- Alertas de login em novo dispositivo.
- Reautenticacao para trocar email, senha ou forma de pagamento.
- Logs de seguranca com monitoramento.

### Cenario de incidente

Um atacante usa uma base vazada e testa milhares de credenciais. Algumas contas usam a mesma senha do vazamento. O atacante entra, troca endereco de entrega e faz compras.

Deteccao:

- Muitos logins falhos de poucos IPs.
- Logins bem-sucedidos apos varias falhas.
- Acesso a muitas contas pelo mesmo user-agent.
- Alteracao de endereco logo apos login suspeito.

Resposta:

- Bloquear IPs e tokens envolvidos.
- Invalidar sessoes suspeitas.
- Forcar reset de senha de contas afetadas.
- Notificar usuarios.
- Revisar logs e escopo.
- Ativar MFA adaptativo.
- Ajustar rate limits e regras antifraude.

## 10. Conclusao

Falhas de autenticacao continuam comuns porque envolvem muitos detalhes: armazenamento de senha, recuperacao de conta, sessao, MFA, UX, APIs, dispositivos moveis, integracoes e logs. Um detalhe esquecido pode comprometer todo o fluxo.

Existe uma tensao real entre usabilidade e seguranca. Por exemplo, manter uma sessao ativa por meses facilita a vida do usuario, mas aumenta o impacto se o cookie for roubado. Permitir senha muito simples reduz atrito no cadastro, mas facilita brute force e credential stuffing. A solucao e equilibrar: sessoes com expiracao razoavel, MFA adaptativo, reautenticacao para acoes criticas e controles invisiveis ao usuario quando possivel.

Uma autenticacao segura deve usar senha armazenada com hash forte, MFA, protecao contra automacao, respostas genericas, sessao bem invalidada e monitoramento continuo. O objetivo nao e apenas permitir login, mas impedir que um atacante consiga automatizar, adivinhar, reutilizar ou manter acesso indevido.
