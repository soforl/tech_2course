package Classes;

import java.time.LocalDate;

public class DebitAccount extends BankAccount {
    public DebitAccount(double percentage, double sum, Client client, Bank bank, LocalDate dateFinishing) {
        super(percentage, sum, client, bank, dateFinishing);
    }
}
