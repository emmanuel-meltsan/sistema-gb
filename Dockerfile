FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar los archivos de proyecto al contenedor
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar biblioapi.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "biblioapi.jar"]
