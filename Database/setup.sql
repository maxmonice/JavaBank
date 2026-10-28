-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS bank
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

-- Select the database
USE bank;

-- Create the accounts table
CREATE TABLE IF NOT EXISTS accounts (
    account_id VARCHAR(20) NOT NULL,
    account_name VARCHAR(100) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,

    PRIMARY KEY (account_id),

    CONSTRAINT chk_pin_format
        CHECK (pin REGEXP '^[0-9]{6}$')
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;