# party-scape-BE

Backend de la aplicación PartyEscape desarrollado en Java 21 con Spring Boot 3.4.4 y conexión a base de datos MySQL

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.4
- MySQL
- JPA / Hibernate
- Maven
- Docker/Docker Compose

---

## Cómo levantar el proyecto (modo Docker)

### 1. Requisitos previos

Tener instalado Docker Desktop
Tener acceso a terminal (PowerShell, CMD, bash, etc.)

### 2. Clonar el repositorio y posicionarse en el backend

```bash
git clone https://github.com/MartaBernardoZamora/party-scape-BE.git
cd party-scape-BE
```

### 3. Levantar backend y base de datos

`docker compose up --build`

Esto construirá el contenedor y arrancará tanto la base de datos como el backend en red interna.El backend estará disponible en: http://localhost:8080

## Variables de entorno utilizadas

Este proyecto utiliza variables de entorno definidas en un archivo .env que no está incluido en el repositorio por seguridad.

Puedes crear uno fácilmente copiando el archivo .env.example incluido:

```bash
cp .env.example .env
```

Luego modifica los valores si es necesario antes de ejecutar el contenedor.

| Variable                     | Uso                                           |
| ---------------------------- | --------------------------------------------- |
| `SPRING_PROFILES_ACTIVE`     | Activa el perfil de Spring (por defecto: dev) |
| `MYSQL_ROOT_PASSWORD`        | Contraseña del usuario root de MySQL          |
| `MYSQL_DATABASE`             | Nombre de la base de datos                    |
| `MYSQL_USER`                 | Usuario que usará la app                      |
| `MYSQL_PASSWORD`             | Contraseña de ese usuario                     |
| `SPRING_DATASOURCE_URL`      | Cadena de conexión JDBC desde el backend      |
| `SPRING_DATASOURCE_USERNAME` | Usuario usado por Spring Boot para conectarse |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña correspondiente                    |

En producción (por ejemplo en Render), estas variables deben definirse desde el panel de configuración del entorno y no mediante un archivo .env.

---

## 📄 Configuración de `application.properties` y perfil `dev`

En `src/main/resources/application.properties`:

```properties
spring.application.name=party_escape
spring.profiles.active=dev
```

En `application-dev.properties` (perfil activo):

```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/party_escape
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.sql.init.mode=always
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```
---

## Autora

[Marta Bernardo: Github](https://github.com/MartaBernardoZamora)

---
