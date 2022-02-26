package Classes;

import Tools.BankException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bank implements IObservable {
    private List<Client> clients;
    private List<PercentageDepositAccount> percentages;
    private List<BankAccount> bankAccounts;
    private double transferLimit;
    private String name;
    private List<IObserver> observers;

    public Bank(String name, double transferLimit) {
        this.name = name;
        clients = new ArrayList<Client>();
        bankAccounts = new ArrayList<BankAccount>();
        this.transferLimit = transferLimit;
        percentages = new ArrayList<PercentageDepositAccount>();
        observers = new ArrayList<IObserver>();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public List<PercentageDepositAccount> getPercentages() {
        return percentages;
    }

    public void setPercentages(ArrayList<PercentageDepositAccount> percentages) {
        this.percentages = percentages;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public double getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(double transferLimit) {
        this.transferLimit = transferLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<IObserver> observers) {
        this.observers = observers;
    }

    public Client addClient(String firstName, String lastName) throws BankException {
        if (!firstName.isBlank() || !lastName.isBlank()) {
            Client client = new ClientBuilder()
                    .buildFirstName(firstName)
                    .buildLastName(lastName)
                    .build();
            if (!clients.contains(client)) {
                clients.add(client);
            }

            return client;
        }

        throw new BankException("Invalid client");
    }

    public Client addClientAddress(String address, Client client) {
        client.addAddress(address);
        return client;
    }

    public Client addClientPassport(String passport, Client client) {
        client.addPassport(passport);
        return client;
    }

    public BankAccount addDepositAccount(Client client, double sum, double percentage, LocalDate dateFinishing) {
        BankAccount bankAccount = new DepositAccount(sum, client, this, dateFinishing);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount addCreditAccount(Client client, double sum, double percentage, LocalDate dateFinishing, Integer daysCommission) {
        BankAccount bankAccount = new CreditAccount(percentage, sum, client, this, dateFinishing, daysCommission);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount addDebitAccount(Client client, double sum, double percentage, LocalDate dateFinishing) {
        BankAccount bankAccount = new DebitAccount(percentage, sum, client, this, dateFinishing);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public void addPercentageSum(double percentage, double sumAccount) {
        double left = -1;
        double right = Integer.MAX_VALUE;
        for (PercentageDepositAccount percent : percentages) {
            if (right > percent.getSum2() - sumAccount && right > 0
                    && left < sumAccount - percent.getSum2() && left < 0) {
                left = sumAccount - percent.getSum2();
                right = percent.getSum2() - sumAccount;
            }
        }

        var percentage2 = new PercentageDepositAccount(this, percentage, sumAccount - left, sumAccount);
        percentages.add(percentage2);
    }

    public double checkPercentage(double sumAccount) {
        double left = -1;
        double right = Integer.MAX_VALUE;
        double percentage = 0;
        for (PercentageDepositAccount percent : percentages) {
            if (right > percent.getSum2() - sumAccount && right > 0
                    && left < sumAccount - percent.getSum2() && left < 0) {
                left = sumAccount - percent.getSum2();
                right = percent.getSum2() - sumAccount;
                percentage = percent.getPercentage();
            }
        }

        return percentage;
    }

    public double changeTransferLimit(double newTransferLimit) {
        transferLimit = newTransferLimit;
        return transferLimit;
    }

    public void cancelTransaction(BankAccount bankAccount1, BankAccount bankAccount2, Transaction transaction) throws BankException {
        for (Transaction transfer : bankAccount1.getTransactions()) {
            if (transaction.getId() == transfer.getId()) {
                if (transfer.getSum() > 0) {
                    bankAccount1.withdrawPartMoney(transfer.getSum());
                    bankAccount2.depositMoney(transfer.getSum());
                    break;
                }

                if (transfer.getSum() < 0) {
                    bankAccount1.depositMoney(transfer.getSum() * (-1));
                    bankAccount2.withdrawPartMoney(transfer.getSum() * (-1));
                    break;
                }
            }
        }
    }

    public void cancelTransaction(BankAccount bankAccount, Transaction transaction) throws BankException {
        for (Transaction transfer : bankAccount.getTransactions()) {
            if (transaction.getId() == transfer.getId()) {
                if (transfer.getSum() > 0) {
                    bankAccount.withdrawPartMoney(transfer.getSum());
                    break;
                }

                if (transfer.getSum() < 0) {
                    bankAccount.depositMoney(transfer.getSum() * (-1));
                    break;
                }
            }
        }
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(BankAccount bankAccount, double operation) {
        for (IObserver observer : observers) {
            observer.update(bankAccount, operation);
        }
    }
}
