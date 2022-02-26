package Classes;

import Tools.BankException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount implements IObserver {
    private final Integer DayMonth = 30;

    private double operation;
    private double sum;
    private Client client;
    private Bank bank;
    private double limit;
    private LocalDate date;
    private LocalDate dateFinishing;
    private List<Transaction> transactions;

    protected BankAccount(double operation, double sum, Client client, Bank bank, LocalDate dateFinishing) {
        this.operation = operation;
        this.sum = sum;
        this.client = client;
        this.bank = bank;
        limit = bank.getTransferLimit();
        date = LocalDate.now();
        this.dateFinishing = dateFinishing;
        transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(sum));
    }

    protected BankAccount(double sum, Client client, Bank bank, LocalDate dateFinishing) {
        this.sum = sum;
        this.client = client;
        this.bank = bank;
        operation = bank.checkPercentage(sum);
        date = LocalDate.now();
        this.dateFinishing = dateFinishing;
        limit = bank.getTransferLimit();
        transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(sum));
    }

    public double getOperation() {
        return operation;
    }

    public void setOperation(double operation) {
        this.operation = operation;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateFinishing() {
        return dateFinishing;
    }

    public void setDateFinishing(LocalDate dateFinishing) {
        this.dateFinishing = dateFinishing;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double depositMoney(double money) {
        transactions.add(new Transaction(money));
        sum += money;
        return sum;
    }

    public double withdrawPartMoney(double money) throws BankException {
        if (sum > 0 && client.checkRegistration()) {
            transactions.add(new Transaction(money * (-1)));
            sum -= money;
            return sum;
        }

        if (sum >= money && !client.checkRegistration() && limit >= money) {
            transactions.add(new Transaction(money * (-1)));
            sum -= money;
            return sum;
        }

        throw new BankException("No  money on the account");
    }

    public double transferPartMoney(double money, BankAccount bankAccount) throws BankException {
        if (sum >= money && client.checkRegistration()) {
            bankAccount.depositMoney(money);

            sum -= money;
            return sum;
        }

        if (sum >= money && !client.checkRegistration() && limit >= money) {
            bankAccount.depositMoney(money);
            sum -= money;
            return sum;
        }

        throw new BankException("Invalid operation");
    }

    public double calculatePercentage(LocalDate date) throws BankException {
        double allPercentages = 0;
        if (dateFinishing.isBefore(date)) {
            Period date2 = Period.between(dateFinishing, date);
            for (int i = 0; i < date2.getDays(); i++) {
                allPercentages += sum * (operation / (365 * 100));
                sum += allPercentages;
                transactions.add(new Transaction(allPercentages));
                allPercentages = 0;
            }
        } else {
            Period date2 = Period.between(date, this.date);
            Integer days = date2.getDays();
            for (int i = 0; i < days; i++) {
                allPercentages += sum * (operation / 365);
                if (i == DayMonth) {
                    sum += allPercentages;
                    transactions.add(new Transaction(allPercentages));
                    days -= DayMonth;
                    i -= DayMonth;
                    allPercentages = 0;
                }
            }
        }

        this.date = date;
        return sum;
    }

    public double calculateCommission(LocalDate date) throws BankException {
        throw new BankException("Invalid operation");
    }

    public double changePercentageOperation(double newPercentage) {
        operation = newPercentage;
        return operation;
    }

    public void update(BankAccount account, double operation) {
        account.changePercentageOperation(operation);
    }
}
