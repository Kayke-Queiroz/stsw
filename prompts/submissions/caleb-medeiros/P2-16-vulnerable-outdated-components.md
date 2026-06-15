# P2-16 - OWASP Top Ten: Vulnerable and Outdated Components

## 1. Introducao

Vulnerable and Outdated Components, categoria A06:2021 do OWASP Top 10, ocorre quando uma aplicacao usa bibliotecas, frameworks, runtimes, containers, sistemas operacionais, plugins ou dependencias com vulnerabilidades conhecidas, sem suporte, sem atualizacao ou sem inventario adequado. O risco aparece porque software moderno e composto por muitos componentes de terceiros. Mesmo que o codigo escrito pela equipe esteja correto, uma falha em uma dependencia pode comprometer todo o sistema.

Essa vulnerabilidade permanece no Top 10 porque a maioria dos projetos depende de ecossistemas como Maven, npm, PyPI, RubyGems, Docker Hub e pacotes de sistema operacional. A quantidade de dependencias diretas e transitivas torna dificil saber exatamente o que esta em producao. Alem disso, muitas equipes nao possuem processo continuo de atualizacao, nao geram SBOM, nao monitoram CVEs e nao testam upgrades regularmente.

A diferenca para Security Misconfiguration e o ponto de origem. Em configuracao incorreta, o componente pode ser seguro, mas foi implantado de modo inseguro. Em componentes vulneraveis, o proprio componente tem uma falha conhecida ou esta obsoleto. Os dois riscos podem coexistir: uma versao vulneravel do Apache Struts exposta com configuracao permissiva, por exemplo, aumenta a chance e o impacto da exploracao.

## 2. Dependencias e bibliotecas de terceiros

Dependencias diretas sao declaradas explicitamente pelo projeto, como `spring-boot-starter-web`, `log4j-core` ou `express`. Dependencias transitivas sao puxadas automaticamente por essas bibliotecas. O problema e que uma aplicacao pode declarar poucas dependencias diretas, mas carregar centenas de pacotes no build final.

Reposititorios como Maven Central, PyPI e npm sao essenciais para distribuicao de software, mas tambem ampliam o risco:

- um pacote vulneravel pode ser baixado por milhares de projetos;
- uma dependencia abandonada pode permanecer anos em producao;
- versoes maliciosas podem ser publicadas por sequestro de conta ou typosquatting;
- nomes parecidos podem enganar desenvolvedores;
- dependencias transitivas podem introduzir CVEs sem que o time perceba.

Exemplos reais:

- Log4Shell: vulnerabilidade critica no Log4j 2 que permitia execucao remota de codigo em muitos cenarios.
- Apache Struts 2: falhas exploradas em aplicacoes expostas, incluindo incidentes de grande impacto.
- Heartbleed: falha no OpenSSL que permitiu vazamento de memoria e dados sensiveis.
- jQuery e bibliotecas frontend antigas: podem viabilizar XSS em funcoes vulneraveis.

## 3. Riscos e impactos

Os principais riscos associados a componentes vulneraveis sao:

- execucao remota de codigo;
- vazamento de dados;
- desvio de autenticacao;
- XSS, SQL Injection ou SSRF em bibliotecas afetadas;
- negação de servico;
- escalada de privilegio;
- comprometimento da cadeia de suprimentos;
- indisponibilidade por dependencia sem suporte;
- nao conformidade com requisitos legais, contratuais e de auditoria.

Em sistemas criticos ou expostos a internet, o impacto e maior porque atacantes automatizam a busca por versoes vulneraveis. Quando uma CVE critica e publicada, scanners passam a procurar banners, endpoints, assinaturas de biblioteca e respostas que indiquem vulnerabilidade. Sistemas bancarios, e-commerce, saude e governo sofrem risco adicional por tratarem dados sensiveis e operacoes financeiras.

Essas falhas podem passar despercebidas em testes manuais porque o comportamento funcional continua correto. Um testador pode validar login, compra e cadastro sem perceber que a aplicacao usa uma versao vulneravel do framework. Por isso, testes funcionais nao substituem SCA, inventario de dependencias, analise de imagem e monitoramento de CVEs.

## 4. Relacao com CWE-1395 e SBOM

A CWE-1395, Dependency on Vulnerable Third-Party Component, descreve a condicao em que um produto depende de um componente de terceiro que contem vulnerabilidade conhecida. A categoria reforca que o risco nao esta necessariamente no codigo produzido internamente, mas no uso de um bloco externo inseguro.

A SBOM, Software Bill of Materials, ajuda a prevenir esse risco ao listar os componentes usados no software, suas versoes, fornecedores, hashes, licencas e relacoes. Com uma SBOM, a equipe consegue responder rapidamente a perguntas como: "usamos Log4j?", "em quais sistemas?", "qual versao esta em producao?" e "qual imagem Docker precisa ser reconstruida?".

## 5. Deteccao e monitoramento

Componentes vulneraveis podem ser detectados por:

- revisao dos manifests de dependencias, como `pom.xml`, `build.gradle`, `package.json`, `package-lock.json`, `requirements.txt` e `go.mod`;
- scanners SCA, como OWASP Dependency-Check, Snyk, Trivy, GitHub Dependabot e npm audit;
- analise de imagens de container;
- geracao e verificacao de SBOM;
- monitoramento continuo de CVEs;
- politica de versoes suportadas e atualizacao periodica.

Scanners de dependencia comparam os componentes do projeto contra bases de vulnerabilidades conhecidas. Eles identificam o pacote, a versao, CVEs relacionadas, severidade, evidencias e recomendacoes.

Comparacao resumida:

| Ferramenta | Foco | Pontos fortes | Limitacoes |
|---|---|---|---|
| OWASP Dependency-Check | SCA de dependencias de aplicacao | Bom para Java/Maven/Gradle e relatorios CVE/CVSS | Pode gerar falso positivo; precisa base NVD atualizada |
| Trivy | Containers, filesystem, IaC, SBOM e dependencias | Simples, rapido, bom para Docker e CI | Resultado depende do ecossistema e da imagem analisada |
| Snyk | SCA, containers e IaC com plataforma SaaS | Priorizacao, correcoes sugeridas, integracao ampla | Recursos avancados podem depender de conta/plano |

O OWASP Dependency-Check funciona criando evidencias a partir dos arquivos do projeto, identificando bibliotecas e comparando com vulnerabilidades conhecidas. O relatorio normalmente mostra CVE, severidade, CVSS, descricao, referencias e caminho da dependencia afetada.

CVSS e a pontuacao padronizada de severidade. Em geral:

- 0.1 a 3.9: baixa;
- 4.0 a 6.9: media;
- 7.0 a 8.9: alta;
- 9.0 a 10.0: critica.

Mesmo assim, a decisao de correcao deve considerar contexto. Uma CVE critica em biblioteca nao carregada em producao pode ter risco menor que uma CVE alta em endpoint publico exploravel.

## 6. Exemplo Java com dependencia vulneravel

Exemplo de `pom.xml` vulneravel:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.14.1</version>
    </dependency>
</dependencies>
```

Execucao simulada do OWASP Dependency-Check:

```bash
dependency-check.sh \
  --project "sistema-pagamentos" \
  --scan ./ \
  --format HTML \
  --out ./dependency-check-report
```

Interpretação esperada do relatorio:

```text
Dependency: log4j-core-2.14.1.jar
Vulnerability: CVE-2021-44228
Severity: Critical
CVSS: 10.0
Impact: possibilidade de execucao remota de codigo em cenarios vulneraveis
```

Medidas corretivas:

- atualizar para versao corrigida e suportada;
- remover a dependencia se nao for necessaria;
- verificar dependencias transitivas que ainda puxam versao antiga;
- adicionar teste de regressao;
- gerar nova SBOM;
- reconstruir e republicar imagem/container;
- monitorar se a vulnerabilidade desapareceu do relatorio.

Exemplo corrigido:

```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.23.1</version>
</dependency>
```

## 7. Pratica com Juice Shop, Dependency-Check e Trivy

Procedimento para analisar o OWASP Juice Shop:

```bash
git clone https://github.com/juice-shop/juice-shop.git
cd juice-shop
npm ci
```

Analise com OWASP Dependency-Check:

```bash
dependency-check.sh \
  --project "OWASP Juice Shop" \
  --scan . \
  --format HTML \
  --out ./reports/dependency-check
```

O relatorio deve ser analisado procurando pacotes vulneraveis em `package-lock.json`, dependencias transitivas, severidades altas/criticas e recomendacoes de upgrade. Como o Juice Shop e intencionalmente vulneravel para ensino, a existencia de dependencias inseguras pode fazer parte do laboratorio.

Analise da imagem Docker com Trivy:

```bash
docker pull bkimminich/juice-shop
trivy image --severity HIGH,CRITICAL bkimminich/juice-shop
```

O Trivy pode apontar vulnerabilidades em pacotes do sistema operacional da imagem, dependencias Node.js e configuracoes relacionadas ao container. A correcao normal seria atualizar a imagem base, atualizar pacotes, reconstruir a imagem e validar novamente.

## 8. Cenario real: sistema bancario legado

Em um sistema bancario legado com bibliotecas antigas, eu seguiria estes passos:

1. Inventariar aplicacoes, imagens, bibliotecas, runtimes e servidores.
2. Gerar SBOM para cada componente implantado.
3. Rodar SCA em repositorios e imagens.
4. Classificar vulnerabilidades por severidade, explorabilidade e exposicao.
5. Identificar bibliotecas sem suporte ou abandonadas.
6. Criar plano de atualizacao incremental com testes automatizados.
7. Priorizar componentes expostos a internet e dados sensiveis.
8. Substituir bibliotecas sem manutencao.
9. Implantar monitoramento continuo de CVEs.
10. Documentar riscos aceitos temporariamente com prazo e responsavel.

Riscos legais e de conformidade incluem violacao de dever de seguranca, descumprimento de LGPD, falhas em auditorias, multas, quebra contratual, perda de certificacoes e responsabilidade por incidentes evitaveis.

## 9. Politica de CI/CD

Politica proposta:

- todo projeto deve possuir arquivo de lock, como `package-lock.json`, `yarn.lock`, `pom.xml` com versoes controladas ou equivalente;
- builds devem executar SCA automaticamente;
- vulnerabilidades criticas devem bloquear merge ou deploy;
- vulnerabilidades altas precisam de prazo curto de correcao ou aceite formal;
- dependencias sem manutencao devem ter plano de substituicao;
- imagens Docker devem ser escaneadas antes da publicacao;
- SBOM deve ser gerada a cada release;
- dependencias devem ser atualizadas em ciclos regulares;
- alertas de CVE devem abrir issue ou ticket automaticamente;
- excecoes devem ter responsavel, justificativa e data de expiracao.

Exemplo de etapa em CI:

```yaml
security-scan:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v4
    - name: Trivy filesystem scan
      uses: aquasecurity/trivy-action@master
      with:
        scan-type: fs
        scan-ref: .
        severity: HIGH,CRITICAL
        exit-code: "1"
```

## 10. Conclusao

Componentes vulneraveis e desatualizados sao uma ameaca porque transferem para a aplicacao todos os riscos das bibliotecas, frameworks, imagens e pacotes usados no projeto. A diferenca entre vulnerabilidade conhecida e zero-day e que a conhecida ja possui identificacao publica, como uma CVE, e normalmente alguma orientacao de correcao; a zero-day ainda nao e conhecida publicamente ou nao possui correcao disponivel.

Para manter um projeto seguro, a equipe deve conhecer suas dependencias, gerar SBOM, usar scanners, atualizar continuamente, remover bibliotecas abandonadas, controlar versoes, revisar imagens Docker e automatizar bloqueios no pipeline. Segurança de componentes nao e uma atividade pontual; e um processo continuo de inventario, monitoramento, atualizacao e validacao.
