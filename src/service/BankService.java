package service;

import model.BankAccount;
import java.util.HashMap;
import java.util.Map;
import dao.AccountDAO;
import dao.TransactionDAO;
import model.Transaction;

// BankService handles operations related to bank accounts
public class BankService {

    private AccountDAO accountDAO = new AccountDAO();
    public BankService() {
        // Load existing accounts from the database
        this.accounts = accountDAO.loadAccounts();
    }

    private Map<String, BankAccount> accounts = new HashMap<>();
    private TransactionDAO transactionDAO = new TransactionDAO();

    // Create a new bank account
    public void addAccount(BankAccount account) {
    accounts.put(account.getAccountNumber(), account);
    accountDAO.saveAccount(account);
    System.out.println("Account added: " + account.getAccountNumber());
}

    // Retrieve account details by account number
    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new exception.AccountNotFoundException(
                "Account not found: " + accountNumber
            );
        }
        return account;
    }

    private void syncBalance(BankAccount account) {
        accountDAO.updateBalance(
        account.getAccountNumber(),
        account.getBalance()
    );
}

    // Transfer money between accounts
    public void transferMoney(String fromAcc, String toAcc, double amount) {

        BankAccount source = getAccount(fromAcc);
        BankAccount target = getAccount(toAcc);

        source.withdraw(amount);
        target.deposit(amount);

        accountDAO.updateBalance(fromAcc, source.getBalance());
        accountDAO.updateBalance(toAcc, target.getBalance());

        transactionDAO.saveTransaction(
            new Transaction(fromAcc, "TRANSFER_OUT", amount)
        );

        transactionDAO.saveTransaction(
            new Transaction(toAcc, "TRANSFER_IN", amount)
        );
    }

    // Display all accounts
    public void displayAllAccounts() {
        for (BankAccount acc : accounts.values()) {
            System.out.println("-----------------------");
            acc.displayAccountDetails();
        }
    }

    public void deposit(String accNo, double amount) {
        BankAccount acc = getAccount(accNo);
        acc.deposit(amount);
        accountDAO.updateBalance(accNo, acc.getBalance());
        transactionDAO.saveTransaction(
            new Transaction(accNo, "DEPOSIT", amount)
       );
    }

    public void withdraw(String accNo, double amount) {

        BankAccount acc = getAccount(accNo);
        acc.withdraw(amount);
        accountDAO.updateBalance(accNo, acc.getBalance());
        transactionDAO.saveTransaction(
            new Transaction(accNo, "WITHDRAW", amount)
        );
    }
}