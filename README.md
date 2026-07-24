# Media Backlog API 🎬📚

A RESTful web service built with Spring Boot to manage a personal backlog of media items (movies, books, video games, etc.). This project serves as a practical implementation of enterprise Java backend architecture, featuring strict REST semantics, automated object mapping, and comprehensive test coverage.

## 🚀 Key Features

*   **Standardized REST Endpoints:** Full CRUD lifecycle (GET, POST, PUT, PATCH, DELETE) with strict semantic separation between full replacements (`PUT`) and partial updates (`PATCH`).
*   **3-Tier Architecture:** Strict separation of concerns between Controllers, Services, and Repositories.
*   **Data Transfer Objects (DTOs):** Secure data payloads shielding internal database entities from the presentation layer. Includes dedicated DTOs for dynamic partial updates.
*   **Automated Object Mapping:** Utilizes **MapStruct** for highly performant, compile-time generation of mapping logic between DTOs and Entities (including null-safe mapping for `PATCH` requests).
*   **Jakarta Validation:** Strong input validation (`@NotBlank`, `@Size`) enforced at the Controller layer to prevent bad data from entering the system.
*   **Global Exception Handling:** Custom `@RestControllerAdvice` intercepts errors (like validation failures or missing records) and returns clean, predictable JSON responses.
*   **Interactive Documentation:** Automated, real-time API documentation via **Swagger UI** (OpenAPI 3).
*   **Robust Unit Testing:** Comprehensive service-tier testing using **JUnit 5** and **Mockito** to verify state mutations and business logic.
*   **In-Memory Database:** Uses H2 for rapid development, testing, and zero-config setup.

## 🛠️ Tech Stack

*   **Java 17+**
*   **Spring Boot** (Web, Data JPA, Validation)
*   **MapStruct** (Automated Bean Mapping)
*   **SpringDoc OpenAPI** (Swagger UI)
*   **H2 Database** (In-memory SQL)
*   **JUnit 5 & Mockito** (Testing Framework)
*   **Lombok** (Boilerplate reduction)
*   **Maven** (Dependency management)

## 🏗️ Architecture Flow

1.  **Request:** Client sends a JSON payload to the `MediaController`.
2.  **Validation:** The Controller validates the payload via `MediaItemRequestDTO` or `MediaItemPatchDTO`.
3.  **Service Layer:** Valid data is passed to the `MediaService` (Interface/Impl pattern).
4.  **Mapping:** `MapStruct` safely translates the DTO into a `MediaItem` Entity (ignoring nulls for partial updates).
5.  **Persistence:** The mapped Entity is passed to the `MediaRepository`. Spring Data JPA automatically translates the repository call into SQL and saves it to the H2 Database.

## 📚 API Documentation (Swagger)

Once the application is running, you can explore the endpoints, view the schemas, and send real requests directly from your browser.

Navigate to: `http://localhost:8081/swagger-ui.html`
*(Note: If the port was changed in `application.properties`, replace `8081` with your active port).*

## 🚦 Getting Started

### Prerequisites
*   Java Development Kit (JDK) installed.
*   Maven installed (or use the included Maven wrapper).

### Running the Application
```bash
./mvnw spring-boot:run