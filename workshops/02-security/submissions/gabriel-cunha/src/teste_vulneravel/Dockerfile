# Dockerfile
# Imagem base intencionalmente vulnerável: Ubuntu 18.04 LTS (Bionic Beaver)
# Esta imagem contém pacotes de SO com vulnerabilidades conhecidas.
FROM ubuntu:18.04

# Instala o OpenJDK 8 JRE para executar nossa aplicação Java
# Usamos 'openjdk-8-jre-headless' para uma instalação mais leve.
RUN apt-get update && \
    apt-get install -y openjdk-8-jre-headless curl  && \
    rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR compilado da sua aplicação para o contêiner
# O nome do JAR é o padrão gerado pelo Maven para a versão 1.0-SNAPSHOT.
COPY target/my-vulnerable-app-1.0-SNAPSHOT.jar app.jar

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
