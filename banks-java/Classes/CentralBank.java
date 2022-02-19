package Classes;

import Tools.BankException;

import java.time.LocalDate;
import java.util.ArrayList;

public class CentralBank {
    private ArrayList<Bank> banks = new ArrayList<Bank>();

    public Bank registerBank(String name, double limit) {
        var bank = new Bank(name, limit);
        banks.add(bank);
        return bank;
    }

    public void scrollingTime(LocalDate date) throws BankException {
        for (Bank bank: banks) {
            for (BankAccount bankAccount: bank.getBankAccounts()) {
                bankAccount.calculatePercentage(date);
            }
        }
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }
}
