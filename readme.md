# Petshop API

## Overview

Petshop API is a RESTful backend application built with **Java 21** and **Spring Boot**. The project models a pet shop domain and was designed to demonstrate backend development skills, clean architecture principles, and industry-standard practices.

This repository is intended as a **backend portfolio project**, with emphasis on code organization, domain modeling, and RESTful API design.

---

## Project Goals

The main goals of this project are to demonstrate:

- Clear separation of concerns using a layered architecture
- RESTful API design following HTTP standards
- Strong domain modeling using enums
- DTO-based communication to protect domain entities
- Handling of complex JPA relationships (many-to-many)
- Clean, readable, and maintainable code structure

---

## Architecture

The application follows a layered architecture with explicit responsibilities per layer.

```
com.lucas.petshop
├── controller    // REST endpoints
├── dto           // Request and response models
├── exception     // Custom exceptions
├── mapper        // DTO ↔ Entity mapping (MapStruct)
├── model         // JPA entities and domain enums
├── repository    // Persistence layer (Spring Data JPA)
├── service       // Business logic (interfaces and implementations)
├── util          // Utility classes
└── PetshopApplication
```

### Layer Responsibilities

| Layer | Responsibility |
|------|---------------|
| Controller | Handle HTTP requests and responses |
| Service | Business rules and orchestration |
| Repository | Data access using Spring Data JPA |
| DTO | External API contracts |
| Mapper | Conversion between DTOs and entities |
| Model | Domain entities, relationships, and enums |

---

## Technology Stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- MapStruct
- Lombok

---

## Domain Model

### Main Entities

- **Product**
- **Order**
- **Rating**
- **ProductOrder** (join entity)

### Relationships

- An **Order** can contain multiple **Products**
- A **Product** can belong to multiple **Orders**
- The many-to-many relationship is handled through a join entity with a composite key
- A **Product** can have multiple **Ratings**

---

## Domain Enums

Enums are used to enforce strong domain modeling and avoid magic strings:

- `OrderStatusEnum`
- `ProductTypeEnum`
- `ProductAnimalTypeEnum`
- `RatingStarsEnum`

---

## API Features

- Product management (CRUD)
- Order creation and retrieval
- Many-to-many product–order association
- Product ratings
- DTO-based request and response handling

---

## API Endpoints

### Products

- GET `/products`
- GET `/products/{id}`
- POST `/products`
- PUT `/products/{id}`
- DELETE `/products/{id}`

### Orders

- GET `/orders`
- GET `/orders/{id}`
- POST `/orders`

### Ratings

- GET `/ratings`
- POST `/ratings`

---

## Configuration

Application configuration is defined in `application.yaml`, including database connection and JPA settings.

PostgreSQL is used as the relational database.

---

## Running the Application

Clone the repository:

```bash
git clone https://github.com/buffo2fs/petshop-api.git
cd petshop-api
```

Run the application:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```
http://localhost:8080
```

---

## Testing

Run automated tests using:

```bash
./mvnw test
```

---

## Notes for Recruiters

This project focuses exclusively on backend development topics such as API design, architecture, domain modeling, and code quality.

There is no frontend layer by design.

---

## Future Improvements

- OpenAPI / Swagger documentation
- Authentication and authorization
- Pagination and filtering
- Docker support
- Global exception handling
- Integration tests

