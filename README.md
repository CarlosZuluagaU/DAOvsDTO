API REST de Gestión de Usuarios con Spring Boot y OpenAPI
Este proyecto es una API RESTful desarrollada con Spring Boot para la gestión completa (CRUD) de usuarios. Ha sido diseñada siguiendo las mejores prácticas de desarrollo de software, incluyendo una clara separación de responsabilidades, el uso del patrón DTO y una documentación de API interactiva y autogenerada con OpenAPI 3 (Swagger).

Este repositorio sirve como una demostración de habilidades clave en el desarrollo backend con Java y el ecosistema de Spring, ideal para un portafolio técnico.

✨ Conceptos y Habilidades Demostradas
Desarrollo de API RESTful: Implementación de un conjunto completo de endpoints para operaciones CRUD (POST, GET, PUT, DELETE).

Spring Boot: Uso del framework para acelerar el desarrollo, gestionar dependencias y configurar la aplicación de forma eficiente.

Patrón DTO (Data Transfer Object): Separación de la representación de datos interna (Entidad JPA) de la externa (API), mejorando la seguridad y la flexibilidad.

Documentación con OpenAPI 3: Integración con springdoc-openapi para generar automáticamente una documentación de API interactiva.

Anotaciones Descriptivas: Uso de @Tag, @Operation, @ApiResponse, y @Parameter para enriquecer la documentación y hacerla más clara y útil.

Manejo de Respuestas HTTP: Uso correcto de los códigos de estado HTTP (200 OK, 201 Created, 204 No Content, 404 Not Found, etc.) para comunicar los resultados de las operaciones de la API.

Principios de Código Limpio: Código estructurado, legible y mantenible.

📖 Documentación Interactiva (Swagger UI)
Una de las características principales de este proyecto es su documentación autogenerada. Una vez que la aplicación está en ejecución, puedes acceder a una interfaz de usuario web interactiva para explorar y probar cada endpoint de la API directamente desde tu navegador.

URL de Acceso: http://localhost:8080/swagger-ui.html

Esto elimina la necesidad de herramientas de cliente API externas como Postman para la exploración y prueba básicas.

[AQUÍ PUEDES PONER UNA CAPTURA DE PANTALLA DE TU SWAGGER-UI. ES MUY RECOMENDABLE]

🛠️ Tecnologías Utilizadas
Java 17+

Spring Boot 3+

Spring Web

Maven / Gradle

springdoc-openapi-starter-webmvc-ui (Swagger UI)

H2 Database (Base de datos en memoria para demostración)

(Opcional) Spring Data JPA

🚀 Cómo Empezar
Sigue estos pasos para clonar y ejecutar el proyecto localmente.

Prerrequisitos
Tener instalado JDK 17 o una versión superior.

Tener instalado Apache Maven o Gradle.

Instalación y Ejecución
Clona el repositorio:

Bash

git clone [URL-DE-TU-REPOSITORIO-EN-GITHUB]
cd [NOMBRE-DEL-PROYECTO]
Ejecuta la aplicación usando Maven:

Bash

./mvnw spring-boot:run
O si usas Gradle:

Bash

./gradlew bootRun
La aplicación estará disponible en http://localhost:8080.

endpoints Resumen de Endpoints de la API
Método HTTP	Endpoint	Descripción
GET	/api/users	Obtiene la lista de todos los usuarios.
GET	/api/users/{id}	Obtiene un usuario específico por su ID.
POST	/api/users	Crea un nuevo usuario.
PUT	/api/users/{id}	Actualiza un usuario existente por su ID.
DELETE	/api/users/{id}	Elimina un usuario por su ID.

Exportar a Hojas de cálculo
🌱 Futuras Mejoras
Este proyecto sienta una base sólida. Los siguientes pasos para convertirlo en una aplicación de producción completa incluirían:

✅ Validación de Datos (Bean Validation): Implementar validaciones en el DTO para garantizar la integridad de los datos de entrada.

🛡️ Seguridad (Spring Security): Proteger los endpoints de la API utilizando autenticación y autorización, por ejemplo, con Tokens JWT.

🚨 Manejo Global de Excepciones (@ControllerAdvice): Centralizar el manejo de errores para evitar código repetitivo y estandarizar las respuestas de error.

🧪 Pruebas (JUnit y Mockito): Añadir pruebas unitarias y de integración para garantizar la fiabilidad y facilitar el mantenimiento.

🔄 Mapeo de Objetos (MapStruct): Automatizar la conversión entre entidades JPA y DTOs para reducir el código boilerplate.

🐳 Contenerización (Docker): Crear una imagen Docker de la aplicación para facilitar su despliegue en cualquier entorno.

Desarrollado por Carlos Zuluaga 
