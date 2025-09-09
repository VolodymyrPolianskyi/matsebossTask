# Notes Service

Simple REST service for managing notes and authors built with Spring Boot, Spring Data JPA and H2.

## Requirements
- Java 17+
- Maven

## Build & Run
1. Build:
```
mvn clean package
```

2. Run:
```
mvn spring-boot:run
```
or run the generated jar:
```
java -jar target/notes-service-0.0.1-SNAPSHOT.jar
```

## API Endpoints

### Authors
- `POST /authors` - create author. Body: `{ "name": "Author Name" }`
- `GET /authors` - list all authors
- `GET /authors/<built-in function id>` - get author by id

### Notes
- `POST /notes` - create note. Body: `{ "title":"...", "content":"...", "authorId": 1 }`
- `GET /notes` - list notes
- `GET /notes/<built-in function id>` - get note by id
- `DELETE /notes/<built-in function id>` - delete note

## H2 Console
Accessible at: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:notesdb`)

## Swagger / OpenAPI
After running the app, Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

## Notes
- `createdAt` for Note is automatically set on creation.
- Validation and error handling are provided via a global exception handler.

## Next steps (optional)
- Add DTO responses instead of exposing entities directly.
- Add pagination for listing endpoints.
- Add tests.

