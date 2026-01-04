Petshop API

Petshop API is a RESTful API built with Java 21 and Spring Boot to manage a pet shop domain, including products, orders, order items, and product ratings.

The project follows clean architecture principles, using DTOs, mappers, services, and repositories to ensure maintainability, scalability, and clear separation of concerns.


Project Overview

The Petshop API simulates a real backend system for a pet shop, providing endpoints to:

Manage products and their categories

Create and track orders

Handle order items using a many-to-many relationship

Register and retrieve product ratings

Apply enums for strong domain modeling

Seed database data for development and testing

This project was built with a strong focus on backend best practices, REST standards, and clean architecture.


Architecture

The application follows a layered architecture:

com.lucas.petshop
├── controller   → REST endpoints
├── dto          → API request/response models
├── mapper       → DTO ↔ Entity conversions
├── model        → JPA entities & composite keys
├── repository   → Database access (JPA)
├── service      → Business logic
├── util         → Utility classes


Layer Responsibilities
Layer	Responsibility
Controller	Handle HTTP requests and responses
Service	Business rules and orchestration
Repository	Persistence using Spring Data JPA
Model	Domain entities and relationships
DTO	External API contracts
Mapper	Convert between DTOs and entities

Technologies

Java 21

Spring Boot

Spring Web (REST)

Spring Data JPA / Hibernate

PostgreSQL

Maven

YAML configuration

JSON database seed files

Domain Model
Main Entities

Product

Order

ProductOrder (join table)

Rating

Relationships

An Order contains multiple Products

A Product can belong to multiple Orders

The many-to-many relationship is handled by ProductOrder using a composite key

A Product can have multiple Ratings

Enums (Strong Domain Modeling)

The project uses enums to avoid magic strings and enforce consistency:

OrderStatusEnum

ProductTypeEnum

ProductAnimalTypeEnum

RatingStarsEnum

API Endpoints
Products
Method	Endpoint	Description
GET	/products	List all products
GET	/products/{id}	Get product by ID
POST	/products	Create a product
PUT	/products/{id}	Update a product
DELETE	/products/{id}	Delete a product
Orders
Method	Endpoint	Description
GET	/orders	List all orders
GET	/orders/{id}	Get order by ID
POST	/orders	Create a new order
Ratings
Method	Endpoint	Description
GET	/ratings	List ratings
POST	/ratings	Create a rating
DTO and Mapper Strategy

The API never exposes entities directly.

Controllers receive Request DTOs

Services return Response DTOs

Mappers handle conversions:

ProductMapper

OrderMapper

RatingMapper

This approach ensures:

Encapsulation

API stability

Easier future changes

Database
SQL Schema

The database structure is defined in:

src/main/resources/db/sql/create_tables.sql

Seed Data

Preloaded JSON data for development and testing:

src/main/resources/db/seed/
├── products.json
├── order.json
├── product_order.json
└── ratings.json

Configuration

Main configuration file:

src/main/resources/application.yaml


Example configuration:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/petshop
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

Running the Application
Clone the repository
git clone https://github.com/buffo2fs/petshop-api.git
cd petshop-api

Run with Maven
./mvnw spring-boot:run

Or build the JAR
./mvnw clean package
java -jar target/petshop-api-*.jar


The application will be available at:

http://localhost:8080

Testing
./mvnw test

Future Improvements

Swagger / OpenAPI documentation

Authentication and authorization (JWT)

Pagination and filtering

Docker and Docker Compose

Global exception handling

Integration tests

Contributing

Contributions are welcome.
Feel free to open issues or submit pull requests.

License

This project currently does not include a license.
Consider adding MIT or Apache 2.0 if you plan to keep it open source.
