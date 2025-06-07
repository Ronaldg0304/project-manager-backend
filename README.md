# Project Manager API – Backend

API RESTful desarrollada con Spring Boot para la gestión de proyectos en compañías o empresas, mediante seguimiento de tareas, reportes y usuarios. Esta API forma parte de una aplicación fullstack, y puede ser consumida desde un frontend construido en React + TypeScript.

## 📦 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- SQL Server
- JWT (autenticación)
- OpenAI API (opcional)

Cómo ejecutar localmente
------------------------

1. Clona el repositorio:

   git clone https://github.com/Ronaldg0304/project-manager-backend.git
   cd project-manager-backend

2. Configura las variables de entorno:

Crea un archivo `appication.properties` o define las variables temporalmente en la terminal:

    DB_URL=jdbc:sqlserver://localhost:1433;databaseName=mi_basededatos
    DB_USER=admin
    DB_PASSWORD=1234
    TOKEN_JWT=secret_key
    KEY_OPENAI=key_api_openai (optional)

También puedes guiarte por el archivo `application-example.properties`.

3. Ejecuta el proyecto:

   ./mvnw spring-boot:run

O si tienes el `.jar` generado:

    java -jar target/project-manager-0.0.1-SNAPSHOT.jar

Estructura del proyecto
-----------------------
src/
└── main/
├── src/main/java/com/projects/projectmanager/project_manager
└── resources/

Seguridad
---------
- Uso de JWT para autenticación de usuarios
- Validación de datos de entrada
- Manejo global de excepciones

Variables de entorno necesarias
-------------------------------
Incluidas en `.application-example.properties`:
    DB_URL=
    DB_USER=
    DB_PASSWORD=
    TOKEN_JWT=
    KEY_OPENAI=

Autor
-----
Ronald Guerra Álvarez
LinkedIn: https://www.linkedin.com/in/ronaldguerra
GitHub: https://github.com/Ronaldg0304
