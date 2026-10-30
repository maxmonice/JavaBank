# JavaBank - Database Setup

-- Create the database
CREATE DATABASE IF NOT EXISTS JavaBank
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

-- Select the database
USE JavaBank;

-- Create the accounts table
CREATE TABLE IF NOT EXISTS accounts (
    account_id VARCHAR(20) NOT NULL,
    account_name VARCHAR(100) NOT NULL,
    pin_hash VARCHAR(255) NOT NULL,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,

    PRIMARY KEY (account_id),

    CONSTRAINT chk_account_id_not_empty
        CHECK (account_id <> ''),

    CONSTRAINT chk_account_name_not_empty
        CHECK (account_name <> '')
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;

-- Verify table structure
DESCRIBE accounts;