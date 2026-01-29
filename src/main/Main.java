package main;

import model.SavingsAccount;
import model.CurrentAccount;
import service.BankService;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== SMART BANKING SYSTEM =====");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. View All Accounts");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Account Number: ");
                        String accNo = scanner.nextLine();

                        System.out.print("Holder Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Initial Balance: ");
                        double bal = scanner.nextDouble();
                        scanner.nextLine(); // clear buffer

                        bankService.addAccount(
                            new SavingsAccount(accNo, name, bal)
                        );
                    }  

                    case 2 -> {
                        System.out.print("Account Number: ");
                        String accNo = scanner.nextLine();

                        System.out.print("Holder Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Initial Balance: ");
                        double bal = scanner.nextDouble();

                        System.out.print("Overdraft Limit: ");
                        double limit = scanner.nextDouble();
                        scanner.nextLine(); // clear buffer
                        bankService.addAccount(
                            new CurrentAccount(accNo, name, bal, limit)
                        );
                    }

                    case 3 -> {
                        System.out.print("Account Number: ");
                        String accNo = scanner.nextLine();

                        System.out.print("Amount: ");
                        double amt = scanner.nextDouble();
                        scanner.nextLine();
                        bankService.deposit(accNo, amt);
                    }

                    case 4 -> {
                        System.out.print("Account Number: ");
                        String accNo = scanner.nextLine();

                        System.out.print("Amount: ");
                        double amt = scanner.nextDouble();
                        scanner.nextLine();
                        bankService.withdraw(accNo, amt);
                    }

                    case 5 -> {
                        System.out.print("From Account: ");
                        String from = scanner.nextLine();

                        System.out.print("To Account: ");
                        String to = scanner.nextLine();

                        System.out.print("Amount: ");
                        double amt = scanner.nextDouble();
                        scanner.nextLine();

                        bankService.transferMoney(from, to, amt);
                    }


                    case 6 -> bankService.displayAllAccounts();

                    case 0 -> {
                        running = false;
                        System.out.println("Thank you for using Smart Banking System.");
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (AccountNotFoundException |
                     InsufficientBalanceException |
                     IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        scanner.close();
    }
}
