# Resumo Técnico - OWASP Dependency-Track

## 1. O que é?

**OWASP Dependency-Track** é uma plataforma de Análise de Componentes (Component Analysis) que permite organizações identificar e reduzir riscos na cadeia de suprimentos de software através do uso de **SBOM (Software Bill of Materials)**.

- **Projeto OWASP Flagship** (nível mais alto de maturidade)
- **Licença:** Apache 2.0
- **Linguagem:** Java (backend), Vue.js (frontend)
- **Criado em:** 2013
- **Stars GitHub:** 2.5k+

## 2. Problema que Resolve

O software moderno é composto majoritariamente por código de terceiros (bibliotecas, frameworks). Exemplos de incidentes:

- **Log4Shell (2021)** — CVE-2021-44228 afetou milhões de aplicações Java
- **SolarWinds (2020)** — ataque à supply chain comprometeu 18.000 organizações
- **event-stream (2018)** — pacote NPM com milhões de downloads comprometido

Dependency-Track responde perguntas como:
- "Quais aplicações usam a biblioteca X vulnerável?"
- "Qual o risco total do meu portfolio de software?"
- "Estamos em conformidade com políticas de licença?"

## 3. Conceitos Fundamentais

### SBOM (Software Bill of Materials)
- Lista completa de todos os componentes de um software
- Análogo a "lista de ingredientes" de um produto
- Formatos: **CycloneDX** (preferido), SPDX
- Mandatório pela Executive Order 14028 (EUA, 2021)

### CycloneDX
- Padrão OWASP para SBOM
- Formato leve (JSON/XML)
- Suporta: software, hardware, serviços, ML models
- Inclui VEX (Vulnerability Exploitability Exchange)

### VEX (Vulnerability Exploitability Exchange)
- Documento que comunica se uma vulnerabilidade é realmente exploitável
- Status possíveis: Not Affected, Affected, Fixed, Under Investigation
- Reduz ruído de falsos positivos

### EPSS (Exploit Prediction Scoring System)
- Score de 0 a 1 indicando probabilidade de exploração nos próximos 30 dias
- Complementa CVSS (severidade ≠ probabilidade de exploração)

## 4. Arquitetura

```
┌─────────────────────────────────────────────────────┐
│                    Frontend (Vue.js)                  │
│                   Port 8080 (nginx)                   │
└──────────────────────────┬──────────────────────────┘
                           │ REST API
┌──────────────────────────▼──────────────────────────┐
│                  API Server (Java)                    │
│                     Port 8081                         │
│  ┌─────────┐ ┌──────────┐ ┌─────────────────────┐  │
│  │Analyzers│ │Policy Eng│ │Notification Service │  │
│  └─────────┘ └──────────┘ └─────────────────────┘  │
└──────────────────────────┬──────────────────────────┘
                           │
          ┌────────────────┼────────────────┐
          ▼                ▼                ▼
   ┌──────────┐    ┌──────────┐    ┌──────────────┐
   │ Database │    │  NVD/OSS │    │ Repositories │
   │(Postgres)│    │  Index   │    │ (Maven,NPM..)│
   └──────────┘    └──────────┘    └──────────────┘
```

## 5. Fontes de Vulnerabilidades

| Fonte | Tipo | Cobertura |
|-------|------|-----------|
| NVD | Pública/Gratuita | CVEs globais |
| GitHub Advisories | Pública/Gratuita | Ecossistemas GitHub |
| OSS Index (Sonatype) | Gratuita (rate-limited) | Multi-ecossistema |
| Snyk | Comercial | Ampla, curada |
| Trivy | Gratuita | Containers, OS |
| OSV | Pública/Gratuita | Multi-ecossistema |
| VulnDB | Comercial | Mais abrangente |

## 6. Fluxo de Trabalho Típico

1. **Gerar SBOM** durante build (CycloneDX plugins para Maven, NPM, Pip, etc.)
2. **Upload SBOM** para Dependency-Track (via API ou UI)
3. **Análise automática** contra fontes de vulnerabilidades
4. **Avaliação de políticas** (segurança, licença, operacional)
5. **Notificação** de novas vulnerabilidades (Slack, Email, Teams)
6. **Auditoria** — triagem de findings (suprimir, aceitar risco, etc.)
7. **Monitoramento contínuo** — re-análise quando novas CVEs surgem

## 7. Integrações CI/CD

### Ferramentas de Geração de SBOM
- **cyclonedx-maven-plugin** (Java)
- **@cyclonedx/cyclonedx-npm** (Node.js)
- **cyclonedx-python** (Python)
- **cyclonedx-dotnet** (.NET)
- **syft** (containers, multi-linguagem)

### Plugins CI/CD
- GitHub Actions
- Jenkins Plugin
- GitLab CI (via API)
- Azure DevOps

## 8. Requisitos de Infraestrutura

| Componente | Mínimo | Recomendado |
|-----------|--------|-------------|
| API Server RAM | 2 GB | 8 GB |
| API Server CPU | 2 cores | 4 cores |
| Frontend RAM | 128 MB | 512 MB |
| Frontend CPU | 0.5 core | 1 core |
| Banco de Dados | H2 (embarcado) | PostgreSQL |

## 9. Comparação com Alternativas

| Critério | Dependency-Track | Dependency-Check | Snyk | Dependabot |
|----------|:---------------:|:----------------:|:----:|:----------:|
| Open Source | ✅ | ✅ | ❌ | ❌ |
| Baseado em SBOM | ✅ | ❌ | ❌ | ❌ |
| Gestão de Portfolio | ✅ | ❌ | ✅ | ❌ |
| Motor de Políticas | ✅ | ❌ | ✅ | ❌ |
| VEX Support | ✅ | ❌ | ❌ | ❌ |
| Multi-ecossistema | ✅ | ✅ | ✅ | ✅ |
| Monitoramento Contínuo | ✅ | ❌ | ✅ | ✅ |
| CI/CD Integration | ✅ | ✅ | ✅ | ✅ |

> **Nota:** OWASP Dependency-**Check** é um *scanner* que gera findings. Dependency-**Track** é uma *plataforma* que consome SBOMs e gerencia o ciclo de vida. São complementares!
