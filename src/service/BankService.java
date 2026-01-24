package service;

import model.BankAccount;
import java.util.HashMap;
import java.util.Map;

// BankService handles operations related to bank accounts
public class BankService {
    
    private Map<String, BankAccount> accounts = new HashMap<>();

    // Create a new bank account
    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    // Retrieve account details by account number
    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Transfer money between accounts
    public void transferMoney(String fromAcc, String toAcc, double amount) {
        BankAccount source = accounts.get(fromAcc);
        BankAccount target = accounts.get(toAcc);

        if (source == null || target == null) {
            System.out.println("Invalid account number(s).");
            return;
        }

        source.withdraw(amount);
        target.deposit(amount);

        System.out.println("Transferred " + amount + " from " + fromAcc + " to " + toAcc);
    }

    // Display all accounts
    public void displayAllAccounts() {
        for (BankAccount acc : accounts.values()) {
            System.out.println("-----------------------");
            acc.displayAccountDetails();
        }
    }
}
