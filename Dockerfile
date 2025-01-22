# Imagen base
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado desde el directorio target
COPY target/test-0.0.1-SNAPSHOT.jar /app/test-0.0.1-SNAPSHOT.jar

# Exponer el puerto
EXPOSE 8080

# Comando de inicio para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "test-0.0.1-SNAPSHOT.jar"]
