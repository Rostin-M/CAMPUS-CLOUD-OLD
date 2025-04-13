# CampusCloud

CampusCloud es una aplicación web diseñada para optimizar y modernizar los sistemas académicos actuales.  
Con un enfoque en la innovación tecnológica, busca simplificar la gestión de cursos y usuarios, ofreciendo soluciones eficientes para los desafíos educativos del presente.

---

## Tecnologías utilizadas

- **Java**: 21 (JDK)
- **Spring Boot**: 3.4.4
- **Thymeleaf**: Motor de plantillas para la interfaz de usuario.
- **MySQL**: 8.0 (Base de datos relacional)
- **Docker**: Para la contenedorización de la aplicación.
- **Docker Compose**: Para la orquestación de servicios.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.

---

## Requisitos previos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- [Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## Cómo clonar el repositorio

1. Clona este repositorio en tu máquina local:

    ```sh
    git clone https://github.com/Rostin-M/CAMPUS-CLOUD.git
    cd CampusCloud
    ```

2. Construye el proyecto con Maven:

    ```sh
    mvn clean package
    ```

---

## Opciones de ejecución

### Opción 1: Ejecutar la aplicación usando Docker

1. Construye la imagen de Docker:

    ```sh
    docker build -t campuscloud:latest .
    ```

2. Ejecuta los servicios con Docker Compose:

    ```sh
    docker-compose up
    ```

3. Accede a la aplicación en tu navegador:  
    [http://localhost:8080](http://localhost:8080)

> **Nota:** También puedes usar la imagen preconstruida disponible en Docker Hub:  
> [CampusCloud en Docker Hub](https://hub.docker.com/r/rostinm/campuscloud)

---

### Opción 2: Ejecutar localmente

1. Configura la base de datos MySQL:
    - Crea una base de datos llamada `campus_cloud`.
    - Configura las credenciales en el archivo `src/main/resources/application.properties` si es necesario.

2. Ejecuta la aplicación:

    ```sh
    mvn spring-boot:run
    ```

3. Accede a la aplicación en tu navegador:  
    [http://localhost:8080](http://localhost:8080)

---

## Notas adicionales

- La aplicación incluye un sistema de autenticación con roles (`ROLE_ESTUDIANTE`, `ROLE_DOCENTE`).
- Los estilos y scripts están ubicados en `src/main/resources/static/`.
- Las plantillas HTML están en `src/main/resources/templates/`.
- La API de Swagger está disponible y escuchando en:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

> **Nota técnica:**  
> La configuración de la base de datos para pruebas (usando MySQL Workbench) está pendiente de revisión y ajustes.

---

## Licencia

Este proyecto está protegido por derechos de autor. Todos los derechos reservados.  
No se permite la reproducción, distribución o modificación de este software sin el permiso explícito del autor.  
Para más información, por favor contacta al propietario del repositorio.

---
