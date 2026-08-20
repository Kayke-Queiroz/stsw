Feature: Validar política de matrícula usando equivalence class partitioning
  Como pessoa estudante de teste de software
  Quero escolher representantes de classes equivalentes de entrada
  Para reduzir casos redundantes sem perder cobertura de comportamento relevante

  Rule: A matrícula em disciplina avançada exige idade válida, nota suficiente, vaga e pagamento aceito

    Scenario Outline: Particionar idade em classes equivalentes
      When eu avalio uma solicitação com idade <idade>, nota 85, vagas 3 e pagamento "PAGO"
      Then a decisão da matrícula deve ser "<decisao>"

      Examples:
        | classe                              | idade | decisao               |
        | inválida: menor de idade            | 16    | DADOS_INVALIDOS       |
        | válida: adulto                      | 35    | MATRICULA_CONFIRMADA  |
        | válida: pessoa sênior               | 68    | MATRICULA_CONFIRMADA  |
        | inválida: idade fora do limite real | 130   | DADOS_INVALIDOS       |

    Scenario Outline: Particionar nota do pré-requisito em classes equivalentes
      When eu avalio uma solicitação com idade 35, nota <nota>, vagas 3 e pagamento "PAGO"
      Then a decisão da matrícula deve ser "<decisao>"

      Examples:
        | classe                                  | nota | decisao               |
        | inválida: abaixo da escala              | -5   | DADOS_INVALIDOS       |
        | válida: insuficiente para a disciplina  | 55   | RECUSADA              |
        | válida: suficiente para a disciplina    | 85   | MATRICULA_CONFIRMADA  |
        | inválida: acima da escala               | 105  | DADOS_INVALIDOS       |

    Scenario Outline: Particionar disponibilidade de vagas em classes equivalentes
      When eu avalio uma solicitação com idade 35, nota 85, vagas <vagas> e pagamento "PAGO"
      Then a decisão da matrícula deve ser "<decisao>"

      Examples:
        | classe                         | vagas | decisao               |
        | inválida: quantidade negativa  | -1    | DADOS_INVALIDOS       |
        | válida: turma cheia            | 0     | LISTA_DE_ESPERA       |
        | válida: turma com vagas        | 5     | MATRICULA_CONFIRMADA  |

    Scenario Outline: Particionar situação de pagamento em classes equivalentes
      When eu avalio uma solicitação com idade 35, nota 85, vagas 3 e pagamento "<pagamento>"
      Then a decisão da matrícula deve ser "<decisao>"

      Examples:
        | classe                            | pagamento    | decisao               |
        | válida: pagamento confirmado      | PAGO         | MATRICULA_CONFIRMADA  |
        | válida: bolsa aprovada            | BOLSA        | MATRICULA_CONFIRMADA  |
        | válida: pagamento ainda pendente  | PENDENTE     | RECUSADA              |
        | válida: pagamento cancelado       | CANCELADO    | RECUSADA              |
        | inválida: código desconhecido     | PIX_AGENDADO | DADOS_INVALIDOS       |

    Scenario Outline: Combinações representativas entre classes válidas
      When eu avalio uma solicitação com idade <idade>, nota <nota>, vagas <vagas> e pagamento "<pagamento>"
      Then a decisão da matrícula deve ser "<decisao>"

      Examples:
        | classe de combinação                         | idade | nota | vagas | pagamento | decisao               |
        | elegível, com vaga e pagamento confirmado     | 35    | 85   | 3     | PAGO      | MATRICULA_CONFIRMADA  |
        | elegível, com vaga e bolsa aprovada           | 68    | 90   | 2     | BOLSA     | MATRICULA_CONFIRMADA  |
        | elegível, mas turma cheia                     | 35    | 85   | 0     | PAGO      | LISTA_DE_ESPERA       |
        | idade válida, mas nota insuficiente           | 35    | 55   | 3     | PAGO      | RECUSADA              |
        | idade válida, nota suficiente e pagamento ruim | 35    | 85   | 3     | PENDENTE  | RECUSADA              |
