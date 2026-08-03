#!/bin/bash

# Diretório base
BASE_DIR=$(pwd)

echo "Limpando submissions/ exceto .gitkeep..."
find "${BASE_DIR}" \
  -path "${BASE_DIR}/.git" -prune -o \
  -path "${BASE_DIR}/readings" -prune -o \
  -type d -name submissions -print -prune |
while IFS= read -r submission_dir; do
  find "$submission_dir" -mindepth 1 -not -name ".gitkeep" -exec rm -rf {} +
  touch "$submission_dir/.gitkeep"
done

echo "Limpando arquivos de alunos em readings/ exceto .gitkeep..."
find "${BASE_DIR}/readings" -mindepth 1 -maxdepth 1 -type d -print |
while IFS= read -r reading_dir; do
  find "$reading_dir" -mindepth 1 -not -name ".gitkeep" -exec rm -rf {} +
  touch "$reading_dir/.gitkeep"
done

echo "Diretórios limpos com sucesso!"
