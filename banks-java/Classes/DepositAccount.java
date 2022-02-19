package Classes;

import Tools.BankException;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    public DepositAccount(double sum, Client client, Bank bank, LocalDate dateFinishing)
    {
        super(sum, client, bank, dateFinishing);
    }

    @Override
    public double withdrawPartMoney(double money) throws BankException {
        throw new BankException("Invalid operation");
    }

    @Override
    public double transferPartMoney(double money, BankAccount bankAccount) throws BankException{
        throw new BankException("Invalid operation");
    }
}
