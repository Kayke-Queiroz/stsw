## Software and Data Integrity Failures (A08:2021)

Referências:

- [OWASP Top Ten - Software and Data Integrity Failures](https://owasp.org/Top10/A08_2021-Software_and_Data_Integrity_Failures/)

### Introdução à Vulnerabilidade Software and Data Integrity Failures (A08:2021)

```
Explique o que a OWASP define como falha de integridade de software e dados.
- Quais são os principais problemas associados à falta de verificação de integridade em software e dados?
- Por que a ausência de mecanismos de verificação de confiabilidade em atualizações, bibliotecas ou plugins representa um risco?
- Como essas falhas podem permitir ataques como CI/CD pipeline attacks, dependency confusion e insecure deserialization?
- O que significa permitir que bibliotecas e atualizações sejam carregadas sem verificação de origem e integridade?
- Quais as consequências da desserialização de objetos não confiáveis?
- Em que tipos de sistemas os riscos são mais críticos: cloud, containers, CI/CD pipelines?
```

### Compreensão dos Impactos de Software and Data Integrity Failures

```
Analise os impactos típicos da exploração de falhas de integridade de software e dados.
- Como um invasor pode comprometer a integridade de dados sensíveis através de bibliotecas externas?
- Qual a diferença entre falhas de integridade e falhas de confidencialidade?
- O que pode acontecer se um atacante conseguir executar código malicioso a partir de uma atualização automatizada?
- Relacione essas falhas com os princípios de segurança da informação: integridade, disponibilidade e confiabilidade.
- Quais os prejuízos financeiros e de reputação que uma empresa pode sofrer após uma exploração bem-sucedida?
```

### Exemplos Reais de Ataques Ligados à Falha de Integridade

```
Apresente exemplos reais ou cenários simulados de ataques que exploraram falhas de integridade.
- O que foi o ataque de Event-Stream no ecossistema Node.js? Qual o vetor de ataque?
- Como ocorreu o ataque ao SolarWinds Orion e como ele se relaciona com falhas de integridade em processos de build e distribuição?
- Por que o ataque dependency confusion afetou empresas como Apple e Microsoft?
- Explique como falhas no processo de CI/CD podem permitir a introdução de backdoors.
- Quais foram os erros de segurança cometidos nesses casos?
```

### Técnicas de Prevenção e Boas Práticas

```
Descreva as práticas recomendadas para evitar Software and Data Integrity Failures.
- O que significa assinar digitalmente pacotes e binários?
- Como ferramentas como OWASP Dependency Check, SLSA (Supply Chain Levels for Software Artifacts) e Provenance metadata ajudam na prevenção?
- Por que é importante restringir o uso de plugins e bibliotecas externas desconhecidas?
- Como configurar políticas de segurança para o pipeline de CI/CD?
- Que papel tem o controle de versões e o gerenciamento de dependências na prevenção dessas falhas?
```

### Prática com Ferramentas Reais

```
Demostre como utilizar ferramentas para verificar dependências inseguras e falhas de integridade em um projeto real.
- Explore o OWASP Dependency-Check em um projeto Java. Quais vulnerabilidades foram detectadas?
- Explore o Snyk ou o Trivy para detectar dependências vulneráveis em contêineres e projetos Node.js/Python.
- Crie um pipeline simples de CI/CD com GitHub Actions e demonstre como configurar uma etapa de verificação de integridade das dependências.
- Quais arquivos devem ser monitorados no pipeline (e.g., package-lock.json, pom.xml)?
- Como integrar alertas de segurança automatizados para atualização de bibliotecas?
```

### Aplicação em Cenários Reais

```
Avalie a maturidade de segurança em um projeto de software quanto à integridade de componentes.
- Simule uma situação onde um desenvolvedor insere uma biblioteca de fonte duvidosa no projeto. Como o time de DevSecOps deve agir?
- Crie uma política organizacional de controle de bibliotecas externas. Quais controles devem ser aplicados?
- Avalie um ambiente de CI/CD e identifique pontos de vulnerabilidade relacionados à integridade.
```

### Conclusão

``` 
Apresente uma síntese sobre falhas de integridade de software e dados.
- Que medidas um desenvolvedor pode adotar para garantir a integridade de um projeto em produção?
- Qual o papel das assinaturas digitais, do versionamento e dos controles no CI/CD no combate a essas falhas.
```
