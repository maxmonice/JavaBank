# Polarity Bank

Polarity Bank is an educational banking application built with Java, Spring Boot, and MariaDB/MySQL. The project started as a Java console-based banking application using object-oriented programming (OOP) and JDBC, and has since expanded to include a Spring Boot backend for REST API development.

The project serves as a practical learning experience in Java development, backend architecture, database integration, RESTful APIs, build tooling, and authentication security.

> **Status:** In development  
> **Purpose:** Educational project. It is not production-ready banking software. Do not use real account credentials or financial data.

## Current Features

### Banking Application
- Account verification using an account ID and PIN
- Balance inquiry
- Deposit and withdrawal workflows
- Java-to-database connectivity through JDBC
- Parameterized SQL queries using prepared statements
- Monetary values represented with `BigDecimal`
- Database setup script with a six-digit PIN format constraint

### Spring Boot Backend
- Integrated Spring Boot backend alongside the original Java banking application
- REST API development using Spring MVC
- HTTP request handling through controller classes
- Endpoint mapping using annotations such as `@GetMapping` and `@PostMapping`
- Centralized application startup through the Spring Boot application class
- Maven-based dependency management and build workflow

Features and implementation details may change as development continues.

## Tech Stack

| Technology | Purpose |
|---|---|
| **Java 25** | Application logic and object-oriented programming |
| **Spring Boot** | Backend application framework |
| **Spring Web** | REST API development and HTTP request handling |
| **Maven** | Dependency management and build lifecycle |
| **MariaDB / MySQL** | Relational database management |
| **JDBC** | Database connectivity for the original console application |
| **MariaDB Connector/J or MySQL Connector/J** | Database driver |
| **XAMPP** | Local database environment |

## Project Structure

The project is evolving toward a backend-oriented architecture. The following represents its current organization at a high level:

```text
java-bank/
├── pom.xml
├── database/
│   └── setup.sql
├── src/
│   └── main/
│       └── java/
│           ├── JavaBank/
│           │   ├── Program.java
│           │   ├── UI.java
│           │   ├── BankAccount.java
│           │   └── Database.java
│           │
│           └── com/
│               └── javabank/
│                   └── JavabankBackendApplication.java
│
├── .gitignore
└── README.md
```

The structure above is representative. Update it as additional packages and classes are introduced.

Maven generates build output in `target/`, which should not be committed to Git.

## Getting Started

### 1. Install the Requirements

Install the following:

- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [XAMPP](https://www.apachefriends.org/) or another compatible MariaDB/MySQL server
- Git, if you want to clone the repository

Verify Java and Maven are available in your terminal:

```powershell
java -version
mvn -version
```

If you recently changed Windows environment variables, restart VS Code and its terminals so they inherit the updated `PATH`.

### 2. Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd java-bank
```

Replace `<YOUR_REPOSITORY_URL>` with the repository's actual URL.

### 3. Start the Database Server

For a typical XAMPP setup, open the XAMPP Control Panel and start **MySQL** (which may run MariaDB underneath).

### 4. Set Up the Database

Run the SQL script at `database/setup.sql`.

**Using the MySQL/MariaDB command-line client:**

```sql
SOURCE C:/path/to/java-bank/database/setup.sql;
```

**Using phpMyAdmin:**

1. Open `http://localhost/phpmyadmin`.
2. Open the **SQL** tab.
3. Paste the contents of `database/setup.sql`.
4. Execute the script.

The script creates the `bank` database and `accounts` table if they do not already exist.

> **Important:** `CREATE TABLE IF NOT EXISTS` does not update an existing table's schema. Use a deliberate migration when modifying an existing database.

### 5. Configure the Database Connection

Check the JDBC URL and credentials in the database connection code.

A local setup may resemble:

```java
private String url = "jdbc:mariadb://localhost:3306/bank";
private String username = "root";
private String password = "";
```

Use the URL scheme corresponding to the JDBC driver declared in `pom.xml`.

Do not commit real database credentials or other secrets to Git. Prefer environment variables or another secrets-management mechanism.

## Running the Application

### Original Java Console Application

The original banking application can be launched through Maven:

```powershell
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=JavaBank.Program"
```

This executes the console application using Maven's runtime classpath.

### Spring Boot Backend

The Spring Boot backend provides the foundation for exposing banking functionality through REST API endpoints.

To start the backend, run:

```powershell
mvn spring-boot:run
```

Alternatively, if the Maven Wrapper is available:

```powershell
.\mvnw.cmd spring-boot:run
```

Once the application starts successfully, the backend is accessible locally at:

```text
http://localhost:8080
```

For example, a mapped endpoint such as `/` can be accessed through:

```text
http://localhost:8080/
```

Spring Boot handles incoming HTTP requests and routes them to the appropriate controller methods based on their request mappings and HTTP methods.

### Building the Project

To compile and package the project:

```powershell
mvn package
```

Maven compiles the source code, runs configured tests, and packages the application into a JAR under `target/`.

The `package` command does not automatically start the Spring Boot application.

## Database Schema

The current setup script creates the following table:

| Column | Type | Purpose |
|---|---|---|
| `account_id` | `VARCHAR(20)` | Account identifier and primary key |
| `account_name` | `VARCHAR(100)` | Account holder's name |
| `pin` | `VARCHAR(6)` | Currently stores a six-digit PIN as text |
| `balance` | `DECIMAL(12, 2)` | Account balance, defaulting to `0.00` |

The schema uses `utf8mb4` with `utf8mb4_general_ci` and the InnoDB storage engine.

### PIN Format Constraint

The table includes:

```sql
CONSTRAINT chk_pin_format
CHECK (pin REGEXP '^[0-9]{6}$')
```

This database-level constraint restricts the PIN to exactly six numeric characters.

Storing the PIN as `VARCHAR(6)` instead of an integer preserves leading zeros, such as `004281`.

**Planned security change:** Once Argon2id is implemented, the database should store the encoded PIN hash rather than the raw PIN. This will require updating the database schema and authentication logic together.

## Security Roadmap

The project is still under development. Planned improvements include:

- [ ] **Argon2id PIN hashing** — replace plaintext PIN storage with secure password hashing.
- [ ] **Unique random salts** — generate a cryptographically secure salt for each account.
- [ ] **Optional pepper from configuration** — keep any pepper outside the database and source control.
- [ ] **Login rate limiting and temporary lockouts** — reduce repeated authentication attempts.
- [ ] **Safer secrets configuration** — remove hardcoded database credentials.
- [ ] **Database transaction improvements** — ensure money movement operations are atomic.
- [ ] **Automated testing** — cover authentication, invalid inputs, and database failures.

These are planned improvements, not claims that the protections have already been implemented.

## Future Development

The project is gradually moving toward a full-stack architecture.

Potential future improvements include:

- **React frontend** — create a web-based user interface for banking operations.
- **REST API integration** — expose banking functionality through Spring Boot controllers.
- **Spring Data JPA** — simplify database persistence and entity management.
- **Authentication and authorization** — implement secure authentication and access control.
- **Cloud deployment** — explore deploying the backend and database to cloud infrastructure.

## Development Notes

- Use `BigDecimal` for monetary calculations instead of `double`.
- Use prepared statements for SQL queries containing user input.
- Close JDBC resources reliably using try-with-resources.
- Never store real financial data in this educational project.
- Update database scripts, application code, and documentation together when changing the schema.
- Keep generated files such as `target/`, compiled `.class` files, and local secrets out of Git.

## Contributing

1. Clone the repository and create a branch for your changes.
2. Make a focused change.
3. Run the application and relevant tests locally.
4. Update documentation when application behavior or database structure changes.
5. Commit and push your changes.

## License

No license has been specified yet. Unless a license is added to the repository, others should not assume they have permission to redistribute or reuse this project.

---

Made for learning Java, backend development, OOP, SQL, REST APIs, and secure application development.