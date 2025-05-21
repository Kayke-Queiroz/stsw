## Server Side Request Forgey (SSRF) (A10:2021)

Referências:

* [OWASP Top Ten - SSRF](https://owasp.org/Top10/A10_2021-Server-Side_Request_Forgery_%28SSRF%29/)
* [SSRF](https://owasp.org/www-community/attacks/Server_Side_Request_Forgery)
* [SSRF (Portswigger)](https://portswigger.net/web-security/ssrf)
* [SSRF cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/Server_Side_Request_Forgery_Prevention_Cheat_Sheet.html)

### Introdução à Vulnerabilidade SSRF

```
Explique o que é uma vulnerabilidade Server-Side Request Forgery (SSRF), segundo a OWASP.
- Em que contexto ela ocorre e por que é categorizada como vulnerabilidade crítica?
- O que significa "requisição forjada do lado do servidor"? Como isso difere de ataques client-side?
- Quais são os alvos mais comuns desse tipo de ataque (ex: serviços internos, metadata de cloud, localhost)?

- Por que a vulnerabilidade SSRF foi incluída no Top 10 da OWASP em 2021?
- Quais são os principais impactos e riscos associados a ataques SSRF?
- Como a arquitetura moderna baseada em microserviços e cloud aumenta o risco de SSRF?
```

### Exemplos Práticos de Ataques SSRF

```
Analise exemplos reais de exploração de SSRF em aplicações web.
- Considere uma aplicação que permite buscar imagens externas via URL: como um atacante poderia abusar dessa funcionalidade?
- Dê um exemplo de ataque SSRF contra a AWS, explorando o endpoint `http://169.254.169.254/latest/meta-data/`.

- Crie um pequeno código Java (ou pseudocódigo) que simule uma requisição HTTP sem validação adequada.
- Mostre como um atacante poderia explorar esse código para acessar um serviço interno.

- Qual a diferença entre um ataque SSRF simples e um ataque SSRF cego?
- Como um atacante pode usar SSRF para realizar port scanning na rede interna?
```

### Técnicas de Mitigação e Boas Práticas

```
Quais são as melhores práticas de mitigação para SSRF segundo a OWASP e a cheat sheet oficial?
- Explique a diferença entre listas de bloqueio (deny list) e listas de permissão (allow list). Qual é mais eficaz e por quê?
- Por que validar apenas o hostname pode não ser suficiente para bloquear SSRF?

- O que é DNS rebinding e como ele pode ser usado para burlar validações em SSRF?
- Quais medidas de defesa em profundidade devem ser adotadas (WAF, isolamento de rede, etc)?

- Proponha uma função segura que valide URLs externas fornecidas pelo usuário antes de fazer a requisição.
- Como restringir o acesso de aplicações a serviços internos via configurações de firewall e políticas de rede?
```

### Detecção e Exploração de SSRF com SSRFmap

```
Apresente a ferramenta SSRFmap e sua utilidade para explorar SSRF em aplicações web.
- O que é o SSRFmap e como ele automatiza ataques SSRF?
- Quais tipos de serviços podem ser mapeados por meio dessa ferramenta?
- Qual a diferença entre módulos ativos e passivos no SSRFmap?

- Instale e execute o SSRFmap em um ambiente controlado (lab local ou DVWA).
- Utilize o SSRFmap para detectar serviços internos acessíveis via uma vulnerabilidade SSRF simulada.
- Interprete os resultados apresentados pela ferramenta.

- Quais são as boas práticas de uso da ferramenta em ambientes de teste?
- Que cuidados éticos e legais devem ser tomados ao utilizar ferramentas como o SSRFmap?
```

### Estudo de Caso: Incidente Real de SSRF

```
Pesquise um caso real de ataque baseado em SSRF que causou impacto significativo.
- Quais foram as falhas de arquitetura ou validação que permitiram o ataque?
- Que tipos de dados ou sistemas foram comprometidos?
- Como a organização respondeu ao incidente e quais foram as lições aprendidas?

- Com base no caso estudado, elabore um conjunto de requisitos de segurança que poderiam ter evitado o ataque.
- Que medidas pós-incidente você recomendaria para uma organização exposta a esse tipo de vulnerabilidade?
```

### Atividade Guiada: Simulação de Exploração e Mitigação de SSRF

```
Simule um cenário onde uma aplicação vulnerável permite a inserção de uma URL externa.
- Reproduza o ataque SSRF explorando a requisição para um serviço interno fictício.
- Utilize o SSRFmap para automatizar a exploração.

- Em seguida, implemente uma proteção usando uma allow list de domínios válidos.
- Mostre como o ataque falha após a aplicação das defesas.

- Documente a atividade com capturas de tela, códigos utilizados e conclusões da exploração e mitigação.
```
### Conclusão

```
Com base no que conversamos sobre SSRF:
- Resuma o que é SSRF e por que ela é perigosa.
- Liste as principais causas e formas de mitigação.
- Qual aspecto da vulnerabilidade você é mais difícil de lidar na prática?  
```