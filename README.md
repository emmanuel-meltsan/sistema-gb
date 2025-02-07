# Reto Backend: Sistema de Gestión de Biblioteca 📚

## 🎯 Objetivo

Desarrollar un microservicio para gestionar el catálogo de libros de una biblioteca, implementando operaciones CRUD básicas y siguiendo las mejores prácticas de desarrollo.

## 🛠️ Tecnologías

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0.28-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.9-red)
![Docker](https://img.shields.io/badge/Docker-Compose%20%26%20Dockerfile-blue)

## 📋 Pre-requisitos

1. **Java 21**: [Descargar e instalar Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
2. **MySQL** : [Descargar e instalar MySQL Server](https://dev.mysql.com/downloads/mysql/)
3. **Maven**: [Descargar e instalar Maven](https://maven.apache.org/download.cgi)
4. **Docker**: [Descargar e instalar Docker](https://www.docker.com/get-started)
5. **Postman**: [Descargar e instalar Postman](https://www.postman.com/downloads/)

## 🚀 Instrucciones de Instalación y Uso

🔗 **Navega rápidamente a la sección que necesites:**
- [Instalación por medio de Docker Compose](#1-instalación-por-medio-de-docker-compose)
- [Instalación y uso con un IDE local](#2-instalación-y-uso-con-un-ide)

### 1. Instalación por medio de Docker Compose

#### 1.1. Clonar el repositorio y cambiar a la ruta del proyecto
```
git clone https://github.com/emmanuel-meltsan/sistema-gb.git
cd sistema-gb
```
#### 1.2. Ejecutar Docker Compose
```
docker-compose -p <nombre_proyecto> up --build
```
#### 1.3. Visualizar los endpoints

- **Base de datos (phpMyAdmin):**
```
  http://localhost:8081
```

- **API Backend:**
```
  http://localhost:8080/api/books
```
- **Swagger:**
```
  http://localhost:8080/swagger-ui.html
```
- **Postman:** [Link para descargar colección de Postman](https://drive.google.com/file/d/1W06G7MgebZN3cATpImBj7DpCqYC3YK7I/view?usp=sharing)

### 2. Instalación y uso con un IDE

#### 2.1. Clonar el repositorio y cambiar a la ruta del proyecto
```
git clone https://github.com/emmanuel-meltsan/sistema-gb.git
cd sistema-gb
```
#### 2.2. Abrir tu IDE favorito

_Nota: No olvides checar la configuración del proyecto con Java 21._

#### 2.3. Crear la base de datos o ejecutar el archivo `init.sql`
CREATE DATABASE IF NOT EXISTS gbibliotecadb;

_Nota: Si necesitas cambiar la ruta o nombre de la base de datos, no olvides actualizarlo en el archivo `application.properties` de Spring._

#### 2.4. Limpiar y compilar el proyecto
```
mvn clean package
```
#### 2.5. Ejecutar el proyecto con tu IDE

#### 2.6. Dirigirse a Swagger para ver los endpoints
```
http://localhost:8080/swagger-ui.html
```
#### 2.7. O probar los endpoints con Postman
[Link para descargar colección de Postman](https://drive.google.com/file/d/1W06G7MgebZN3cATpImBj7DpCqYC3YK7I/view?usp=sharing)






