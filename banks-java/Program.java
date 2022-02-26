import Classes.*;
import Tools.BankException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws BankException {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = new CentralBank();
        Bank bank = centralBank.registerBank("Sberbank", 10000);
        System.out.println("Enter your first name");
        String name = in.next();
        System.out.println("Enter your last name");
        String lastName = in.next();
        Client client = bank.addClient(name, lastName);
        System.out.println("Do you want to add address? Type yes or no");
        if (Objects.equals(in.next(), "yes")) {
            System.out.println("Enter your address");
            String address = in.next();
            bank.addClientAddress(address, client);
        }

        System.out.println("Do you want to add passport? Type yes or no");
        if (Objects.equals(in.next(), "yes")) {
            System.out.println("Enter your passport");
            String passport = in.next();
            bank.addClientPassport(passport, client);
        }

        BankAccount account = null;

        System.out.println("What first account do you want to create? Enter 1 - credit; 2 - debit; 3 - deposit");
        Integer number = Integer.parseInt(in.next());
        if (number == 1) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account = bank.addCreditAccount(client, sum, 10, LocalDate.of(2022, 12, 30), 730);
        } else if (number == 2) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account = bank.addDebitAccount(client, sum, 10, LocalDate.of(2022, 12, 30));
        } else if (number == 3) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account = bank.addDepositAccount(client, sum, 10, LocalDate.of(2022, 12, 30));
        } else {
            System.out.println("Enter the right number");
        }

        System.out.println("Money on the accounts before scrolling time:");

        for (Bank bank1 : centralBank.getBanks()) {
            for (BankAccount account1 : bank1.getBankAccounts()) {
                System.out.println(account1.getSum());
            }
        }

        System.out.println("Money on the accounts after scrolling time:");

        centralBank.scrollingTime(LocalDate.of(2022, 12, 29));
        for (Bank bank1 : centralBank.getBanks()) {
            for (BankAccount account1 : bank1.getBankAccounts()) {
                System.out.println(account1.getSum());
            }
        }

        BankAccount account2 = null;

        System.out.println("What second account do you want to create? Enter 1 - credit; 2 - debit; 3 - deposit");
        Integer number2 = Integer.parseInt(in.next());
        if (number2 == 1) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account2 = bank.addCreditAccount(client, sum, 10, LocalDate.of(2022, 12, 30), 730);
        } else if (number2 == 2) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account2 = bank.addDebitAccount(client, sum, 10, LocalDate.of(2022, 12, 30));
        } else if (number2 == 3) {
            System.out.println("Write sum that will be on your bank account");
            double sum = Double.parseDouble(in.next());
            account2 = bank.addDepositAccount(client, sum, 10, LocalDate.of(2022, 12, 30));
        } else {
            System.out.println("Enter the right number");
        }

        System.out.println("Write sum that will be transfered to different bank account");
        double sum2 = Double.parseDouble(in.next());
        System.out.println(account.transferPartMoney(sum2, account2));

        System.out.println("Money on the accounts after transfering:");

        for (Bank bank1 : centralBank.getBanks()) {
            for (BankAccount account1 : bank1.getBankAccounts()) {
                System.out.println(account1.getSum());
            }
        }
    }
}
