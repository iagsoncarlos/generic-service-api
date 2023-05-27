# Generic Service API

The Generic Service API is a project template for building RESTful APIs using Spring Boot, Spring Data JPA, and Swagger for API documentation.

![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=flat&logo=Apache%20Maven&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=flat&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=flat&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=flat&logo=swagger&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=flat)](./LICENSE)

## Features

- Generic CRUD operations for managing entities
- PostgreSQL database integration with Spring Data JPA
- Database migrations made easy. Version control for database using Flyway.
- API documentation using Swagger and Springfox
- Docker Compose configuration for development and production environments



## Project Structure

The project follows a well-organized structure to maintain clarity and modularity:

- `com.service.generic.config`: Contains the Swagger configuration for API documentation.
- `com.service.generic.controller`: Handles HTTP requests and responses through controller classes.
- `com.service.generic.model`: Defines entity classes representing the data models.
- `com.service.generic.dto`: Includes data transfer object (DTO) classes for transferring data between the API and the service layer.
- `com.service.generic.exception`: Handles exception handling and provides error responses.
- `com.service.generic.service`: Implements the service layer, handling business logic and data operations.
- `com.service.generic.mapper`: Includes mapper classes for converting between entity objects and DTOs.
- `com.service.generic.repository`: Contains repository interfaces for performing database operations.

## Swagger Configuration

The `SwaggerConfig` class in the `com.service.generic.config` package provides the configuration for Swagger, which generates API documentation. It includes details such as the API title, version, description, contact information, and license.

## Controller Classes

The controller classes in the `com.service.generic.controller` package handle the HTTP requests and responses for the API endpoints. The `CrudController` class provides basic CRUD operations for the entities, while the `UsersController` class is an example of a specific controller for managing user entities.

## Entity and DTO Classes

The entity classes in the `com.service.generic.model` package represent the data models used in the application. The `BaseEntity` class is an abstract class that provides common fields such as `id`, `createdAt`, and `updatedAt`. The `Users` class is an example entity representing user information.

The DTO classes in the `com.service.generic.dto` package are used for transferring data between the API and the service layer. The `BaseDTO` class is an abstract class that provides common fields such as `id`, `createdAt`, and `updatedAt`. The `UsersDTO` class is an example DTO representing user information.

## Service Classes

The service classes in the `com.service.generic.service` package handle the business logic and data operations. The `CrudService` class provides common CRUD operations that can be extended by specific service implementations. The `UsersService` class is an example of a specific service implementation for managing user entities.

## Repository Interface

The repository interfaces in the `com.service.generic.repository` package define the database operations using Spring Data JPA. The `PagingAndSortingRepository` interface provides methods for pagination and sorting.

## Implementation Example
- `com.service.generic.controller.users`: Controller for the user service.
- `com.service.generic.dto.users`: DTO examples for the user service.
- `com.service.generic.service.users`: Interface for the user service.
- `com.service.generic.service.users.impl`: Implementation class for the user service.
- `com.service.generic.mapper.impl`: Generic CRUD mapper implementation class for services.
- `com.service.generic.mapper.users`: Example class for the user mapper.

## Dependencies

The project includes several dependencies in the `pom.xml` file, including:

- Spring Boot Starter dependencies for web and data JPA
- Flyway for database migration
- PostgreSQL JDBC driver
- Lombok for generating boilerplate code
- Testing dependencies
- Swagger and Springfox for API documentation

## Configuration

The `application.yml` file contains the configuration settings for the application, including the database connection, Hibernate settings, server context path, and logging levels.

## Building and Running the Application

To build and run the application, you can use Maven or an integrated development environment (IDE) such as

IntelliJ IDEA or Eclipse. Run the main class `com.service.generic.GenericApplication` to start the Spring Boot application.

After starting the application, you can access the API documentation generated by Swagger by navigating to `http://localhost:8080/generic-service/api/swagger-ui`.

## Docker Compose Configuration

The project includes two Docker Compose configuration files: `docker-compose.dev.yml` and `docker-compose.prod.yml`.

### Development Environment

The `docker-compose.dev.yml` file is used for simplified development. It defines the following services:

- `postgres`: A PostgreSQL database container based on the Alpine version of the PostgreSQL image. It uses the environment variables defined in the `.env.development` file to configure the database.
- `pgadmin`: A PgAdmin administration tool container based on the `dpage/pgadmin4` image. It provides a web-based interface for managing the PostgreSQL database. The environment variables defined in the `.env.development` file are used to configure PgAdmin.

To start the development environment, run the following command:

```bash
docker-compose -f docker-compose.dev.yml up -d
```

### Production Environment

The `docker-compose.prod.yml` file is used for deployment in a production environment. It defines the following services:

- `postgres`: A PostgreSQL database container based on the official PostgreSQL image. It uses the environment variables defined in the `.env.production` file to configure the database.
- `app`: The Generic Service API container built from the project's Dockerfile. It depends on the `postgres` service and exposes the API on port 8080.

To start the production environment, run the following command:

```bash
docker-compose -f docker-compose.prod.yml up -d
```
NOTE: Please make sure to update the environment variables in the respective `.env` files to match your specific configuration.

## Getting Started Development

To get started with the Generic Service, follow these steps:

1. Clone the repository or download the source code.

```bash
git clone https://github.com/iagsoncarlos/generic-service-api.git
```
```bash
cd /generic-service-api
```

2. Make sure you have Java 17 and Maven installed on your system.
3. Set up a PostgreSQL database and update the configuration in `application.yml` with the appropriate connection details.
4. Build the application using Maven: `mvn clean package`.
5. Run the application: `java -jar target/generic-service-api.jar`.

After starting the application, you can access the API documentation generated by Swagger by navigating to `http://localhost:8080/generic-service/api/swagger-ui`.


## Usage Example

- `POST /api/{entity}`: Create a new entity.

```bash
curl -X 'POST' \
  'http://localhost:8080/generic-service/api/users' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "username": "string",
  "email": "string",
  "password": "string"
}'
```

- `GET /api/{entity}`: Get all entities of a specific type.

```bash
curl -X 'GET' \
  'http://localhost:8080/generic-service/api/users' \
  -H 'accept: */*'
```

- `GET /api/{entity}/{id}`: Get a specific entity by ID.

```bash
curl -X 'GET' \
  'http://localhost:8080/generic-service/api/users/3fa85f64-5717-4562-b3fc-2c963f66afa6' \
  -H 'accept: */*'
```

- `PUT /api/{entity}/{id}`: Update a specific entity by ID.

```bash
curl -X 'PUT' \
  'http://localhost:8080/generic-service/api/users/3fa85f64-5717-4562-b3fc-2c963f66afa6' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "username": "string",
  "email": "string",
  "password": "string"
}'
```

- `DELETE /api/{entity}/{id}`: Delete a specific entity by ID.

```bash
curl -X 'DELETE' \
  'http://localhost:8080/generic-service/api/users/3fa85f64-5717-4562-b3fc-2c963f66afa6' \
  -H 'accept: */*'
```

## Acknowledgements

Generics in Java have revolutionized the way developers write code, offering numerous benefits in terms of type safety, code reuse, readability, and error detection. Their widespread adoption and usage in Java libraries and frameworks demonstrate their significance in modern Java development.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.