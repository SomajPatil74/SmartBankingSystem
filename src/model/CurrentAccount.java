package model;

public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountNumber,
                          String holderName,
                          double balance,
                          double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Runtime Polymorphism (Method Overriding)
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (amount > balance + overdraftLimit) {
            throw new exception.InsufficientBalanceException(
                    "Withdrawal exceeds overdraft limit."
            );
        }
        balance -= amount;
    }

    // Current accounts do not earn interest
    @Override
    public void calculateInterest() {
        System.out.println("No interest for current accounts.");
    }
}
