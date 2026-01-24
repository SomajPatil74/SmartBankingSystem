package model;

public class SavingsAccount extends BankAccount {

    private static final double INTEREST_RATE = 0.04; // 4% annual interest rate

    // Constructor
    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance); // call to superclass constructor
    }

    // Runtime Polymorphism (Method Overriding)
    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}