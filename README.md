# Posts API - Banco Hipotecario Argentina 🏦

API REST desarrollada como parte de la prueba técnica para **Banco Hipotecario de Argentina**.  
La aplicación consume y procesa datos de la API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/) para obtener **posts**, **comentarios** y **usuarios**, combinando la información en una única respuesta.

---

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Quarkus 3.28.1**
- **Maven 3.8+**
- **MicroProfile REST Client** (consumo de servicios externos)
- **Jackson** (serialización JSON)
- **SmallRye OpenAPI** (documentación con Swagger UI)
- **SmallRye Health** (verificación de estado)
- **Hibernate Validator** (validaciones)
- **Quarkus Cache** (optimización de llamadas repetidas)
- **JUnit 5 + Mockito** (tests unitarios)

---

## 📋 Requisitos previos

- Java 21
- Maven 3.8+
- Git

---

## 🔧 Instrucciones de compilación y ejecución

### Compilar
\`\`\`bash
./mvnw clean compile
\`\`\`

### Ejecutar en modo desarrollo (hot reload)
\`\`\`bash
./mvnw quarkus:dev
\`\`\`

### Ejecutar en modo producción
\`\`\`bash
./mvnw clean package
java -jar target/quarkus-app/quarkus-run.jar
\`\`\`

La aplicación quedará disponible en:  
👉 http://localhost:8080

---

## 🌐 Endpoints

### 1. GET \`/posts\` (Endpoint Principal)
Obtiene posts de la API externa, sus comentarios y la información del autor, mergeando todo en una única respuesta.

Ejemplo de respuesta:
\`\`\`json
[
  {
    "post": {
      "id": 1,
      "userId": 1,
      "title": "Post title",
      "body": "Post body"
    },
    "user": {
      "id": 1,
      "name": "User Name",
      "email": "user@example.com"
    },
    "comments": [
      {
        "id": 1,
        "postId": 1,
        "name": "Comment Name",
        "email": "comment@example.com",
        "body": "Comment body"
      }
    ]
  }
]
\`\`\`

### 2. DELETE \`/posts/{id}\` (Endpoint Secundario)
Simula la eliminación de un post en la API externa.  
⚠️ **Nota:** JSONPlaceholder no persiste cambios, solo devuelve una respuesta simulada.

---

## 🏗️ Arquitectura

- **Capa de clientes REST**  
  - PostExternalClient: consume \`/posts\`  
  - CommentExternalClient: consume \`/posts/{id}/comments\`  
  - UserExternalClient: consume \`/users/{id}\`  

- **DTOs externos**  
  - ExternalPost, ExternalComment, ExternalUser  

- **DTO interno**  
  - PostWithDetails: combina post, usuario y comentarios  

- **Servicio**  
  - PostService / PostServiceImpl: lógica principal con llamadas concurrentes  

- **Controlador**  
  - PostController: define los endpoints REST  

---

## ⚡ Decisiones técnicas

1. **Llamadas concurrentes con CompletableFuture**  
   → Reduce el tiempo de respuesta al realizar múltiples requests en paralelo.  

2. **Arquitectura por capas**  
   → Favorece el testing y la escalabilidad.  

3. **OpenAPI/Swagger**  
   → Documentación y prueba interactiva de endpoints.  

4. **Cache**  
   → Optimización de llamadas repetidas a servicios externos.  

5. **Manejo de errores y logging**  
   → Logs estructurados + respuestas HTTP claras.  

---

## 🧪 Tests

- Unitarios para GET /posts  
- Integración con servicios externos mockeados  
- Manejo de errores y casos límite  

Ejecutar:
\`\`\`bash
./mvnw test
./mvnw verify
\`\`\`

---

## 📊 Documentación y monitoreo

- **Swagger UI** → http://localhost:8080/q/swagger-ui  
- **OpenAPI JSON** → http://localhost:8080/q/openapi  
- **Health check** → http://localhost:8080/q/health  

---

✅ Cumple con los requisitos de la prueba técnica:  
- API REST funcional (GET y DELETE)  
- Manejo de errores y logging  
- Configuración externalizada  
- Tests unitarios básicos  
- Documentación con Swagger/OpenAPI  
- Llamadas concurrentes + cache para optimización  
EOF
