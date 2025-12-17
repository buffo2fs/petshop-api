# petshop-api

A small Spring Boot REST service for managing products, orders and product-order entries (a pet shop demo).

This repository contains a Maven-based Spring Boot application written for Java 21.

Table of contents
Project
Prerequisites
Configuration
Run (local)
Run with Docker Compose (recommended for dev)
Build
API - Quick reference
Testing
Development notes & tips
Troubleshooting
Contributing
License
Project
Framework: Spring Boot (WebMVC, Data JPA, Validation)
Java: 21
Build tool: Maven (wrapper included)
Runtime DB (default): PostgreSQL (configured in src/main/resources/application.yaml)
This project exposes REST endpoints for managing Products, Orders and ProductOrder (product lines within an order). DTOs are used for API boundaries and entities are JPA-mapped.

Prerequisites
JDK 21 installed and java available on PATH
Git (optional)
Docker & Docker Compose (optional, recommended for local dev)
If you prefer to run PostgreSQL manually, install Postgres and create a database matching the configuration in application.yaml (see next section).

Configuration
The default runtime configuration is in src/main/resources/application.yaml. Important settings:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/petshop
    username: petshop
    password: 12345
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect
server:
  port: 8080
  servlet:
    context-path: /petshop
spring.jpa.hibernate.ddl-auto is set to none by default so the schema won't be auto-created. For local development you can set it to update or create — but avoid this in production.
The server context path is /petshop so endpoints are prefixed with /petshop.
Run (local)
Start Postgres (see Docker Compose below) or ensure your DB is running and credentials in application.yaml are correct.

From the project root (Windows PowerShell):

# Run with Spring Boot via Maven (development)
.\mvnw.cmd spring-boot:run

# Or build and run the packaged jar
.\mvnw.cmd -DskipTests package
java -jar target\petshop-0.0.1-SNAPSHOT.jar
The server will be reachable at: http://localhost:8080/petshop

Run with Docker Compose (recommended for dev)
Create docker-compose.yml in the repo (example below) and run:

docker-compose up -d
Example docker-compose.yml (Postgres service only):

version: '3.8'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: petshop
      POSTGRES_USER: petshop
      POSTGRES_PASSWORD: 12345
    ports:
      - '5432:5432'
    volumes:
      - db-data:/var/lib/postgresql/data

volumes:
  db-data:
After the DB is ready, run the application (see "Run (local)").

Build
From the project root (Windows PowerShell):

# Build (skip tests to speed up)
.\mvnw.cmd -DskipTests package

# Run tests
.\mvnw.cmd test
The Maven wrapper is included so Maven need not be preinstalled.

API - Quick reference
All endpoints are prefixed with the server context path /petshop (see application.yaml).

Products

GET /petshop/products — list products
GET /petshop/products/{id} — get product by id
POST /petshop/products — create product
PUT /petshop/products/{id} — update product (full)
PATCH /petshop/products/{id} — partial update
DELETE /petshop/products/{id} — delete (soft)
Orders

GET /petshop/orders — list orders
GET /petshop/orders/{id} — get order by id
POST /petshop/orders — create order
PUT /petshop/orders/{id} — update order
DELETE /petshop/orders/{id} — delete (soft)
ProductOrder (product lines inside orders)

GET /petshop/product-order — list product-order entries
GET /petshop/product-order/{id} — get entry
POST /petshop/product-order — create product-order entry
PUT /petshop/product-order/{id} — update
DELETE /petshop/product-order/{id} — delete (soft)
Request/response DTOs are in src/main/java/com/lucas/petshop/dto — check those classes for payload shapes and validation constraints.

Example: create product (curl)
Example payload (JSON):

{
  "name": "Dog Food",
  "type": "FOOD",
  "animalType": "DOG",
  "brand": "Acme",
  "description": "Complete adult dog food",
  "stock": 100,
  "price": 29.99,
  "sizeWeight": 10.0
}
PowerShell curl (Invoke-WebRequest) example (or use Postman):

curl -X POST "http://localhost:8080/petshop/products" -H "Content-Type: application/json" -d @product.json
Testing
Run unit tests:

.\mvnw.cmd test
If you add integration tests that require DB access, either point them at a test database or use a testcontainer/in-memory DB profile.

Development notes & tips
Lombok is used for entity boilerplate (@Data). Enable Lombok support in your IDE to avoid editor warnings.
Prefer constructor injection over field injection for easier unit testing and better immutability.
Replace deprecated getById(id) calls with findById(id).orElseThrow(...) for explicit not-found handling.
Add database migrations (Flyway or Liquibase) for production schema changes instead of ddl-auto in production.
Troubleshooting
Build fails with Lombok errors: ensure lombok dependency and annotation processor are configured in your IDE and pom.xml.
App can't connect to DB: verify Postgres is running, credentials match application.yaml, and port 5432 is reachable.
Deprecation warnings for getById: update code to use findById with Optional.
Contributing
Fork the repository
Create a branch for your change
Open a Pull Request with a clear description
Next improvements (optional)
Add docker-compose.yml to repo for easier developer setup (I included an example above)
Add GitHub Actions to build and run tests on PRs
Add API docs (OpenAPI/Swagger) and example Postman collection
License
No license file included. Add a LICENSE if you want to publish this code under a permissive or specific license.
