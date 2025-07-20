# Translation Management Service

Spring Boot 3.2-based REST API for managing translations with support for:
- JWT Authentication
- Multi-locale storage
- Tag-based searching
- JSON export endpoint
- H2 in-memory DB

## Login
POST `/auth/login`
- username: `admin`
- password: `password`

## Export Translations
GET `/api/v1/translations/export?locale=en`
- Requires `Authorization: Bearer <JWT_TOKEN>`

## Run
```bash
./mvnw spring-boot:run
