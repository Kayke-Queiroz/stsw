Feature: Autorizar missões de drones
  Como equipe da Defesa Civil
  Quero validar missões de drones
  Para reduzir o risco de autorizar operações inseguras

  Rule: A missão só é autorizada quando bateria, vento e peso da carga estão dentro dos limites

    Scenario Outline: BVA normal com suposição de falha única
      When eu avalio uma missão com bateria <bateria>, vento <vento> e peso da carga <pesoCarga>
      Then a missão deve ser "<resultado>"

      Examples:
        | bateria | vento | pesoCarga | resultado  |
        | 70      | 20    | 4         | AUTORIZADA |
        | 30      | 20    | 4         | AUTORIZADA |
        | 31      | 20    | 4         | AUTORIZADA |
        | 99      | 20    | 4         | AUTORIZADA |
        | 100     | 20    | 4         | AUTORIZADA |
        | 70      | 0     | 4         | AUTORIZADA |
        | 70      | 1     | 4         | AUTORIZADA |
        | 70      | 39    | 4         | AUTORIZADA |
        | 70      | 40    | 4         | AUTORIZADA |
        | 70      | 20    | 1         | AUTORIZADA |
        | 70      | 20    | 2         | AUTORIZADA |
        | 70      | 20    | 7         | AUTORIZADA |
        | 70      | 20    | 8         | AUTORIZADA |

    Scenario Outline: BVA robusto com suposição de falha única
      When eu avalio uma missão com bateria <bateria>, vento <vento> e peso da carga <pesoCarga>
      Then a missão deve ser "<resultado>"

      Examples:
        | bateria | vento | pesoCarga | resultado  |
        | 70      | 20    | 4         | AUTORIZADA |
        | 29      | 20    | 4         | NEGADA     |
        | 30      | 20    | 4         | AUTORIZADA |
        | 31      | 20    | 4         | AUTORIZADA |
        | 99      | 20    | 4         | AUTORIZADA |
        | 100     | 20    | 4         | AUTORIZADA |
        | 101     | 20    | 4         | NEGADA     |
        | 70      | -1    | 4         | NEGADA     |
        | 70      | 0     | 4         | AUTORIZADA |
        | 70      | 1     | 4         | AUTORIZADA |
        | 70      | 39    | 4         | AUTORIZADA |
        | 70      | 40    | 4         | AUTORIZADA |
        | 70      | 41    | 4         | NEGADA     |
        | 70      | 20    | 0         | NEGADA     |
        | 70      | 20    | 1         | AUTORIZADA |
        | 70      | 20    | 2         | AUTORIZADA |
        | 70      | 20    | 7         | AUTORIZADA |
        | 70      | 20    | 8         | AUTORIZADA |
        | 70      | 20    | 9         | NEGADA     |

    Scenario Outline: Casos representativos de worst-case BVA
      When eu avalio uma missão com bateria <bateria>, vento <vento> e peso da carga <pesoCarga>
      Then a missão deve ser "<resultado>"

      Examples:
        | bateria | vento | pesoCarga | resultado  |
        | 30      | 0     | 1         | AUTORIZADA |
        | 30      | 40    | 8         | AUTORIZADA |
        | 100     | 0     | 8         | AUTORIZADA |
        | 100     | 40    | 1         | AUTORIZADA |
        | 31      | 1     | 2         | AUTORIZADA |
        | 99      | 39    | 7         | AUTORIZADA |
        | 70      | 20    | 4         | AUTORIZADA |

    Scenario Outline: Casos representativos de robust worst-case BVA
      When eu avalio uma missão com bateria <bateria>, vento <vento> e peso da carga <pesoCarga>
      Then a missão deve ser "<resultado>"

      Examples:
        | bateria | vento | pesoCarga | resultado  |
        | 29      | 20    | 4         | NEGADA     |
        | 101     | 20    | 4         | NEGADA     |
        | 70      | -1    | 4         | NEGADA     |
        | 70      | 41    | 4         | NEGADA     |
        | 70      | 20    | 0         | NEGADA     |
        | 70      | 20    | 9         | NEGADA     |
        | 29      | -1    | 0         | NEGADA     |
        | 101     | 41    | 9         | NEGADA     |
        | 30      | 0     | 1         | AUTORIZADA |
        | 100     | 40    | 8         | AUTORIZADA |
