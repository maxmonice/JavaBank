# JavaBank

**JavaBank** is a full-stack banking application developed to demonstrate backend engineering, database management, secure authentication, and modern web application development.

Originally developed as a Java console-based application, the project is being restructured into a full-stack system using **Spring Boot** for the backend, **React** for the frontend, and **MariaDB** for persistent data storage.

The project emphasizes clean architecture, object-oriented programming, RESTful API design, data integrity, and application security.

## Tech Stack

### Backend
- **Java** – Primary programming language
- **Spring Boot** – Backend application framework
- **Spring Web** – RESTful API development
- **Spring Data JPA** – Database access and persistence
- **Maven** – Dependency and build management
- **Jakarta Bean Validation** – Request validation

### Frontend
- **React** – User interface development
- **TypeScript** – Frontend programming language

### Database
- **MariaDB** – Relational database management system

## Project Structure

```text
JavaBank/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml/
│
├── frontend/
│
├── .gitignore
├── LICENSE
└── README.md
```

## Architecture

JavaBank follows a client-server architecture:

```text
React Frontend
      |
      | HTTP / JSON
      v
Spring Boot REST API
      |
      | Spring Data JPA
      v
MariaDB Database
```

The frontend communicates with the backend through HTTP requests. The backend handles application logic, request validation, authentication, and database operations.

## Planned Features

- Account registration and authentication
- Secure PIN/password hashing
- Account balance management
- Deposits and withdrawals
- Transaction history
- Account information management
- Input validation and error handling
- RESTful API endpoints

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- MariaDB Server
- Node.js and npm (for frontend development)

### Backend Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/maxmonice/JavaBank.git
   ```

2. Navigate to the backend directory:

   ```bash
   cd JavaBank/backend
   ```

3. Configure your database connection in `src/main/resources/application.properties`.

4. Create the required database in MariaDB.

5. Build the application:

   ```bash
   mvn clean install
   ```

6. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

The backend will run on the configured application port.

### Frontend Setup

Frontend setup instructions will be added once the React application has been initialized.

## Security

Security is an important consideration in JavaBank's development.

The project aims to incorporate:

- Password/PIN hashing using Argon2id
- Parameterized database operations
- Server-side input validation
- Secure handling of application credentials
- Proper authentication and authorization mechanisms

## Development Status

<text>JavaBank is currently under active development.</text>

The project is undergoing a transition from its original Java console implementation to a modular full-stack architecture.

Features, architecture, and documentation will evolve as development progresses.

## License

This project is distributed under the license included in this repository. See [LICENSE](LICENSE) for details.
