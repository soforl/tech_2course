package Classes;

import Tools.BankException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private List<Bank> banks = new ArrayList<Bank>();

    public Bank registerBank(String name, double limit) {
        var bank = new Bank(name, limit);
        banks.add(bank);
        return bank;
    }

    public void scrollingTime(LocalDate date) throws BankException {
        for (Bank bank : banks) {
            for (BankAccount bankAccount : bank.getBankAccounts()) {
                bankAccount.calculatePercentage(date);
            }
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }
}
