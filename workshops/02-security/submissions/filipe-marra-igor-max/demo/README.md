# Demo - OWASP Dependency-Track

## Pré-requisitos

- Docker e Docker Compose instalados
- Mínimo 8GB RAM disponível (recomendado)
- Portas 8080 e 8081 livres

## Como Executar

```bash
docker compose up -d
```

Aguarde aproximadamente 2-3 minutos para o API Server inicializar completamente.

## Acessos

| Serviço | URL | Credenciais |
|---------|-----|-------------|
| Frontend | http://localhost:8080 | admin / admin (troque na 1ª vez) |
| API Server | http://localhost:8081 | - |

## SBOM de Exemplo (já incluso!)

O arquivo `bom.json` contém um SBOM CycloneDX com **15 componentes vulneráveis conhecidos**, incluindo:

| Componente | Versão | Vulnerabilidade Famosa |
|-----------|--------|------------------------|
| log4j-core | 2.14.1 | 🔴 Log4Shell (CVE-2021-44228) |
| spring-webmvc | 5.3.17 | 🔴 Spring4Shell (CVE-2022-22965) |
| jackson-databind | 2.9.8 | 🟠 Deserialization RCE |
| struts2-core | 2.5.10 | 🔴 Remote Code Execution |
| commons-collections | 3.2.1 | 🔴 Deserialization RCE |
| lodash | 4.17.19 | 🟡 Prototype Pollution |
| minimist | 1.2.5 | 🟡 Prototype Pollution |
| axios | 0.21.1 | 🟠 SSRF |
| django | 3.1.6 | 🟠 SQL Injection, XSS |
| pillow | 8.1.0 | 🟠 Buffer Overflow |
| moment | 2.29.1 | 🟡 ReDoS, Path Traversal |

## Passo a Passo da Demo

### 1. Subir o ambiente
```bash
docker compose up -d
```

### 2. Aguardar o API Server ficar saudável
```bash
# Verificar status
docker compose ps
# Deve mostrar "healthy" no apiserver (~2-3 min)
```

### 3. Acessar o Dashboard
- Abra http://localhost:8080
- Login: `admin` / `admin`
- Troque a senha na primeira vez

### 4. Criar Projeto (via UI)
1. Menu lateral → **Projects**
2. Botão **"Create Project"**
3. Nome: `Demo Vulnerable App`
4. Versão: `1.0.0`
5. Salvar

### 5. Upload do SBOM (via UI)
1. Clique no projeto criado
2. Aba **"Components"**
3. Botão **"Upload BOM"**
4. Selecione o arquivo `bom.json` desta pasta
5. Clique em Upload

### 6. Upload do SBOM (via API - alternativa)
```bash
# Login
TOKEN=$(curl -s -X POST http://localhost:8081/api/v1/user/login \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin&password=SUA_SENHA")

# Criar projeto
PROJECT=$(curl -s -X PUT http://localhost:8081/api/v1/project \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Demo Vulnerable App","version":"1.0.0"}')

# Extrair UUID do projeto
PROJECT_UUID=$(echo $PROJECT | python3 -c "import sys,json; print(json.load(sys.stdin)['uuid'])")

# Upload do SBOM (base64)
BOM_BASE64=$(base64 < bom.json)
curl -s -X PUT http://localhost:8081/api/v1/bom \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d "{\"project\":\"$PROJECT_UUID\",\"bom\":\"$BOM_BASE64\"}"
```

### 7. Aguardar Análise
- O Dependency-Track analisa automaticamente os componentes
- Na primeira execução, o **NVD mirror** leva ~10-15 min para sincronizar
- Após isso, vulnerabilidades aparecem no dashboard
- Você pode forçar re-análise: botão "Reanalyze" no projeto

### 8. O que mostrar na apresentação
1. **Dashboard** → métricas consolidadas (Critical, High, Medium, Low)
2. **Projeto → Vulnerabilities** → lista de CVEs com CVSS e EPSS
3. **Projeto → Components** → inventário completo
4. **Administration → Policy Management** → criar política de exemplo
5. **Vulnerability Audit** → marcar uma vuln como "Not Affected"

## Troubleshooting

| Problema | Solução |
|----------|---------|
| 0 vulnerabilidades | Aguarde o NVD mirror terminar (~15 min na 1ª vez) |
| Container reiniciando | Aumente memória do Docker para 8GB+ |
| Frontend não conecta | Verifique que API está em http://localhost:8081 |
| Porta em uso | `docker compose down` e tente novamente |

## Parar o Ambiente

```bash
docker compose down
# Para remover todos os dados:
docker compose down -v
```
