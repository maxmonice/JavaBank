# Polarity Bank

A Java console-based banking application built with object-oriented programming (OOP), JDBC, and MariaDB/MySQL. The project is being developed as a practical way to learn Java, database integration, build tooling, and authentication security.

> **Status:** In development  
> **Purpose:** Educational project. It is not production-ready banking software. Do not use real account credentials or financial data.

## Current Features

- Account verification using an account ID and PIN
- Balance inquiry
- Deposit and withdrawal workflows
- Java-to-database connectivity through JDBC
- Parameterized SQL queries with prepared statements
- Monetary values represented with `BigDecimal`
- Database setup script with a six-digit PIN format constraint
- Maven-based dependency management and build workflow

Features and implementation details may change as development continues.

## Tech Stack

- **Java 25 LTS** — application logic and OOP
- **Maven** — dependency management and build lifecycle
- **MariaDB / MySQL** — account data storage
- **JDBC** — Java-to-database connectivity
- **MariaDB Connector/J or MySQL Connector/J** — database driver, as configured in `pom.xml`
- **XAMPP** — local database environment

## Project Structure

```text
java-bank/
├── pom.xml
├── database/
│   └── setup.sql
├── src/
│   └── main/
│       └── java/
│           └── JavaBank/
│               ├── Program.java
│               ├── UI.java
│               ├── BankAccount.java
│               └── Database.java
├── .gitignore
└── README.md
```

The class names shown are representative; adjust this list if the actual source files differ. Maven generates build output in `target/`, which should not be committed to Git.

## Getting Started

### 1. Install the requirements

Install:

- [Java 25 JDK (LTS)](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [XAMPP](https://www.apachefriends.org/) or another compatible MariaDB/MySQL server
- Git, if you want to clone the repository

Verify Java and Maven are available in your terminal:

```powershell
java -version
mvn -version
```

If you recently changed Windows environment variables, restart VS Code and its terminals so they inherit the updated `PATH`.

### 2. Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd java-bank
```

Replace `<YOUR_REPOSITORY_URL>` with the repository's actual URL and adjust the directory name if necessary.

### 3. Start the database server

For a typical XAMPP setup, open the XAMPP Control Panel and start **MySQL** (the service may be MariaDB underneath).

### 4. Set up the database

Run the SQL script at `database/setup.sql`.

**Using the MySQL/MariaDB command-line client:**

```sql
SOURCE C:/path/to/java-bank/database/setup.sql;
```

Replace the example path with the real path to the script. Forward slashes are convenient in Windows SQL client paths.

**Using phpMyAdmin:**

1. Open `http://localhost/phpmyadmin` (if that is the address for your local setup).
2. Open the **SQL** tab.
3. Paste the contents of `database/setup.sql`.
4. Execute the script.

The script creates the `bank` database and `accounts` table if they do not already exist. It does not insert sample accounts or intentionally delete existing data.

> **Important:** `CREATE TABLE IF NOT EXISTS` does not change a table that already exists. If you are updating an older database schema, use a deliberate migration or alter the existing table separately. Do not assume rerunning this script adds the new constraint to an existing table.

### 5. Configure the database connection

Check the JDBC URL and credentials in the project's database connection code. A local setup may resemble:

```java
private String url = "jdbc:mariadb://localhost:3306/bank";
private String username = "root";
private String password = "";
```

Use the URL scheme that matches the JDBC driver declared in `pom.xml` (`jdbc:mariadb:` for MariaDB Connector/J or `jdbc:mysql:` for MySQL Connector/J). The username, password, host, and port above are only examples; configure them for your own database.

Do not commit real database credentials, PIN peppers, or other secrets to Git. Prefer environment variables or another secrets-management mechanism.

### 6. Build and run with Maven

Maven reads `pom.xml` to manage dependencies and execute the project's build lifecycle. It downloads declared dependencies into the local Maven repository, so the project generally does not need a manually maintained `lib/` folder for those dependencies.

From the directory containing `pom.xml`, run:

**Compile and run the application:**

```powershell
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=JavaBank.Program"
```

This Maven goal compiles the project as needed and runs `JavaBank.Program` using Maven's runtime classpath. It is not the same as creating a distributable JAR.

**Compile and package the project:**

```powershell
mvn package
```

Maven compiles the source, runs tests if configured, and packages the application into a JAR under `target/`. The exact JAR name is based on the artifact ID and version in `pom.xml`. The `package` command does not automatically run the banking application, and a normal JAR is not a snapshot of the whole project or its database.

If a command fails, check that the terminal is in the project directory containing `pom.xml`, Java and Maven are on `PATH`, and the database is running when the application needs it.

## Database Schema

The current setup script creates this table:

| Column | Type | Purpose |
|---|---|---|
| `account_id` | `VARCHAR(20)` | Account identifier and primary key |
| `account_name` | `VARCHAR(100)` | Account holder's name; required |
| `pin` | `VARCHAR(6)` | Currently stores a six-digit PIN as text |
| `balance` | `DECIMAL(12, 2)` | Account balance, defaulting to `0.00` |

The schema uses `utf8mb4` with `utf8mb4_general_ci` and the InnoDB storage engine.

### PIN format constraint

The table includes:

```sql
CONSTRAINT chk_pin_format
    CHECK (pin REGEXP '^[0-9]{6}$')
```

This database-level check is intended to restrict `pin` to exactly six numeric characters. Storing the PIN as `VARCHAR(6)` rather than an integer also preserves leading zeros, such as `004281`.

Application-side validation should still be used. Database constraints are an additional layer, not a replacement for validating user input.

**Planned security change:** once Argon2id is implemented, the database should store the encoded password/PIN hash, not the raw six-digit PIN. The current six-digit format constraint will then no longer apply to the hash column. The schema and Java authentication code will need to be updated together—for example, by using a column such as `pin_hash VARCHAR(255)` and removing or replacing `chk_pin_format`. Plan this as a schema migration rather than assuming `CREATE TABLE IF NOT EXISTS` will update existing databases.

## Security Roadmap

The project is still in development. Planned improvements include:

- [ ] **Argon2id PIN hashing** — replace plaintext PIN storage with a salted, deliberately expensive password hash using a maintained library managed by Maven.
- [ ] **Unique random salts** — generate a different cryptographically secure salt for each account PIN hash. The salt is stored with the encoded hash and does not need to be secret.
- [ ] **Optional pepper from configuration** — if implemented, keep the pepper outside the database and source control, such as from environment configuration. A pepper must never be committed to the repository.
- [ ] **Login rate limiting and temporary lockouts** — limit repeated failed PIN attempts to reduce automated guessing of six-digit PINs. Track attempts in a way that cannot be bypassed simply by restarting the application.
- [ ] **Safer secrets configuration** — remove hard-coded database credentials and avoid committing secrets.
- [ ] **Database transaction and input validation review** — make money movement operations atomic and validate account state and transaction amounts.
- [ ] **Automated tests and improved error handling** — cover authentication, invalid inputs, and database failures.

These items are planned work, not claims that the protections have already been implemented. Argon2id protects stored PIN verifiers, but it does not by itself prevent online guessing; rate limiting and careful authentication logic remain important. A six-digit PIN has only one million possible combinations, so it requires particularly strict attempt controls.

## Development Notes

- Use `BigDecimal` for monetary calculations rather than binary floating-point types such as `double`.
- Use prepared statements for SQL queries that include user input.
- Close JDBC resources reliably, for example with try-with-resources.
- Never store real financial data in this educational project.
- When changing the schema, update `database/setup.sql`, the Java database/authentication code, and this README together.
- Keep generated files such as `target/`, compiled `.class` files, and local secrets out of Git.

## Contributing

1. Clone the repository and create a branch for your change.
2. Make a focused change.
3. Run the application and relevant tests locally.
4. Update documentation and SQL setup when behavior or schema changes.
5. Commit and push your branch.

## License

No license has been specified yet. Unless a license is added to the repository, others should not assume they have permission to redistribute or reuse this project.

---

Made for learning Java, OOP, SQL, and secure application development.
