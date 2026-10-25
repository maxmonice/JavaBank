# 🏦 JavaBank

A Java console-based banking application built with **Object-Oriented Programming (OOP)** and **MySQL**. This project is designed for learning and demonstrating basic banking operations, database connectivity, and organized Java code.

> **Status:** In development  
> **Note:** This is an educational project, not production-ready banking software. Do not use real account credentials or financial data.

---

## ✨ Features

- **Account verification** using an account ID and PIN
- **Balance inquiry** to view an account's current balance
- **Deposit and withdrawal** workflows
- **MySQL database integration** through JDBC
- **Prepared statements** for parameterized SQL queries
- **Reusable database setup script** for contributors

> Features may change as development continues.

## 🧰 Built With
- **Visual Studio Code** - IDE used
- **Java** — application logic and OOP
- **MySQL / MariaDB** — account data storage
- **JDBC** — Java-to-database connectivity
- **XAMPP** — local database environment
- **MySQL Connector/J** — JDBC driver

---

## 📁 Project Structure

```text
JavaBank/
├── src/                    # Java source code
│   └── Bank/               # Banking classes (e.g., UI, Database, BankAccount)
├── database/
│   └── setup.sql           # Creates the database and accounts table
├── lib/                    # Optional: locally stored JDBC driver
└── README.md
```

*Adjust this example if your repository uses a different folder layout.*

---

## 🚀 Getting Started

### 1. Install the requirements

Make sure you have:

- [Java JDK](https://www.oracle.com/java/technologies/downloads/) installed
- [XAMPP](https://www.apachefriends.org/) or another MySQL/MariaDB server
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) available to your Java project

### 2. Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd JavaBank
```

Replace `<YOUR_REPOSITORY_URL>` with the actual Git repository URL.

### 3. Start the database server

If you're using XAMPP, open the XAMPP Control Panel and start **MySQL**.

### 4. Set up the database

Run the SQL script in `database/setup.sql`.

**Option A — MySQL command-line client**

From the MySQL shell, run the command below, replacing the path with the actual location of your file:

```sql
SOURCE C:/path/to/JavaBank/database/setup.sql;
```

Use forward slashes in the path, or escape backslashes.

**Option B — phpMyAdmin**

1. Open phpMyAdmin in your browser (commonly `http://localhost/phpmyadmin`).
2. Select the **SQL** tab.
3. Copy the contents of `database/setup.sql` into the editor.
4. Click **Go** to execute the script.

The script creates the `Bank` database and the `accounts` table if they do not already exist. It does not create sample accounts.

### 5. Configure the database connection

Check the connection settings in your Java `Database` class. For a typical local XAMPP setup, they may look like:

```java
private String url = "jdbc:mysql://localhost:3306/Bank";
private String username = "root";
private String password = "";
```

These are common local defaults, **not universal settings**. Update the host, port, username, and password to match your own MySQL configuration. Never commit real passwords or secrets to Git.

### 6. Configure JDBC in your IDE

Make sure the MySQL Connector/J JAR is included in the project's classpath or build configuration. The exact steps depend on your IDE and project structure.

### 7. Run the application

Run the class that contains your application's `main` method from your IDE or configured Java build setup.

---

## 🗄️ Database Schema

The setup script creates the following table:

### `accounts`

| Column | Type | Purpose |
|---|---|---|
| `account_id` | `VARCHAR(36)` | Unique account identifier and primary key |
| `account_name` | `VARCHAR(100)` | Account holder's name; required |
| `pin` | `INT` | PIN field matching the current Java implementation |
| `balance` | `DECIMAL(15, 2)` | Account balance, defaulting to `0.00` |
| `created_at` | `TIMESTAMP` | Time the row was created |

The schema also includes a balance check constraint intended to prevent negative balances on MySQL versions that enforce `CHECK` constraints.

### Important database notes

- The `pin` column is an integer to match the current implementation. This does not preserve leading zeros. Consider storing PINs as strings and, for a more secure design, storing a salted password hash instead of a raw PIN.
- `DECIMAL(15, 2)` is appropriate for exact decimal storage, but Java code should ideally use `BigDecimal` rather than `double` for monetary calculations.
- `CREATE TABLE IF NOT EXISTS` does not update an existing table if the schema changes. Review migrations before applying future schema changes.
- The setup script does not drop existing data.

---

## 🔐 Security & Project Limitations

This project is for educational use. Before considering any real-world use, it would need significant security and reliability work, including:

- Secure PIN hashing and authentication protections
- Transaction-safe deposit and withdrawal operations
- Validation for amounts and account state
- Proper connection and resource management
- Secrets management instead of hard-coded credentials
- Logging, error handling, and automated tests

Do not use this project to store real financial information.

---

## 🤝 Contributing

1. Clone the repository.
2. Create a branch for your change.
3. Make and test your changes locally.
4. Commit and push your branch.
5. Open a pull request if the project uses pull requests.

When changing the database schema, update `database/setup.sql` and this README so other contributors can reproduce the setup.

---

## 📄 License

JavaBank is licensed under the MIT License.

You are free to use, copy, modify, merge, publish, distribute, sublicense, and sell copies of this software, subject to the terms of the MIT License.

See the LICENSE file for the full license text.

---

<p align="center">
  Made for learning Java, OOP, and database integration.
</p>
