# Media Backlog API 🎬📚

A RESTful web service built with Spring Boot to manage a personal backlog of media items (movies, books, etc.). This project serves as a practical implementation of enterprise Java backend architecture.

## 🚀 Key Features

*   **Standardized REST Endpoints:** Clean routing for creating and retrieving media items.
*   **3-Tier Architecture:** Strict separation of concerns between Controllers, Services, and Repositories.
*   **Data Transfer Objects (DTOs):** Secure data payloads between the client and server.
*   **Jakarta Validation:** Strong input validation (`@NotBlank`, `@Size`) to prevent bad data from reaching the database.
*   **Global Exception Handling:** Custom `@RestControllerAdvice` intercepts errors and returns clean, predictable JSON responses instead of server stack traces.
*   **In-Memory Database:** Uses H2 for rapid development, testing, and zero-config setup.

## 🛠️ Tech Stack

*   **Java** 
*   **Spring Boot** (Web, Data JPA, Validation)
*   **H2 Database** (In-memory SQL)
*   **Lombok** (Boilerplate reduction)
*   **Maven** (Dependency management)

## 🏗️ Architecture Flow

1.  **Client** sends a JSON request to the `MediaController`.
2.  The Controller validates the payload via `MediaItemRequestDTO`.
3.  Valid data is passed to the `MediaService` (Interface/Impl pattern) where business logic is applied.
4.  The Service transforms the DTO into a `MediaItem` Entity and passes it to the `MediaRepository`.
5.  Spring Data JPA automatically translates the repository call into SQL and saves it to the **H2 Database**.

## 🚦 Getting Started

### Prerequisites
*   Java Development Kit (JDK) installed.
*   Maven installed (or use the included Maven wrapper).

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
