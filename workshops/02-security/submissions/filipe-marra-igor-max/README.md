# Seminário de Segurança - OWASP Dependency-Track

## 📋 Sobre este Repositório

Material de apoio para o seminário sobre **OWASP Dependency-Track** - uma plataforma inteligente de Análise de Componentes que permite organizações identificarem e reduzirem riscos na cadeia de suprimentos de software.

## 🔗 Links Importantes

- [🎨 **Apresentação (Canva)**](https://canva.link/9f7ngqtthq1t6b3)
- [Site Oficial do Projeto OWASP](https://owasp.org/www-project-dependency-track/)
- [Repositório GitHub](https://github.com/DependencyTrack/dependency-track)
- [Documentação Oficial](https://docs.dependencytrack.org/)
- [CycloneDX (formato SBOM)](https://cyclonedx.org)

## 📂 Estrutura do Repositório

```
├── README.md                  # Este arquivo
├── demo/
│   ├── docker-compose.yml     # Docker Compose para demo ao vivo
│   └── README.md              # Instruções da demo
└── docs/
    └── resumo-tecnico.md      # Resumo técnico da ferramenta
```

## 🚀 Quick Start (Demo)

```bash
cd demo/
docker compose up -d
# Aguarde ~2 min (tudo é configurado automaticamente!)
```

### Acesso ao Dashboard

| | |
|--|--|
| **URL** | http://localhost:8080 |
| **Usuário** | `admin` |
| **Senha** | `Admin1234!` |

> ⚠️ A senha padrão `admin/admin` é trocada automaticamente pelo container de inicialização.
> Se der erro de login, rode `docker compose down -v && docker compose up -d` para resetar.

### O que acontece automaticamente ao subir:
1. ✅ API Server inicializa (~1-2 min)
2. ✅ Senha padrão é trocada para `Admin1234!`
3. ✅ Projeto "Demo Vulnerable App" é criado
4. ✅ SBOM com 15 componentes vulneráveis é carregado (Log4Shell, Spring4Shell, etc.)
5. ✅ Frontend fica disponível
6. ⏳ Vulnerabilidades aparecem após sincronização do NVD (~10-15 min na 1ª vez)

## 👥 Apresentadores

- Filipe Marra
- Igor Max

---

## 📚 Referências Bibliográficas

### Documentação Oficial
- OWASP Foundation. **OWASP Dependency-Track**. Disponível em: https://owasp.org/www-project-dependency-track/. Acesso em: jun. 2026.
- DependencyTrack. **Dependency-Track Documentation**. Disponível em: https://docs.dependencytrack.org/. Acesso em: jun. 2026.
- DependencyTrack. **Dependency-Track GitHub Repository**. Disponível em: https://github.com/DependencyTrack/dependency-track. Acesso em: jun. 2026.

### SBOM e CycloneDX
- CycloneDX. **CycloneDX Specification**. OWASP Foundation. Disponível em: https://cyclonedx.org/. Acesso em: jun. 2026.
- CycloneDX. **BOM Examples Repository**. Disponível em: https://github.com/CycloneDX/bom-examples. Acesso em: jun. 2026.
- OWASP Foundation. **Component Analysis**. Disponível em: https://owasp.org/www-community/Component_Analysis. Acesso em: jun. 2026.
- National Telecommunications and Information Administration (NTIA). **The Minimum Elements For a Software Bill of Materials (SBOM)**. U.S. Department of Commerce, 2021. Disponível em: https://www.ntia.gov/files/ntia/publications/sbom_minimum_elements_report.pdf. Acesso em: jun. 2026.

### Regulamentações e Padrões
- The White House. **Executive Order on Improving the Nation's Cybersecurity** (E.O. 14028). Washington, D.C., maio 2021. Disponível em: https://www.whitehouse.gov/briefing-room/presidential-actions/2021/05/12/executive-order-on-improving-the-nations-cybersecurity/. Acesso em: jun. 2026.
- Cybersecurity and Infrastructure Security Agency (CISA). **VEX Use Cases**. Disponível em: https://www.cisa.gov/sites/default/files/publications/VEX_Use_Cases_Document_508c.pdf. Acesso em: jun. 2026.

### Fontes de Vulnerabilidades
- National Institute of Standards and Technology (NIST). **National Vulnerability Database (NVD)**. Disponível em: https://nvd.nist.gov/. Acesso em: jun. 2026.
- GitHub. **GitHub Advisory Database**. Disponível em: https://github.com/advisories. Acesso em: jun. 2026.
- Sonatype. **OSS Index**. Disponível em: https://ossindex.sonatype.org/. Acesso em: jun. 2026.
- Google. **Open Source Vulnerabilities (OSV)**. Disponível em: https://osv.dev/. Acesso em: jun. 2026.
- Aqua Security. **Trivy**. Disponível em: https://www.aquasec.com/products/trivy/. Acesso em: jun. 2026.

### EPSS e Priorização de Vulnerabilidades
- FIRST.org. **Exploit Prediction Scoring System (EPSS)**. Disponível em: https://www.first.org/epss/. Acesso em: jun. 2026.
- FIRST.org. **Common Vulnerability Scoring System (CVSS)**. Disponível em: https://www.first.org/cvss/. Acesso em: jun. 2026.

### Supply Chain Security
- Sonatype. **2021 State of the Software Supply Chain Report**. Disponível em: https://www.sonatype.com/state-of-the-software-supply-chain/open-source-supply-demand-security. Acesso em: jun. 2026.
- OWASP Foundation. **OWASP Top 10 CI/CD Security Risks**. Disponível em: https://owasp.org/www-project-top-10-ci-cd-security-risks/. Acesso em: jun. 2026.

### Vulnerabilidades Notáveis (Exemplos da Demo)
- NIST. **CVE-2021-44228 — Log4Shell**. NVD, 2021. Disponível em: https://nvd.nist.gov/vuln/detail/CVE-2021-44228. Acesso em: jun. 2026.
- NIST. **CVE-2022-22965 — Spring4Shell**. NVD, 2022. Disponível em: https://nvd.nist.gov/vuln/detail/CVE-2022-22965. Acesso em: jun. 2026.
- NIST. **CVE-2021-23337 — Lodash Prototype Pollution**. NVD, 2021. Disponível em: https://nvd.nist.gov/vuln/detail/CVE-2021-23337. Acesso em: jun. 2026.

