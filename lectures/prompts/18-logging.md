## Security Logging and Monitoring Failures (A09:2021)

Referências:

- [OWASP TOP Ten - Security Logging and Monitoring Failures]

### Introdução à Vulnerabilidade Security Logging and Monitoring Failures

```
Explique o que caracteriza uma falha de registro e monitoramento de segurança e apresente exemplos de incidentes reais causados por ausência de logging ou monitoramento..
- Por que esse tipo de falha continua relevante no OWASP Top 10?
- Qual a relação entre essa falha e a detecção de ataques?
- Por que a falta de monitoramento é um risco crítico para sistemas em produção?
- Cite brevemente o caso Equifax ou outro de grande impacto.
- Quais foram os impactos organizacionais e técnicos?
```

### Principais Causas e Sintomas de Falhas de Logging e Monitoramento

```
Descreva os sintomas comuns de falhas em sistemas de log e monitoramento e Explique como a ausência de alertas pode atrasar a resposta a incidentes.
- O que significa log insuficiente ou desativado?
- Qual a importância da correlação entre eventos e alertas em tempo real?
- Por que é crítico notificar acessos não autorizados?
- Como logs inadequados afetam investigações pós-incidente?
```

### Boas Práticas de Logging e Monitoramento

```
Liste as melhores práticas de logging recomendadas pela OWASP.
- Quais eventos devem obrigatoriamente ser registrados?
- Como garantir a integridade e confidencialidade dos logs?

Explique o conceito de "Security Information and Event Management" (SIEM).
- Qual o papel das ferramentas SIEM na detecção de ataques?
- Como elas ajudam na análise de ameaças em tempo real?
```

### Exemplos Práticos

```
Analise o trecho de código abaixo e identifique se o log é seguro e completo:

try {
    login(user, password);
} catch (Exception e) {
    System.out.println("Erro ao logar");
}


- O que está faltando nesse log?
- Como tornar esse registro mais útil para detecção de falhas ou ataques?


Reescreva o trecho de código acima utilizando boas práticas de logging.

- Inclua informações relevantes como IP, timestamp e tipo de falha.

```

### Prática com Ferramentas Reais

```

Utilize o OWASP Juice Shop para identificar falhas de monitoramento.

- Quais eventos não são registrados?
- Simule um ataque e verifique se ele gera logs no console ou nos arquivos.

```

### Aplicação em Cenários Reais

```
Imagine que você é o responsável de segurança de um e-commerce.
- Quais eventos críticos você monitoraria?
- Como garantiria que as tentativas de acesso indevido fossem detectadas e alertadas?

Crie um plano de resposta a incidentes baseado em um cenário onde os logs não foram coletados.

- Quais evidências poderiam estar faltando?
- Quais mudanças você proporia na política de logging da empresa?
```
