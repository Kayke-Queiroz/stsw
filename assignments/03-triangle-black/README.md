## Implementar casos de testes em Cucumber aplicando técnicas de caixa-preta (BVA)

- Reaproveite a estrutura Maven usada na atividade anterior (ex.: copiar `lectures/examples/02-hello-cucumber-maven`) para dentro do seu diretório pessoal desta tarefa.
- Copie para esse projeto a implementação do triângulo criada em `assignments/01-triangle` (sua própria versão ou a de referência) para que os testes rodem sobre o mesmo código-fonte.
- Planeje os testes com métodos de caixa-preta: identifique classes de equivalência (triângulos válidos, inválidos e limites) e execute Análise de Valores Limite considerando o domínio 1–200 informado no enunciado original.
- Construa os cenários Gherkin descrevendo somente entradas e saídas observáveis; garanta que cada classe de equivalência relevante e cada limite identificado possuam pelo menos um cenário.
- Implemente os step definitions em Java chamando apenas a API pública da classe do triângulo (sem acessar detalhes internos).
- Mantenha um runner Cucumber (por exemplo, `RunCucumberTest`) e assegure-se de que `mvn test` execute todos os cenários criados.
- Antes de entregar, rode `mvn test` e confirme que todos os cenários passam
- Enviar pull request e submeter link do pull request no ambiente virtual.
