# language: pt

Funcionalidade: Calculadora de Tipos de Triângulo
  Como um estudante de geometria
  Eu quero saber o tipo de um triângulo a partir do comprimento dos seus lados
  Para que eu possa confirmar minhas respostas e aprender corretamente.

Cenário: Um triângulo com todos os lados iguais deve ser Equilátero
  Dado que os lados de um triângulo medem 10, 10 e 10
  Quando eu peço para a calculadora classificar
  Então ela deve me dizer que o triângulo é "Equilatero".

Cenário: Um triângulo com dois lados iguais deve ser Isósceles
  Dado que os lados de um triângulo medem 7, 7 e 10
  Quando eu peço para a calculadora classificar
  Então ela deve me dizer que o triângulo é "Isosceles".

Cenário: Um triângulo com todos os lados diferentes deve ser Escaleno
  Dado que os lados de um triângulo medem 5, 7 e 9
  Quando eu peço para a calculadora classificar
  Então ela deve me dizer que o triângulo é "Escaleno".

Cenário: Medidas que não conseguem formar um triângulo
  Dado que os lados de um triângulo medem 1, 2 e 10
  Quando eu peço para a calculadora classificar
  Então a calculadora deve me avisar que "Nao e um triangulo".

Cenário: Tentativa de usar um lado com medida zero
  Dado que os lados de um triângulo medem 10, 0 e 10
  Quando eu peço para a calculadora classificar
  Então a calculadora deve me avisar que os "Lados invalidos".

Cenário: Tentativa de usar um lado com medida negativa
  Dado que os lados de um triângulo medem 12, 15 e -5
  Quando eu peço para a calculadora classificar
  Então a calculadora deve me avisar que os "Lados invalidos".

Cenário: Tentativa de usar um lado com medida muito grande
  Dado que os lados de um triângulo medem 100, 150 e 201
  Quando eu peço para a calculadora classificar
  Então a calculadora deve me avisar que os "Lados invalidos".