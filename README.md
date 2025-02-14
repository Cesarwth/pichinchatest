# Proyecto Java, Cesar Tenemaza, Test Banco Pichincha

Este proyecto es una aplicación simple de Spring Boot que utiliza H2 como base de datos en memoria. Está configurado para ser ejecutado fácilmente utilizando Docker:

### Requisitos

* Docker (para ejecutar los contenedores)
* Docker Compose (para orquestar los contenedores)
* Java 17 (para compilar y ejecutar la aplicación localmente si no usas Docker)

### Pasos para levantar el proyecto

### 1. Clona el repositorio en local

git clone https://github.com/Cesarwth/pichinchatest.git

### 2. Construye y levanta la aplicación con Docker

docker build -t test-app .

Esto hará lo siguiente:

Construirá la imagen del contenedor para la aplicación.
Levantará el contenedor de la aplicación y lo expondrá en el puerto 8080.

Para levantar la app en el puerto 8080 de localhost:
docker run -p 8080:8080 test-app
### 3. Accede a la aplicación
Una vez que el contenedor esté corriendo, puedes acceder a la aplicación en tu navegador en la siguiente dirección:

http://localhost:8080

### 4. Detener la aplicación
docker-compose down
