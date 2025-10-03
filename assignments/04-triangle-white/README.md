## Implementar testes unitários em JUnit aplicando técnicas de caixa-branca

- Reaproveite uma estrutura Maven com JUnit (ex.: copiar `lectures/examples/02-hello-junit-maven`) para o seu diretório pessoal desta tarefa.
- Copie para esse projeto a implementação do triângulo criada em `assignments/01-triangle` para que os testes exercitem o mesmo código-fonte.
- Faça a análise estrutural do código identificando caminhos relevantes (decisões, ramos e condições compostas) e defina critérios de cobertura-alvo.
- Planeje os casos de teste considerando os caminhos internos necessários para atingir a cobertura-alvo.
- Implemente os testes unitários em JUnit dentro de `src/test/java`, utilizando assertivas para validar a saída e a detecção de triângulos inválidos, bem como verificando mensagens retornadas.
- Considere o uso de testes parametrizados (`@ParameterizedTest`) quando ajudar na cobertura de múltiplas combinações, mantendo os dados organizados e legíveis.
- Execute `mvn test` e, se desejar comprovar cobertura, habilite um plugin como JaCoCo para gerar relatórios; garanta que a cobertura planejada foi alcançada antes da entrega.
- Versione o código e submeta o pull request seguindo as instruções gerais das atividades após garantir que todos os testes passam.
