#!/bin/bash

# Diretório base
BASE_DIR=$(pwd)

# 1️⃣ Limpar submissions, mantendo apenas o .gitkeep
echo "🧹 Limpando submissions/ exceto .gitkeep..."
find "${BASE_DIR}/assignments" -type d -name submissions | while read submission_dir; do
  find "$submission_dir" -mindepth 1 -not -name ".gitkeep" -exec rm -rf {} +
done

# 2️⃣ Limpar arquivos do diretório readings/
echo "🧹 Limpando arquivos em readings/..."
find "${BASE_DIR}/readings" -type f -name "*.pdf" -exec rm -f {} \;

echo "🧹 Limpando 'challenges/' (mantendo .gitkeep)..."
find "${BASE_DIR}/challenges" -mindepth 1 -not -name ".gitkeep" -exec rm -rf {} +

echo "✅ Diretórios limpos com sucesso!"