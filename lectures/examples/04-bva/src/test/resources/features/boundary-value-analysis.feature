Feature: Boundary Value Analysis (BVA) e variações
  Como pessoa estudante de teste de software
  Quero exercitar entradas de fronteira com Cucumber
  Para entender BVA clássico, robusto, worst-case e robust worst-case

  Background:
    Given que a idade aceita vai de 18 até 65
    And que a renda aceita vai de 2000 até 10000
    And que a renda nominal é 5000

  Scenario Outline: BVA unidimensional para idade
    When eu gero casos de "<variacao>" para idade com nominal 40
    Then devem existir <quantidade> casos gerados
    And todos os casos devem respeitar o resultado esperado

    Examples:
      | variacao | quantidade |
      | classico | 5          |
      | robusto  | 7          |

  Scenario Outline: BVA combinatório em duas variáveis
    When eu gero casos combinatórios de "<variacao>" com nominais idade 40 e renda 5000
    Then devem existir <quantidade> casos gerados
    And todos os casos devem respeitar o resultado esperado

    Examples:
      | variacao           | quantidade |
      | worst-case         | 25         |
      | robust worst-case  | 49         |
