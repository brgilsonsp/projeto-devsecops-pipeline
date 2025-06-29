FROM openjdk:21-jdk AS build

WORKDIR /app

# Copia o projeto (ajuste conforme sua estrutura)
COPY ./app .

RUN chmod +x mvnw

# Usa o Maven Wrapper para construir o jar
RUN ./mvnw clean package -DskipTests

# Imagem final para rodar o app
FROM openjdk:21-jdk

WORKDIR /app

# Copia o jar gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]