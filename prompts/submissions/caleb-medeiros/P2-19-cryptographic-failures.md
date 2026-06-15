# P2-19 - OWASP Top Ten: Cryptographic Failures

## 1. Introducao

Cryptographic Failures, categoria A02:2021 do OWASP Top 10, representa falhas no uso de criptografia que levam a exposicao, adulteracao ou uso indevido de dados sensiveis. A categoria substituiu a antiga "Sensitive Data Exposure" porque o problema raiz muitas vezes nao e apenas o dado exposto, mas a decisao criptografica incorreta que permitiu a exposicao: trafego sem TLS, senhas com hash fraco, chaves mal armazenadas, algoritmos obsoletos, segredos em logs ou criptografia aplicada de forma errada.

Objetivos da criptografia em software:

- Confidencialidade: somente partes autorizadas conseguem ler o dado. Falha quando dados sensiveis trafegam em HTTP, ficam em claro no banco ou sao cifrados com chave exposta.
- Integridade: o dado nao pode ser alterado sem deteccao. Falha quando nao ha MAC, assinatura ou modo autenticado.
- Autenticidade: o sistema consegue verificar a identidade ou origem. Falha quando certificados nao sao validados ou quando assinaturas nao sao verificadas.
- Nao repudio: uma parte nao consegue negar uma acao assinada digitalmente. Falha quando operacoes criticas nao usam assinatura, trilha de auditoria ou chaves protegidas.

Tres exemplos reais ou recorrentes:

- Trafego sem TLS: credenciais e cookies de sessao enviados em HTTP podem ser capturados em redes publicas ou proxies intermediarios.
- Hashing fraco de senhas: vazamentos historicos com hashes SHA-1 ou MD5 sem sal permitiram ataques de dicionario e cracking em massa.
- Chaves expostas em repositorios: tokens de cloud ou API publicados em Git podem ser usados para acessar infraestrutura, enviar mensagens, minerar recursos ou extrair dados.

Base64 nao e criptografia. Base64 e uma codificacao reversivel sem segredo; qualquer pessoa pode decodificar. Criptografia exige algoritmo e chave. Confundir os dois e perigoso porque equipes podem "proteger" tokens, senhas ou dados pessoais apenas codificando-os, deixando o conteudo trivialmente recuperavel.

## 2. Causas e sintomas

### Algoritmos e protocolos obsoletos

MD5 e SHA-1 nao devem ser usados para seguranca criptografica moderna, especialmente para senhas ou assinatura. RC4, SSLv3 e TLS antigo tambem sao inseguros. TLS abaixo de 1.2 deve ser rejeitado em sistemas atuais, preferindo TLS 1.3 quando possivel.

Impacto: atacantes podem quebrar hashes, explorar fraquezas de protocolo, interceptar trafego ou falsificar integridade em cenarios especificos.

### Hash rapido de senha

Armazenar senha com `MD5(password)`, `SHA-256(password)` ou hash rapido sem sal e inadequado. Senhas humanas tem baixa entropia; atacantes conseguem testar bilhoes de candidatos por segundo com GPUs.

Impacto: apos vazamento do banco, muitas senhas sao recuperadas rapidamente. Se usuarios reutilizam senhas, outras contas tambem ficam em risco.

### Segredos hardcoded, em logs ou URLs

Tokens, API keys, credenciais de banco e chaves privadas nao devem ficar no codigo, em repositorios, em URL de query string ou em logs. URLs passam por historico do navegador, proxies, observabilidade, Referer e caches.

Impacto: o invasor pode usar o segredo diretamente sem explorar outra falha tecnica.

### Reutilizacao de IV ou nonce

Modos como AES-GCM e AES-CTR exigem IV/nonce unico por chave. Reutilizar nonce pode quebrar confidencialidade e integridade. RNG nao criptografico, como `java.util.Random`, nao deve gerar chaves, IVs ou tokens.

Impacto: mensagens podem ser comparadas, texto claro pode ser recuperado e tags de autenticacao podem perder seguranca.

### TLS mal configurado

Erros comuns:

- aceitar certificado invalido;
- desabilitar verificacao de hostname;
- usar cipher suites sem Perfect Forward Secrecy;
- permitir TLS antigo;
- nao habilitar HSTS;
- confiar em qualquer certificado no cliente.

Impacto: ataques man-in-the-middle, captura de credenciais, downgrade e falsa sensacao de seguranca.

## 3. Boas praticas

Em transito:

- obrigar HTTPS;
- usar TLS 1.2+ e preferir TLS 1.3;
- habilitar HSTS;
- usar cipher suites modernas com PFS;
- rejeitar certificados invalidos;
- nao usar clientes `TrustAll`;
- considerar certificate pinning em aplicativos moveis ou clientes controlados.

Em repouso:

- usar criptografia autenticada, como AES-GCM ou ChaCha20-Poly1305;
- armazenar chaves em KMS, HSM ou Secret Manager;
- aplicar rotacao de chaves;
- limitar acesso por menor privilegio;
- separar dados sensiveis por classificacao;
- evitar armazenar o que nao e necessario.

Senhas:

- usar Argon2id preferencialmente;
- aceitar bcrypt ou PBKDF2 quando Argon2id nao estiver disponivel;
- usar sal aleatorio por senha;
- configurar custo adequado;
- considerar pepper em cofre separado;
- nunca usar MD5, SHA-1 ou SHA-256 puro para senhas.

Integridade e autenticidade:

- usar HMAC para autenticacao de mensagens com segredo compartilhado;
- usar assinaturas digitais, como Ed25519 ou ECDSA, quando e necessario provar origem;
- verificar assinaturas antes de processar artefatos;
- usar modos autenticados de criptografia.

Governanca:

- classificar dados;
- definir retencao;
- mascarar dados em logs;
- proibir segredos em repositorios;
- revisar arquitetura criptografica;
- usar cheat sheets e revisoes de seguranca;
- criar testes automatizados para politicas de TLS, segredo e dependencias.

## 4. Exemplo Java: hash inseguro de senha

Codigo inseguro:

```java
String hash = DigestUtils.md5Hex(password);
saveUser(user, hash);
```

Problemas:

- MD5 e rapido e inadequado para senhas;
- nao ha sal aleatorio;
- hashes iguais revelam senhas iguais;
- facilita ataques de dicionario e rainbow tables;
- nao ha custo ajustavel.

Versao com Argon2id:

```java
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class PasswordHasher {
    private static final int ITERATIONS = 3;
    private static final int MEMORY_KIB = 65536;
    private static final int PARALLELISM = 2;

    public String hashPassword(char[] password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
        try {
            return argon2.hash(ITERATIONS, MEMORY_KIB, PARALLELISM, password);
        } finally {
            argon2.wipeArray(password);
        }
    }

    public boolean verify(char[] password, String storedHash) {
        Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
        try {
            return argon2.verify(storedHash, password);
        } finally {
            argon2.wipeArray(password);
        }
    }
}
```

O hash resultante ja inclui algoritmo, parametros e sal. O custo deve ser calibrado para o ambiente de producao, buscando latencia aceitavel e resistencia a ataques offline.

## 5. Exemplo Java: AES ECB inseguro

Codigo inseguro:

```java
Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
c.init(Cipher.ENCRYPT_MODE, key);
byte[] out = c.doFinal(plaintext);
```

ECB e inseguro porque blocos iguais de texto claro geram blocos iguais de texto cifrado. Em dados estruturados, padroes podem aparecer. Um exemplo classico e cifrar imagens ou registros repetitivos: mesmo sem saber a chave, o atacante pode inferir repeticao e formato.

Versao com AES-GCM:

```java
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AesGcmCrypto {
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;
    private static final SecureRandom RNG = new SecureRandom();

    public byte[] encrypt(byte[] plaintext, byte[] aad, SecretKey key) throws Exception {
        byte[] iv = new byte[IV_LENGTH];
        RNG.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BITS, iv));
        cipher.updateAAD(aad);

        byte[] ciphertext = cipher.doFinal(plaintext);
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);
        return result;
    }

    public byte[] decrypt(byte[] encoded, byte[] aad, SecretKey key) throws Exception {
        byte[] iv = Arrays.copyOfRange(encoded, 0, IV_LENGTH);
        byte[] ciphertext = Arrays.copyOfRange(encoded, IV_LENGTH, encoded.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BITS, iv));
        cipher.updateAAD(aad);
        return cipher.doFinal(ciphertext);
    }
}
```

O IV deve ser unico por mensagem e por chave. A tag do GCM verifica integridade e autenticidade do ciphertext e do AAD.

## 6. Cliente HTTP inseguro

Codigo inseguro:

```java
OkHttpClient client = new OkHttpClient.Builder()
   .hostnameVerifier((h, s) -> true)
   .build();
```

O problema e que o cliente aceita qualquer hostname, quebrando a validacao do certificado. Isso permite man-in-the-middle com certificado invalido ou emitido para outro dominio.

Versao segura:

```java
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

OkHttpClient client = new OkHttpClient.Builder()
    .connectionSpecs(java.util.List.of(ConnectionSpec.MODERN_TLS))
    .certificatePinner(new CertificatePinner.Builder()
        .add("api.banco.example", "sha256/BASE64_DO_PIN_VALIDO")
        .build())
    .build();
```

Em muitos sistemas, basta manter a validacao padrao do OkHttp e nao sobrescrever `hostnameVerifier`. Pinning e opcional e deve ter plano de rotacao, pois pins incorretos podem causar indisponibilidade.

## 7. Auditoria pratica

Scanner TLS:

```bash
testssl.sh https://api.exemplo.com
```

Itens a verificar:

- TLS 1.2 e 1.3 habilitados;
- SSLv3, TLS 1.0 e TLS 1.1 desabilitados;
- cipher suites fortes;
- PFS habilitado;
- certificado valido;
- HSTS presente;
- ausencia de suites RC4, 3DES ou anonimas.

Secret scanning:

```bash
gitleaks detect --source . --redact
trufflehog filesystem .
```

Se um segredo for encontrado:

1. revogar imediatamente;
2. verificar uso indevido;
3. criar novo segredo em Secret Manager;
4. remover do historico quando necessario;
5. adicionar regra de bloqueio no CI;
6. treinar a equipe.

SCA para bibliotecas criptograficas:

```bash
dependency-check.sh --project "app" --scan . --out ./reports
trivy fs --severity HIGH,CRITICAL .
```

O objetivo e localizar bibliotecas criptograficas obsoletas, wrappers inseguros ou versoes com CVEs.

## 8. Benchmark de hashing de senhas

Comparacao conceitual:

| Algoritmo | Caracteristica | Uso recomendado |
|---|---|---|
| Argon2id | memoria e CPU configuraveis; resistente a GPU | preferencial para novos sistemas |
| bcrypt | maduro e amplamente suportado | aceitavel quando Argon2id nao esta disponivel |
| PBKDF2 | padrao antigo e suportado em muitas plataformas | aceitavel com iteracoes altas, mas menos ideal |

Benchmark simulado:

```text
Argon2id 64 MiB, t=3, p=2: 120 ms por hash
bcrypt cost=12: 180 ms por hash
PBKDF2 600k iteracoes: 210 ms por hash
```

Parametros de producao devem mirar uma latencia que nao prejudique usuarios legitimos, mas torne ataque offline caro. Uma meta comum e algo entre 100 ms e 500 ms por hash no ambiente real, revisada periodicamente conforme hardware evolui.

Plano de revisao:

- medir em hardware de producao;
- ajustar custo anualmente;
- aumentar custo em novas senhas;
- rehash transparente no proximo login quando parametros antigos forem detectados;
- monitorar impacto em login e suporte.

## 9. Politica para banco digital

Classificacao de dados:

| Classe | Exemplos | Em transito | Em repouso |
|---|---|---|---|
| Publica | paginas institucionais | HTTPS padrao | sem exigencia especial |
| Interna | metricas nao sensiveis | TLS interno | controle de acesso |
| Confidencial | cadastro, email, telefone | TLS 1.2+ | criptografia e mascaramento |
| Restrita | documentos, saldo, chaves, segredos | TLS forte, mTLS quando aplicavel | KMS/HSM, criptografia autenticada, menor privilegio |

Quando usar:

- criptografia autenticada: dados sensiveis armazenados ou trafegados que precisam de confidencialidade e integridade;
- assinatura digital: documentos, transacoes ou artefatos em que a origem precisa ser comprovada;
- HMAC: integridade e autenticidade entre sistemas que compartilham segredo.

Fluxo de gestao de chaves:

1. Gerar chaves em KMS/HSM.
2. Definir dono, finalidade e escopo.
3. Conceder acesso minimo a servicos.
4. Registrar uso administrativo da chave.
5. Rotacionar periodicamente.
6. Separar chaves por ambiente.
7. Fazer backup seguro conforme criticidade.
8. Revogar chaves suspeitas.
9. Auditar acessos e politicas.

## 10. Cenario: SQL Injection e cripto transparente no banco

Se uma aplicacao decripta dados automaticamente ao consultar o banco, uma SQL Injection pode retornar dados em claro para o atacante. A criptografia no banco nao protege se a propria aplicacao vulneravel possui a chave e entrega o resultado decriptado.

Defesas em camadas:

- usar prepared statements;
- validar entrada;
- limitar privilegios do usuario de banco;
- criptografar no nivel da aplicacao somente campos necessarios;
- segregar funcoes de leitura e decriptacao;
- minimizar dados sensiveis retornados;
- aplicar mascaramento;
- monitorar consultas anormais;
- usar WAF como controle complementar, nao principal.

## 11. Cenario: segredos em URLs e logs

Exemplo inseguro:

```http
GET /reset-password?token=abc123secreto HTTP/1.1
Host: app.exemplo.com
```

Esse token pode vazar em historico, logs de proxy, analytics, observabilidade, bookmarks e header `Referer`.

Correcao:

```http
POST /reset-password HTTP/1.1
Host: app.exemplo.com
Authorization: Bearer token-curto
Content-Type: application/json

{"newPassword":"valor-enviado-sobre-HTTPS"}
```

Boas praticas:

- usar HTTPS;
- usar headers para tokens de API;
- aplicar TTL curto;
- armazenar token de cliente em local seguro;
- mascarar logs;
- invalidar token apos uso;
- evitar dados sensiveis em query string.

## 12. Testes automatizados

Politica de hash de senhas:

```gherkin
Funcionalidade: Politica de hash de senhas
  Cenario: Persistencia segura de credencial
    Dado que um usuario cadastra uma senha
    Quando a credencial e persistida
    Entao o hash deve usar Argon2id ou bcrypt com sal aleatorio e custo >= X
    E nao deve existir uso de MD5, SHA-1 ou SHA-256 puro no caminho de persistencia
```

Transporte seguro:

```gherkin
Funcionalidade: Transporte seguro
  Cenario: Endpoints publicos exigem HTTPS
    Dado que o servico expoe endpoints publicos
    Quando um cliente tenta conectar via HTTP
    Entao deve redirecionar para HTTPS
    E o header HSTS deve estar ativo
    E conexoes com TLS menor que 1.2 devem ser rejeitadas
```

Guardrails no CI:

```yaml
security:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v4

    - name: Secret scan
      run: gitleaks detect --source . --redact

    - name: Dependency scan
      run: trivy fs --severity HIGH,CRITICAL --exit-code 1 .

    - name: Block weak crypto usage
      run: |
        ! grep -R "MD5\\|SHA1\\|AES/ECB\\|TrustAll" src/
```

## 13. Reflexao e avaliacao

Base64 nao e criptografia porque nao usa chave e e totalmente reversivel. E apenas uma representacao textual de bytes.

Diferencas:

- Criptografia simetrica: mesma chave cifra e decifra. Exemplo: AES-GCM para dados em repouso.
- Criptografia assimetrica: par de chave publica e privada. Exemplo: trocar segredo ou verificar assinatura.
- Hashing: funcao unidirecional. Exemplo: Argon2id para senhas.
- Assinatura digital: prova autoria e integridade com chave privada. Exemplo: assinar release de software.

Tres decisoes arquiteturais que reduzem o risco A02:

1. Centralizar segredos em KMS/Secret Manager e proibir segredo em codigo.
2. Usar TLS forte e criptografia autenticada como padrao.
3. Definir biblioteca criptografica aprovada e bloquear algoritmos obsoletos no CI.

## 14. Perguntas objetivas com gabarito

1. Qual versao minima de TLS deve ser aceita em sistemas modernos?
   - Gabarito: TLS 1.2, preferindo TLS 1.3.

2. Por que MD5 nao deve ser usado para armazenar senhas?
   - Gabarito: e rapido, quebrado para varios usos de seguranca e facilita ataques offline, especialmente sem sal.

3. Onde chaves criptograficas de producao devem ser armazenadas?
   - Gabarito: em KMS, HSM ou Secret Manager com controle de acesso e auditoria.

4. O que acontece se um nonce for reutilizado em AES-GCM?
   - Gabarito: a seguranca do modo pode ser quebrada, comprometendo confidencialidade e integridade.

5. Por que tokens na query string sao inseguros?
   - Gabarito: podem vazar em historico, logs, proxies, observabilidade e Referer.

## 15. Metricas de auditoria continua

- Percentual de endpoints publicos com TLS 1.3 habilitado. Meta trimestral: aumentar para 95% e manter 100% com TLS 1.2+.
- Percentual de dependencias criptograficas sem CVE alta ou critica. Meta trimestral: 100% sem criticas e 0 vulnerabilidades altas sem plano aprovado.
- MTTR de rotacao de segredos expostos. Meta trimestral: revogar e substituir em ate 24 horas para segredos criticos.

## 16. Conclusao

Falhas criptograficas surgem quando a aplicacao usa criptografia fraca, mal configurada ou aplicada ao problema errado. A mitigacao exige escolhas tecnicas corretas e governanca: TLS forte, hashing apropriado de senhas, chaves protegidas, segredos fora do codigo, modos autenticados, IVs unicos, scanners, revisoes e testes automatizados. Criptografia segura deve ser padrao arquitetural, nao decisao improvisada em cada funcionalidade.
