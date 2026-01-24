package main;

import model.SavingsAccount;
import model.CurrentAccount;
import service.BankService;

public class Main {

    public static void main(String[] args) {

        BankService bankService = new BankService();

        bankService.addAccount(
                new SavingsAccount("SB1001", "Somaj Patil", 10000)
        );

        bankService.addAccount(
                new CurrentAccount("CA2001", "Somaj Patil", 5000, 3000)
        );

        bankService.displayAllAccounts();

        bankService.transferMoney("SB1001", "CA2001", 2000);

        bankService.displayAllAccounts();
    }
}
