package model;

public class CurrentAccount extends BankAccount {

    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance); // call to superclass constructor
        this.overdraftLimit = overdraftLimit;
    }

    // Runtime Polymorphism (Method Overriding)
    @Override
    public void withdraw(double amount) {
        if (amount <= 0 ) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance + overdraftLimit) {
            System.out.println("Withdrawal exceeds overdraft limit.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    // Current accounts do not earn interest
    @Override
    public void calculateInterest() {
        System.out.println("No interest for current accounts.");
    }
}
