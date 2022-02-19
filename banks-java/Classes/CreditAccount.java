package Classes;

import Tools.BankException;

import java.time.LocalDate;
import java.time.Period;

public class CreditAccount extends BankAccount {
    private double _initialSum;
    private Integer _daysCommission;

    public CreditAccount(double commission, double sum, Client client, Bank bank, LocalDate dateFinishing, Integer daysCommission)
    {
        super(commission, sum, client, bank, dateFinishing);
        _initialSum = sum;
        _daysCommission = daysCommission;
    }

    @Override
    public double calculatePercentage(LocalDate date) throws BankException {
        throw new BankException("Invalid operation");
    }

    @Override
    public double calculateCommission(LocalDate date) {
        Period date2 = Period.between(date, this.getDate());
        int days = date2.getDays();
        if (getSum() < _initialSum && days - _daysCommission < 0)
        {
            for (int i = 0; i < Math.abs(days - _daysCommission); i++)
            {
                this.setSum(-(_initialSum * (this.getOperation() / 100)));
                this.getTransactions().add(new Transaction(_initialSum * (this.getOperation() / 100) * (-1)));
            }

            _daysCommission -= days;
        }

        return this.getSum();
    }
}
