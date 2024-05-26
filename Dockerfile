#Distribuição do AlpineLinux que contem a JRE
FROM eclipse-temurin:21-alpine
#Criando pasta temporária
VOLUME /tmp
#Liberando a porta 8080 do conteiner
EXPOSE 8080
#Informando o arquivo .jar que será inserido dentro da imagem
ARG JAR_FILE=target/SmartLinker-1.0.jar
#Informando que o arquivo SmartLinker-1.0.jar no conteiner irá se chamar app.jar
ADD ${JAR_FILE} app.jar
#Comando de inicialização do conteiner
ENTRYPOINT ["java", "-jar", "/app.jar"]
#Autor da aplicação
LABEL authors="Lucas"