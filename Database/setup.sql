
-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS Bank;

-- Select the database
USE Bank;

-- Create the accounts table
CREATE TABLE IF NOT EXISTS accounts (
    account_id VARCHAR(36) PRIMARY KEY,
    account_name VARCHAR(100) NOT NULL,
    pin INT NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_balance
        CHECK (balance >= 0)
);
