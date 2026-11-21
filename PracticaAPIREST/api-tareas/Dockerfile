
# Etapa 1: construir la aplicacion
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar el proyecto
COPY . .

# Compilar y generar el JAR
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar la aplicacion
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/api-tareas-0.0.1-SNAPSHOT.jar app.jar

# Puerto de la API
EXPOSE 8080

# Iniciar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
