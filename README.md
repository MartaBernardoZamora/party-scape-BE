# party-scape-BE

Backend de la aplicación PartyEscape desarrollado en Java con Spring Boot.
---

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.4
- MySQL
- JPA / Hibernate
- Maven

---

## Variables de entorno necesarias

Consultar a la autora 
[Marta Bernardo: Github](https://github.com/MartaBernardoZamora)

---

## Configuración de `application.properties`

```properties
spring.datasource.url=${MYSQL_URL}
spring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

server.port=8080
```


---

## Autora


[Marta Bernardo: Github](https://github.com/MartaBernardoZamora)

---
