Petshop API

Petshop API is a RESTful backend application built with Java 21 and Spring Boot, designed to model a pet shop domain and demonstrate backend development best practices.

The project focuses on clean code organization, clear separation of concerns, and realistic domain modeling commonly found in production backend systems.

Purpose

This project was created as a backend portfolio application to showcase:

RESTful API design

Layered architecture

DTO-based communication

Domain modeling with enums

JPA relationships, including many-to-many associations

Clean and maintainable project structure

Architecture

The application follows a layered architecture with well-defined responsibilities.

com.lucas.petshop
├── controller   // REST endpoints
├── dto          // Request and response models
├── exception    // Custom exceptions
├── mapper       // DTO ↔ Entity mapping (MapStruct)
├── model        // JPA entities and domain enums
├── repository   // Persistence layer (Spring Data JPA)
├── service      // Business logic (interfaces and implementations)
├── util         // Utility classes
└── PetshopApplication

Layer Responsibilities

Controller: Handles HTTP requests and responses

Service: Contains business logic and orchestration

Repository: Database access using Spring Data JPA

DTO: Defines external API contracts

Mapper: Converts between DTOs and entities

Model: Domain entities, relationships, and enums

Technologies

Java 21

Spring Boot

Spring Web MVC

Spring Data JPA / Hibernate

PostgreSQL

Maven

MapStruct

Lombok

Domain Model

Main entities:

Product

Order

Rating

ProductOrder (join entity)

Relationships

An order can contain multiple products

A product can belong to multiple orders

The many-to-many relationship is handled via a join entity with a composite key

A product can have multiple ratings

Enums

Enums are used to ensure strong domain modeling and consistency:

OrderStatusEnum

ProductTypeEnum

ProductAnimalTypeEnum

RatingStarsEnum

API Features

Product management (CRUD)

Order creation and retrieval

Many-to-many order–product association

Product ratings

DTO-based request and response handling

API Endpoints
Products

GET /products

GET /products/{id}

POST /products

PUT /products/{id}

DELETE /products/{id}

Orders

GET /orders

GET /orders/{id}

POST /orders

Ratings

GET /ratings

POST /ratings

Configuration

Application configuration is defined in application.yaml, including database connection and JPA settings.

PostgreSQL is used as the relational database.

Running the Application
git clone https://github.com/buffo2fs/petshop-api.git
cd petshop-api
./mvnw spring-boot:run


The application runs on:

http://localhost:8080

Testing
./mvnw test
