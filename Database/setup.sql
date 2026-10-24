-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS Bank;

-- Select the database
USE Bank;

-- Create the accounts table
CREATE TABLE IF NOT EXISTS accounts (
    account_id VARCHAR(20) PRIMARY KEY,
    account_name VARCHAR(100) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00
);