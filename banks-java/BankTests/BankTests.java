package BankTests;

import Classes.*;
import Tools.BankException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class BankTests {
    private CentralBank _centralBank;

    @Before
    public void SetUp() {
        _centralBank = new CentralBank();
    }

    @Test(expected = BankException.class)
    public void CheckTransferMoneyTwoDebitAccounts_ThrowException() throws BankException {
        Bank bank = _centralBank.registerBank("Sberbank", 1000);
        Client client1 = bank.addClient("Fedor", "Petrov");
        Client client2 = bank.addClient("Petr", "Petrov");
        BankAccount account1 = bank.addDebitAccount(client1,19000, 10, LocalDate.of(2022, 12, 30));
        BankAccount account2 = bank.addDebitAccount(client2, 14000, 10, LocalDate.of(2022, 12, 30));
        account1.transferPartMoney(2000, account2);
    }

    @Test(expected = BankException.class)
    public void CheckTransferMoneyDebitCreditAccounts_ThrowException() throws BankException{
        Bank bank = _centralBank.registerBank("Sberbank", 100000);
        Client client1 = bank.addClient("Fedor", "Petrov");
        Client client2 = bank.addClient("Petr", "Petrov");
        BankAccount account1 = bank.addDebitAccount(client1, 19000, 10, LocalDate.of(2022, 12, 30));
        BankAccount account2 = bank.addCreditAccount(client2, 10000, 10, LocalDate.of(2022, 12, 30), 10);
        account2.transferPartMoney(12000, account1);
    }

    @Test
    public void CheckTransferMoneyTwoDebitAccounts() throws BankException {
        Bank bank = _centralBank.registerBank("Sberbank", 1000);
        Client client1 = bank.addClient("Fedor", "Petrov");
        Client client2 = bank.addClient("Petr", "Petrov");
        BankAccount account1 = bank.addDebitAccount(client1, 19000, 10,  LocalDate.of(2022, 12, 30));
        BankAccount account2 = bank.addDebitAccount(client2, 14000, 10,  LocalDate.of(2022, 12, 30));
        bank.addClientAddress("Lomonosova", client2);
        bank.addClientPassport("meow", client2);
        account2.transferPartMoney(2000, account1);
        Assert.assertEquals(21000, account1.getSum(), 0.000001);
    }

    @Test
    public void CancelTransaction_() throws BankException {
        Bank bank = _centralBank.registerBank("Sberbank", 10000000);
        Client client1 = bank.addClient("Fedor", "Petrov");
        Client client2 = bank.addClient("Petr", "Petrov");
        BankAccount account1 = bank.addDebitAccount(client1,  19000, 10, LocalDate.of(2022, 12, 30));
        BankAccount account2 = bank.addDebitAccount(client2, 14000, 10, LocalDate.of(2022, 12, 30));
        bank.addClientAddress("Lomonosova", client2);
        bank.addClientPassport("meow", client2);
        account1.transferPartMoney(2000, account2);
        bank.cancelTransaction(account2, account1, account2.getTransactions().get(account2.getTransactions().size()-1));
        Assert.assertEquals(14000, account2.getSum(), 0.000001);
    }
}
