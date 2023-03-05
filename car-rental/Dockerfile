## Imagem base do Java
FROM openjdk:11-jdk-slim
#
## Diretório de trabalho da aplicação
WORKDIR /app
#
## Copia os arquivos do projeto para dentro do container
COPY target/car-rental-0.0.1-SNAPSHOT.jar app.jar
#
## Compila a aplicação
#
## Expose da porta 8080
EXPOSE 8080
#
## Comando que é executado quando o container é iniciado
CMD ["java", "-jar", "app.jar"]



