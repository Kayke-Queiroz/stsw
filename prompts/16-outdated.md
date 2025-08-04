## Vulnerable and Outdated Components (A06:2021)

Referências:

* [OWASP Top Ten - Vulnerable and Outdated Components](https://owasp.org/Top10/A06_2021-Vulnerable_and_Outdated_Components/)
* [CWE-1395: Dependency on Vulnerable Third-Party Component](https://cwe.mitre.org/data/definitions/1395.html)

### Introdução à Vulnerabilidade de Componentes Vulneráveis e Desatualizados

```
Explique o que é a vulnerabilidade "Vulnerable and Outdated Components", segundo a OWASP.
- Por que essa vulnerabilidade permanece no Top 10 da OWASP?
- O que diferencia esta vulnerabilidade de uma falha de configuração (security misconfiguration)?
- Qual a relação entre dependências, bibliotecas de terceiros e essa vulnerabilidade?

- Dê exemplos reais de vulnerabilidades exploradas por causa de componentes desatualizados (ex: Log4Shell).
- Qual é o papel de repositórios como Maven, PyPI ou npm na disseminação desse risco?
```

### Riscos e Impactos Associados

```
Liste os principais riscos associados ao uso de componentes desatualizados e vulneráveis.
- Como essa vulnerabilidade pode levar a falhas como exposição de dados ou negação de serviço?
- Qual o impacto potencial em sistemas críticos ou expostos à internet?
- Por que falhas associadas a componentes desatualizados podem passar despercebidas em testes manuais?

- Relacione a vulnerabilidade A06 com as categorias do CWE. Qual a definição da CWE-1395?
- Qual o papel da SBOM (Software Bill of Materials) na prevenção desses riscos?
```

### Detecção e Monitoramento Prático

```
Descreva como detectar componentes vulneráveis em um projeto de software.
- O que são scanners de dependência? Cite e apresente funcionalidades de ferramentas como OWASP Dependency-Check, Snyk, Trivy.
- Compare as ferramentas Trivy e OWASP Dependency-Check.
- Explique como o OWASP Dependency-Check funciona na prática (estrutura de scan, CVEs, pontuação).
- Qual a importância do CVSS e como interpretar sua pontuação?

- Dê um exemplo de um projeto Java com dependência vulnerável e simule a execução do OWASP Dependency-Check.
- Interprete o relatório gerado e proponha medidas corretivas.
```

### Prática com Ferramentas Reais

```
Demostre a utilização das ferramentas Trivy e OWASP Dependency-Check para identificar bibliotecas desatualizadas no OWASP Juice Shop.
- Clone o repositório oficial do OWASP Juice Shop no GitHub: https://github.com/juice-shop/juice-shop
- Instale e configure o OWASP Dependency-Check para analisar o projeto.
- Instale e configure o Trivy para escanear a imagem Docker do Juice Shop.
```

### Aplicação em Cenário Real

```
Suponha que você trabalha em uma equipe de desenvolvimento que mantém um sistema bancário legado.
- O sistema utiliza bibliotecas antigas que não recebem atualizações.
- Quais passos práticos você tomaria para identificar e corrigir possíveis vulnerabilidades de componentes?
- Quais riscos legais e de conformidade estão associados ao uso de componentes desatualizados?

- Proponha uma política de atualização e verificação contínua de bibliotecas no pipeline CI/CD.
```

### Conclusão

```
Apresente uma síntese final sobre a vulnerabilidade de componentes desatualizados.
- Qual a diferença entre vulnerabilidade conhecida e vulnerabilidade zero-day?
- O que deve ser feito para garantir que seu projeto esteja sempre utilizando versões seguras de componentes?
```
