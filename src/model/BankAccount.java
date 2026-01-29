package model;

public abstract class BankAccount {

    // Encapsulated fields : private/proctected data members
    protected String accountNumber;
    protected String holderName;
    protected double balance;

    // Constructor
    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Encapsulated getters and setters
    public String getAccountNumber() {
        return accountNumber;   
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Common behavior for all account types
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposite amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (amount > balance) {
            throw new exception.InsufficientBalanceException("Insufficient balance.");
        }
        balance -= amount;
    }

    // Abstraction: subclass must implement this method
    public abstract void calculateInterest();

    // Common method to display account details
    public void displayAccountDetails() {       
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}