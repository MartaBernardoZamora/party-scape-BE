# party-scape-BE

Backend de la aplicación PartyEscape desarrollado en Java 21 con Spring Boot 3.4.4 y conexión a base de datos MySQL

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.4
- PostgreSQL
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

Esto construirá el contenedor y arrancará tanto la base de datos como el backend en red interna.El backend estará disponible en: <http://localhost:8080>

## Variables de entorno utilizadas

Este proyecto utiliza variables de entorno definidas en un archivo .env que no está incluido en el repositorio por seguridad.

Puedes crear uno fácilmente copiando el archivo .env.example incluido:

```bash
cp .env.example .env
```

Luego modifica los valores si es necesario antes de ejecutar el contenedor.

| Variable                     | Descripción                                  |
| ---------------------------- | -------------------------------------------- |
| `SPRING_DATASOURCE_URL`      | Cadena JDBC para conectarse a PostgreSQL     |
| `SPRING_DATASOURCE_USERNAME` | Usuario de conexión a la base de datos       |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña del usuario anterior              |
| `POSTGRES_DB`                | Nombre de la base de datos de PostgreSQL     |
| `SPRING_PROFILES_ACTIVE`     | Perfil de Spring activo (por defecto: `dev`) |

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
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=create
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.sql.init.mode=always
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## Autora

[Marta Bernardo: Github](https://github.com/MartaBernardoZamora)

---
