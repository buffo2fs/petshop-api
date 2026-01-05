# 🐾 Petshop Product API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

A backend API built with **Java 21** and **Spring Boot** to manage a Petshop domain. This is a **monolithic application**, centralizing the management of products, orders, and ratings into a single deployable unit to ensure data consistency and simplified architectural overhead.

---

## 📌 Introduction

The **Petshop Product API** simulates a real-world backend system for a petshop application. Its main goal is to demonstrate backend development best practices, including RESTful API design, validation, persistence, and application monitoring.

---

## ✨ Features

- **CRUD Operations:** Full management for Products, Orders, and Ratings.
- **Validation:** Input validation with **Jakarta Bean Validation**.
- **Mapping:** DTO ↔ Entity mapping using **MapStruct** and **Lombok**.
- **Persistence:** PostgreSQL database integration.
- **Monitoring:** Health and metrics via **Spring Boot Actuator**.
- **DevOps:** Support for local development with **Docker Compose**.
- **Clean Code:** Externalized configuration with `application.yaml`.

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Web MVC**
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct**
- **Lombok**
- **Maven**

---

## 🗂️ Data Model

The data model is centered around three main entities:

- **Product**  
  Represents items available in the pet shop (name, price, category).

- **Order**  
  Represents customer orders containing multiple products (**Many-to-Many**).

- **Rating**  
  Represents customer evaluations for specific products (**Many-to-One**).

> 💡 **Tip**  
> You can view the full Entity-Relationship Diagram (ER) here:  
> [**Interactive ER Diagram**](https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Project_Model.drawio&dark=auto#R%3Cmxfile%3E%3Cdiagram%20name%3D%22Page-1%22%20id%3D%22I9RByHBBvjJnGcHX3Wb9%22%3E7Z1dc5s4F4B%2FTWZ2L9IxYPxxGTtpm9Zp8zrZbXdvPLJRbKYYvFhu4vz6VzIS2JEgEAeRSJrJTIwMEj4fPALOOTpxhsuHTzFYLa4iDwYndst7OHHOT2zbatsd%2FI%2B0bJOWXttKGuax7yVNrazhxn%2BE9EjWuvE9uKZtSROKogD5q8PGWRSGcIYO2kAcR%2FeHu91FgXfQsAJzyDXczEDAt%2F7wPbSgrVann33xGfrzBR26Z3eTL5aA7Ux%2FyXoBvOh%2Br8m5OHGGcRSh5NPyYQgDIrxDuXzM%2BTY9sRiGqMwB9mgz%2FvHV%2Fz1e%2Fnj89SWw%2Fv170D6lvfwGwYb%2BYDSdrOLI28zQmp432jJh4J%2BwIh8RmJKmwRqBGFGdOS3cgLWAgB%2FCGDdYu%2B0gAKu1v9s9aVn4gTcC22iDWEdsa3DnP0BvnKiM7Iu1N8KdkU3S%2BR3u%2FIaeDPkaBP48xJ9nWABkxEEM1%2FhcRmCN6B4LtAzoR15a7KfDGMGHvSYqvU8wWkIUb%2FEu9Ns%2BVSS1ZMt2ku37zC4ci2p%2FsWcS7RY9EFBbnKddZ%2BrCH6jGKmjP5rRXrLIxsb%2FBIor9R6KogAp2X4277Xt%2FGYAQ2zXwnjQNop0j79ThB8EwCiKi6zAKIaduspMXR6tbEM8hog2ryA%2FRTg7uAP9hyQxbH9wTF5%2FrEG9b2Tb%2BI7vHaBiFaxRjsyJ9QKzde0g0PEDRinYawDvWf0zlTj5PI4SiZa4BFDrE81ZBzcBplbWCmozA4Yzg%2BmuuGeDfj3wQjPGlEoTzIFHa7soJMqUJNCuUdSrfp4J%2F6qkRFuddsLv4LXzPg9hrB%2FcLH8GbFZiRne4xPJ7z10IPeF5d%2B%2FppVVMP7SwTWuXeQICvUCFA2H82obfmdJ6e58vNoM2ZAb2MT%2FBhdicgHrNegfDALjr%2FbQh%2BEmWcrhNtnBHBx3CnBLYD%2FjSn%2F09sp598CpKWj0m36Q6yhhpcfrq5GF%2BejRq2dsahZF%2Fyy2d%2BOB8lR3aeuIP7RtzhIffqZfcqXr2K3aNUdxL8wzWsLMXKVo2s7JZkpVMXKzvljUDOtQN6PuuvXlC6pXX1DNqKdFMdlE97k3Ah6HI2EIIlVBOREof6%2B2w8%2FHw2%2FsN23T%2FfOpDfgptVBPDL3a5UdxL8jj0JMsCVB1yr1TRx2eMw9QnL7Nsg9tTucYxF25Vh7DsaiuHcaWlM8wourR3Oe22Dc%2Bk4L%2FuwuTac91xdcM7s2%2BD8tMc%2FNgGhvwTBRF2qG%2F5V8wHt%2BGdZ5n5WPgA7TQPQsrS5oU0t3CAQy4K%2FpZ3GIPTUpJ8BrVzQVnE1DUnrGNJKJ22%2FedLmP2FQjrROaf2oT1o%2BUsOD61nsr5AfhWpC8Pbi563O9Ctv%2FtrRj593GhjKhmEaw98YDPvlrUAxNvZKK0t5MrY4I1ijaPZLTSRKHOrym8bwLe9f2qHX4rOjDHuls9dtmr2WrS18rfLqUp6%2BfJ7VKvZnir7%2BlDjU%2BcXw8ups9AeZ3gztP9X8kVKH0nUqU%2BFqpd9cxoRryZ%2B69BqfumgTrmWZcK1UFHy4FqmLMLlPTktJ6LFJhEvnENoi0ERskd4G7sXUujxfuKO%2F7Nt%2FV5Mb7%2B7%2B1BBQOgEdSyIBhUpXEYBF1q0X%2F4SS4PE3iyEe0ZsARel3e3l1cXN7dnWtBfiONH%2FtuJcfsGq4Vxf32k1zr6cL97qldaM69%2FgYgQD7xWSz8vCwBnyKgq%2B8%2FWsHPnbzYcgnkXwyazyJtW7pgr40IMiwT%2FCO3oMBJDd9tAyiRCoNvn8fXZx905dJFQxTZSjlvKTk52loOokBwtop5tUTNqlfcdl16bMcChdBTordsXi1ptXDa4gF4oMwzSTj2JrLqUscU3VZaAk1BoXxwYEa1V3O%2FECruUeOLPjJR3I5N6WXm57jyCu9XMUjCiY9TsWL2DOTnjLdyXCRgrpBWlOyyq14dUoKqmVIpqSgdNDHpikpLyapZ6ouZ7Lg6y43skABRpfW%2BT5VjFI%2FTtl8lL8Bl3xwlb3Rr%2FH2jl9PRRts2aYaRSYLvhoF8e21mvd1epOxgt3rR0bXgLABEFqCyhOSSejmV99SDXzMxg34sCz4CdAs8IkAlSQfK0Poal2GsIID6EdAm3%2BWZZDYABIFBSFk3xzyz7K0YaRZ5mZPFnxxtlm0XELicmblNdl3b69SQldVdlUoIGfYVR%2B7BBUBJLOL9awlu8orTHl2sSvTfsQsHnGCfKmLoeqVwXG0WepHrl6JArjQm0MW5YNl7aPtGAaAlIu%2ByL5JYoESdFn2oaZg6J3F8U65F2NsPrfRFQi3xETID4MMbbjvePtzf%2BMfsvHBZZvnD%2FtfnjP%2BwQcf%2FWRD4c%2FJUQ7dyg4iG%2FvHXMMYe%2BMu1DYHTCzXIdrEM1ggRiozxPiaL276voOItOwFMl1ldN9W0sZ4p4rf8OCECwzymmA%2FM%2FV2iz5%2FZGuIu4c9JD%2BdHpTZH9%2BPe9iP1e4edpTIhuvo9QxZGPcdxR6M18VzsSfzLvXjvnuudaCrttPjr3d9UeB3t7Z5U7%2FCvEnrGXSlWJRXCfwWWUJ9E%2Bg%2BP3HSKfA79QMzh7b6NmcKu%2Bu5OnHf7%2Fz0c4Yy0eyv6eYF9yRVH208c09Spjspfm%2FeBL3%2B07TqcwFReLvkuYDGL4L65kVQJgv%2BIQWKsJ9PsKSX68kMj6xo3ITeEYMVfEA7StodA8UGoCgKnZcKRbujTcBgauKGgVgWegUMqjmUCYOs6NYqY%2F3UCj9vt9Ov%2Fzx%2Bvzu923zZnn37ZqqJNgF1YRpAXVQXql3FeqJF9q0X0YWSELyv3N3TgqW6t7MSh%2BLWvdIAt0d6nMqwFc9E3L7BbQO4FaUYSL6JbikI3GIb1wu5OWrngwuwL6ONovnmag6VJmRofRNdwa015HrHcL0BrovSL%2BRy3c1%2FfqIc180L4kwW%2FAviJFJstzSVH4VqklDiUCaRpJLHqYzc0U3v6vTXJvjyabHcPlqL9tXAYitZGODKBK4tWP%2BxNuAK1Z4fg%2FB%2BcVtk33rBVigJvoRjglqVF8IyoK0JtEf6mnaYNa%2BHm8CsYLlJuZhV8fVwkX0bzApeD7MVt3a4VRN%2BEofSaRWxI11NZcrmvCfiyxKjKVvqbm2yykXm0e5QSqVLVQqyynsOr%2BD6ssrtjskkKzeBqhS%2BWX6Fg4KscpEl1Pi%2Bn88k0yirPPMDreZVObLg3xWwlVIayC43ydFHWWvB1MSteIF5ZmpSpjsp5mtKDb76M4EXIE2UHC0XaWzWpOXaX5kbGKLZXT6qTc7aX%2B%2Blz3J1SvIJrXUWdhVX0w%2FHXdvg%2BA3gWJSWLRnH%2FHtRjXCcuoHBsd3lM7VN2TLD%2FXfH%2FQo%2BrTL3hUl1JtytAcgL07TrorxQ7flzu%2FcL9CL71gvnQknw07r%2FNmBXBN3A%2FP3AXBeSH%2BnM2nG8bTjeAMdF%2Bd9SOe7qwvF2ad2oznE%2BEmQT%2Bmiyiv1ZrUHr76XPo%2FBqiq1U9jeVUTsNJ9vr6H%2Fo%2FHc%2FOO%2BOLv%2FaLr8b1DaCWlFKdl2oFapdRdQW2bdeqBVKgkftLg0behNQa12z99LnUajVKz%2FsSE%2FTDrLmuXQDkBWmYUuFrIrPpYvs20BW8Fw6wJ6RZWGDJZFwOF2v1CFkczA21DVPkfNFY7Kym6CuKCtbKnVVzMousm9D3YKs7IMcIonA0imP%2BUjjVJlLOSFcKq3u%2FqHVYmu6J0d2T97eCu9ptPzbWeGd3Z0%2BjaKqusS7y%2FLy8jqqeYl3u1si9U26NVsvs%2BbWgSW7hZb8cqtN15l93m5ZtOebsdtO%2B0k9gbbzMrvttg476rS6r2S3eDOOCK6z3THFFlcRnhTgxv8D%3C%2Fdiagram%3E%3C%2Fmxfile%3E)


---

## 🚀 Postman Collections

To facilitate testing and integration, Postman collections are included in this repository.

* **Base URL:** `http://localhost:8080/petshop`
* **Environment:** Ensure your local server is running before executing requests.

---

## 🛠 API Reference

### 1. Products API
Manages the store's inventory of pet supplies.
* **Endpoint:** `/products`.
* **Methods:** Supports `GET`, `POST`, `PUT`, `DELETE`, and `PATCH`.
* **Key Fields:** Includes `name`, `type` (e.g., FOOD), `animalType` (e.g., DOG), `brand`, `description`, `stock`, `price`, and `sizeWeight`.
* **Sample Payload:**
    ```json
    {
      "name": "test",
      "type": "FOOD",
      "animalType": "DOG",
      "brand": "test",
      "description": "1234567890",
      "stock": 1,
      "price": 1.00,
      "sizeWeight": 1.00
    }
    ```

### 2. Orders API
Handles customer transactions and tracking.
* **Endpoint:** `/orders`.
* **Methods:** Supports `GET`, `POST`, `PUT`, and `DELETE`.
* **Order Statuses:** Includes `PENDING` and `IN_PROGRESS`.
* **Structure:** Orders link to multiple products via an `items` array containing `productId` and `quantity`.
* **Sample POST Payload:**
    ```json
    {
      "client": "teste",
      "status": "PENDING",
      "items": [
        { "productId": 1, "quantity": 10 },
        { "productId": 2, "quantity": 10 }
      ]
    }
    ```

### 3. Ratings API
Collects customer feedback and quality scores.
* **Endpoint:** `/ratings`.
* **Methods:** Supports `GET`, `POST`, `PUT`, and `DELETE`.
* **Rating Levels:** Uses an enum for `stars` (e.g., `ONE`).
* **Sample Payload:**
    ```json
    {
      "stars": "ONE",
      "client": "teste",
      "comments": "this is a rating test"
    }
    ```

---

## 🧪 Automated Testing

The collections include pre-configured test scripts to verify API responses:
* **GET Requests:** Validates a `200 OK` status code.
* **POST Requests:** Ensures a successful creation with status code `200` or `201`.
* **PUT Requests:** Checks for codes `200`, `201`, or `204` to confirm updates.
* **DELETE Requests:** Verifies deletion with codes `200`, `202`, or `204`.

--- 


## 🔗 API Endpoints

### 📦 Products

| Method | Endpoint          | Description                   |
|------|-------------------|-------------------------------|
| GET  | `/products`       | Retrieve all products         |
| GET  | `/products/{id}`  | Retrieve product by ID        |
| POST | `/products`       | Create a new product          |
| PUT  | `/products/{id}`  | Update a product              |
| PATCH| `/products/{id}`  | Partially update a product    |
| DELETE | `/products/{id}`| Delete a product              |

---

### 🧾 Orders

| Method | Endpoint        | Description              |
|------|------------------|--------------------------|
| GET  | `/orders`        | Retrieve all orders      |
| POST | `/orders`        | Create a new order       |
| DELETE | `/orders/{id}` | Delete an order          |

---

### ⭐ Ratings

| Method | Endpoint   | Description            |
|------|------------|------------------------|
| GET  | `/ratings` | Retrieve all ratings   |
| POST | `/ratings` | Create a new rating    |

---

## 📂 Project Structure

```plaintext
src/main/java/com/lucas/petshop
├── controller   # Web Layer (REST Controllers)
├── dto          # Data Transfer Objects
├── enums        # Enumerations (Domain constants)
├── service      # Business Logic
├── repository   # Data Access Layer
├── mapper       # Entity-DTO Mapping
├── entity       # Database Models
└── exception    # Global Exception Handling
```

--- 

## ⚙️ Running the Project

### 1. Configure the database
Ensure PostgreSQL is running or use the provided Docker configuration:
```bash
docker-compose up -d
```
### 2. Run the application
```
mvn spring-boot:run
```


