#!/bin/bash
# ============================================================
# Script de preparação da Demo - OWASP Dependency-Track
# Execute ANTES do seminário para ter tudo pronto!
# ============================================================

set -e

echo "🚀 Preparando ambiente do Dependency-Track para demo..."
echo ""

# 1. Subir o ambiente
echo "📦 [1/4] Subindo containers Docker..."
docker compose up -d
echo "✅ Containers iniciados!"
echo ""

# 2. Aguardar API Server ficar pronto
echo "⏳ [2/4] Aguardando API Server inicializar (pode levar 2-3 min)..."
until curl -s http://localhost:8081/api/version > /dev/null 2>&1; do
    printf "."
    sleep 5
done
echo ""
echo "✅ API Server pronto!"
echo ""

# 3. Obter API Key padrão
echo "🔑 [3/4] Obtendo API Key..."
# Login para obter token (admin/admin)
TOKEN=$(curl -s -X POST http://localhost:8081/api/v1/user/login \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin&password=admin")

echo "Token JWT obtido!"
echo ""

# 4. Baixar SBOM de exemplo
echo "📥 [4/4] Baixando SBOM de exemplo (OWASP Juice Shop)..."
curl -sLO https://raw.githubusercontent.com/CycloneDX/bom-examples/master/SBOM/juice-shop-11.1.2/bom.json
echo "✅ SBOM baixado: bom.json"
echo ""

echo "============================================================"
echo "🎉 Ambiente pronto para a demo!"
echo ""
echo "📌 Acesse: http://localhost:8080"
echo "📌 Login:  admin / admin"
echo ""
echo "📌 Próximos passos manuais:"
echo "   1. Faça login no frontend"
echo "   2. Crie um projeto 'Juice Shop' versão '11.1.2'"
echo "   3. Faça upload do arquivo bom.json"
echo "   4. Aguarde a análise de vulnerabilidades (~1 min)"
echo "============================================================"
