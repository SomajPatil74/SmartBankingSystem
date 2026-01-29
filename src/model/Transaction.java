package model;

public class Transaction {

    private String accountNumber;
    private String type; // "DEPOSIT" or "WITHDRAW"
    private double amount;

    public Transaction(String accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}