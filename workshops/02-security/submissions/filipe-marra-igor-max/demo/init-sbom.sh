#!/bin/sh
# Script que aguarda o API Server e faz upload automático do SBOM
# Executado automaticamente pelo docker compose na primeira inicialização

API_URL="http://dtrack-apiserver:8080"
USERNAME="admin"
DEFAULT_PASSWORD="admin"
NEW_PASSWORD="Admin1234!"

echo "⏳ Aguardando API Server ficar pronto..."
until curl -sf "$API_URL/api/version" > /dev/null 2>&1; do
  sleep 5
done
echo "✅ API Server pronto!"
sleep 10

echo "🔑 Fazendo login..."

# Tenta login direto (caso senha já tenha sido trocada em execução anterior)
TOKEN=$(curl -s -X POST "$API_URL/api/v1/user/login" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=$USERNAME&password=$NEW_PASSWORD")

if echo "$TOKEN" | grep -q "eyJ"; then
  echo "✅ Login com senha já configurada!"
else
  # Primeira execução: força troca de senha
  echo "🔄 Primeira execução detectada. Trocando senha padrão..."
  curl -s -X POST "$API_URL/api/v1/user/forceChangePassword" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    --data-urlencode "username=$USERNAME" \
    --data-urlencode "password=$DEFAULT_PASSWORD" \
    --data-urlencode "newPassword=$NEW_PASSWORD" \
    --data-urlencode "confirmPassword=$NEW_PASSWORD" > /dev/null 2>&1

  TOKEN=$(curl -s -X POST "$API_URL/api/v1/user/login" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d "username=$USERNAME&password=$NEW_PASSWORD")

  if ! echo "$TOKEN" | grep -q "eyJ"; then
    echo "❌ Falha no login. Faça upload manualmente."
    echo "   Resposta: $TOKEN"
    exit 0
  fi
  echo "✅ Senha trocada e login realizado!"
fi

echo "📦 Criando projeto 'Demo Vulnerable App'..."
PROJECT=$(curl -s -X PUT "$API_URL/api/v1/project" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Demo Vulnerable App","version":"1.0.0","description":"App de demo com componentes vulneráveis (Log4Shell, Spring4Shell, etc.)"}')

PROJECT_UUID=$(echo "$PROJECT" | grep -o '"uuid":"[^"]*"' | head -1 | cut -d'"' -f4)

if [ -z "$PROJECT_UUID" ]; then
  echo "   Projeto já existe. Buscando UUID..."
  PROJECT_UUID=$(curl -s "$API_URL/api/v1/project/lookup?name=Demo+Vulnerable+App&version=1.0.0" \
    -H "Authorization: Bearer $TOKEN" | grep -o '"uuid":"[^"]*"' | head -1 | cut -d'"' -f4)
fi

if [ -z "$PROJECT_UUID" ]; then
  echo "❌ Não foi possível criar/encontrar o projeto."
  exit 1
fi
echo "   UUID: $PROJECT_UUID"

echo "📤 Fazendo upload do SBOM (15 componentes)..."
BOM_BASE64=$(base64 /sbom/bom.json | tr -d '\n')
RESULT=$(curl -s -X PUT "$API_URL/api/v1/bom" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d "{\"project\":\"$PROJECT_UUID\",\"bom\":\"$BOM_BASE64\"}")

echo "✅ SBOM enviado!"
echo ""
echo "=========================================="
echo "🎉 DEMO PRONTA!"
echo ""
echo "   Dashboard: http://localhost:8080"
echo "   Login:     admin / $NEW_PASSWORD"
echo ""
echo "   Projeto 'Demo Vulnerable App' criado"
echo "   15 componentes carregados"
echo "   Vulnerabilidades aparecem após NVD sync"
echo "=========================================="
