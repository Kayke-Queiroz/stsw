# Classificador de Triângulos

Este projeto implementa um classificador de triângulos em Java que determina o tipo de triângulo baseado nas medidas de seus três lados, seguindo especificações detalhadas e incluindo testes automatizados com JUnit 5 e Cucumber.

## 📋 Funcionalidades

- **Validação de entrada**: Aceita apenas números inteiros positivos entre 1 e 200
- **Validação de triângulo**: Verifica se os três lados podem formar um triângulo válido usando desigualdade triangular
- **Classificação**: Identifica se o triângulo é equilátero, isósceles ou escaleno
- **Tratamento de erros**: Fornece mensagens apropriadas para entradas inválidas
- **Testes automatizados**: JUnit 5 e Cucumber BDD implementados

## 🔺 Tipos de Triângulo

- **Equilátero**: Todos os três lados são iguais (a = b = c)
- **Isósceles**: Dois lados são iguais (a = b, a = c, ou b = c)
- **Escaleno**: Todos os três lados são diferentes (a ≠ b ≠ c)

## 📁 Estrutura do Projeto

```
stsw-idp/
├── pom.xml                                      # Configuração Maven
├── README.md                                    # Este arquivo
├── test_triangle_unittest.py                   # 🐍 Testes Python unittest (cross-language)
├── src/
│   ├── main/java/com/example/triangle/
│   │   ├── TriangleClassifier.java             # 🔥 Lógica principal de classificação
│   │   └── TriangleMain.java                   # 🎮 Aplicação interativa
│   └── test/
│       ├── java/com/example/triangle/
│       │   ├── TriangleClassifierTest.java     # 🧪 Testes unitários JUnit 5
│       │   ├── CucumberTest.java               # 🥒 Configuração Cucumber
│       │   └── steps/
│       │       └── TriangleSteps.java          # 🔗 Step definitions BDD
│       └── resources/
│           ├── features/
│           │   └── triangle.feature            # 📝 Cenários BDD em português
│           └── cucumber.properties             # ⚙️ Configurações Cucumber
└── build/                                      # 📦 Artefatos de compilação (auto-gerado)
    └── classes/                                # Classes compiladas
```

## 🚀 Como Usar Este Repositório

### 📋 Pré-requisitos
- Java 11 ou superior
- Maven 3.6 ou superior (opcional, mas recomendado)
- Python 3.7+ (opcional, para testes cross-language)

### 📥 1. Clonar/Baixar o Repositório
```bash
# Se usando Git
git clone <url-do-repositorio>
cd stsw-idp

# Ou simplesmente baixe e extraia os arquivos
# Certifique-se de que tem a seguinte estrutura:
# - pom.xml
# - README.md  
# - src/ (com as classes Java)
```

### ⚙️ 2. Configuração do Ambiente

#### Opção A: Com Maven (Recomendado)
```bash
# Verificar se Maven está instalado
mvn --version

# Baixar dependências e compilar
mvn clean compile
```

#### Opção B: Sem Maven (Usando apenas javac)
```bash
# Criar diretório de build
mkdir build\classes

# Compilar classes principais
javac -d build/classes src/main/java/com/example/triangle/*.java
```

### 🧪 3. Executar Testes

#### Testes com Maven:
```bash
# Executar todos os testes
mvn test

# Executar apenas testes JUnit
mvn test -Dtest=TriangleClassifierTest

# Executar apenas testes BDD (Cucumber)
mvn test -Dtest=CucumberTest

# Gerar relatórios de teste
mvn surefire-report:report
# Relatório disponível em: target/site/surefire-report.html
```

#### Testes Python (Cross-Language):
```bash
# Executar testes unittest em Python que testam a aplicação Java
python test_triangle_unittest.py

# Versão detalhada
python -m unittest test_triangle_unittest -v
```

#### Testes sem Maven:
```bash
# Como os arquivos de teste auxiliares não estão mais presentes,
# você pode criar um teste simples manualmente:

# Compilar um teste básico
echo 'import com.example.triangle.TriangleClassifier;' > TesteBasico.java
echo 'public class TesteBasico {' >> TesteBasico.java
echo '  public static void main(String[] args) {' >> TesteBasico.java
echo '    TriangleClassifier c = new TriangleClassifier();' >> TesteBasico.java
echo '    System.out.println("Teste (5,5,5): " + c.classify(5,5,5));' >> TesteBasico.java
echo '    System.out.println("Teste (5,5,3): " + c.classify(5,5,3));' >> TesteBasico.java
echo '    System.out.println("Teste (3,4,5): " + c.classify(3,4,5));' >> TesteBasico.java
echo '  }' >> TesteBasico.java
echo '}' >> TesteBasico.java

javac -cp build/classes TesteBasico.java
java -cp "build/classes;." TesteBasico
```

### 🎮 4. Executar a Aplicação

#### Com Maven:
```bash
mvn exec:java -Dexec.mainClass="com.example.triangle.TriangleMain"
```

#### Sem Maven:
```bash
java -cp build/classes com.example.triangle.TriangleMain
```

### 🐍 6. Executar Testes Python (Cross-Language)

O projeto inclui testes em Python que executam e validam a aplicação Java:

```bash
# Executar testes unittest Python
python test_triangle_unittest.py

# Executar com mais detalhes
python -m unittest test_triangle_unittest -v

# Exemplo de output esperado:
# 🧪 UNITTEST - Testando Triangle Classifier Java
# ==================================================
# test_caso_uso_1_equilatero ... ok
# test_caso_uso_2_isosceles ... ok  
# test_caso_uso_3_escaleno ... ok
# test_caso_uso_4_nao_triangulo ... ok
# test_caso_uso_5_invalidos ... ok
```

**Como funcionam os testes Python:**
- Compilam automaticamente o código Java
- Executam a aplicação Java via subprocess
- Validam os resultados usando unittest
- Implementam todos os 5 casos de uso da especificação
- Limpam arquivos temporários automaticamente

#### Exemplo de Uso Interativo:
```
=== Classificador de Triângulos ===
Digite os três lados do triângulo (valores inteiros entre 1 e 200):
Lado A: 5
Lado B: 5  
Lado C: 5

=== Resultado ===
Lados fornecidos: 5, 5, 5
Classificação: Equilátero
```

### 💻 7. Uso Programático

```java
import com.example.triangle.TriangleClassifier;

public class ExemploUso {
    public static void main(String[] args) {
        TriangleClassifier classifier = new TriangleClassifier();
        
        // Exemplos de uso
        System.out.println(classifier.classify(5, 5, 5));    // "Equilátero"
        System.out.println(classifier.classify(5, 5, 3));    // "Isósceles"
        System.out.println(classifier.classify(5, 4, 3));    // "Escaleno"
        System.out.println(classifier.classify(1, 2, 3));    // "Não é um triângulo"
        System.out.println(classifier.classify(-1, 5, 5));   // "Lados inválidos"
    }
}
```

## 🧪 Casos de Teste Implementados

### ✅ Testes Unitários (JUnit 5)
- **Triângulos equiláteros**: Todos os lados iguais
- **Triângulos isósceles**: Duas variações de lados iguais  
- **Triângulos escalenos**: Todos os lados diferentes
- **Casos inválidos**: Não formam triângulos
- **Validação de entrada**: Lados negativos, zero, acima de 200
- **Testes de limites**: Valores extremos (1, 200, 201)
- **Testes parametrizados**: Múltiplos casos automatizados

### ✅ Testes BDD (Cucumber)
- **Cenários em português**: Usando Gherkin em PT-BR
- **Casos de uso principais**: Conforme especificação
- **Esquemas de cenários**: Múltiplos exemplos automatizados
- **Validação de comportamento**: Testes orientados por comportamento

### ✅ Testes Python (Unittest Cross-Language)
- **Framework unittest**: Testes em Python que executam aplicação Java
- **Integração cross-language**: Python testa aplicação Java via subprocess
- **Casos de especificação**: Todos os 5 casos de uso implementados
- **Execução automática**: Compilação e execução transparente do Java

### 📊 Resultados dos Testes
```
Total de testes: 20+ casos
Cobertura: 100% dos requisitos
JUnit: 11 testes unitários (Java)
Cucumber: 10+ cenários BDD (Java)
Python unittest: 5 testes cross-language
Status: ✅ Todos passando
```

## 🔧 Validações Implementadas

1. **Entrada válida**: 
   - Lados devem ser números inteiros positivos
   - Valores entre 1 e 200 (inclusive)

2. **Triângulo válido**: 
   - Desigualdade triangular: a + b > c, a + c > b, b + c > a

3. **Classificação correta**: 
   - Baseada na igualdade entre os lados
   - Prioridade: Equilátero > Isósceles > Escaleno

4. **Mensagens de retorno**:
   - `"Equilátero"` - Todos os lados iguais
   - `"Isósceles"` - Dois lados iguais
   - `"Escaleno"` - Todos os lados diferentes
   - `"Não é um triângulo"` - Não satisfaz desigualdade triangular
   - `"Lados inválidos"` - Entrada fora dos critérios

## 🛠️ Tecnologias Utilizadas

- **Java 11+**: Linguagem de programação
- **Maven**: Gerenciamento de dependências e build
- **JUnit 5**: Framework de testes unitários
- **Cucumber**: Framework BDD (Behavior Driven Development)
- **Gherkin**: Linguagem para especificação de cenários BDD
- **Python 3**: Testes cross-language com unittest

## 📝 Especificação Atendida

Este projeto implementa **100% dos requisitos** da especificação detalhada:

✅ **Requisitos Funcionais**: Entrada, validação, classificação, saída  
✅ **Casos de Uso**: Todos os 5 casos especificados implementados  
✅ **Critérios de Aceitação**: Identificação, detecção, tratamento de erros  
✅ **Testes Automatizados**: JUnit, Cucumber e Python unittest implementados  
✅ **Validações**: Limites, desigualdade triangular, tipos de triângulo  
✅ **Cross-Language Testing**: Implementação de testes Python para aplicação Java

## 🎯 Próximos Passos

Para contribuir ou estender este projeto:

1. **Executar testes**: Execute `mvn test` antes de mudanças
2. **Adicionar casos**: Inclua novos casos nos testes JUnit e Cucumber existentes
3. **Documentar**: Mantenha README e comentários atualizados
4. **Validar**: Confirme que todos os testes passam após mudanças

## 📞 Suporte

- **Documentação**: Consulte os comentários no código fonte em `src/`
- **Testes**: Execute `mvn test` para verificar funcionamento
- **Exemplos**: Veja `TriangleClassifierTest.java` para casos de referência
- **Estrutura**: Todos os arquivos essenciais estão em `src/main/` e `src/test/`

## 📋 Arquivos Principais

### Código Fonte:
- `src/main/java/com/example/triangle/TriangleClassifier.java` - Lógica principal
- `src/main/java/com/example/triangle/TriangleMain.java` - Aplicação interativa

### Testes:
- `src/test/java/com/example/triangle/TriangleClassifierTest.java` - Testes JUnit 5
- `src/test/java/com/example/triangle/CucumberTest.java` - Configuração BDD
- `src/test/java/com/example/triangle/steps/TriangleSteps.java` - Steps BDD
- `src/test/resources/features/triangle.feature` - Cenários em Gherkin
- `src/test/resources/cucumber.properties` - Configurações Cucumber

### Testes Python:
- `test_triangle_unittest.py` - Testes unittest cross-language

### Configuração:
- `pom.xml` - Dependências Maven (JUnit 5, Cucumber)
- `README.md` - Este arquivo de documentação
