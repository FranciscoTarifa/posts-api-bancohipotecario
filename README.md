# Posts API - Banco Hipotecario 🏠

API REST desarrollada como parte de la prueba técnica para Banco Hipotecario. La aplicación consume y procesa datos de servicios externos, específicamente la API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/), para obtener posts, comentarios y usuarios, y los combina en una única respuesta.

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Quarkus 3.28.1** (Supersonic Subatomic Java Framework)
- **Maven 3.8+**
- **MicroProfile REST Client** (para consumir servicios externos)
- **Jackson** (para serialización/deserialización JSON)
- **SmallRye OpenAPI** (documentación de API con Swagger UI)
- **SmallRye Health** (verificación de estado de la aplicación)
- **Hibernate Validator** (validación de datos)
- **Quarkus Cache** (opcional, para optimización de llamadas repetidas)
- **JUnit 5** (tests unitarios)
- **JSONPlaceholder API** (servicio externo de prueba)

## 📋 Requisitos previos

- Java 21
- Maven 3.8+
- Git
- (Opcional) Docker (para build nativo)

## 🔧 Instalación

1. Clonar el repositorio:
   ```bash
   git clone <url-del-repositorio>
   cd posts-api
