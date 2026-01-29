CREATE DATABASE IF NOT EXISTS smart_banking;
USE smart_banking;

-- Accounts table
CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    holder_name VARCHAR(100),
    balance DOUBLE,
    account_type VARCHAR(20),
    overdraft_limit DOUBLE
);

-- Transactions table
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20),
    type VARCHAR(20),
    amount DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
